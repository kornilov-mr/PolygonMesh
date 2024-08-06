package core.tools.keys;

import core.tools.keys.oneTimeKeyBind.OneTimeKeyBind;
import core.tools.keys.radioKeyBind.RadioKeyBind;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class KeyBindsPreset {
    private final Map<String,RadioKeyBind> radioKeyBindMap = new HashMap<>();
    private final Map<String,OneTimeKeyBind> oneTimeKeyBindMap = new HashMap<>();

    protected KeyBindsPreset() {
        for(KeyBinds keyBind : KeyBinds.values()){
            if(keyBind.getOneTimeFlag()==1){
                try {
                    oneTimeKeyBindMap.put(keyBind.getNameInJson(), (OneTimeKeyBind) keyBind.getKeyBindClass().getConstructors()[1].newInstance());
                } catch (InstantiationException e) {
                    throw new RuntimeException(e);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                } catch (InvocationTargetException e) {
                    throw new RuntimeException(e);
                }
            }else{
                try {
                    radioKeyBindMap.put(keyBind.getNameInJson(), (RadioKeyBind) keyBind.getKeyBindClass().getConstructors()[1].newInstance());
                } catch (InstantiationException e) {
                    throw new RuntimeException(e);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                } catch (InvocationTargetException e) {
                    throw new RuntimeException(e);
                }

            }
        }
    }

    public Map<String, RadioKeyBind> getRadioKeyBindMap() {
        return radioKeyBindMap;
    }
    public void changeRequiredKey(String name, Set<Integer> newKeys){
        if(radioKeyBindMap.containsKey(name)){
            radioKeyBindMap.get(name).setNewKeys(newKeys);
        }else if(oneTimeKeyBindMap.containsKey(name)){
            oneTimeKeyBindMap.get(name).setNewKeys(newKeys);
        }
    }
    public Map<String, OneTimeKeyBind> getOneTimeKeyBindMap() {
        return oneTimeKeyBindMap;
    }
    public Collection<OneTimeKeyBind> getOneTimeKeyBinds(){
        return oneTimeKeyBindMap.values();
    }
    public Collection<RadioKeyBind> getRadioKeyBinds(){
        return radioKeyBindMap.values();
    }
}
