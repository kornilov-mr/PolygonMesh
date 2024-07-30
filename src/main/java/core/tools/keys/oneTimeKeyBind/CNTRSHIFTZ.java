package core.tools.keys.oneTimeKeyBind;

import core.scene.SceneManipulator;

import java.awt.event.KeyEvent;
import java.util.Arrays;
import java.util.HashSet;

public class CNTRSHIFTZ extends OneTimeKeyBind{
    public CNTRSHIFTZ() {
        super(new HashSet<>(Arrays.asList(KeyEvent.VK_CONTROL,KeyEvent.VK_Z, KeyEvent.VK_SHIFT)));
    }

    @Override
    protected void run(SceneManipulator sceneManipulator) {
        sceneManipulator.getChangeManager().reverseNextChange();
    }
}
