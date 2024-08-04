package core.tools.keys.oneTimeKeyBind;

import core.scene.SceneManipulator;
import core.tools.keys.KeyBinds;

import java.awt.event.KeyEvent;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class CNTRSHIFTZ extends OneTimeKeyBind{

    public CNTRSHIFTZ(Set<Integer> keysRequired) {
        super(keysRequired);
    }

    public CNTRSHIFTZ() {
        super(new HashSet<>(Arrays.asList(KeyEvent.VK_CONTROL,KeyEvent.VK_Z, KeyEvent.VK_SHIFT)));
    }

    @Override
    protected void run(SceneManipulator sceneManipulator) {
        sceneManipulator.getChangeManager().reverseNextChange();
    }

    @Override
    protected KeyBinds getKeyBind() {
        return KeyBinds.CNTRSHIFTZ;
    }
}
