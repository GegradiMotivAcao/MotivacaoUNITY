﻿using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

public class TrocaSprite : MonoBehaviour
{

 public Sprite sprite1; // Drag your first sprite here
 public Sprite sprite2 ; // Drag your second sprite here
 
 private Image spriteRenderer; 
 
 void Start ()
 {
     spriteRenderer = GetComponent<Image>(); // we are accessing the SpriteRenderer that is attached to the Gameobject
     if (spriteRenderer.sprite == null) // if the sprite on spriteRenderer is null then
         spriteRenderer.sprite = sprite1; // set the sprite to sprite1
 }
 
 void Update ()
 {
     if (Input.GetMouseButtonDown(0)) // If the space bar is pushed down
     {
         ChangeTheDamnSprite (); // call method to change sprite
     }
 }
 
 void ChangeTheDamnSprite ()
 {
     if (spriteRenderer.sprite == sprite1) // if the spriteRenderer sprite = sprite1 then change to sprite2
     {
         spriteRenderer.sprite = sprite2;
     }
     else
     {
         spriteRenderer.sprite = sprite1; // otherwise change it back to sprite1
     }
}
}