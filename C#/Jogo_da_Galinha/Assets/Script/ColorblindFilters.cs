using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;


public class ColorblindFilters : MonoBehaviour{


    public Toggle toggleNone;
    public Toggle toggleProtanopia;
    public Toggle toggleDeuteranotopia;

    public CameraController cam;


    // Start is called before the first frame update
    void Start(){
        cam = Camera.main.GetComponent<CameraController>();

        if (PlayerPrefs.GetInt("ToggleBool") == 1){
            toggleNone.isOn = true;
        }
        else{
            toggleNone.isOn = false;
        }
        if (PlayerPrefs.GetInt("ToggleBool2") == 1){
            toggleProtanopia.isOn = true;
        }
        else{
            toggleProtanopia.isOn = false;
        }
        if (PlayerPrefs.GetInt("ToggleBool3") == 1){
            toggleDeuteranotopia.isOn = true;
        }
        else{
            toggleDeuteranotopia.isOn = false;
        }
    }

    // Update is called once per frame
    void Update(){
        if (toggleNone.isOn == true){
            PlayerPrefs.SetInt("ToggleBool", 1);
            cam.filter.mode = ColorBlindMode.Normal;
        } else {
            PlayerPrefs.SetInt("ToggleBool", 0);
        }
        if (toggleProtanopia.isOn == true){
            PlayerPrefs.SetInt("ToggleBool2", 1);
            cam.filter.mode = ColorBlindMode.Protanopia;
        } else {
            PlayerPrefs.SetInt("ToggleBool2", 0);
        }
        if (toggleDeuteranotopia.isOn == true){
            PlayerPrefs.SetInt("ToggleBool3", 1);
            cam.filter.mode = ColorBlindMode.Deuteranopia;
        } else {
            PlayerPrefs.SetInt("ToggleBool3", 0);
        }
    }

}
