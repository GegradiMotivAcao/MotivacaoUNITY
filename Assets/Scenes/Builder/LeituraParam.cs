﻿using System.Collections;
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
    public Sprite Fundo;
    public GameObject LocalFundo;
	public Sprite bt;
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

    	Sprite imagem;
        arq = (TextAsset)AssetDatabase.LoadAssetAtPath("Assets/Resources/lista.txt", typeof(TextAsset));
        theWholeFileAsOneLongString = arq.text;
        int i=0;
     eachLine = new List<string>();
     eachLine.AddRange(theWholeFileAsOneLongString.Split("\n"[0]) );
     Debug.Log(eachLine[0]);
     Debug.Log(eachLine[1]);

    	foreach(string line in eachLine)
		{   
            //CRIAR AQUI BOTÃO DINAMICAMENTE - DUPLICANDO 1 EXISTENTE 
    	  	string[] leitura = line.Split(';');
    	  	//Objs.Add(new objetos(leitura[0],leitura[1],leitura[2],leitura[3]));

    	  	string filePath = "Assets/Resources/"+leitura[0];
            Debug.Log(filePath);
            Texture2D texture = null;
            byte[] fileData;
 
            if ( File.Exists(filePath) )
            {
                fileData = File.ReadAllBytes(filePath);
                texture = new Texture2D(2, 2);
                texture.LoadImage(fileData);
            }

            if(int.Parse(leitura[1]) == 0){ 
                Fundo = Sprite.Create( texture, new Rect(0, 0, texture.width, texture.height), new Vector2(texture.width/2, texture.height/2) );
               LocalFundo.GetComponent<Image>().sprite = Fundo;
                
            }
            
            //ALTERAR - CASO NÃO TENHA A POSIÇÃO, EXCLUIR O BOTÃO E O GAMESPOT

            if(int.Parse(leitura[1]) != 0){ 
                bt = Sprite.Create( texture, new Rect(0, 0, texture.width, texture.height), new Vector2(texture.width/2, texture.height/2) );
                //imagem = Resources.Load<Sprite>(leitura[0]);
                SpritesBotoes.Add(bt);

                Botoes[int.Parse(leitura[2])].transform.GetChild(0).GetComponent<DragMeMenu>().spot = GSs[int.Parse(leitura[2])]; //atribui ao botão qual seu GameSpot referente
                Botoes[int.Parse(leitura[2])].transform.GetChild(0).GetComponent<Image>().sprite = SpritesBotoes[i];
                GSs[int.Parse(leitura[2])].GetComponent<DropMeMenu>().respectiveImage[0] = Botoes[int.Parse(leitura[2])].transform.GetChild(0).gameObject;//atribui ao GameSpot qual seu botão/imagem referente
                i +=1;
            }
            

			
             //iteração do index
		}
		/*Debug.Log(Objs[0].Title);
    	Debug.Log(Objs[0].Position);
    	Debug.Log(Objs[0].Type);
    	Debug.Log(Objs[0].FilePath);
    	Debug.Log(SpritesBotoes[0].ToString());

    	//ADICIONAR ARTRIBUIÇÃO DE IMAGEM AO Source Image DO BOTÃO, BASEADO NO ENDEREÇO DO TXT
    	Botoes[0].transform.GetChild(0).GetComponent<DragMeMenu>().spot = GSs[0]; //atribui ao botão qual seu GameSpot referente
    	Botoes[0].transform.GetChild(0).GetComponent<Image>().sprite = SpritesBotoes[0];
    	GSs[0].GetComponent<DropMeMenu>().respectiveImage[0] = Botoes[0].transform.GetChild(0).gameObject;//atribui ao GameSpot qual seu botão/imagem referente

        Botoes[1].transform.GetChild(0).GetComponent<DragMeMenu>().spot = GSs[1]; //atribui ao botão qual seu GameSpot referente
        Botoes[1].transform.GetChild(0).GetComponent<Image>().sprite = SpritesBotoes[1];
        GSs[1].GetComponent<DropMeMenu>().respectiveImage[0] = Botoes[1].transform.GetChild(0).gameObject;//atribui ao GameSpot qual seu botão/imagem referente
        */
    }

    
    // Update is called once per frame
    void Update()
    {
        
    }
}