package core.UI.compositers;

import core.UI.elements.toolPanel.pointer.ObjectPanel;
import core.UI.managers.UpdateManager;
import core.tools.selecting.PointMouseListener;

import javax.swing.*;

public class ToolBar extends JPanel {
    public ToolBar(ObjectPanel objectPanel){

        setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));

        // set texts
        // add text area to panel
        add(new JLabel("Tool bar"));
        add(objectPanel);
    }
}
