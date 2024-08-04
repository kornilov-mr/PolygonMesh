package core.tools.keys;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

public class KeySaver {
    private final File file = new File("src/main/java/core/data/keyBinds.json");

    public KeySaver() {
        if(!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            writeDefaultSettings();
        }
    }
    private void writeDefaultSettings(){
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
}
