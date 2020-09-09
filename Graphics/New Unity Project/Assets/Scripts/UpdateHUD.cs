using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

public class UpdateHUD : MonoBehaviour {

    GetCubes cubes;
    private int cucount;
    public Text cubetext;

	// Use this for initialization
	void Start () {
        cubes = GameObject.Find("FirstPersonCharacter").GetComponent<GetCubes>();
    }
	
	// Update is called once per frame
	void Update () {
        cucount = cubes.CubeCount;
        cubetext.text = cucount.ToString();
    }
}
