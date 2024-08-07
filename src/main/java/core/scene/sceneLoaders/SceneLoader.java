package core.scene.sceneLoaders;

import core.scene.Scene;
import core.scene.resentProjects.ResentProjectData;
import core.scene.resentProjects.ResentProjectManager;

import javax.swing.*;
import java.io.File;
import java.nio.file.Paths;
import java.util.Date;

public abstract class SceneLoader {
    private final FolderSceneManager folderSceneManager;
    private final ResentProjectManager resentProjectManager;

    protected SceneLoader(File sceneFolder, ResentProjectManager resentProjectManager) {
        this.folderSceneManager = new FolderSceneManager(sceneFolder);
        this.resentProjectManager = resentProjectManager;
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
    public final void readScene(File file,Scene scene){
        scene.clear();
        read(file,scene);
    }
    protected abstract void read(File file,Scene scene);

    protected abstract void saving(File file,Scene scene);
    public void saveScene(Scene scene,File file){
        saving(file, scene);
        ResentProjectData projectData = new ResentProjectData(file,scene.getCanvas(),scene.getSceneName(),new Date().getTime());
        resentProjectManager.addNewProject(projectData);
    }
}
