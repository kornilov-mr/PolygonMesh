package core.scene.sceneLoaders;

import java.io.File;
import java.util.Objects;

public class SceneLoaderFactory {
    public static SceneLoader createSceneLoaderFromExtension(Extensions extensions){
        if (extensions.equals(Extensions.JSON)){
            return new JsonSceneLoader();
        }
        return new JsonSceneLoader();
    }
    public static SceneLoader createSceneLoaderFromFile(File file){
        String extension = file.getName().substring(file.getName().lastIndexOf("."));
        SceneLoader sceneLoader = null;
        for(Extensions extensionCurr: Extensions.values()){
            if(Objects.equals(extensionCurr.getRealExtension(),extension)){
                sceneLoader=extensionCurr.createSceneLoader();
            }
        }
        if(sceneLoader==null){
            throw new RuntimeException("unknown extension");
        }
        return sceneLoader;
    }
}
