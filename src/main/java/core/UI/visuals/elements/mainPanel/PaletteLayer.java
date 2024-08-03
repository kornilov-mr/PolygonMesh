package core.UI.visuals.elements.mainPanel;

import core.UI.visuals.elements.toolPanel.toolInterface.instructions.InstructionPanel;

import javax.swing.*;
import java.awt.*;

public class PaletteLayer extends JPanel {

    public PaletteLayer(InfoPanel infoPanel, InstructionPanel instructionPanel) {
        setLayout(new BorderLayout());
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel,BoxLayout.Y_AXIS));
        leftPanel.add(infoPanel);
        leftPanel.setOpaque(false);

        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BorderLayout());
        rightPanel.add(instructionPanel,BorderLayout.PAGE_END);
        rightPanel.setOpaque(false);

        add(leftPanel, BorderLayout.WEST);
        add(rightPanel, BorderLayout.EAST);
        setOpaque(false);

    }
}
