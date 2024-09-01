package core.scene.sceneLoaders;

import core.scene.Scene;

import java.io.File;

public class FolderSceneManager {
    //class, which ensures, that the folder for saving Scene exesits
    private File pathToSceneFolder;

    protected FolderSceneManager(File pathToSceneFolder) {
        this.pathToSceneFolder = pathToSceneFolder;
    }
    protected File getSaveFile(Scene scene,Extensions extension) {
        if (!pathToSceneFolder.exists()) {
            pathToSceneFolder.mkdir();
        }
        int copyCount = 0;
        File saveFile = pathToSceneFolder.toPath().resolve(scene.getSceneName() + copyCount+extension.getRealExtension()).toFile();
        while (saveFile.exists()) {
            copyCount += 1;
            saveFile = pathToSceneFolder.toPath().resolve(scene.getSceneName() + copyCount+extension.getRealExtension()).toFile();
        }
        return saveFile;
    }

    public void setPathToSceneFolder(File pathToSceneFolder) {
        this.pathToSceneFolder = pathToSceneFolder;
    }
}
