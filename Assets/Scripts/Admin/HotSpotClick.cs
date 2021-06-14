using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class HotSpotClick : MonoBehaviour
{
    [SerializeField]
    private Vector3 lastPoint, vect3;
    [SerializeField]
    private bool _flag = false;
    [SerializeField]
    private int _conta = 0;
    // Start is called before the first frame update
    void Start()
    {
        vect3 = new Vector3(0.0f,0.0f, -2.0f);
        vect3 = Camera.main.ScreenToWorldPoint(vect3);
        vect3.z = -2.0f;
        lastPoint = vect3;

    }


    // Update is called once per frame
    void Update()
    {

        if (Input.GetMouseButtonUp(0))
        {
            if (_conta % 2 == 0)
            {
                
                vect3 = new Vector3(Input.mousePosition.x, Input.mousePosition.y, -2.0f);
                vect3 = Camera.main.ScreenToWorldPoint(vect3);
                vect3.z = -2.0f;
               
                _conta++;

            }
            else
            {
                lastPoint = vect3;
                _conta++;

            }
            //Debug.DrawLine(vect3, lastPoint,Color.red);

        }
    }


    void OnDrawGizmos()
    {
        // Draw a yellow sphere at the transform's position
        Gizmos.color = Color.red;
        Gizmos.DrawSphere(vect3, 0.2f);
        Gizmos.DrawSphere(lastPoint, 0.2f);
        Gizmos.color = Color.blue;
        Gizmos.DrawLine(vect3, lastPoint) ;
       
        // Gizmos.DrawLine(vect3, new Vector3(3f,3f,-2f));
    }


}