using UnityEngine;
using UnityEngine.SceneManagement;

public class CarregarCenaMenu : MonoBehaviour
{
	public int type;
	public string nomeDaCena;


	public void transitionType()
	{
		if(type==1){
			loadScene();
		}
		else{
			if(type==2){
				exitGame();
			}
		}
	}
	
	public void loadScene ()
	{
		SceneManager.LoadScene (nomeDaCena);
		Debug.Log ("carregando cena " + nomeDaCena);
	}

	public void exitGame ()
	{
		Application.Quit ();
	}

}
