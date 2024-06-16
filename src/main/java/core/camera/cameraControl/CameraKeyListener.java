package core.camera.cameraControl;

import core.render.RenderConfig;
import core.camera.Camera;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashSet;
import java.util.Set;

public class CameraKeyListener implements KeyListener, Updatable {
    private final Set<Integer> keyPressedSet= new HashSet<>();
    private final Camera camera;
    private final RenderConfig renderConfig;

    private double dx=0;
    private double dy=0;
    private double dz=0;

    public CameraKeyListener(Camera camera, RenderConfig renderConfig) {
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
    @Override
    public void keyReleased(KeyEvent e) {
        keyPressedSet.remove(e.getKeyCode());

    }
    @Override
    public void update() {
        camera.moveCameraFront(dx);
        camera.moveCameraRight(dy);
        camera.moveCameraUp(dz);
        dx=0; dy=0; dz=0;
    }

    public Camera getCamera() {
        return camera;
    }
}
