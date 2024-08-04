package core.tools.keys;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class KeyBindData {
    private final KeyBinds keyBind;
    private final Set<Integer> keysRequired;


    public KeyBindData(KeyBinds keyBind, Set<Integer> keysRequired) {
        this.keyBind = keyBind;
        this.keysRequired = keysRequired;
    }
    public KeyBindData(JSONObject jsonObject){
        String name = jsonObject.getString("name");
        KeyBinds keyBind= null;
        for(KeyBinds keyBinds: KeyBinds.values()){
            if(Objects.equals(name,keyBinds.getNameInJson())){
                keyBind= keyBinds;
            }
        }
        if(keyBind==null){
            throw new RuntimeException("unknown name in keyBindJson");
        }
        Set<Integer> keys= new HashSet<>();
        JSONArray jsonArray = new JSONArray();
        for(int i=0;i<jsonArray.length();i++){
            keys.add(jsonArray.getInt(i));
        }
        this.keysRequired=keys;
        this.keyBind=keyBind;
    }
    public JSONObject createJsonData(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name",keyBind.getNameInJson());

        JSONArray jsonArray = new JSONArray();
        for(Integer key: keysRequired){
            jsonArray.put(key);
        }
        jsonObject.put("keysRequired",jsonArray);
        return jsonObject;
    }
    public KeyBinds getKeyBind() {
        return keyBind;
    }

    public Set<Integer> getKeysRequired() {
        return keysRequired;
    }

}
