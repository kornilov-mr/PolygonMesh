package core.UI.compositers;

import core.UI.elements.toolPanel.pointer.ObjectPanel;
import core.UI.elements.toolPanel.pointer.PointMouseListener;

import javax.swing.*;
import java.awt.*;

public class ToolBar extends JPanel {
    private final PointMouseListener pointMouseListener;
    public ToolBar(PointMouseListener pointMouseListener){
        this.pointMouseListener=pointMouseListener;

        ObjectPanel objectPanel = new ObjectPanel();
        pointMouseListener.setObjectPanel(objectPanel);

        setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));

        // set texts
        // add text area to panel
        add(new JLabel("Tool bar"));
        add(objectPanel);
    }
}
