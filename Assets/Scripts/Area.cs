using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Area 
{
    private int _tipo;
    private Rect _retangulo;
    private RectTransform _transform;

    public Area(int tipo)
    {
        _tipo = tipo;

    }
    
    public int getTipo()
    {
        return _tipo;

    }


    public RectTransform getTransform()
    {
        return _transform;

    }

    public void setTransform(RectTransform r)
    {
         _transform = r;

    }
}

