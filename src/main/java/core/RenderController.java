package core;

import core.render.Frame;
import core.render.Render;
import core.render.RenderConfig;
import core.scene.Scene;
import core.show.Window;


public class RenderController {

    private final RenderConfig renderConfig;
    private final Render render;
    private final Window window;
    private Scene scene;

    public RenderController(){
        this(new RenderConfig());
    }
    public RenderController(RenderConfig renderConfig) {
        this.renderConfig = renderConfig;
        this.render=new Render(renderConfig);
        this.window=new Window(renderConfig.resolution[0],renderConfig.resolution[1]);
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
        }
    }
}
