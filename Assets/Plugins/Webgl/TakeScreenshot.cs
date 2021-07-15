using UnityEngine;
using System.Collections;
using System.Runtime.InteropServices;
 
 
public class TakeScreenshot : MonoBehaviour {
 
    // Use this for initialization
    public void Screenshot () {
        StartCoroutine(UploadPNG());
        //Debug.log (encodedText);
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
 
    IEnumerator UploadPNG() {
        // We should only read the screen after all rendering is complete
        yield return new WaitForEndOfFrame();
 		InativeGO ();//turn off interface
        // Create a texture the size of the screen, RGB24 format
        int width = Screen.width;
        int height = Screen.height;
        var tex = new Texture2D( width, height, TextureFormat.RGB24, false );
 
        // Read screen contents into the texture
        tex.ReadPixels( new Rect(0, 0, width, height), 0, 0 );
        tex.Apply();
 
        // Encode texture into PNG
        byte[] bytes = tex.EncodeToPNG();
        Destroy( tex );
 
        //string ToBase64String byte[]
        string encodedText = System.Convert.ToBase64String (bytes);
   
        var image_url = "data:image/png;base64," + encodedText;
 
        Debug.Log (image_url);
 
        #if !UNITY_EDITOR
        SaveScreenshotWebGL(image_url,encodedText);
        #endif
        AtiveGO (); //turn on interface
    }
 
    [DllImport("__Internal")]
  	private static extern void SaveScreenshotWebGL(string filename, string data);
 
}      
 