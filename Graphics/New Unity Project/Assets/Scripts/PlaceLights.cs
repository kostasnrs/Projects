using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class PlaceLights : MonoBehaviour {

    public GameObject light1;
    public GameObject light2;
    public GameObject light3;
    public GameObject light4;

    TransferDimensions trans;
    // Use this for initialization

    void Start () {

        trans = GameObject.Find("GameObject").GetComponent<TransferDimensions>();

        int maxdim = trans.defx;

        Instantiate(light1, new Vector3(0, maxdim, 0), Quaternion.identity);
        Instantiate(light2, new Vector3(0, maxdim, maxdim), Quaternion.identity);
        Instantiate(light3, new Vector3(maxdim, maxdim, 0), Quaternion.identity);
        Instantiate(light4, new Vector3(maxdim, maxdim, maxdim), Quaternion.identity);

    }

    
}
