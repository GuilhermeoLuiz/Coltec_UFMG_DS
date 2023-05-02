using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class DumbAIPlayer : MonoBehaviour
{
    public Transform target;
    public float speed = 10.0f;

// Update is called once per frame
    void Update(){
        float step = speed * Time.deltaTime;
        transform.position = Vector2.MoveTowards(transform.position ,
            new Vector2(transform.position.x, target.position.y), step);
    }
}