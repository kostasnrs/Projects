using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class PlaceCharacter : MonoBehaviour {


    private int x = 15;
    private int y = 15;
    private int z= 15;

    TransferDimensions trans;

    // Use this for initialization
    void Start () {

        trans = GameObject.Find("GameObject").GetComponent<TransferDimensions>();

        x = trans.defx;
        y = trans.defx;
        z = trans.defx;

        transform.position = new Vector3(x, 3, z);
	}
	
	// Update is called once per frame
	void Update () {
		
	}
}
