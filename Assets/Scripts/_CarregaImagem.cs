using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;
using UnityEngine.SceneManagement;

public class _CarregaImagem : MonoBehaviour
{
    [SerializeField]
    private string _arquivoImagem;
    [SerializeField]
    public Button _imagem;

    //
    // Start is called before the first frame update
    void Start()
    {
        _arquivoImagem = PlayerPrefs.GetString("imagem");
        Debug.Log(_arquivoImagem);

        //   Debug.Log(StaticClass.CrossSceneInformation);
        //Sprite _img = GameObject.Find("imagem_a_editar").GetComponent<Image>();
        _imagem = GameObject.FindGameObjectWithTag("VP").GetComponent<Button>(); 
            if (_imagem != null)
            {
            _imagem.image.sprite = new CarregaSprite().getSprite(_arquivoImagem);
            }
            else
            {
                Debug.Log("erro 2" + _imagem.ToString());
            }
            //.image = new CarregaTextura().getTextura(_arquivoImagem);
     }
        
    

}