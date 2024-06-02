package core;

import core.render.Frame;
import core.render.Render;
import core.render.RenderConfig;
import core.render.camera.Camera;
import core.render.camera.cameraControl.CameraKeyListener;
import core.render.camera.cameraControl.CameraMouseListener;
import core.scene.Scene;
import core.UI.Window;


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
            Frame frame = render.ProcessFrame(scene);
            window.showOneFrame(frame);
            cameraKeyListener.update();
            cameraMouseListener.update();
        }
    }
}
