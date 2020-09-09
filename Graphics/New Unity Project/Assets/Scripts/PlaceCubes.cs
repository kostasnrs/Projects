using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class PlaceCubes : MonoBehaviour {

    public int xdim=15;
    public int ydim = 15;
    public int zdim=15;

    public int xpos=0;
    public int ypos = 0;
    public int zpos=0;

    public GameObject RCube;
    public GameObject YCube;
    public GameObject BCube;
    public GameObject MCube;
    public GameObject GCube;


    TransferDimensions trans;

    // Use this for initialization
    void Start () {

        trans = GameObject.Find("GameObject").GetComponent<TransferDimensions>();

        xdim = trans.defx;
        ydim = trans.defx;
        zdim = trans.defx;

        for (int i = 0; i < xdim; i++){
            for(int j = 0; j < zdim; j++)
            {
                int random=Random.Range(1, 5);
                if (j == Mathf.Round(zdim / 2) && i == Mathf.Round(xdim / 2))
                {

                    Instantiate(MCube, new Vector3(xpos, 0, zpos), Quaternion.identity);
                }
                else
                {

                    if (random == 1)
                    {
                        Instantiate(RCube, new Vector3(xpos, 0, zpos), Quaternion.identity);
                    }
                    else if (random == 2)
                    {
                        Instantiate(BCube, new Vector3(xpos, 0, zpos), Quaternion.identity);
                    }
                    else if (random == 3)
                    {
                        Instantiate(GCube, new Vector3(xpos, 0, zpos), Quaternion.identity);
                    }
                    else
                    {
                        Instantiate(YCube, new Vector3(xpos, 0, zpos), Quaternion.identity);
                    }

                }
                
                
                zpos += 2;
            }
            zpos = 0;
            xpos += 2;
        }

	}
	
	// Update is called once per frame
	void Update () {
		
	}
}
