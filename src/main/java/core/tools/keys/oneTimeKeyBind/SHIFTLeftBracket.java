package core.tools.keys.oneTimeKeyBind;

import core.UI.visuals.elements.toolPanel.toolInterface.instructions.creatingInstructions.PolygonCreatingInstruction;
import core.scene.SceneManipulator;

import java.awt.event.KeyEvent;
import java.util.Arrays;
import java.util.HashSet;

public class SHIFTLeftBracket extends OneTimeKeyBind{
    public SHIFTLeftBracket() {
        super(new HashSet<>(Arrays.asList(KeyEvent.VK_SHIFT,KeyEvent.VK_OPEN_BRACKET)));
    }

    @Override
    protected void run(SceneManipulator sceneManipulator) {
        sceneManipulator.getInstructionManager().queueInstruction(new PolygonCreatingInstruction());
    }
}
