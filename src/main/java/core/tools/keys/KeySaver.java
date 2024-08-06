package core.tools.keys;

import core.tools.keys.oneTimeKeyBind.OneTimeKeyBind;
import core.tools.keys.radioKeyBind.RadioKeyBind;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class KeySaver {
    private final File file = new File("src/main/java/core/data/keyBinds.json");

    protected KeySaver() {
        if(!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            writeDefaultSettings();
        }
    }
    protected void writeDefaultSettings(){
        PrintWriter printWriter = null;
        try {
            printWriter = new PrintWriter(file);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        JSONArray jsonArray = new JSONArray();
        for(KeyBindDefault keyBind: KeyBindDefault.values()){
            JSONObject jsonObject = keyBind.getKeyBindData().createJsonData();
            jsonArray.put(jsonObject);
        }
        printWriter.println(jsonArray.toString());
        printWriter.flush();
    }
    protected void saveKeyPreset(KeyBindsPreset preset){
        PrintWriter printWriter = null;
        JSONArray jsonArray = new JSONArray();
        try {
            printWriter = new PrintWriter(file);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        for(KeyBind keyBind : preset.getOneTimeKeyBindMap().values()){
            JSONObject jsonObject = keyBind.getKeyBindData().createJsonData();
            jsonArray.put(jsonObject);
        }
        for(KeyBind keyBind : preset.getRadioKeyBindMap().values()){
            JSONObject jsonObject = keyBind.getKeyBindData().createJsonData();
            jsonArray.put(jsonObject);
        }
        printWriter.println(jsonArray);
        printWriter.flush();
    }
    protected void loadKeyPreset(KeyBindsPreset preset){
        String content = null;
        try {
            content = new String(Files.readAllBytes(Paths.get(file.toURI())));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        JSONArray jsonArray = new JSONArray(content);
        for(int i =0;i<jsonArray.length();i++){
            JSONObject keyData = jsonArray.getJSONObject(i);
            KeyBindData keyBindData = new KeyBindData(keyData);
            preset.changeRequiredKey(keyBindData.getKeyBind().getNameInJson(),keyBindData.getKeysRequired());
        }
    }
}
