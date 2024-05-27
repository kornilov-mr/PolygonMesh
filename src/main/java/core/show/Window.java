package core.show;

import core.render.Frame;

import javax.swing.*;

public class Window {

    private final int width;
    private final int height;
    private final JFrame windowFrame;
    private final DirectDrawer directDrawer;
    public Window(int width, int height) {
        this.width = width;
        this.height = height;
        this.directDrawer=new DirectDrawer(width,height);
        this.windowFrame = new JFrame("3D render demo");
        windowFrame.setVisible(true);
        windowFrame.setResizable(false);
        windowFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        windowFrame.setSize(width,height);

    }
    public void showOneFrame(Frame frame){
        directDrawer.showFrame(frame);
        windowFrame.add(directDrawer);
        windowFrame.setVisible(true);
    }

}
