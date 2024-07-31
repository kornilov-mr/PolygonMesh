package core.tools.keys.oneTimeKeyBind;

import core.UI.visuals.elements.toolPanel.toolInterface.instructions.creatingInstructions.PointCreatingInstruction;
import core.scene.SceneManipulator;

import java.awt.event.KeyEvent;
import java.util.Arrays;
import java.util.HashSet;

public class SHIFTP extends OneTimeKeyBind{
    public SHIFTP() {
        super(new HashSet<>(Arrays.asList(KeyEvent.VK_SHIFT,KeyEvent.VK_P)));
    }

    @Override
    protected void run(SceneManipulator sceneManipulator) {
        sceneManipulator.getInstructionManager().queueInstruction(new PointCreatingInstruction());
    }
}
