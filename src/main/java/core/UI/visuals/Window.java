package core.UI.visuals;

import core.UI.controller.WindowController;
import core.UI.visuals.elements.toolPanel.pointer.ObjectPanel;
import core.UI.visuals.compositers.MainPanel;
import core.UI.visuals.compositers.ToolBar;
import core.tools.managers.UpdateManager;
import core.render.RenderSwitcher;
import core.scene.Scene;
import core.tools.changes.ChangeManager;
import core.tools.commands.CommandManager;
import core.tools.selecting.PointMouseListener;
import core.tools.managers.FocusTabManager;
import core.camera.Camera;
import core.render.Frame;
import core.camera.cameraControl.CameraKeyListener;
import core.camera.cameraControl.CameraMouseListener;
import core.render.RenderConfig;
import core.statistic.FPS.FPSTracker;
import core.tools.keys.MainKeyListener;

import javax.swing.*;
import java.util.Date;

public class Window {
    private final WindowController windowController;
    private final MainPanel mainPanel;
    public Window(WindowController windowController) {
        this.windowController=windowController;
        JFrame windowFrame = new JFrame("3D render demo");
        windowController.getFocusTabManager().setMainWindow(windowFrame);

        this.mainPanel =new MainPanel(windowController.getRenderConfig(),windowController.getScene(),windowController.getCamera(),
                windowController.getCameraMouseListener(),windowController.getPointMouseListener(),
                windowController.getObjectPanel(), windowController.getCommandManager(), windowController.getInstructionPanel());

        ToolBar toolBar = new ToolBar(windowController.getObjectPanel());

        JSplitPane sl = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,mainPanel,toolBar);
        windowFrame.add(sl);

        windowFrame.addKeyListener(windowController.getCameraKeyListener());
        windowFrame.addKeyListener(windowController.getMainKeyListener());
        setWindowSettings(windowFrame);
        windowFrame.setFocusable(true);
    }
    private void setWindowSettings(JFrame window){
        window.setSize(windowController.getRenderConfig().resolution[0]+200,windowController.getRenderConfig().resolution[1]);
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
        windowController.getUpdateManager().updateAll();
        FPSTracker.addComponent("UI update",new Date().getTime()-deltaTime);
    }

}
