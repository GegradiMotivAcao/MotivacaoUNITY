using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEditor;

public class objetos
	{
    // Auto-implemented properties.
    public string A { get; set; }
    public string B { get; set; }
    public string C { get; set; }

    public objetos()
    {

    }

    public objetos(string name, string name2,string name3)
    {
        A = name;
        B = name2;
        C = name3;
    }
}

public class LeituraParam : MonoBehaviour
{	

	List<objetos>Objs = new List<objetos>(0);

	public TextAsset arq;
	private string theWholeFileAsOneLongString;
 	private List<string> eachLine;

	[MenuItem("AssetDatabase/LoadAssetExample")]
    static void ImportExample()
    {
        
    
    }
    // Start is called before the first frame update
    void Start()
    {
        arq = (TextAsset)AssetDatabase.LoadAssetAtPath("Assets/Scenes/Builder/copiado.txt", typeof(TextAsset));
        theWholeFileAsOneLongString = arq.text;
     
     eachLine = new List<string>();
     eachLine.AddRange(theWholeFileAsOneLongString.Split("\n"[0]) );
     Debug.Log(eachLine[0]);
     Debug.Log(eachLine[1]);


    	//for (int i = 0; eachLine[i] != null; i++)
    	foreach(string line in eachLine)
		{
    	  	string[] leitura = line.Split(';');
    	  	Objs.Add(new objetos(leitura[0],leitura[1],leitura[2]));
			
		}
		Debug.Log(Objs[0].A);
    	Debug.Log(Objs[0].B);
    	Debug.Log(Objs[0].C);
    }

    
    // Update is called once per frame
    void Update()
    {
        
    }
}