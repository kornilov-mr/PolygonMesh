package core.UI.visuals.elements.mainPanel;

import core.UI.visuals.elements.mainPanel.InfoData.CameraPositionInfo;
import core.UI.visuals.elements.mainPanel.InfoData.FPSInfo;
import core.UI.visuals.elements.mainPanel.InfoData.InfoData;
import core.camera.cameraControl.Updatable;
import primitive.OrientedObject;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class InfoPanel extends JLabel implements Updatable {
    private final ArrayList<InfoData> infoDatas = new ArrayList<>();

    public InfoPanel(OrientedObject camera){
        super("infoPanel", SwingConstants.LEFT);
        infoDatas.add(new CameraPositionInfo(camera));
        infoDatas.add(new FPSInfo());
        setFont(new Font("Serif", Font.PLAIN, 10));
    }

    private String createLabelText(){
        String label ="<html>";
        for(InfoData infoData : infoDatas){
            label += infoData.createNewLabelText();
        }
        label +="</html>";
        return label;
    }

    @Override
    public void update() {
        setText(createLabelText());
    }
}
