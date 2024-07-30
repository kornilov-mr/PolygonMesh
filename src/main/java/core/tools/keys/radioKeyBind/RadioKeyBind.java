package core.tools.keys.radioKeyBind;

import core.scene.SceneManipulator;

import java.util.Set;

public abstract class RadioKeyBind {
    private final Set<Integer> keysRequired;

    public RadioKeyBind(Set<Integer> keysRequired) {
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
