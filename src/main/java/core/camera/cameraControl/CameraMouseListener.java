package core.camera.cameraControl;

import core.render.RenderConfig;
import core.camera.Camera;
import utils.vectors.Vector2D;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class CameraMouseListener implements MouseMotionListener,Updatable {

    private Vector2D lastMousePosition = new Vector2D(0,0);
    private double newCameraHorizontalAngle;
    private double newCameraVerticalAngle;
    private final RenderConfig renderConfig;
    private final Camera camera;

    public CameraMouseListener(Camera camera, RenderConfig renderConfig) {
        this.camera = camera;
        this.renderConfig = renderConfig;
        this.newCameraVerticalAngle=0;
        this.newCameraHorizontalAngle=0;
    }
    @Override
    public void mouseDragged(MouseEvent e) {
        double newVerticalAngle = (lastMousePosition.getY()-e.getY())/renderConfig.resolution[1]*Math.PI/2;
        double newHorizontalAngle = (lastMousePosition.getX()-e.getX())/renderConfig.resolution[0]*Math.PI/2;
        this.lastMousePosition = new Vector2D(e.getX(),e.getY());
        this.newCameraHorizontalAngle+=newHorizontalAngle;
        this.newCameraVerticalAngle+=newVerticalAngle;
    }
    @Override
    public void mouseMoved(MouseEvent e) {
        this.lastMousePosition = new Vector2D(e.getX(),e.getY());
    }

    @Override
    public void update() {
        camera.moveAroundSphereOnHorizontalAngle(newCameraHorizontalAngle);
        camera.moveAroundSphereOnVerticalAngle(newCameraVerticalAngle);
        newCameraHorizontalAngle=0;
        newCameraVerticalAngle=0;
    }
    public Camera getCamera() {
        return camera;
    }
}
