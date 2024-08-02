package core.UI.visuals.elements.toolPanel.pointer;

import core.UI.visuals.elements.toolPanel.pointer.objectInfoPanels.ObjectInfoPanel;
import core.camera.cameraControl.Updatable;

import javax.swing.*;
import java.awt.*;

public class ObjectPanel extends JPanel implements Updatable {
    private ObjectInfoPanel currentObjectInfoPanel;

    public ObjectPanel() {
        setAlignmentY(Component.TOP_ALIGNMENT);
    }

    public void loadObjectPanel(ObjectInfoPanel objectInfoPanel){
        this.currentObjectInfoPanel = objectInfoPanel;
        removeAll();
        revalidate();
        repaint();
        add(objectInfoPanel.createJPanel());
    }
    public void unloadObjectPanel(){
        removeAll();
        revalidate();
        repaint();
    }

    @Override
    public void update() {
        if(currentObjectInfoPanel!=null){
            currentObjectInfoPanel.update();
        }
    }
}
