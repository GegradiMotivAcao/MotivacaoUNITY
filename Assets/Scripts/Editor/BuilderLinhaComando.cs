/*using UnityEditor;
using UnityEngine;
using UnityEditor.Build.Reporting;
class BuilderLinhaComando
{
     static void PerformBuild ()
     {
         string[] scenes = {"Assets/Scenes/TelaInicial.unity",
                            "Assets/Scenes/Creditos.unity",
                            "Assets/Scenes/Meu.unity",
                            "Assets/Scenes/imagemCena001.unity",
                            "Assets/Scenes/imagemCena003.unity",
                            "Assets/Scenes/imagemCena004.unity",
                            "Assets/Scenes/imagemCena005.unity",
                            "Assets/Scenes/imagemCena006.unity" };
         string pathToDeploy = "builds/testeLinhaComando.exe";

         BuildPipeline.BuildPlayer(scenes, pathToDeploy, BuildTarget.StandaloneWindows, BuildOptions.None);

        BuildReport report = BuildPipeline.BuildPlayer(buildPlayerOptions);
        BuildSummary summary = report.summary;

         if (summary.result == BuildResult.Succeeded)
        {
            Debug.Log("Build succeeded: " + summary.totalSize + " bytes");
        }

        if (summary.result == BuildResult.Failed)
        {
            Debug.Log("Build failed");
        }
     }
}
*/
// "C:\Program Files\Unity\Hub\Editor\2018.4.35f1\Editor\unity.exe" -quit -batchmode -projectPath "D:\Projetos\GEGRADI\MotivacaoUNITY" -executeMethod BuilderLinhaComando.PerformBuild

//"C:\Program Files\Unity\Hub\Editor\2018.4.35f1\Editor\unity.exe" -quit -batchmode -executeMethod BuilderLinhaComando.PerformBuild


// C# example.
using UnityEditor;
using System.Diagnostics;

public class BuilderLinhaComando 
{
    [MenuItem("MyTools/Windows Build With Postprocess")]
    public static void PerformBuild ()
    {
        // Get filename.
        string path = EditorUtility.SaveFolderPanel("builds/testeLinhaComando/","","");
        string[] levels = new string[] { 
                            "Assets/Scenes/TelaInicial.unity",
                            "Assets/Scenes/Creditos.unity",
                            "Assets/Scenes/Meu.unity",
                            "Assets/Scenes/imagemCena001.unity",
                            "Assets/Scenes/imagemCena003.unity",
                            "Assets/Scenes/imagemCena004.unity",
                            "Assets/Scenes/imagemCena005.unity",
                            "Assets/Scenes/imagemCena006.unity"};
        // Build player.
        BuildPipeline.BuildPlayer(levels, path + "/testeLinhaComando.exe", BuildTarget.StandaloneWindows, BuildOptions.None);

        // Copy a file from the project folder to the build folder, alongside the built game.
        //FileUtil.CopyFileOrDirectory("Assets/Templates/Readme.txt", path + "Readme.txt");

        // Run the game (Process class from System.Diagnostics).
       /* Process proc = new Process();
        proc.StartInfo.FileName = path + "/testeLinhaComando.exe";
        proc.Start();*/
    }
}