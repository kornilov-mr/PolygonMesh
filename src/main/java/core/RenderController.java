package core;

import core.render.Frame;
import core.render.Render;
import core.render.RenderConfig;
import core.camera.Camera;
import core.camera.cameraControl.CameraKeyListener;
import core.camera.cameraControl.CameraMouseListener;
import core.scene.Scene;
import core.UI.Window;
import core.statistic.FPSTracker;

import java.util.Date;


public class RenderController {

    private final RenderConfig renderConfig;
    private final Render render;
    private final Window window;
    private final Camera camera;
    private final CameraKeyListener cameraKeyListener;
    private final CameraMouseListener cameraMouseListener;
    private Scene scene;

    public RenderController(){
        this(new RenderConfig());
    }
    public RenderController(RenderConfig renderConfig) {
        this.renderConfig = renderConfig;
        this.camera = new Camera(renderConfig);
        this.cameraKeyListener = new CameraKeyListener(camera,renderConfig);
        this.cameraMouseListener = new CameraMouseListener(camera,renderConfig);
        this.render=new Render(renderConfig,camera);
        this.window=new Window(renderConfig.resolution[0],renderConfig.resolution[1], cameraKeyListener,cameraMouseListener);
    }

    public void setScene(Scene scene) {
        this.scene = scene;
    }

    public void startRender(){

        if(scene==null){
            System.out.println("emptyScene");
            return;
        }
        while(true){
            FPSTracker.reset();
            Frame frame = getProcessedFrame();
            updateUI(frame);
            updateKeyListeners();
            FPSTracker.endWriting();
            System.out.print("FPS:");
            System.out.println(FPSTracker.getFPS());
        }
    }
    public Frame getProcessedFrame(){
        long deltaTime = new Date().getTime();
        Frame frame = render.ProcessFrame(scene);
        FPSTracker.addComponent("Processing frame",new Date().getTime()-deltaTime);
        return frame;
    }
    public void updateUI(Frame frame){
        long deltaTime = new Date().getTime();
        window.showOneFrame(frame);
        FPSTracker.addComponent("UI update",new Date().getTime()-deltaTime);
    }
    public void updateKeyListeners(){
        long deltaTime = new Date().getTime();
        cameraKeyListener.update();
        cameraMouseListener.update();
        FPSTracker.addComponent("Listener update",new Date().getTime()-deltaTime);
    }
}
