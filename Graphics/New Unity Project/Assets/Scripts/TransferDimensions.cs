using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class TransferDimensions : MonoBehaviour {

    GetDimensions refer;
    public GameObject obj;
    public int defx = 7;
	// Use this for initialization
	void Awake () {

        DontDestroyOnLoad(transform.gameObject);
        refer=obj.GetComponent<GetDimensions>();
       
	}

    private void Update()
    {
        defx = refer.x; 
    }
}
