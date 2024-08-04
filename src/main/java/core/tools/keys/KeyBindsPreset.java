package core.tools.keys;

import core.tools.keys.oneTimeKeyBind.OneTimeKeyBind;
import core.tools.keys.radioKeyBind.RadioKeyBind;

import java.util.*;

public class KeyBindsPreset {
    private final Map<String,RadioKeyBind> radioKeyBindMap = new HashMap<>();
    private final Map<String,OneTimeKeyBind> oneTimeKeyBindMap = new HashMap<>();

    protected KeyBindsPreset() {
    }
    protected void addRadioKeyBind(String name, RadioKeyBind radioKeyBind){
        radioKeyBindMap.put(name,radioKeyBind);
    }
    protected void addOneTimeKeyBind(String name, OneTimeKeyBind oneTimeKeyBind){
        oneTimeKeyBindMap.put(name,oneTimeKeyBind);
    }

    public Map<String, RadioKeyBind> getRadioKeyBindMap() {
        return radioKeyBindMap;
    }
    public void changeRequiredKey(String name, Set<Integer> newKeys){
        radioKeyBindMap.get(name).setNewKeys(newKeys);
        oneTimeKeyBindMap.get(name).setNewKeys(newKeys);
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
