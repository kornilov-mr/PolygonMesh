package core.UI;

import core.UI.controller.WindowController;
import core.UI.visuals.MainWindow;
import core.camera.Camera;
import core.render.Render;
import core.render.RenderConfig;
import core.render.RenderController;
import core.scene.Scene;

public class App {
    private final WindowController windowController;
    private final MainWindow mainWindow;
    private final RenderConfig renderConfig;
    private final Camera camera;
    private final Scene scene;
    private final Render render;
    public App(RenderConfig renderConfig, Camera camera, Scene scene, Render render){
        this.windowController = new WindowController(renderConfig, camera, scene, render);
        this.mainWindow = new MainWindow(windowController);
        this.renderConfig=renderConfig;
        this.camera=camera;
        this.scene=scene;
        this.render=render;
    }
    public void start(){
        RenderController renderController = new RenderController(renderConfig,render, mainWindow,camera, scene);
        renderController.run();
    }
}
