package core.UI.elements.toolPanel.pointer;

import core.camera.Camera;
import core.scene.Scene;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class PointMouseListener implements MouseListener {

    private final Camera camera;

    public PointMouseListener(Camera camera) {
        this.camera = camera;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getButton()==1){
            System.out.println(camera.getPrimitiveOnPixel(e.getX(),e.getY()));
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
