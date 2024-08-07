package core.scene.resentProjects;

import core.scene.Scene;
import core.scene.sceneLoaders.Extensions;

import java.io.File;

public class LastImagesFolderManager {
    private File pathToSceneFolder;

    protected LastImagesFolderManager(File pathToSceneFolder) {
        this.pathToSceneFolder = pathToSceneFolder;
    }
    protected File getSaveFile(String name) {
        if (!pathToSceneFolder.exists()) {
            pathToSceneFolder.mkdir();
        }
        int copyCount = 0;
        File saveFile = pathToSceneFolder.toPath().resolve(name + copyCount+".png").toFile();
        while (saveFile.exists()) {
            copyCount += 1;
            saveFile = pathToSceneFolder.toPath().resolve(name + copyCount+".png").toFile();
        }
        return saveFile;
    }

    public void setPathToSceneFolder(File pathToSceneFolder) {
        this.pathToSceneFolder = pathToSceneFolder;
    }
}
