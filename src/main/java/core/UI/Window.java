package core.UI;

import core.UI.compositers.ToolBar;
import core.UI.elements.toolPanel.pointer.PointMouseListener;
import core.UI.managers.FocusTabManager;
import core.camera.Camera;
import core.render.Frame;
import core.camera.cameraControl.CameraKeyListener;
import core.UI.compositers.MainPanel;
import core.camera.cameraControl.CameraMouseListener;
import core.render.RenderConfig;
import core.statistic.FPSTracker;

import javax.swing.*;
import java.util.Date;

public class Window {
    private final CameraKeyListener cameraKeyListener;
    private final CameraMouseListener cameraMouseListener;
    private final RenderConfig renderConfig;
    private final MainPanel mainPanel;
    private final FocusTabManager focusTabManager;
    private final PointMouseListener pointMouseListener;
    public Window(RenderConfig renderConfig, Camera camera) {
        JFrame windowFrame = new JFrame("3D render demo");

        this.renderConfig=renderConfig;
        this.cameraKeyListener = new CameraKeyListener(camera,renderConfig);
        this.cameraMouseListener = new CameraMouseListener(camera,renderConfig);
        this.focusTabManager=new FocusTabManager();

        focusTabManager.setMainWindow(windowFrame);

        this.pointMouseListener=new PointMouseListener(camera,focusTabManager);
        this.mainPanel =new MainPanel(renderConfig,camera,cameraMouseListener,pointMouseListener);
        ToolBar toolBar = new ToolBar(pointMouseListener);

//        windowFrame.setLayout(new BorderLayout());


//        windowFrame.add(mainPanel,BorderLayout.CENTER);
        JSplitPane sl = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,mainPanel,toolBar);
        windowFrame.add(sl);

        setWindowSettings(windowFrame);
        windowFrame.addKeyListener(cameraKeyListener);
        windowFrame.setFocusable(true);
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
