package core.UI.visuals.compositers;

import core.UI.controller.WindowController;
import core.UI.visuals.elements.mainPanel.InfoPanel;
import core.UI.visuals.elements.mainPanel.MainRenderPlane;
import core.UI.visuals.elements.mainPanel.PaletteLayer;
import core.UI.visuals.elements.toolPanel.pointer.ObjectPanel;
import core.UI.visuals.elements.toolPanel.toolInterface.instructions.InstructionPanel;
import core.scene.Scene;
import core.tools.commands.CommandManager;
import core.tools.keys.MainKeyListener;
import core.tools.selecting.PointMouseListener;
import core.camera.Camera;
import core.camera.cameraControl.CameraMouseListener;
import core.render.Frame;
import core.render.RenderConfig;
import core.tools.selecting.SelectedMovementMouseMotionListener;

import javax.swing.*;
import java.awt.*;

public class MainPanel extends JLayeredPane {
    private final RenderConfig renderConfig;

    private final MainRenderPlane mainRenderPlane;
    private final InfoPanel infoPanel;
    public MainPanel(WindowController windowController){
        this.renderConfig=windowController.getRenderConfig();
        this.mainRenderPlane =windowController.getMainRenderPlane();

        this.infoPanel = new InfoPanel(windowController.getCamera());
        setPreferredSize(new Dimension(renderConfig.resolution[0],renderConfig.resolution[1]));
        PaletteLayer paletteLayer = new PaletteLayer(infoPanel, windowController.getInstructionPanel());
        OverlayLayout overlayLayout = new OverlayLayout(this);
        setLayout(overlayLayout);
        add(paletteLayer,JLayeredPane.PALETTE_LAYER);
        add(mainRenderPlane,JLayeredPane.DEFAULT_LAYER);
        infoPanel.setAlignmentX(0.2f);
        infoPanel.setAlignmentY(0.0f);
        infoPanel.setBounds(0,0,infoPanel.getPreferredSize().width,infoPanel.getPreferredSize().height);
    }

    @Override
    public void invalidate() {
        super.invalidate();
    }
    public void showOneFrame(Frame frame){
        mainRenderPlane.showFrame(frame);
    }
    public void updateInfoLabels(){
        infoPanel.update();
    }
}
