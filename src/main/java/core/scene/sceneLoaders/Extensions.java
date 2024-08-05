package core.scene.sceneLoaders;

import java.io.File;
import java.lang.reflect.InvocationTargetException;

public enum Extensions {
    JSON(".json",JsonSceneLoader.class);

    Extensions(String realExtension, Class<? extends SceneLoader> loaderClass){
        this.realExtension =realExtension;
        this.loaderClass = loaderClass;
    }
    private final String realExtension;
    private final Class<? extends SceneLoader> loaderClass;
    public String getRealExtension() {
        return realExtension;
    }
    SceneLoader createSceneLoader(){
        SceneLoader sceneLoader=null;
        try {
            sceneLoader = (SceneLoader) loaderClass.getConstructors()[0].newInstance();
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        }
        return sceneLoader;
    }
}
