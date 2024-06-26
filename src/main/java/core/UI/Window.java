package core.UI;

import core.UI.compositers.ToolBar;
import core.UI.elements.toolPanel.pointer.ObjectPanel;
import core.UI.managers.UpdateManager;
import core.tools.selecting.PointMouseListener;
import core.UI.managers.FocusTabManager;
import core.camera.Camera;
import core.render.Frame;
import core.camera.cameraControl.CameraKeyListener;
import core.UI.compositers.MainPanel;
import core.camera.cameraControl.CameraMouseListener;
import core.render.RenderConfig;
import core.statistic.FPSTracker;
import core.tools.selecting.SelectionKeyListener;

import javax.swing.*;
import java.util.Date;

public class Window {
    private final RenderConfig renderConfig;
    private final MainPanel mainPanel;
    private final UpdateManager updateManager = new UpdateManager();
    public Window(RenderConfig renderConfig, Camera camera) {
        JFrame windowFrame = new JFrame("3D render demo");
        this.renderConfig=renderConfig;

        CameraKeyListener cameraKeyListener = new CameraKeyListener(camera,renderConfig);
        CameraMouseListener cameraMouseListener = new CameraMouseListener(camera,renderConfig);
        ObjectPanel objectPanel = new ObjectPanel();
        updateManager.addToUpdates(cameraKeyListener);
        updateManager.addToUpdates(cameraMouseListener);
        updateManager.addToUpdates(objectPanel);

        SelectionKeyListener selectionKeyListener = new SelectionKeyListener();

        FocusTabManager focusTabManager=new FocusTabManager();
        focusTabManager.setMainWindow(windowFrame);

        PointMouseListener pointMouseListener=new PointMouseListener(camera,focusTabManager, selectionKeyListener);
        pointMouseListener.setObjectPanel(objectPanel);

        this.mainPanel =new MainPanel(renderConfig,camera,cameraMouseListener,pointMouseListener);

        ToolBar toolBar = new ToolBar(objectPanel);

        JSplitPane sl = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,mainPanel,toolBar);
        windowFrame.add(sl);

        windowFrame.addKeyListener(cameraKeyListener);
        windowFrame.addKeyListener(selectionKeyListener);
        setWindowSettings(windowFrame);
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
    public void updateAll(){
        long deltaTime = new Date().getTime();
        updateManager.updateAll();
        FPSTracker.addComponent("UI update",new Date().getTime()-deltaTime);
    }

}
