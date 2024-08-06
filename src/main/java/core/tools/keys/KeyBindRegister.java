package core.tools.keys;

import core.scene.SceneManipulator;
import core.tools.keys.oneTimeKeyBind.*;
import core.tools.keys.radioKeyBind.RadioKeyBind;

import java.io.File;
import java.util.Set;

public class KeyBindRegister {
    private final KeyBindsPreset preset;
    private final KeySaver keySaver;

    public KeyBindRegister() {
        this.keySaver = new KeySaver();
        this.preset= new KeyBindsPreset();
        try {
            keySaver.loadKeyPreset(preset);

        }catch (Exception e){
            keySaver.writeDefaultSettings();
            keySaver.loadKeyPreset(preset);
        }
    }

    public void loopThroughOneTimeBinds(Set<Integer> keyPressed,SceneManipulator sceneManipulator){
        for(OneTimeKeyBind keyBind: preset.getOneTimeKeyBinds()){
            keyBind.execute(keyPressed,sceneManipulator);
        }
    }
    public void loopThroughRadioKeyBindOn(Set<Integer> keyPressed,SceneManipulator sceneManipulator){
        for(RadioKeyBind keyBind: preset.getRadioKeyBinds()){
            keyBind.executeOn(keyPressed,sceneManipulator);
        }
    }
    public void loopThroughRadioKeyBindOff(Set<Integer> keyPressed,SceneManipulator sceneManipulator){
        for(RadioKeyBind keyBind: preset.getRadioKeyBinds()){
            keyBind.executeOff(keyPressed,sceneManipulator);
        }
    }
    private void changeRequiredKey(String name, Set<Integer> newKeys){
        preset.changeRequiredKey(name,newKeys);
    }
    public void saveKeySetUp(){
        keySaver.saveKeyPreset(preset);
    }

    public KeyBindsPreset getPreset() {
        return preset;
    }
    public void setToDefault(){
        keySaver.writeDefaultSettings();
        keySaver.loadKeyPreset(preset);
    }
}
