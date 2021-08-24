﻿using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.EventSystems;
using UnityEngine.UI;

[RequireComponent (typeof(Image))]
public class DragMeMenu : MonoBehaviour, IBeginDragHandler, IDragHandler, IEndDragHandler
{
	public bool dragOnSurfaces = true;
	public GameObject spot;

	private Dictionary<int, GameObject> m_DraggingIcons = new Dictionary<int, GameObject> ();
	private Dictionary<int, RectTransform> m_DraggingPlanes = new Dictionary<int, RectTransform> ();

	public void OnBeginDrag (PointerEventData eventData)
	{

		var canvas = FindInParents<Canvas> (gameObject);
		if (canvas == null)
			return;

		// We have clicked something that can be dragged.
		// What we want to do is create an icon for this.
		m_DraggingIcons [eventData.pointerId] = new GameObject ("icon");
		
		m_DraggingIcons [eventData.pointerId].transform.SetParent (canvas.transform, false);
		m_DraggingIcons [eventData.pointerId].transform.SetAsLastSibling ();

		var image = m_DraggingIcons [eventData.pointerId].AddComponent<Image> ();
		// The icon will be under the cursor.
		// We want it to be ignored by the event system.
		var group = m_DraggingIcons [eventData.pointerId].AddComponent<CanvasGroup> ();
		group.blocksRaycasts = false;

		image.sprite = GetComponent<Image> ().sprite;
		image.SetNativeSize ();

		image.transform.localScale = new Vector3 (0.4f, 0.4f, 0.4f);
		//add transparency to dragged image/icon
		var icone = m_DraggingIcons [eventData.pointerId].GetComponent<Image>();
		Color c = icone.color;
        c.a = 0.65f;
        icone.color = c;


		if (dragOnSurfaces)
			m_DraggingPlanes [eventData.pointerId] = transform as RectTransform;
		else
			m_DraggingPlanes [eventData.pointerId] = canvas.transform as RectTransform;

		SetDraggedPosition (eventData);
	}

	public void OnDrag (PointerEventData eventData)
	{
		if (m_DraggingIcons [eventData.pointerId] != null)
			SetDraggedPosition (eventData);

		GameObject[] gos = eventData.hovered.ToArray ();
		foreach (GameObject go in gos) {
			if (go.GetComponent<DropMeMenu> () == spot.GetComponent <DropMeMenu> ())
				go.transform.SetAsLastSibling ();
		}
	}

	private void SetDraggedPosition (PointerEventData eventData)
	{
		if (dragOnSurfaces && eventData.pointerEnter != null && eventData.pointerEnter.transform as RectTransform != null)
			m_DraggingPlanes [eventData.pointerId] = eventData.pointerEnter.transform as RectTransform;

		var rt = m_DraggingIcons [eventData.pointerId].GetComponent<RectTransform> ();
		Vector3 globalMousePos;
		if (RectTransformUtility.ScreenPointToWorldPointInRectangle (m_DraggingPlanes [eventData.pointerId], eventData.position, eventData.pressEventCamera, out globalMousePos)) {
			rt.position = globalMousePos;
			rt.rotation = m_DraggingPlanes [eventData.pointerId].rotation;
		}
	}

	public void OnEndDrag (PointerEventData eventData)
	{
		if (m_DraggingIcons [eventData.pointerId] != null) {
			Destroy (m_DraggingIcons [eventData.pointerId]);

			GameObject[] gos = eventData.hovered.ToArray ();
			/*foreach (GameObject go in gos) {
				if (go.GetComponent<DropMeMenu> () == spot.GetComponent <DropMeMenu> ())
					gameObject.transform.parent.gameObject.SetActive (false);
			}*/
		}

		m_DraggingIcons [eventData.pointerId] = null;

	}

	static public T FindInParents<T> (GameObject go) where T : Component
	{
		if (go == null)
			return null;
		var comp = go.GetComponent<T> ();

		if (comp != null)
			return comp;

		var t = go.transform.parent;
		while (t != null && comp == null) {
			comp = t.gameObject.GetComponent<T> ();
			t = t.parent;
		}
		return comp;
	}
}
