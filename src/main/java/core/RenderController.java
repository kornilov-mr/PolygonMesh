package core;

import core.render.Frame;
import core.render.Render;
import core.render.RenderConfig;
import core.camera.Camera;
import core.scene.Scene;
import core.UI.Window;
import core.statistic.FPSTracker;

import java.util.Date;


public class RenderController {

    private final RenderConfig renderConfig;
    private final Render render;
    private final Window window;
    private final Camera camera;
    private final Scene scene;

    protected RenderController(RenderConfig renderConfig, Render render, Window window, Camera camera, Scene scene) {
        this.renderConfig = renderConfig;
        this.render = render;
        this.window = window;
        this.camera = camera;
        this.scene = scene;
    }

    public void startRender(){

        if(scene==null){
            System.out.println("emptyScene");
            return;
        }
        while(true){
            Frame frame = getProcessedFrame();
            UIUpdateWorker uiUpdateWorker = new UIUpdateWorker(frame, window);
            try {
                uiUpdateWorker.doInBackground();
            } catch (Exception e) {
                System.out.println("A problem accrued during UI update");
                throw new RuntimeException(e);
            }
            window.updateKeyListeners();
            window.updateInfoLabels();
        }
    }
    public Frame getProcessedFrame(){
        long deltaTime = new Date().getTime();
        Frame frame = render.ProcessFrame(scene);
        FPSTracker.addComponent("Processing frame",new Date().getTime()-deltaTime);
        return frame;
    }

}
