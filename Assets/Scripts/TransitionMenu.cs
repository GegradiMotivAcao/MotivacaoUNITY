using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

public class TransitionMenu : MonoBehaviour
{
    public List<GameObject> buttons = new List<GameObject>();
    public Animator animator;
    private GameObject btnClicked;

    // Update is called once per frame
    void Update()
    {
        foreach(GameObject button in buttons){
            button.GetComponent<Button>().onClick.AddListener( () =>
                {
                    btnClicked = button;
                    animationFade();
                });
        }
    }

	public void animationFade()
	{
		animator.SetTrigger("FadeOut");
	}

    public void animationCompleted()
	{
		btnClicked.GetComponent<CarregarCenaMenu>().transitionType();
	}
}
