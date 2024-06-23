package core.UI.elements.toolPanel.pointer;

import javax.swing.*;

public class ObjectPanel extends JPanel {

    public void loadObjectPanel(JPanel objectPanel){
        removeAll();
        revalidate();
        repaint();
        add(objectPanel);

    }
}
