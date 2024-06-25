package core.UI.managers;

import core.camera.cameraControl.Updatable;

import java.util.ArrayList;

public class UpdateManager {
    private final ArrayList<Updatable> updatables = new ArrayList<>();
    public void addToUpdates(Updatable updatable){
        updatables.add(updatable);
    }
    public void updateAll(){
        for(Updatable updatable: updatables){
            updatable.update();
        }
    }
}
