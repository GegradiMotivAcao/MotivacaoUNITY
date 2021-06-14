using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;
using System.IO;

public class _CarregaBotoes : MonoBehaviour
{
    [SerializeField]
    private Button _btOriginal;
    [SerializeField]
    private Transform _buttonParent;
    
    [SerializeField]
    private Sprite _textura;
    [SerializeField]
   

    private string _myPath = "imagens/comEdicao/";
    private int i = 0;
    // Start is called before the first frame update
    void Start()
    {
        DirectoryInfo dir = new DirectoryInfo(_myPath);
        FileInfo[] info = dir.GetFiles("*.JPG");
        //pega o content do viewport do scrollview
        _buttonParent = GameObject.FindGameObjectWithTag("VP").transform;
       

        foreach (FileInfo f in info)
        {
          
          
                _textura = new CarregaSprite().getSprite(f.ToString());

                GameObject _gbAux = Instantiate(Resources.Load("Prefabs/Botao")) as GameObject;
                //configura aspectos do botão
                Button _bt = _gbAux.GetComponent<Button>();
                _bt.image.sprite = _textura;
                _bt.name = "botao" + i;     
                //_bt.GetComponent<Button>().onClick.AddListener(() => MinhaAcao());
                _bt.GetComponent<_ClickBotao>().setArquivoImagem(f.ToString());
                _bt.GetComponent<_ClickBotao>().setTextura(_textura);
                //instancia botão
                Instantiate(_gbAux, _buttonParent);
                
             
            
            i++;
        }

    }


}
