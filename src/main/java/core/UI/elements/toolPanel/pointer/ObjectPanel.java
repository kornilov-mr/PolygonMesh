package core.UI.elements.toolPanel.pointer;

import core.UI.elements.toolPanel.pointer.objectInfoPanels.ObjectInfoPanel;
import core.camera.cameraControl.Updatable;

import javax.swing.*;

public class ObjectPanel extends JPanel implements Updatable {
    private ObjectInfoPanel currentObjectInfoPanel;

    public void loadObjectPanel(ObjectInfoPanel objectInfoPanel){
        this.currentObjectInfoPanel = objectInfoPanel;
        removeAll();
        revalidate();
        repaint();
        add(objectInfoPanel.createJPanel());
    }

    @Override
    public void update() {
        if(currentObjectInfoPanel!=null){
            currentObjectInfoPanel.update();
        }
    }
}
