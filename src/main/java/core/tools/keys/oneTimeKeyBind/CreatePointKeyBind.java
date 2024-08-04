package core.tools.keys.oneTimeKeyBind;

import core.UI.visuals.elements.toolPanel.toolInterface.instructions.creatingInstructions.PointCreatingInstruction;
import core.scene.SceneManipulator;
import core.tools.keys.KeyBinds;

import java.awt.event.KeyEvent;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class CreatePointKeyBind extends OneTimeKeyBind{
    public CreatePointKeyBind(Set<Integer> keysRequired) {
        super(keysRequired);
    }

    public CreatePointKeyBind() {
        super(new HashSet<>(Arrays.asList(KeyEvent.VK_SHIFT,KeyEvent.VK_P)));
    }

    @Override
    protected void run(SceneManipulator sceneManipulator) {
        sceneManipulator.getInstructionManager().queueInstruction(new PointCreatingInstruction());
    }
    @Override
    protected KeyBinds getKeyBind() {
        return KeyBinds.CREATE_POINT;
    }
}
