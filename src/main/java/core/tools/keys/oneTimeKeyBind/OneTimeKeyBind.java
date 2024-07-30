package core.tools.keys.oneTimeKeyBind;

import core.scene.SceneManipulator;

import java.util.Set;

public abstract class OneTimeKeyBind {
    private final Set<Integer> keysRequired;

    public OneTimeKeyBind(Set<Integer> keysRequired) {
        this.keysRequired = keysRequired;
    }
    protected abstract void run(SceneManipulator sceneManipulator);
    public final void execute(Set<Integer> keysPressed,SceneManipulator sceneManipulator){
        if (keysPressed.size()!= keysRequired.size()){
            return;
        }
        for(Integer key: keysRequired){
            if(!keysPressed.contains(key)){
                return;
            }
        }
        run(sceneManipulator);
    }
}
