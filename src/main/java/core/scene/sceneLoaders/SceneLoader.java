package core.scene.sceneLoaders;

import core.scene.Scene;

import java.io.File;

public abstract class SceneLoader {
    private final FolderSceneManager folderSceneManager;

    protected SceneLoader(File sceneFolder) {
        this.folderSceneManager = new FolderSceneManager(sceneFolder);
    }
    protected File getSaveFile() {
        return folderSceneManager.getSaveFile();
    }
    public void fastSaveScene(Scene scene){
        File file =getSaveFile();
        saveScene(scene,file);
    }
    public abstract void readScene(File file,Scene scene);
    public abstract void saveScene(Scene scene,File file);
}
