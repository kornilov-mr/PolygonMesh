package core.UI.compositers;

import core.UI.elements.CameraPositionInfo;
import core.UI.elements.RenderedPixelPlane;

import javax.swing.*;

public class MainPanel extends JPanel {
    public MainPanel(CameraPositionInfo cameraPositionInfo, RenderedPixelPlane renderedPixelPlane){
        add(cameraPositionInfo);
        add(renderedPixelPlane);
    }
}
