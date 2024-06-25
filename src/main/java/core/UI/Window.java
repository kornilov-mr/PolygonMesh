package core.UI;

import core.UI.compositers.ToolBar;
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
import core.tools.selecting.SelectedKeyListener;

import javax.swing.*;
import java.util.Date;

public class Window {
    private final RenderConfig renderConfig;
    private final MainPanel mainPanel;
    private final FocusTabManager focusTabManager;
    private final PointMouseListener pointMouseListener;
    private final UpdateManager updateManager = new UpdateManager();
    public Window(RenderConfig renderConfig, Camera camera) {
        JFrame windowFrame = new JFrame("3D render demo");

        this.renderConfig=renderConfig;
        CameraKeyListener cameraKeyListener = new CameraKeyListener(camera,renderConfig);
        CameraMouseListener cameraMouseListener = new CameraMouseListener(camera,renderConfig);
        SelectedKeyListener selectedKeyListener = new SelectedKeyListener();
        updateManager.addToUpdates(cameraKeyListener);
        updateManager.addToUpdates(cameraMouseListener);


        this.focusTabManager=new FocusTabManager();

        focusTabManager.setMainWindow(windowFrame);

        this.pointMouseListener=new PointMouseListener(camera,focusTabManager,selectedKeyListener);
        this.mainPanel =new MainPanel(renderConfig,camera,cameraMouseListener,pointMouseListener);
        ToolBar toolBar = new ToolBar(pointMouseListener,updateManager);

//        windowFrame.setLayout(new BorderLayout());


//        windowFrame.add(mainPanel,BorderLayout.CENTER);
        JSplitPane sl = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,mainPanel,toolBar);
        windowFrame.add(sl);

        setWindowSettings(windowFrame);
        windowFrame.addKeyListener(cameraKeyListener);
        windowFrame.addKeyListener(selectedKeyListener);
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
