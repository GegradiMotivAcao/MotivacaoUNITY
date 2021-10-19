using System.Reflection;
using UnityEngine;
using UnityEngine.EventSystems;
using UnityEngine.UI;

public class DropMeMenu : MonoBehaviour, IDropHandler, IPointerEnterHandler, IPointerExitHandler
{
	public Image containerImage;
	public Image receivingImage;
	public GameObject[] respectiveImage;
	private Color normalColor;
	public Color highlightColor = Color.yellow;

	

	public void OnEnable ()
	{
		if (containerImage != null)
			normalColor = containerImage.color;
	}

	public void OnDrop (PointerEventData data)
	{
		if (receivingImage == null)
			return;

		var originalObj = data.pointerDrag;											   // IF ANTI-BAGUNÇA >:(
		if ( (originalObj != respectiveImage[0]) && (originalObj != respectiveImage[1]) ) //Aqui ocorre a verificação de que imagem vai em qual gamespot
			return;    																	 // Se desativar, pode dropar em qualquer local
		

		//PARA PEGAR SO AS IMGENS NO DRAG
		//var originalObj = data.pointerDrag;											   
		//if ( !(originalObj.gameObject.CompareTag("imagem")) ) //se não for imagem para
			//return;

		Sprite dropSprite = GetDropSprite (data);
		if (dropSprite != null)
			receivingImage.overrideSprite = dropSprite;
		
		Color c = receivingImage.color;
		c.a = 255;
		receivingImage.color = c;
		//receivingImage.rectTransform.sizeDelta = new Vector2(GetSpriteWidth (data), GetSpriteHeight (data));
	}

	public void OnPointerEnter (PointerEventData data)
	{
		if (containerImage == null)
			return;

		//Debug.Break();

		var originalObj = data.pointerDrag; 												// IF ANTI-BAGUNÇA part2 - O império contra ataca
		if ( (originalObj != respectiveImage[0]) && (originalObj != respectiveImage[1]) )   // Aqui ele verifica se mostra o highlight apenas para a imagem "certa"
			return;																	// Desativando conseguimos ver o highlight em qualquer spot pra qqlr imagem
	
		Sprite dropSprite = GetDropSprite (data);
		if (dropSprite != null) {
			this.transform.SetAsLastSibling ();
			containerImage.color = highlightColor;
		}
	}

	public void OnPointerExit (PointerEventData data)
	{
		if (containerImage == null)
			return;
		
		containerImage.color = normalColor;
	}

	private Sprite GetDropSprite (PointerEventData data)
	{
		var originalObj = data.pointerDrag;
		if (originalObj == null)
			return null;

		var dragMe = originalObj.GetComponent<DragMeMenu> ();
		if (dragMe == null)
			return null;

		var srcImage = originalObj.GetComponent<Image> ();
		if (srcImage == null)
			return null;

		return srcImage.sprite;
	}

	private float GetSpriteWidth (PointerEventData data)
	{
		var originalObj = data.pointerDrag;

		var dragMe = originalObj.GetComponent<DragMeMenu> ();

		var srcImage = originalObj.GetComponent<Image> ();

		Debug.Log(srcImage.sprite.rect.width);
		return srcImage.sprite.rect.width * 20;
	}

	private float GetSpriteHeight (PointerEventData data)
	{
		var originalObj = data.pointerDrag;

		var dragMe = originalObj.GetComponent<DragMeMenu> ();

		var srcImage = originalObj.GetComponent<Image> ();

		Debug.Log(srcImage.sprite.rect.height);
		return srcImage.sprite.rect.height * 20;
	}

	public void EraseDropSprite ()
	{
		GameObject go = transform.GetChild(0).gameObject;;
		
		Color c = go.GetComponent<Image> ().color;
		c.a = 0;
		go.GetComponent<Image>().color = c;
		
				//go.SetActive (false);
	}

	void Update ()
 {
     if (Input.GetMouseButtonDown(0)) // If the space bar is pushed down
     {
         EraseDropSprite(); // call method to change sprite
     }
 }

 


}
