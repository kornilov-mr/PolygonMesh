package core;

import core.UI.App;
import core.camera.Camera;
import core.render.Render;
import core.render.RenderConfig;
import core.scene.Scene;

public class AppBuilder {

    private RenderConfig renderConfig;
    private Scene scene;

    public void setRenderConfig(RenderConfig renderConfig) {
        this.renderConfig = renderConfig;
    }

    public void setScene(Scene scene) {
        this.scene = scene;
    }
    public App buildApp(){

        if(scene==null){
            scene = new Scene();
            System.out.println("scene isn't specified");
        }
        if(renderConfig==null){
            System.out.println("renderConfing isn't specified, creating default preset");
            this.renderConfig= new RenderConfig();
        }
        Camera camera = new Camera(renderConfig, scene);
        Render render=new Render(renderConfig,camera);
        return  new App(renderConfig,camera,scene,render);
    }
}
