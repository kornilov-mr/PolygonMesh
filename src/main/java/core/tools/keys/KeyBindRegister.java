package core.tools.keys;

import core.scene.SceneManipulator;
import core.tools.keys.oneTimeKeyBind.*;
import core.tools.keys.radioKeyBind.OnlyPolygonShaderBind;
import core.tools.keys.radioKeyBind.RadioKeyBind;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;

public class KeyBindRegister {
    private final Set<RadioKeyBind> radioKeyBindSet = new HashSet<>();
    private final Set<OneTimeKeyBind> oneTimeKeyBindSet = new HashSet<>();
    private final File file = new File("src/main/java/core/data/keyBinds.json");

    public KeyBindRegister() {
        this.radioKeyBindSet.add(new OnlyPolygonShaderBind());
        this.oneTimeKeyBindSet.add(new CNTRZ());
        this.oneTimeKeyBindSet.add(new CNTRSHIFTZ());
        this.oneTimeKeyBindSet.add(new CreatePolygonBySelectedPointsKeyBind());
        this.oneTimeKeyBindSet.add(new CreatePointKeyBind());
        this.oneTimeKeyBindSet.add(new CreatePolygonKeyBind());
        this.oneTimeKeyBindSet.add(new CreateBoxKeyBind());
        this.oneTimeKeyBindSet.add(new RotateSelectedObjectsKeyBInd());
    }

    public void loopThroughOneTimeBinds(Set<Integer> keyPressed,SceneManipulator sceneManipulator){
        for(OneTimeKeyBind keyBind: oneTimeKeyBindSet){
            keyBind.execute(keyPressed,sceneManipulator);
        }
    }
    public void loopThroughRadioKeyBindOn(Set<Integer> keyPressed,SceneManipulator sceneManipulator){
        for(RadioKeyBind keyBind: radioKeyBindSet){
            keyBind.executeOn(keyPressed,sceneManipulator);
        }
    }
    public void loopThroughRadioKeyBindOff(Set<Integer> keyPressed,SceneManipulator sceneManipulator){
        for(RadioKeyBind keyBind: radioKeyBindSet){
            keyBind.executeOff(keyPressed,sceneManipulator);
        }
    }
    public void registerKeyBinds(){
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
            if(keyBindData.getKeyBind().getOneTimeFlag()==1){
                try {
                    this.oneTimeKeyBindSet.add((OneTimeKeyBind) keyBindData.getKeyBind().getKeyBindClass().getConstructors()[0]
                            .newInstance(keyBindData.getKeysRequired()));
                } catch (InvocationTargetException e) {
                    throw new RuntimeException(e);
                } catch (InstantiationException e) {
                    throw new RuntimeException(e);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }else{
                try {
                    this.radioKeyBindSet.add((RadioKeyBind) keyBindData.getKeyBind().getKeyBindClass().getConstructors()[0]
                            .newInstance(keyBindData.getKeysRequired()));
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
}
