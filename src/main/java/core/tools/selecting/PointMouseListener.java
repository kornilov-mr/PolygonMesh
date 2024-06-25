package core.tools.selecting;

import core.UI.elements.toolPanel.pointer.ObjectPanel;
import core.UI.elements.toolPanel.pointer.ObjectPanelFactory;
import core.UI.managers.FocusTabManager;
import core.camera.Camera;
import core.scene.Scene;
import primitive.Primitive;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class PointMouseListener implements MouseListener {

    private final Camera camera;
    private final ObjectPanelFactory objectPanelFactory;
    private ObjectPanel objectPanel=null;
    private final SelectedKeyListener selectedKeyListener;

    public PointMouseListener(Camera camera, FocusTabManager focusTabManager, SelectedKeyListener selectedKeyListener) {
        this.camera = camera;
        this.selectedKeyListener =selectedKeyListener;
        this.objectPanelFactory=new ObjectPanelFactory(focusTabManager,camera.getScene());
    }

    public void setObjectPanel(ObjectPanel objectPanel) {
        this.objectPanel = objectPanel;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println(e.getButton());
        if(e.getButton()==MouseEvent.BUTTON3){
            camera.getScene().selectedObjectManager.clearSelection();
            return;
        }
        if(e.getButton()==MouseEvent.BUTTON1) {
            if (objectPanel != null) {

                Primitive primitive = camera.getPrimitiveOnPixel(e.getX(), e.getY());
                if(primitive!=null) {
                    if(selectedKeyListener.isShiftPressed()){
                        camera.getScene().selectedObjectManager.changeSelection(primitive);
                    }else{
                        camera.getScene().selectedObjectManager.changeSelectionWithRemove(primitive);
                    }
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
