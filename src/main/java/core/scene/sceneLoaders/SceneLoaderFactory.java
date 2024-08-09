package core.scene.sceneLoaders;

import core.scene.resentProjects.ResentProjectManager;

import java.io.File;
import java.util.Objects;

public class SceneLoaderFactory {
    public static SceneLoader createSceneLoaderFromExtension(Extensions extensions, ResentProjectManager resentProjectManager){
        if (extensions.equals(Extensions.JSON)){
            return new JsonSceneLoader(resentProjectManager);
        }else if(extensions.equals(Extensions.OBJ)){
            return new OBJSceneLoader(resentProjectManager);
        }
        return new JsonSceneLoader(resentProjectManager);
    }
    public static SceneLoader createSceneLoaderFromFile(File file,ResentProjectManager resentProjectManager){
        String extension = file.getName().substring(file.getName().lastIndexOf("."));
        SceneLoader sceneLoader = null;
        for(Extensions extensionCurr: Extensions.values()){
            if(Objects.equals(extensionCurr.getRealExtension(),extension)){
                sceneLoader=extensionCurr.createSceneLoader(resentProjectManager);
            }
        }
        if(sceneLoader==null){
            throw new RuntimeException("unknown extension");
        }
        return sceneLoader;
    }
}
