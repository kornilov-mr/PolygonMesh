package core.tools.keys.oneTimeKeyBind;

import core.scene.SceneManipulator;
import core.tools.commands.creatingCommands.polygon.CreatePolygonByPointsSelected;

import java.awt.event.KeyEvent;
import java.util.Arrays;
import java.util.HashSet;

public class SHIFTA extends OneTimeKeyBind{
    public SHIFTA() {
        super(new HashSet<>(Arrays.asList(KeyEvent.VK_SHIFT,KeyEvent.VK_A)));
    }

    @Override
    protected void run(SceneManipulator sceneManipulator) {
        sceneManipulator.getCommandManager().executeCommand(new CreatePolygonByPointsSelected());
    }
}
