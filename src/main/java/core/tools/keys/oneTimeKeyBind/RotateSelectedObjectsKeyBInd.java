package core.tools.keys.oneTimeKeyBind;

import core.UI.visuals.elements.toolPanel.toolInterface.instructions.editingInstruction.RotateInstruction;
import core.scene.SceneManipulator;

import java.awt.event.KeyEvent;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class RotateSelectedObjectsKeyBInd extends OneTimeKeyBind{
    public RotateSelectedObjectsKeyBInd(Set<Integer> keysRequired) {
        super(keysRequired);
    }

    public RotateSelectedObjectsKeyBInd() {
        super(new HashSet<>(Arrays.asList(KeyEvent.VK_SHIFT,KeyEvent.VK_R)));
    }

    @Override
    protected void run(SceneManipulator sceneManipulator) {
        sceneManipulator.getInstructionManager().queueInstruction(new RotateInstruction());
    }
}
