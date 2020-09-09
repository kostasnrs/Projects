using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class GetCubes : MonoBehaviour {

    public GameObject RCube;
    public GameObject YCube;
    public GameObject BCube;
    public GameObject GCube;
    public int CubeCount = 0;
	// Use this for initialization
	void Start () {
		
	}


    void OnTriggerStay(Collider other)
    {
        if (Input.GetKeyDown("e"))
        {
            if (other.gameObject.name == "GCube(Clone)")
            {
                CubeCount += 1;
                Instantiate(RCube, new Vector3(other.transform.position.x, other.transform.position.y, other.transform.position.z), Quaternion.identity);
                Destroy(other.gameObject);
            }
            else if (other.gameObject.name == "RCube(Clone)")
            {
                CubeCount += 1;
                Instantiate(YCube, new Vector3(other.transform.position.x, other.transform.position.y, other.transform.position.z), Quaternion.identity);
                Destroy(other.gameObject);
            }
            else if (other.gameObject.name == "YCube(Clone)")
            {
                CubeCount += 1;
                Instantiate(BCube, new Vector3(other.transform.position.x, other.transform.position.y, other.transform.position.z), Quaternion.identity);
                Destroy(other.gameObject);
            }

        }



    }


    // Update is called once per frame
    void Update () {
		
	}
}
