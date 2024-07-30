package core.UI.visuals.elements.mainPanel;

import core.tools.managers.MouseMotionManager;
import core.tools.selecting.PointMouseListener;
import core.render.Frame;
import core.camera.cameraControl.CameraMouseListener;
import core.tools.selecting.SelectedMovementMouseMotionListener;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class MainRenderPlane extends JPanel {
    public volatile  BufferedImage canvas;
    private final int width;
    private final int height;
    private int i=0;
    public Dimension getPreferredSize() {
        return new Dimension(canvas.getWidth(), canvas.getHeight());
    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.drawImage(canvas, null, null);
        setOpaque(false);
    }

    public MainRenderPlane(int width, int height, CameraMouseListener cameraMouseListener, PointMouseListener pointMouseListener, SelectedMovementMouseMotionListener selectedMovementMouseMotionListener){
        this.width=width;
        this.height=height;
        this.canvas = newEmptyFrame();
        MouseMotionManager mouseMotionManager = new MouseMotionManager(selectedMovementMouseMotionListener,cameraMouseListener);
        addMouseMotionListener(cameraMouseListener);
        addMouseMotionListener(selectedMovementMouseMotionListener);
        selectedMovementMouseMotionListener.setMouseMotionManager(mouseMotionManager);
        addMouseListener(pointMouseListener);
        setBounds(0,0,width,height);
    }
    public BufferedImage newEmptyFrame(){
        return new BufferedImage(width,height,BufferedImage.TYPE_INT_ARGB);
    }
    public void showFrame(Frame frame){
        for(int i=0;i<width;i++){
            for(int j=0;j<height;j++){
                canvas.setRGB(i,j,frame.colors[i*height+j]);
            }
        }
        repaint();
    }

}
