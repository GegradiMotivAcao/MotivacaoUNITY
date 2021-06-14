using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

public class Retangulo : MonoBehaviour
{
    [SerializeField]
    private Color _cor;
    [SerializeField]
    private Vector2 _pos1, _pos2;
    [SerializeField]
    private string _tipo;
    [SerializeField]
    private GameObject _pai;


    // Start is called before the first frame update
    void Start()
    {

        _pai = GameObject.FindGameObjectWithTag("imagemEditar");
        Debug.Log(_pai);
        
    }

    public void DesenhaRetangulo()
    {
        Rect _r = new Rect();
        int _largura = (int)_pos2.x - (int)_pos1.x;
        int _altura = (int)_pos2.y - (int)_pos1.y;
        _r.x = _pos1.x;
        _r.y = _pos1.y;
        _r.width = _largura;
        _r.height = _altura;

        

        Texture2D texture = new Texture2D(_largura, _altura, TextureFormat.RGB24, false);
        texture.Rectangle(Color.red, 100, 100);
        texture.Apply();


    }

    public void SetPos1(Vector2 p)
    {
        _pos1 = p;
    }

    public void SetPos2(Vector2 p)
    {
        _pos2 = p;
    }

}
