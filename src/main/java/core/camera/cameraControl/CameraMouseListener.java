package core.camera.cameraControl;

import core.render.RenderConfig;
import core.camera.Camera;
import utils.vectors.Vector2D;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class CameraMouseListener implements MouseMotionListener,Updatable {

    private Vector2D lastMousePosition = new Vector2D(0,0);
    private double cameraHorizontalAngle;
    private double cameraVerticalAngle;
    private final RenderConfig renderConfig;
    private final Camera camera;
    private boolean working = true;

    public CameraMouseListener(Camera camera, RenderConfig renderConfig) {
        this.camera = camera;
        this.renderConfig = renderConfig;
        this.cameraVerticalAngle =0;
        this.cameraHorizontalAngle =0;
    }
    @Override
    public void mouseDragged(MouseEvent e) {
        if(!working){
            return;
        }
        double newVerticalAngle = (lastMousePosition.getY()-e.getY())/renderConfig.resolution[1]*Math.PI/2;
        double newHorizontalAngle = (lastMousePosition.getX()-e.getX())/renderConfig.resolution[0]*Math.PI/2;
        this.lastMousePosition = new Vector2D(e.getX(),e.getY());
        this.cameraHorizontalAngle +=newHorizontalAngle;
        this.cameraVerticalAngle +=newVerticalAngle;
    }
    @Override
    public void mouseMoved(MouseEvent e) {
        this.lastMousePosition = new Vector2D(e.getX(),e.getY());
    }

    @Override
    public void update() {
        camera.setVectorsFromAngles(camera.getHorizontalAngle()+ cameraHorizontalAngle ,camera.getVerticalAngle()+cameraVerticalAngle);
    }
    public Camera getCamera() {
        return camera;
    }

    public boolean isWorking() {
        return working;
    }

    public void setWorking(boolean working) {
        this.working = working;
    }
}
