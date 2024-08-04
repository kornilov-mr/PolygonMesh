package core.tools.keys;

import java.util.Set;

public abstract class KeyBind {
    protected Set<Integer> keysRequired;
    public KeyBind(Set<Integer> keysRequired) {
        this.keysRequired = keysRequired;
    }
    public void setNewKeys(Set<Integer> keysRequired){
        this.keysRequired=keysRequired;
    }
    protected abstract KeyBinds getKeyBind();
    public KeyBindData getKeyBindData(){
        return new KeyBindData(getKeyBind(),keysRequired);
    }
}
