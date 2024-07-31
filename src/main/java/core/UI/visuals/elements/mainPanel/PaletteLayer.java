package core.UI.visuals.elements.mainPanel;

import core.UI.visuals.elements.toolPanel.toolInterface.instructions.InstructionPanel;

import javax.swing.*;
import java.awt.*;

public class PaletteLayer extends JPanel {

    public PaletteLayer(InfoPanel infoPanel, InstructionPanel instructionPanel) {
        setLayout(new BorderLayout());
        add(infoPanel, BorderLayout.WEST);
        add(instructionPanel, BorderLayout.EAST);
        setOpaque(false);

    }
}
