package core.UI.elements;

import primitive.OrientedObject;
import utils.Calculation;
import utils.vectors.Vector3D;

import javax.swing.*;

public class CameraPositionInfo extends JLabel {

    private final OrientedObject camera;
    public CameraPositionInfo(OrientedObject camera){
        this.camera=camera;
    }
    public void updateCameraInfo(){
        setText(createLabelText());
    }

    private String createLabelText(){
        Vector3D cameraPosition = camera.getPosition();
        String label ="<html>";
        label += "x:"+ Calculation.round(cameraPosition.getX(),3)+"<br>";
        label += "y:"+ Calculation.round(cameraPosition.getY(),3)+"<br>";
        label += "z:"+ Calculation.round(cameraPosition.getZ(),3)+"<br>";
        label += "vertical:"+ Calculation.round(camera.getVerticalAngle(),3)+"<br>";
        label += "horizontal:"+ Calculation.round(camera.getHorizontalAngle(),3)+"<br>";
        label += camera.getFrontVector().toString()+"<br>";
        label += camera.getAboveVector().toString()+"<br>";
        label += camera.getRightVector().toString();
        label +="</html>";
        return label;
    }



}
