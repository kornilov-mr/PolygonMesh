package core.tools.selecting;

import core.UI.visuals.elements.toolPanel.pointer.objectInfoPanels.InfoPanelConvertible;
import core.UI.visuals.elements.toolPanel.pointer.objectInfoPanels.ObjectInfoPanel;
import core.UI.visuals.elements.toolPanel.pointer.ObjectPanel;
import core.scene.Scene;
import core.tools.managers.FocusTabManager;
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
    private final Scene scene;

    public PointMouseListener(Camera camera,Scene scene, FocusTabManager focusTabManager, MainKeyListener mainKeyListener, CommandManager commandManager) {
        this.camera = camera;
        this.scene=scene;
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
            scene.selectedObjectManager.clearSelection();
            return;
        }
        if(e.getButton()==MouseEvent.BUTTON1) {
            if (objectPanel != null) {

                Primitive primitive = camera.getPrimitiveOnPixel(e.getX(), e.getY());
                if(primitive!=null) {
                    if(mainKeyListener.isShiftPressed()){
                       scene.selectedObjectManager.changeSelection(primitive);
                    }else{
                        scene.selectedObjectManager.changeSelectionWithRemove(primitive);
                        if(scene.selectedObjectManager.getSelected().size()!=1){
                            objectPanel.unloadObjectPanel();
                            return;
                        }
                    }
                    ObjectInfoPanel objectInfoPanel =((InfoPanelConvertible) primitive).toInfoPanel(focusTabManager,commandManager);
                    objectPanel.loadObjectPanel(objectInfoPanel);
                }else{
                    objectPanel.unloadObjectPanel();
                    scene.selectedObjectManager.clearSelection();
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
