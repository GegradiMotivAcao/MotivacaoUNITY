using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEditor;
using UnityEngine.UI;
using System.IO;


public class objetos
	{
    // Auto-implemented properties.
    public string Title { get; set; } //endereço, arquivo, tipo e posição
    public string Position { get; set; }
    public string Type { get; set; }
    public string FilePath { get; set; }

    public objetos()
    {

    }

    public objetos(string name, string name2,string name3, string name4)
    {
        Title = name;
        Position = name2;
        Type = name3;
        FilePath = name4;

    }
}



public class LeituraParam : MonoBehaviour
{	

	List<objetos>Objs = new List<objetos>(0);
	public List<Sprite>SpritesBotoes = new List<Sprite>(0);
	public Sprite bosta;
	public List<GameObject> GSs;
	public List<GameObject> Botoes;
	public TextAsset arq;
	private string theWholeFileAsOneLongString;
 	private List<string> eachLine;
 	 private DragMeMenu scriptElem;
	[MenuItem("AssetDatabase/LoadAssetExample")]
    static void ImportExample()
    {
        
    
    }
    // Start is called before the first frame update
    void Start()
    {		

    	string filePath = "Assets/Resources/1629239505_presetm4.jpg";
        Texture2D texture = null;
        byte[] fileData;
 
        if ( File.Exists(filePath) )
        {
            fileData = File.ReadAllBytes(filePath);
            texture = new Texture2D(2, 2);
            texture.LoadImage(fileData);
        }
 
        bosta = Sprite.Create( texture, new Rect(0, 0, texture.width, texture.height), new Vector2(texture.width/2, texture.height/2) );




    	Sprite imagem;
        arq = (TextAsset)AssetDatabase.LoadAssetAtPath("Assets/Scenes/Builder/lista.txt", typeof(TextAsset));
        theWholeFileAsOneLongString = arq.text;
     
     eachLine = new List<string>();
     eachLine.AddRange(theWholeFileAsOneLongString.Split("\n"[0]) );
     Debug.Log(eachLine[0]);
     Debug.Log(eachLine[1]);

    	foreach(string line in eachLine)
		{
    	  	string[] leitura = line.Split(';');
    	  	Objs.Add(new objetos(leitura[0],leitura[1],leitura[2],leitura[3]));
    	  	imagem = Resources.Load<Sprite>(leitura[0]);
    	  	SpritesBotoes.Add(imagem);
			
		}
		Debug.Log(Objs[0].Title);
    	Debug.Log(Objs[0].Position);
    	Debug.Log(Objs[0].Type);
    	Debug.Log(Objs[0].FilePath);
    	Debug.Log(SpritesBotoes[0].ToString());

    	//ADICIONAR ARTRIBUIÇÃO DE IMAGEM AO Source Image DO BOTÃO, BASEADO NO ENDEREÇO DO TXT
    	Botoes[0].transform.GetChild(0).GetComponent<DragMeMenu>().spot = GSs[0]; //atribui ao botão qual seu GameSpot referente
    	Botoes[0].transform.GetChild(0).GetComponent<Image>().sprite = SpritesBotoes[0];
    	GSs[0].GetComponent<DropMeMenu>().respectiveImage[0] = Botoes[0].transform.GetChild(0).gameObject;//atribui ao GameSpot qual seu botão/imagem referente
    }

    
    // Update is called once per frame
    void Update()
    {
        
    }
}