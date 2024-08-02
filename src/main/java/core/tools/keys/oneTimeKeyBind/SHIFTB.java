package core.tools.keys.oneTimeKeyBind;

import core.UI.visuals.elements.toolPanel.toolInterface.instructions.creatingInstructions.BoxCreatingInstruction;
import core.scene.SceneManipulator;

import java.awt.event.KeyEvent;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class SHIFTB extends OneTimeKeyBind{
    public SHIFTB() {
        super(new HashSet<>(Arrays.asList(KeyEvent.VK_SHIFT,KeyEvent.VK_B)));
    }

    @Override
    protected void run(SceneManipulator sceneManipulator) {
        sceneManipulator.getInstructionManager().queueInstruction(new BoxCreatingInstruction());
    }
}
