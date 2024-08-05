package core.scene.sceneLoaders;

import core.scene.Scene;

import java.io.File;
import java.nio.file.Paths;

public abstract class SceneLoader {
    private final FolderSceneManager folderSceneManager;

    protected SceneLoader(File sceneFolder) {
        this.folderSceneManager = new FolderSceneManager(sceneFolder);
    }
    protected File getSaveFile(Scene scene, Extensions extension) {
        return folderSceneManager.getSaveFile(scene,extension);
    }
    public void fastSaveScene(Scene scene){
        File file =getSaveFile(scene, Extensions.JSON);
        saveScene(scene,file);
    }
    public void saveSceneInDirOrFile(Scene scene,File file, Extensions extension){
        if(file.isDirectory()){
            setFolder(file);
            File realFile = Paths.get(file.getAbsolutePath()).resolve(getSaveFile(scene,extension).getName()).toFile();
            saveScene(scene,realFile);
        }else{
            saveScene(scene,file);

        }
    }
    protected void setFolder(File file){
        folderSceneManager.setPathToSceneFolder(file);
    }
    public abstract void readScene(File file,Scene scene);
    public abstract void saveScene(Scene scene,File file);
}
