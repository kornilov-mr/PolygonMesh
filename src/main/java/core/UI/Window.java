package core.UI;

import core.UI.elements.toolPanel.pointer.PointMouseListener;
import core.camera.Camera;
import core.render.Frame;
import core.camera.cameraControl.CameraKeyListener;
import core.UI.compositers.MainPanel;
import core.camera.cameraControl.CameraMouseListener;
import core.render.RenderConfig;
import core.scene.Scene;
import core.statistic.FPSTracker;

import javax.swing.*;
import java.util.Date;

public class Window {
    private final CameraKeyListener cameraKeyListener;
    private final CameraMouseListener cameraMouseListener;
    private final RenderConfig renderConfig;
    private final MainPanel mainPanel;
    private final PointMouseListener pointMouseListener;
    public Window(RenderConfig renderConfig, Camera camera) {
        this.renderConfig=renderConfig;
        this.cameraKeyListener = new CameraKeyListener(camera,renderConfig);
        this.cameraMouseListener = new CameraMouseListener(camera,renderConfig);
        this.pointMouseListener=new PointMouseListener(camera);
        this.mainPanel =new MainPanel(renderConfig,camera,cameraMouseListener,pointMouseListener);

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
    public void updateKeyListeners(){
        long deltaTime = new Date().getTime();
        cameraKeyListener.update();
        cameraMouseListener.update();
        FPSTracker.addComponent("Listener update",new Date().getTime()-deltaTime);
    }

}
