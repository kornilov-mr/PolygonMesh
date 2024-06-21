package core.UI.elements.mainPanel.InfoData;

import core.camera.cameraControl.Updatable;
import core.statistic.FPSTracker;

import javax.swing.*;
import java.awt.*;

public class FPSInfo implements InfoData {

    public FPSInfo(){
    }
    @Override
    public String createNewLabelText() {
        String label ="MPF info<br>";
        label+=FPSTracker.toHTMLString();
        return label;
    }
}
