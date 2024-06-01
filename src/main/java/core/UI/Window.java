package core.UI;

import core.render.Frame;
import core.render.camera.CameraController;
import core.UI.compositers.MainPanel;
import core.UI.elements.CameraPositionInfo;
import core.UI.elements.RenderedPixelPlane;

import javax.swing.*;

public class Window {
    private final CameraPositionInfo cameraInfo;
    private final int width;
    private final int height;
    private final RenderedPixelPlane renderedPixelPlane;
    public Window(int width, int height, CameraController cameraController) {
        this.width = width;
        this.height = height;
        this.renderedPixelPlane =new RenderedPixelPlane(width,height,cameraController);
        this.cameraInfo = new CameraPositionInfo(cameraController.getCamera());

        JFrame windowFrame = new JFrame("3D render demo");

        MainPanel mainPanel =new MainPanel(cameraInfo,renderedPixelPlane);
        windowFrame.add(mainPanel);

        setWindowSettings(windowFrame);
        windowFrame.addKeyListener(cameraController);
    }
    private void setWindowSettings(JFrame window){
        window.setSize(width+200,height);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setVisible(true);
    }
    public void showOneFrame(Frame frame){
        renderedPixelPlane.showFrame(frame);
        cameraInfo.updateCameraInfo();

    }

}
