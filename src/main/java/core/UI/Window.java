package core.UI;

import core.UI.compositers.ToolBar;
import core.UI.elements.toolPanel.pointer.ObjectPanel;
import core.UI.managers.UpdateManager;
import core.render.RenderSwitcher;
import core.scene.Scene;
import core.tools.changes.ChangeManager;
import core.tools.commands.CommandManager;
import core.tools.selecting.PointMouseListener;
import core.UI.managers.FocusTabManager;
import core.camera.Camera;
import core.render.Frame;
import core.camera.cameraControl.CameraKeyListener;
import core.UI.compositers.MainPanel;
import core.camera.cameraControl.CameraMouseListener;
import core.render.RenderConfig;
import core.statistic.FPS.FPSTracker;
import core.tools.keys.MainKeyListener;

import javax.swing.*;
import java.util.Date;

public class Window {
    private final RenderConfig renderConfig;
    private final MainPanel mainPanel;
    private final UpdateManager updateManager = new UpdateManager();
    public Window(RenderConfig renderConfig, Camera camera, Scene scene, RenderSwitcher renderSwitcher) {
        JFrame windowFrame = new JFrame("3D render demo");
        this.renderConfig=renderConfig;
        ChangeManager changeManager = new ChangeManager(scene.idManager, scene);
        CommandManager commandManager = new CommandManager(scene, scene.selectedObjectManager, changeManager);

        CameraKeyListener cameraKeyListener = new CameraKeyListener(camera,renderConfig);
        CameraMouseListener cameraMouseListener = new CameraMouseListener(camera,renderConfig);

        updateManager.addToUpdates(cameraKeyListener);
        updateManager.addToUpdates(cameraMouseListener);


        FocusTabManager focusTabManager=new FocusTabManager();
        focusTabManager.setMainWindow(windowFrame);

        MainKeyListener mainKeyListener = new MainKeyListener(commandManager, changeManager, renderSwitcher);
        PointMouseListener pointMouseListener=new PointMouseListener(camera,focusTabManager, mainKeyListener,commandManager);
        ObjectPanel objectPanel = new ObjectPanel();
        pointMouseListener.setObjectPanel(objectPanel);

        this.mainPanel =new MainPanel(renderConfig,camera,cameraMouseListener,pointMouseListener,objectPanel, commandManager);

        ToolBar toolBar = new ToolBar(objectPanel);

        JSplitPane sl = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,mainPanel,toolBar);
        windowFrame.add(sl);

        windowFrame.addKeyListener(cameraKeyListener);
        windowFrame.addKeyListener(mainKeyListener);
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
