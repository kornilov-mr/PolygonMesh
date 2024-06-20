package core.UI.elements.mainPanel.InfoData;

import core.camera.cameraControl.Updatable;
import primitive.OrientedObject;
import utils.Calculation;
import utils.vectors.Vector3D;

import javax.swing.*;
import java.awt.*;

public class CameraPositionInfo implements InfoData {

    private final OrientedObject camera;
    public CameraPositionInfo(OrientedObject camera){
        this.camera=camera;
    }

    @Override
    public String createNewLabelText() {
        Vector3D cameraPosition = camera.getPosition();
        String label ="Camera info<br>";
        label += "x:"+ Calculation.round(cameraPosition.getX(),3)+"<br>";
        label += "y:"+ Calculation.round(cameraPosition.getY(),3)+"<br>";
        label += "z:"+ Calculation.round(cameraPosition.getZ(),3)+"<br>";
        label += "Vertical angle:"+ String.format("%.4g%n", (camera.getVerticalAngle()/Math.PI)*180)+"<br>";
        label += "Horizontal: angle:"+ String.format("%.4g%n", (camera.getHorizontalAngle()/Math.PI)*180)+"<br>";
        label += "Front vector:"+camera.getFrontVector().toString()+"<br>";
        label += "Above vector:"+camera.getAboveVector().toString()+"<br>";
        label += "Right vector:"+camera.getRightVector().toString()+"<br>";
        return label;
    }
}
