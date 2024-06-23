package core.UI.elements.toolPanel.pointer;

import core.UI.managers.FocusTabManager;
import core.camera.Camera;
import primitive.Primitive;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class PointMouseListener implements MouseListener {

    private final Camera camera;
    private final ObjectPanelFactory objectPanelFactory;
    private ObjectPanel objectPanel=null;

    public PointMouseListener(Camera camera, FocusTabManager focusTabManager) {
        this.camera = camera;
        this.objectPanelFactory=new ObjectPanelFactory(focusTabManager);
    }

    public void setObjectPanel(ObjectPanel objectPanel) {
        this.objectPanel = objectPanel;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getButton()==1) {
            if (objectPanel != null) {

                Primitive primitive = camera.getPrimitiveOnPixel(e.getX(), e.getY());
                if(primitive!=null) {

                    JPanel panel = objectPanelFactory.createObjectPanel(primitive);
                    objectPanel.loadObjectPanel(panel);
                    System.out.println(camera.getPrimitiveOnPixel(e.getX(), e.getY()));
                }
            }
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
