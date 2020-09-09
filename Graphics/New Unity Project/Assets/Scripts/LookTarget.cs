using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class LookTarget : MonoBehaviour {

    public Transform target;

  
    // Update is called once per frame
    void Update () {
        transform.LookAt(GameObject.Find("FPSController").transform);
    }
}
