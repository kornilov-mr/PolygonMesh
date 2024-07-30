package core.tools.selecting;

import core.UI.elements.toolPanel.pointer.objectInfoPanels.InfoPanelConvertible;
import core.UI.elements.toolPanel.pointer.objectInfoPanels.ObjectInfoPanel;
import core.UI.elements.toolPanel.pointer.ObjectPanel;
import core.UI.managers.FocusTabManager;
import core.camera.Camera;
import core.tools.commands.CommandManager;
import core.tools.keys.MainKeyListener;
import primitive.Primitive;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class PointMouseListener implements MouseListener {

    private final Camera camera;
    private final FocusTabManager focusTabManager;
    private ObjectPanel objectPanel=null;
    private final MainKeyListener mainKeyListener;
    private final CommandManager commandManager;

    public PointMouseListener(Camera camera, FocusTabManager focusTabManager, MainKeyListener mainKeyListener, CommandManager commandManager) {
        this.camera = camera;
        this.commandManager=commandManager;
        this.focusTabManager=focusTabManager;
        this.mainKeyListener = mainKeyListener;
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
                    if(mainKeyListener.isShiftPressed()){
                        camera.getScene().selectedObjectManager.changeSelection(primitive);
                    }else{
                        camera.getScene().selectedObjectManager.changeSelectionWithRemove(primitive);
                    }
                    ObjectInfoPanel objectInfoPanel =((InfoPanelConvertible) primitive).toInfoPanel(focusTabManager,commandManager);
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
