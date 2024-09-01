package core.tools.keys.radioKeyBind;

import core.UI.visuals.icons.Icons;
import core.scene.SceneManipulator;
import core.tools.keys.KeyBind;

import javax.swing.*;
import java.util.Set;

public abstract class RadioKeyBind extends KeyBind {
    //Abstract class, which trigger while required keys are pressed and deactivates after releasing them
    private final JLabel workingLabel = new JLabel();
    private boolean working = false;
    public RadioKeyBind(Set<Integer> keysRequired) {
        super(keysRequired);
    }
    protected abstract void turnOn(SceneManipulator sceneManipulator);
    protected abstract void turnOff(SceneManipulator sceneManipulator);
    public final void executeOn(Set<Integer> keysPressed,SceneManipulator sceneManipulator){
        for(Integer key: keysRequired){
            if(!keysPressed.contains(key)){
                return;
            }
        }
        working=true;
        updateLabel();
        turnOn(sceneManipulator);
    }
    public final void executeOff(Set<Integer> keysPressed,SceneManipulator sceneManipulator){
        for(Integer key: keysRequired){
            if(!keysPressed.contains(key)){
                working=false;
                updateLabel();
                turnOff(sceneManipulator);
                return;
            }
        }
    }
    public void updateLabel(){
        workingLabel.removeAll();
        if(working){
            workingLabel.add(Icons.RADIO_WORKING_ICON.label());
        }
    }

    public JLabel getWorkingLabel() {
        return workingLabel;
    }
    public void changeActivation(SceneManipulator sceneManipulator){
        if(working){
            turnOff(sceneManipulator);
            working=false;
        }else{
            turnOn(sceneManipulator);
            working=true;
        }
    }
}
