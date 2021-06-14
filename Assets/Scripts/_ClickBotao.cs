using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;
using UnityEngine.SceneManagement;

public class _ClickBotao : MonoBehaviour
{
    [SerializeField]
    private string _arquivoImagem;
    [SerializeField]
    private Sprite _sprite;

    // Start is called before the first frame update
    void Start()
    {
        
        
    }

    /// <summary>
    /// 
    /// </summary>
    public void FazAcao()
    {
     //AQUI!
       // StaticClass.CrossSceneInformation = getArquivoImagem();
      //  GuardaTextura.CrossSceneSprite = getTextura();
        PlayerPrefs.SetString("imagem", _arquivoImagem);
        Debug.Log(_arquivoImagem.ToString());
        SceneManager.LoadScene("imagem2");
    }

    /// <summary>
    /// 
    /// </summary>
    /// <param name="s"></param>
    public void setArquivoImagem(string s)
    {
        _arquivoImagem = s;
    }

    /// <summary>
    /// 
    /// </summary>
    /// <returns></returns>
    public string getArquivoImagem()
    {
        return (_arquivoImagem);
    }


    /// <summary>
    /// 
    /// </summary>
    /// <param name="s"></param>
    public void setTextura(Sprite s)
    {
        _sprite = s;
    }

    /// <summary>
    /// s
    /// </summary>
    /// <returns></returns>
    public Sprite getTextura()
    {
        return (_sprite);
    }



    void Awake()
    {
        //Button myButton = GetReferenceToButton();
        this.GetComponentInParent<Button>().onClick.AddListener((UnityEngine.Events.UnityAction)this.OnClick);
    }

    public void OnClick()
    {
        Debug.Log(_arquivoImagem);
        FazAcao();
    }

}