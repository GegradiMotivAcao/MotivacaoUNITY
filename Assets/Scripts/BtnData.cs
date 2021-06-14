using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;
using System;  
using System.IO; 
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Text.RegularExpressions;

public class BtnData : MonoBehaviour
{
    public string id;
    public Board matrix;
    public Image img;

    private int countframesuntil;
    private bool notused = true;

    // Start is called before the first frame update
    void Start()
    {

    }

    // Update is called once per frame
    void Update()
    {
        if(notused && (countframesuntil > 30)){
        List<Tuple<string,string,string,string>> refData = matrix.GetInfo();

        foreach (Tuple<string,string,string,string> data in refData){
            Debug.Log(data.Item1);
            if(data.Item2.Contains(id)){
                img.enabled = true;
                Debug.Log(id);
                //img.sprite = Resources.Load(data.Item1) as Sprite;
            }
        }
        notused = false;
        }

        else if(countframesuntil <= 30)
            countframesuntil++;
    }

}
