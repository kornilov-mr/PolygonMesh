package core.tools.keys;

import core.scene.SceneManipulator;
import core.tools.keys.oneTimeKeyBind.*;
import core.tools.keys.radioKeyBind.RadioKeyBind;

import java.io.File;
import java.util.Set;

public class KeyBindRegister {
    private KeyBindsPreset preset;
    private final KeySaver keySaver;

    public KeyBindRegister() {
        this.keySaver = new KeySaver();
        try {
            this.preset= keySaver.loadKeyPreset();

        }catch (Exception e){
            keySaver.writeDefaultSettings();
            this.preset= keySaver.loadKeyPreset();

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

}
