package core.UI;

import core.render.Frame;
import core.camera.cameraControl.CameraKeyListener;
import core.UI.compositers.MainPanel;
import core.camera.cameraControl.CameraMouseListener;
import core.render.RenderConfig;

import javax.swing.*;

public class Window {
    private final RenderConfig renderConfig;
    private final MainPanel mainPanel;
    public Window(RenderConfig renderConfig, CameraKeyListener cameraKeyListener, CameraMouseListener cameraMouseListener) {
        this.renderConfig=renderConfig;
        this.mainPanel =new MainPanel(renderConfig,cameraKeyListener.getCamera(),cameraMouseListener);

        JFrame windowFrame = new JFrame("3D render demo");

        windowFrame.add(mainPanel);

        setWindowSettings(windowFrame);
        windowFrame.addKeyListener(cameraKeyListener);
    }
    private void setWindowSettings(JFrame window){
        window.setSize(renderConfig.resolution[0]+200,renderConfig.resolution[1]);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(true);
        window.setVisible(true);
   }
    public void showOneFrame(Frame frame){
        mainPanel.showOneFrame(frame);
    }
    public void updateInfoLabels(){
        mainPanel.updateInfoLabels();
    }


}
