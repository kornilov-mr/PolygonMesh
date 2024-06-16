package core.UI.elements;

import core.render.Frame;
import core.camera.cameraControl.CameraMouseListener;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class RenderedPixelPlane extends JPanel {
    public BufferedImage canvas;
    private final int width;
    private final int height;
    public Dimension getPreferredSize() {
        return new Dimension(canvas.getWidth(), canvas.getHeight());
    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.drawImage(canvas, null, null);
    }

    public RenderedPixelPlane(int width, int height, CameraMouseListener cameraMouseListener){
        this.width=width;
        this.height=height;
        addMouseMotionListener(cameraMouseListener);
        this.canvas = newEmptyFrame();
    }
    public BufferedImage newEmptyFrame(){
        return new BufferedImage(width,height,BufferedImage.TYPE_INT_ARGB);
    }
    public void showFrame(Frame frame){
        canvas=newEmptyFrame();

        for(int i=0;i<width;i++){
            for(int j=0;j<height;j++){
                canvas.setRGB(i,j,frame.colors[i][j].getRGB());
            }
        }
        repaint();
    }

}
