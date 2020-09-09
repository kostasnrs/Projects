using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

public class GetDimensions : MonoBehaviour {

    public int x;
    public string[] split;
    public string dimentions;
    public InputField input;
	// Use this for initialization
	void Start () {
		
	}

    // Update is called once per frame
    void Awake()
    {
        input.onEndEdit.AddListener(AcceptStringInput);

    }

    void AcceptStringInput(string userinput)
    {
        dimentions = userinput;
        split = dimentions.Split('x');
        x = int.Parse(split[0]);
    }

}
