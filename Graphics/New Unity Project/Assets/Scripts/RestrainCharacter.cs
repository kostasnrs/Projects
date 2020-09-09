using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class RestrainCharacter : MonoBehaviour {

    TransferDimensions trans;

    private int xwall = 0;
    private int ywall = 0;
    private int zwall = 0;

    // Use this for initialization
    void Start () {

        trans = GameObject.Find("GameObject").GetComponent<TransferDimensions>();

        xwall = trans.defx;
        ywall = trans.defx;
        zwall = trans.defx;
    }
	
	// Update is called once per frame
	void Update () {
		
        if(transform.position.x > (xwall*2)-1)
        {
            transform.position = new Vector3((xwall*2) - 1, transform.position.y, transform.position.z);

        }
        if (transform.position.x < -1)
        {
            transform.position = new Vector3(-0.999999999f, transform.position.y, transform.position.z);
        }
        if(transform.position.z > (zwall*2)-1)
        {
            transform.position = new Vector3(transform.position.x, transform.position.y, (zwall*2)-1);
        }
        if(transform.position.z < -1)
        {
            transform.position = new Vector3(transform.position.x, transform.position.y,  -0.9999999999f);
        }
	}
}
