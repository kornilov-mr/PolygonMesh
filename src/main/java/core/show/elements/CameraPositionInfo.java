package core.show.elements;

import core.render.camera.CameraController;
import utils.Calculation;
import utils.vectors.Vector3D;

import javax.swing.*;

public class CameraPositionInfo extends JLabel {

    private final CameraController cameraController;
    public CameraPositionInfo(CameraController cameraController){
        this.cameraController=cameraController;
        setText("test");
    }
    public void updateCameraInfo(){
        setText(createLabelText());
    }

    private String createLabelText(){
        Vector3D cameraPosition = cameraController.getCameraPosition();
        String label ="<html>";
        label += "x:"+ Calculation.round(cameraPosition.getX(),3)+"<br>";
        label += "y:"+ Calculation.round(cameraPosition.getY(),3)+"<br>";
        label += "z:"+ Calculation.round(cameraPosition.getZ(),3)+"<br>";
        label += "vertical:"+ Calculation.round(cameraController.getCameraVerticalAngle(),3)+"<br>";
        label += "horizontal:"+ Calculation.round(cameraController.getCameraHorizontalAngle(),3);
        label +="</html>";
        return label;
    }



}
