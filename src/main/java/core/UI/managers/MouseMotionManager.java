package core.UI.managers;

import core.camera.cameraControl.CameraMouseListener;
import core.tools.selecting.SelectedMovementMouseMotionListener;

public class MouseMotionManager {
    private final SelectedMovementMouseMotionListener selectedMovementMouseMotionListener;
    private final CameraMouseListener cameraMouseListener;

    public MouseMotionManager(SelectedMovementMouseMotionListener selectedMovementMouseMotionListener, CameraMouseListener cameraMouseListener) {
        this.selectedMovementMouseMotionListener = selectedMovementMouseMotionListener;
        this.cameraMouseListener = cameraMouseListener;
    }
    public void stopCameraMotion(){
        cameraMouseListener.setWorking(false);
    }
    public void startCameraMotion(){
        cameraMouseListener.setWorking(true);
    }
}
