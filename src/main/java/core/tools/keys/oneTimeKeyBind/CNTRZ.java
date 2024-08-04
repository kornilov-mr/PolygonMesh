package core.tools.keys.oneTimeKeyBind;

import core.scene.SceneManipulator;
import core.tools.keys.KeyBinds;

import java.awt.event.KeyEvent;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class CNTRZ extends OneTimeKeyBind {
    public CNTRZ(Set<Integer> keysRequired) {
        super(keysRequired);
    }

    public CNTRZ() {
        super(new HashSet<>(Arrays.asList(KeyEvent.VK_CONTROL,KeyEvent.VK_Z)));
    }

    @Override
    protected void run(SceneManipulator sceneManipulator) {
        sceneManipulator.getChangeManager().reversePreviousChange();
    }
    @Override
    protected KeyBinds getKeyBind() {
        return KeyBinds.CNTRZ;
    }
}
