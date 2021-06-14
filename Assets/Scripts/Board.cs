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

public class Board : MonoBehaviour
{
    public List<Tuple<string,string,string,string>> refData = new List<Tuple<string,string,string,string>>();
    public string[] usedCell;
    public Text txt;

    private string[] refMatrix = {"0_0", "0_1", "0_2", "0_3", "0_4",
                        "0_-1", "0_-2", "0_-3", "0_-5",
                        "1_0", "1_1", "1_2", "1_3", "1_4",
                        "1_-1", "1_-2", "1_-3", "1_-5",
                        "2_0", "2_1", "2_2", "2_3", "2_4",
                        "2_-1", "2_-2", "2_-3", "2_-5",
                        "-1_0", "-1_1", "-1_2", "-1_3", "-1_4",
                        "-1_-1", "-1_-2", "-1_-3", "-1_-5",
                        "-2_0", "-2_1", "-2_2", "-2_3", "-2_4",
                        "-2_-1", "-2_-2", "-2_-3", "-2_-5"
                        };

    // Start is called before the first frame update
    void Start()
    {
        string[] lines = File.ReadAllLines("AutoBuilder/Motivacao AutoBuilder/ref.txt");

        int i=0;
        foreach (string line in lines)
        {

            if(i>1){
                string[] datalist = line.Split('|');
                /*foreach (string data in datalist)
                    Debug.Log(data);
                Debug.Log(datalist[0]);
                Debug.Log(datalist[1]);
                Debug.Log(datalist[2]);
                Debug.Log(datalist[3]);*/
                refData.Add(Tuple.Create(datalist[0],datalist[1],datalist[2],datalist[3]));
            }
            i++;
        }
        //Debug.Log(refData.Count);
        i=0;
        Array.Resize(ref usedCell, refData.Count);
        foreach (Tuple<string,string,string,string> data in refData){
            usedCell[i] = data.Item2;
            i++;
        }

        /*foreach (string data in usedCell)   
            Debug.Log(data);
        foreach (Tuple<string,string,string,string> data in refData){
            Debug.Log(data.Item1);
            Debug.Log(data.Item2);
            Debug.Log(data.Item3);
            Debug.Log(data.Item4);
        }*/
    }

    // Update is called once per frame
    void Update()
    {
        
    }

    public string[] GetRef(){
        return usedCell;
    }

    public List<Tuple<string,string,string,string>> GetInfo(){
        return refData;
    }
}
