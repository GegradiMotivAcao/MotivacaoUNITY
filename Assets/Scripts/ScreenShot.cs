﻿using UnityEngine;
using System.Collections;
using UnityEngine.SceneManagement;

public class ScreenShot : MonoBehaviour
{
	private GameObject parent;
	public GameObject cursor;
	public string nomeDaCena;
	public string filepath;

	// Use this for initialization
	void Start ()
	{
		parent = GameObject.Find ("Canvas");
		filepath=Application.dataPath;
		Debug.Log("dataPath : " + filepath);
	}

	public void InativeGO ()
	{
		foreach (Transform child in parent.transform) {
			string cn = child.name;
			if (cn == "imagem_a_editar" || cn == "drop_spots" || cn == "HandCursor")
				continue;

			child.gameObject.SetActive (false);
		}
		//cursor.SetActive (false);
	}

	public void AtiveGO ()
	{
		foreach (Transform child in parent.transform) {
			string cn = child.name;
			if (cn == "menu_arvore" || cn == "menu_elementos" || cn == "HandCursor")
				continue;

			child.gameObject.SetActive (true);
		}
		//cursor.SetActive (true);
	}

	public void SS ()
	{
		StartCoroutine (CaptureScreen ());
	}


	public IEnumerator CaptureScreen ()
	{
		// Wait till the last possible moment before screen rendering to hide the UI
		yield return null;
		InativeGO ();

		// Wait for screen rendering to complete
		yield return new WaitForEndOfFrame ();

		// Take screenshot
		string filename = "/motivacao-";
		filename += System.DateTime.Now.ToString ("dd-MM-yyyy") + System.DateTime.Now.ToString ("hhmmss");
		filename += ".png";
		Debug.Log (filename);
		ScreenCapture.CaptureScreenshot (filepath + filename);

		// Show UI after we're done
		AtiveGO ();

		//SceneManager.LoadScene (nomeDaCena);
	}

	
}
