package core.show.compositers;

import core.show.elements.CameraPositionInfo;
import core.show.elements.RenderedPixelPlane;

import javax.swing.*;

public class MainPanel extends JPanel {
    public MainPanel(CameraPositionInfo cameraPositionInfo, RenderedPixelPlane renderedPixelPlane){
        add(cameraPositionInfo);
        add(renderedPixelPlane);
    }
}
