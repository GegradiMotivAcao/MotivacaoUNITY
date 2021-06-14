using UnityEditor;
using UnityEngine;
using System.Collections;
using System;  
using System.IO; 
 
public class BuildScript: MonoBehaviour
{
    static void Start()
    {

    }

    static void ForWindows()
     {
         string[] lines = File.ReadAllLines("AutoBuilder/Motivacao AutoBuilder/ref.txt");
         
         string name = lines[0];
         string end = lines[1];
         string[] scenes = {"Assets/Scenes/TelaInicial.unity","Assets/Scenes/Menu.unity", "Assets/Scenes/imagem2.unity", "Assets/Scenes/Creditos.unity"};
         BuildPipeline.BuildPlayer(scenes, end + name + ".exe", BuildTarget.StandaloneWindows64, BuildOptions.None);
     }
    
    static void ForMac()
     {
         string[] lines = File.ReadAllLines("AutoBuilder/Motivacao AutoBuilder/ref.txt");
         
         string name = lines[0];
         string end = lines[1];
         string[] scenes = {"Assets/Scenes/TelaInicial.unity","Assets/Scenes/Menu.unity", "Assets/Scenes/imagem2.unity", "Assets/Scenes/Creditos.unity"};
         BuildPipeline.BuildPlayer(scenes, end + name + ".exe", BuildTarget.StandaloneOSX, BuildOptions.None);
     }
}