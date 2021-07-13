using UnityEngine;

public class ChangeResolution : MonoBehaviour
{
    void Start()
    {
        // Switch to 640 x 480 full-screen at 60 hz
        Screen.SetResolution(768, 1366, true, 60);
    }
}