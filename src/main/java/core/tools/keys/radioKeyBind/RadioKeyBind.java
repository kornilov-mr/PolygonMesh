package core.tools.keys.radioKeyBind;

import core.scene.SceneManipulator;
import core.tools.keys.KeyBind;
import core.tools.keys.KeyBinds;

import java.util.Set;

public abstract class RadioKeyBind extends KeyBind {
    public RadioKeyBind(Set<Integer> keysRequired) {
        super(keysRequired);
        this.keysRequired = keysRequired;
    }
    protected abstract void turnOn(SceneManipulator sceneManipulator);
    protected abstract void turnOff(SceneManipulator sceneManipulator);
    public final void executeOn(Set<Integer> keysPressed,SceneManipulator sceneManipulator){
        for(Integer key: keysRequired){
            if(!keysPressed.contains(key)){
                return;
            }
        }
        turnOn(sceneManipulator);
    }
    public final void executeOff(Set<Integer> keysPressed,SceneManipulator sceneManipulator){
        for(Integer key: keysRequired){
            if(!keysPressed.contains(key)){
                turnOff(sceneManipulator);
                return;
            }
        }
    }
}
