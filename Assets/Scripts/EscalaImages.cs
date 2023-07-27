using UnityEngine;
using UnityEngine.UI;
using UnityEngine.EventSystems;

public class EscalaImages : MonoBehaviour
{
    public float Escala = 1.0f;
    public Slider slider;
    private GameObject ultimo;

    void Start()
    {
        // Inicialmente, não há objeto selecionado
        ultimo = null;
    }

    void Update()
    {
        // Verifica se há um objeto selecionado e aplica a escala somente a ele
        if (ultimo != null)
        {
            ultimo.transform.localScale = new Vector3(Escala * slider.value, Escala * slider.value, Escala * slider.value);
        }
    }

    // Método para detectar cliques em objetos
    public void ObjetoClicado(GameObject objeto)
    {   
        // Armazena o objeto clicado como o objeto "último"
        ultimo = objeto;
    }
}


/*using UnityEngine;
using UnityEngine.UI;
using UnityEngine.EventSystems;

public class EscalaImages : MonoBehaviour
{
     public float Escala = 1.0f;
	public Slider slider;
    public GameObject ultimo;
   void Start ()
	{
		
	}

    void Update()
    {   
        transform.localScale = new Vector3(Escala * slider.value, Escala * slider.value, Escala * slider.value);
    }   
    }*/