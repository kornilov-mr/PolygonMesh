package core.tools.keys.oneTimeKeyBind;

import core.scene.SceneManipulator;
import core.tools.keys.KeyBind;
import core.tools.keys.KeyBinds;

import java.util.Set;

public abstract class OneTimeKeyBind extends KeyBind {

    public OneTimeKeyBind(Set<Integer> keysRequired) {
        super(keysRequired);
    }
    public abstract void run(SceneManipulator sceneManipulator);
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
