package core.tools.keys.oneTimeKeyBind;

import core.UI.visuals.elements.toolPanel.toolInterface.instructions.editingInstruction.RotateInstruction;
import core.scene.SceneManipulator;
import core.tools.commands.editingCommands.RotatingSelectedObjects;

import java.awt.event.KeyEvent;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class SHIFTR extends OneTimeKeyBind{
    public SHIFTR() {
        super(new HashSet<>(Arrays.asList(KeyEvent.VK_SHIFT,KeyEvent.VK_R)));
    }

    @Override
    protected void run(SceneManipulator sceneManipulator) {
        sceneManipulator.getInstructionManager().queueInstruction(new RotateInstruction());
    }
}
