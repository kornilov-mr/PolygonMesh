package core.scene.sceneLoaders;

import java.io.File;

public class FolderSceneManager {
    private final File pathToSceneFolder;

    protected FolderSceneManager(File pathToSceneFolder) {
        this.pathToSceneFolder = pathToSceneFolder;
    }
    protected File getSaveFile() {
        if (!pathToSceneFolder.exists()) {
            pathToSceneFolder.mkdir();
        }
        int copyCount = 0;
        File saveFile = pathToSceneFolder.toPath().resolve("Scene" + copyCount+".json").toFile();
        while (saveFile.exists()) {
            copyCount += 1;
            saveFile = pathToSceneFolder.toPath().resolve("Scene" + copyCount+".json").toFile();
        }
        return saveFile;
    }
}
