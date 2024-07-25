package core.scene.sceneLoaders;

import java.io.File;
import java.util.Objects;

public class SceneLoaderFactory {
    public static SceneLoader createSceneLoaderFromExtension(File file, Extensions extensions){
        if (extensions.equals(Extensions.JSON)){
            return new JsonSceneLoader(file);
        }
        return new JsonSceneLoader(file);
    }
    public static SceneLoader createSceneLoaderFromFile(File file){
        String extension = file.getName().substring(file.getName().lastIndexOf("."));
        if (Objects.equals(extension,Extensions.JSON.getRealExtension())){
            return new JsonSceneLoader(file);
        }
        return new JsonSceneLoader(file);
    }
}
