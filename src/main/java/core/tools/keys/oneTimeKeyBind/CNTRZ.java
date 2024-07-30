package core.tools.keys.oneTimeKeyBind;

import core.scene.SceneManipulator;

import java.awt.event.KeyEvent;
import java.util.Arrays;
import java.util.HashSet;

public class CNTRZ extends OneTimeKeyBind {
    public CNTRZ() {
        super(new HashSet<>(Arrays.asList(KeyEvent.VK_CONTROL,KeyEvent.VK_Z)));
    }

    @Override
    protected void run(SceneManipulator sceneManipulator) {
        sceneManipulator.getChangeManager().reversePreviousChange();
    }
}
