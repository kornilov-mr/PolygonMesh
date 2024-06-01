package core.render.camera;

import core.render.RenderConfig;
import utils.vectors.Vector2D;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.HashSet;
import java.util.Set;

public class CameraController implements KeyListener, MouseMotionListener {
    private Vector2D lastMousePosition = new Vector2D(0,0);
    private double newCameraHorizontalAngle;
    private double newCameraVerticalAngle;
    private final Set<Integer> keyPressedSet= new HashSet<>();
    private final Camera camera;
    private final RenderConfig renderConfig;

    private double dx=0;
    private double dy=0;
    private double dz=0;

    public CameraController(Camera camera, RenderConfig renderConfig) {
        this.camera = camera;
        this.renderConfig = renderConfig;
        this.newCameraVerticalAngle=0;
        this.newCameraHorizontalAngle=0;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        keyPressedSet.add(e.getKeyCode());
        for(int key : keyPressedSet){
            switch (key){
                case KeyEvent.VK_W:
                    dx+=0.1;
                    break;
                case KeyEvent.VK_S:
                    dx-=0.1;
                    break;
                case KeyEvent.VK_A:
                    dy-=0.1;
                    break;
                case KeyEvent.VK_D:
                    dy+=0.1;
                    break;
                case KeyEvent.VK_SPACE:
                    dz-=0.1;
                    break;
                case KeyEvent.VK_SHIFT:
                    dz+=0.1;
                    break;
            }
        }
    }
    public void applyNewCameraPosition(){
        camera.moveCameraFront(dx);
        camera.moveCameraRight(dy);
        camera.moveCameraUp(dz);
        dx=0; dy=0; dz=0;
        camera.moveAroundSphereOnHorizontalAngle(newCameraHorizontalAngle);
        camera.moveAroundSphereOnVerticalAngle(newCameraVerticalAngle);
        newCameraHorizontalAngle=0;
        newCameraVerticalAngle=0;

    }
    @Override
    public void keyReleased(KeyEvent e) {
        keyPressedSet.remove(e.getKeyCode());

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

    public Camera getCamera() {
        return camera;
    }

}
