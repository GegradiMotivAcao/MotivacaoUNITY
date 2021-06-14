using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;
using System.IO;

public class Listar : MonoBehaviour
{

    [SerializeField]
    private string _myPath = "imagens/comEdicao/";
    // Start is called before the first frame update
    void Start()
    {

        DirectoryInfo dir = new DirectoryInfo(_myPath);
        FileInfo[] info = dir.GetFiles("*.*");
        foreach (FileInfo f in info)
        {
            Debug.Log(f.ToString());
        }
    }

    // Update is called once per frame
    void Update()
    {
        
    }
}
