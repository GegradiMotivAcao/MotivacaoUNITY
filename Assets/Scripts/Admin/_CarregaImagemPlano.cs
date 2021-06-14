using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;
using UnityEngine.SceneManagement;
using System.IO;

public class _CarregaImagemPlano : MonoBehaviour
{

    [SerializeField]
    private string _arquivoImagem;
    [SerializeField]
    public Renderer _imagem;
    //[SerializeField]
    ///private ArrayList _areas = new ArrayList();
    [SerializeField]
    List<Area> _listaAreas = new List<Area>();
    // Start is called before the first frame update
    void Start()
    {

        
        _listaAreas.Add(new Area(0));
        _arquivoImagem = PlayerPrefs.GetString("imagem");
        

        //   Debug.Log(StaticClass.CrossSceneInformation);
        //Sprite _img = GameObject.Find("imagem_a_editar").GetComponent<Image>();
        _imagem = GameObject.FindGameObjectWithTag("VP").GetComponent<Renderer>();
        if (_imagem != null)
        {
            _imagem.material.SetTexture("_MainTex", new CarregaTextura().getTextura (_arquivoImagem));

            //procura nas playerprefs se já existe uma entrada para esta imagem
            string _nomeArquivoImagem = Path.GetFileName(_arquivoImagem);
            
            string _jsonTransform = PlayerPrefs.GetString(_nomeArquivoImagem);
            if (_jsonTransform == "")
            {
                //cria nova entrada
                PlayerPrefs.SetString(_nomeArquivoImagem, _nomeArquivoImagem);
                Debug.Log("gravado: " + _nomeArquivoImagem);

            }
            else
            {
                Debug.Log("lido: " + _jsonTransform);
            }
        }
        else
        {
            Debug.Log("erro 2" + _imagem.ToString());
        }
        //.image = new CarregaTextura().getTextura(_arquivoImagem);
    }



}
