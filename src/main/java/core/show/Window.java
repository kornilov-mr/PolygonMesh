package core.show;

import core.render.Frame;
import core.render.camera.Camera;
import core.render.camera.CameraController;

import javax.swing.*;

public class Window {
    private final CameraPositionInfo cameraInfo;
    private final int width;
    private final int height;
    private final JFrame windowFrame;
    private final DirectDrawer directDrawer;
    public Window(int width, int height, CameraController cameraController) {
        this.width = width+1000;
        this.height = height;
        this.directDrawer=new DirectDrawer(width,height);
        this.windowFrame = new JFrame("3D render demo");
        this.cameraInfo = new CameraPositionInfo(cameraController);

        windowFrame.setVisible(true);
        windowFrame.setResizable(false);
        windowFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        windowFrame.setSize(width+200,height);
        JPanel p =new JPanel();

        windowFrame.addKeyListener(cameraController);
        windowFrame.addMouseMotionListener(cameraController);


        p.add(directDrawer);
        p.add(cameraInfo);
        windowFrame.add(p);

        windowFrame.setVisible(true);


    }
    public void showOneFrame(Frame frame){
        directDrawer.showFrame(frame);
        cameraInfo.updateCameraInfo();

    }

}
