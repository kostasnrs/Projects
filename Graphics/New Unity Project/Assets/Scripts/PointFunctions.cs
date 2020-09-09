using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class PointFunctions : MonoBehaviour {

    TransferDimensions trans;
    private int xwall = 0;
    private bool reachedtop = false;
    public int points=50;
    public int lives = 3;

    // Use this for initialization
    void Start () {
        trans = GameObject.Find("GameObject").GetComponent<TransferDimensions>();
        xwall = trans.defx;
    }
	
	// Update is called once per frame
	void Update () {

        if (transform.position.y > xwall - 2 && reachedtop == false)
        {
            points += 100;
            lives += 1;
        }
	}
}
