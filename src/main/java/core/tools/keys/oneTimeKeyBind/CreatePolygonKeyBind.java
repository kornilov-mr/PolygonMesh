package core.tools.keys.oneTimeKeyBind;

import core.UI.visuals.elements.toolPanel.toolInterface.instructions.creatingInstructions.PolygonCreatingInstruction;
import core.scene.SceneManipulator;
import core.tools.keys.KeyBinds;

import java.awt.event.KeyEvent;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class CreatePolygonKeyBind extends OneTimeKeyBind{
    public CreatePolygonKeyBind(Set<Integer> keysRequired) {
        super(keysRequired);
    }

    public CreatePolygonKeyBind() {
        super(new HashSet<>(Arrays.asList(KeyEvent.VK_SHIFT,KeyEvent.VK_OPEN_BRACKET)));
    }

    @Override
    public void run(SceneManipulator sceneManipulator) {
        sceneManipulator.getInstructionManager().queueInstruction(new PolygonCreatingInstruction());
    }
    @Override
    protected KeyBinds getKeyBind() {
        return KeyBinds.CNTRSHIFTZ;
    }
}
