package core;

import core.UI.Window;
import core.camera.Camera;
import core.render.Render;
import core.render.RenderConfig;
import core.scene.Scene;

public class RenderFactory {
    private RenderConfig renderConfig;
    private Scene scene;

    public void setRenderConfig(RenderConfig renderConfig) {
        this.renderConfig = renderConfig;
    }

    public void setScene(Scene scene) {
        this.scene = scene;
    }
    public RenderController buildRenderController(){

        if(scene==null){
            System.out.println("scene isn't specified");
        }
        if(renderConfig==null){
            System.out.println("renderConfing isn't specified, creating default preset");
            this.renderConfig= new RenderConfig();
        }
        Camera camera = new Camera(renderConfig);
        camera.setScene(scene);
        Render render=new Render(renderConfig,camera);
        Window window=new Window(renderConfig,camera,scene,render);
        return  new RenderController(renderConfig,render,window,camera, scene);
    }
}
