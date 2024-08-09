package core.scene.sceneLoaders;

import core.scene.resentProjects.ResentProjectManager;

import java.io.File;
import java.lang.reflect.InvocationTargetException;

public enum Extensions {
    JSON(".json",JsonSceneLoader.class),
    OBJ(".obj",OBJSceneLoader.class);

    Extensions(String realExtension, Class<? extends SceneLoader> loaderClass){
        this.realExtension =realExtension;
        this.loaderClass = loaderClass;
    }
    private final String realExtension;
    private final Class<? extends SceneLoader> loaderClass;
    public String getRealExtension() {
        return realExtension;
    }
    SceneLoader createSceneLoader(ResentProjectManager resentProjectManager){
        SceneLoader sceneLoader=null;
        try {
            sceneLoader = (SceneLoader) loaderClass.getConstructors()[0].newInstance(resentProjectManager);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        }
        return sceneLoader;
    }
    public static String[] extensions(){
        String[] ex = new String[Extensions.values().length];
        for(int i=0;i< Extensions.values().length;i++){
            ex[i]=Extensions.values()[i].realExtension.replace(".","");
        }
        return ex;
    }
}
