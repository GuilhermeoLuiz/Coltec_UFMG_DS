using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class CameraController : MonoBehaviour
{
    public Transform target; //para colocarmos nosso personagem como alvo

    public Vector2 minPosition;
    public Vector2 maxPosition;
    public float smoothing;
    
    public ColorBlindFilter filter;
    
    void Start(){
        filter = GetComponent<ColorBlindFilter>();
    }


   void LateUpdate(){


    if (transform.position != target.position){

        Vector3 targetPosition = new Vector3(target.transform.position.x, target.transform.position.y, transform.position.z);

        //clamp in between
        targetPosition.x = Mathf.Clamp(targetPosition.x, minPosition.x, maxPosition.x);
        targetPosition.y = Mathf.Clamp(targetPosition.y, minPosition.y, maxPosition.y);

        //gradualmente move o objeto: posição original, posição alvo, velocidade
        transform.position = Vector3.Lerp(transform.position, targetPosition, smoothing);
        }
    }
}

