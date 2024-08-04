package core.tools.keys.oneTimeKeyBind;

import core.scene.SceneManipulator;
import core.tools.commands.creatingCommands.polygon.CreatePolygonByPointsSelected;

import java.awt.event.KeyEvent;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class CreatePolygonBySelectedPointsKeyBind extends OneTimeKeyBind{
    public CreatePolygonBySelectedPointsKeyBind(Set<Integer> keysRequired) {
        super(keysRequired);
    }

    public CreatePolygonBySelectedPointsKeyBind() {
        super(new HashSet<>(Arrays.asList(KeyEvent.VK_SHIFT,KeyEvent.VK_A)));
    }

    @Override
    protected void run(SceneManipulator sceneManipulator) {
        sceneManipulator.getCommandManager().executeCommand(new CreatePolygonByPointsSelected());
    }
}
