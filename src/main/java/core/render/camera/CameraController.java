package core.render.camera;

import core.render.RenderConfig;
import utils.vectors.Vector2D;
import utils.vectors.Vector3D;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.HashSet;
import java.util.Set;

public class CameraController implements KeyListener, MouseMotionListener {
    private Vector2D lastNotDraggedMousePosition = new Vector2D(0,0);
    private double lastCameraVerticalAngle=0;
    private double lastCameraHorizontalAngle=0;
    private final Set<Integer> keyPressedSet= new HashSet<>();
    private final Camera camera;
    private final RenderConfig renderConfig;

    public CameraController(Camera camera, RenderConfig renderConfig) {
        this.camera = camera;
        this.renderConfig = renderConfig;
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
                    camera.moveCameraFront(0.1);
                    break;
                case KeyEvent.VK_S:
                    camera.moveCameraFront(-0.1);
                    break;
                case KeyEvent.VK_A:
                    camera.moveCameraRight(-0.1);
                    break;
                case KeyEvent.VK_D:
                    camera.moveCameraRight(0.1);
                    break;
                case KeyEvent.VK_SPACE:
                    camera.moveCameraUp(0.1);
                    break;
                case KeyEvent.VK_SHIFT:
                    camera.moveCameraUp(-0.1);
                    break;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        keyPressedSet.remove(e.getKeyCode());
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        double newVerticalAngle = (lastNotDraggedMousePosition.getX()-e.getX())/renderConfig.resolution[1]*Math.PI/2 + lastCameraVerticalAngle;
        double newHorizontalAngle = (lastNotDraggedMousePosition.getY()-e.getY())/renderConfig.resolution[0]*Math.PI/2 + lastCameraHorizontalAngle;
        camera.setHorizontalAngle(newHorizontalAngle);
        camera.setVerticalAngle(newVerticalAngle);
        System.out.println("sample");
        System.out.println("pre"+lastNotDraggedMousePosition);
        System.out.println("for"+new Vector2D(e.getX(),e.getY()));

    }
    @Override
    public void mouseMoved(MouseEvent e) {
        this.lastNotDraggedMousePosition = new Vector2D(e.getX(),e.getY());
        this.lastCameraHorizontalAngle=camera.getHorizontalAngle();
        this.lastCameraVerticalAngle= camera.getVerticalAngle();
        System.out.println("skip");
    }

    public Vector3D getCameraPosition(){
        return camera.getCameraPosition();
    }
    public double getCameraHorizontalAngle(){
        return camera.getHorizontalAngle();
    }
    public double getCameraVerticalAngle(){
        return camera.getVerticalAngle();
    }
}
