package core.UI.compositers;

import core.UI.elements.mainPanel.InfoData.CameraPositionInfo;
import core.UI.elements.mainPanel.InfoData.FPSInfo;
import core.UI.elements.mainPanel.InfoPanel;
import core.UI.elements.mainPanel.MainRenderPlane;
import core.UI.elements.toolPanel.pointer.PointMouseListener;
import core.camera.Camera;
import core.camera.cameraControl.CameraMouseListener;
import core.render.Frame;
import core.render.RenderConfig;

import javax.swing.*;

public class MainPanel extends JLayeredPane {
    private final RenderConfig renderConfig;

    private final MainRenderPlane mainRenderPlane;
    private final InfoPanel infoPanel;
    public MainPanel(RenderConfig renderConfig, Camera camera, CameraMouseListener cameraMouseListener, PointMouseListener pointMouseListener){
        this.renderConfig=renderConfig;
        this.mainRenderPlane =new MainRenderPlane(renderConfig.resolution[0],renderConfig.resolution[1],cameraMouseListener,pointMouseListener);

        this.infoPanel = new InfoPanel(camera);

        OverlayLayout overlayLayout = new OverlayLayout(this);
        setLayout(overlayLayout);

        add(infoPanel,JLayeredPane.PALETTE_LAYER);
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
