package core.UI.elements.toolPanel.pointer.objectInfoPanels;

import javax.swing.*;
import java.util.ArrayList;

public abstract class ObjectInfoPanel {
    protected final ArrayList<ObjectInfoPanel> infoPanels;
    public JPanel jPanel;
    public ObjectInfoPanel(ArrayList<ObjectInfoPanel> infoPanels){
        this.infoPanels=infoPanels;
        this.jPanel=createJPanel();
    }
    public JPanel createJPanel(){
        return null;
    }
    public void update(){
        for(ObjectInfoPanel infoPanel: infoPanels){
            infoPanel.update();
        }
        jPanel.revalidate();
        jPanel.repaint();
    }
}
