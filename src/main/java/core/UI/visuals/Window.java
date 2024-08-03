package core.UI.visuals;

import core.UI.controller.WindowController;
import core.UI.visuals.compositers.MainPanel;
import core.UI.visuals.compositers.ToolBar;
import core.render.Frame;
import core.statistic.FPS.FPSTracker;

import javax.swing.*;
import java.awt.*;
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
                windowController.getObjectPanel(), windowController.getCommandManager(), windowController.getInstructionPanel(), windowController.getMainKeyListener());

        ToolBar toolBar = new ToolBar(windowController.getObjectPanel(), windowController.getInstructionToolBar());
        toolBar.setPreferredSize(new Dimension(300, 500));
        JSplitPane sl = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,mainPanel,toolBar);
        sl.setDividerLocation(windowController.getRenderConfig().resolution[0]);
//        sl.setResizeWeight(0.5);
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
