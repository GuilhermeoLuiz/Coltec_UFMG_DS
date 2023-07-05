using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class PlayerControls : MonoBehaviour
{
    public float speed = 10.0f;
    public string axis;

    // Update is called once per frame
    void Update(){
        
        float v = Input.GetAxisRaw(axis);
        GetComponent<Rigidbody2D>().velocity = 
            new Vector2(0,v) * speed;

    }
}