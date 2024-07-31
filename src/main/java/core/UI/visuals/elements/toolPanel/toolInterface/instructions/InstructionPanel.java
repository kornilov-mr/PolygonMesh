package core.UI.visuals.elements.toolPanel.toolInterface.instructions;

import javax.swing.*;

public class InstructionPanel extends JPanel {
    private boolean load = false;
    public void loadInstructionPanel(JPanel instructionPanel){
        removeAll();
        revalidate();
        repaint();
        add(instructionPanel);
        setOpaque(false);
        load=true;
    }

    public void unload() {
        this.load = false;
        removeAll();
    }

    public boolean isLoad() {
        return load;
    }
}
