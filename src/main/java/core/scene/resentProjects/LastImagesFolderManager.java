package core.scene.resentProjects;

import core.scene.Scene;
import core.scene.sceneLoaders.Extensions;

import java.io.File;
import java.nio.file.Paths;

public class LastImagesFolderManager {
    private File pathToSceneFolder;

    protected LastImagesFolderManager(File pathToSceneFolder) {
        this.pathToSceneFolder = pathToSceneFolder;
    }
    protected File getSaveFile(String name) {
        if (!pathToSceneFolder.exists()) {
            pathToSceneFolder.mkdir();
        }
        File screensFolder= new File(String.valueOf(Paths.get(pathToSceneFolder.getAbsolutePath()).resolve("lastScreens")));
        if(!screensFolder.exists()){
            screensFolder.exists();
        }
        int copyCount = 0;
        File saveFile = screensFolder.toPath().resolve(name + copyCount+".png").toFile();
        while (saveFile.exists()) {
            copyCount += 1;
            saveFile = screensFolder.toPath().resolve(name + copyCount+".png").toFile();
        }
        return saveFile;
    }

    public void setPathToSceneFolder(File pathToSceneFolder) {
        this.pathToSceneFolder = pathToSceneFolder;
    }
}
