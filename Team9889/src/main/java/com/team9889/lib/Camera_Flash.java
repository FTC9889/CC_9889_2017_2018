package com.team9889.lib;

import android.hardware.Camera;

/**
 * Created by joshua9889 on 5/7/17.
 */

public class Camera_Flash{
    private Camera camera;
    private Camera.Parameters parm;

    //Constructor
    public Camera_Flash(){}

    public void ReleaseCamera(){
        if (camera != null)
            camera.release();
    }

    public void On(boolean on){
        //Try to on camera
        try {
            camera = Camera.open();
            parm = camera.getParameters();
        }catch (Exception e){

        }

        //Set Mode
        try{
            if(on){
                parm.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
            }else {
                parm.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
            }
        } catch (Exception e){

        }

        //Push settings to camera
        try {
            camera.setParameters(parm);
        } catch (Exception e){

        }
    }
}
