package core.tools.selecting;

import core.UI.elements.toolPanel.pointer.objectInfoPanels.InfoPanelConvertible;
import core.UI.elements.toolPanel.pointer.objectInfoPanels.ObjectInfoPanel;
import core.UI.elements.toolPanel.pointer.ObjectPanel;
import core.UI.managers.FocusTabManager;
import core.camera.Camera;
import primitive.Primitive;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class PointMouseListener implements MouseListener {

    private final Camera camera;
    private final FocusTabManager focusTabManager;
    private ObjectPanel objectPanel=null;
    private final SelectionKeyListener selectionKeyListener;

    public PointMouseListener(Camera camera, FocusTabManager focusTabManager, SelectionKeyListener selectionKeyListener) {
        this.camera = camera;
        this.focusTabManager=focusTabManager;
        this.selectionKeyListener = selectionKeyListener;
    }

    public void setObjectPanel(ObjectPanel objectPanel) {
        this.objectPanel = objectPanel;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getButton()==MouseEvent.BUTTON3){
            camera.getScene().selectedObjectManager.clearSelection();
            return;
        }
        if(e.getButton()==MouseEvent.BUTTON1) {
            if (objectPanel != null) {

                Primitive primitive = camera.getPrimitiveOnPixel(e.getX(), e.getY());
                if(primitive!=null) {
                    if(selectionKeyListener.isShiftPressed()){
                        camera.getScene().selectedObjectManager.changeSelection(primitive);
                    }else{
                        camera.getScene().selectedObjectManager.changeSelectionWithRemove(primitive);
                    }
                    ObjectInfoPanel objectInfoPanel =((InfoPanelConvertible) primitive).toInfoPanel(focusTabManager);
                    objectPanel.loadObjectPanel(objectInfoPanel);
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
