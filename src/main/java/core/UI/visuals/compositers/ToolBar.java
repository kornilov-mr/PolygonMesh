package core.UI.visuals.compositers;

import core.UI.visuals.elements.toolPanel.pointer.ObjectPanel;
import core.UI.visuals.elements.toolPanel.toolInterface.InstructionToolBar;

import javax.swing.*;
import java.awt.*;

public class ToolBar extends JPanel {
    public ToolBar(ObjectPanel objectPanel, InstructionToolBar instructionToolBar){

        // set texts
        // add text area to panel
        JLabel titleLabel  = new JLabel("Tool bar");
        titleLabel.setAlignmentY(Component.TOP_ALIGNMENT);
        add(titleLabel);

        add(objectPanel);
        add(instructionToolBar);
        BoxLayout boxLayout = new BoxLayout(this,BoxLayout.Y_AXIS);
        setLayout(boxLayout);
    }
}
