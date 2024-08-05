package core.render;

import core.UIUpdateWorker;
import core.camera.Camera;
import core.scene.Scene;
import core.UI.visuals.MainWindow;
import core.statistic.FPS.TrackFPS;
import core.statistic.FPS.FPSTracker;

import java.util.Date;


public class RenderController extends Thread {

    private final RenderConfig renderConfig;
    private final Render render;
    private final MainWindow mainWindow;
    private final Camera camera;
    private final Scene scene;

    public RenderController(RenderConfig renderConfig, Render render, MainWindow mainWindow, Camera camera, Scene scene) {
        this.renderConfig = renderConfig;
        this.render = render;
        this.mainWindow = mainWindow;
        this.camera = camera;
        this.scene = scene;
    }


    @Override
    public void run() {
        while(true){
            Frame frame = getProcessedFrame();
            UIUpdateWorker uiUpdateWorker = new UIUpdateWorker(frame, mainWindow);
            try {
                uiUpdateWorker.doInBackground();
            } catch (Exception e) {
                System.out.println("A problem accrued during UI update");
                throw new RuntimeException(e);
            }
            mainWindow.updateAll();
            mainWindow.updateInfoLabels();
        }
    }

    @TrackFPS
    public Frame getProcessedFrame(){
        long deltaTime = new Date().getTime();
        Frame frame = render.ProcessFrame(scene);
        FPSTracker.addComponent("Processing frame",new Date().getTime()-deltaTime);
        return frame;
    }

}
