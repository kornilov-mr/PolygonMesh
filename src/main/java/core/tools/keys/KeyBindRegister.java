package core.tools.keys;

import core.scene.SceneManipulator;
import core.tools.keys.oneTimeKeyBind.*;
import core.tools.keys.radioKeyBind.OnlyPolygonShaderBind;
import core.tools.keys.radioKeyBind.RadioKeyBind;

import java.util.HashSet;
import java.util.Set;

public class KeyBindRegister {
    private final Set<RadioKeyBind> radioKeyBindSet = new HashSet<>();
    private final Set<OneTimeKeyBind> oneTimeKeyBindSet = new HashSet<>();

    public KeyBindRegister() {
        this.radioKeyBindSet.add(new OnlyPolygonShaderBind());
        this.oneTimeKeyBindSet.add(new CNTRZ());
        this.oneTimeKeyBindSet.add(new CNTRSHIFTZ());
        this.oneTimeKeyBindSet.add(new SHIFTA());
        this.oneTimeKeyBindSet.add(new SHIFTP());
        this.oneTimeKeyBindSet.add(new SHIFTLeftBracket());
    }

    public void loopThroughOneTimeBinds(Set<Integer> keyPressed,SceneManipulator sceneManipulator){
        for(OneTimeKeyBind keyBind: oneTimeKeyBindSet){
            keyBind.execute(keyPressed,sceneManipulator);
        }
    }
    public void loopThroughRadioKeyBindOn(Set<Integer> keyPressed,SceneManipulator sceneManipulator){
        for(RadioKeyBind keyBind: radioKeyBindSet){
            keyBind.executeOn(keyPressed,sceneManipulator);
        }
    }
    public void loopThroughRadioKeyBindOff(Set<Integer> keyPressed,SceneManipulator sceneManipulator){
        for(RadioKeyBind keyBind: radioKeyBindSet){
            keyBind.executeOff(keyPressed,sceneManipulator);
        }
    }
}
