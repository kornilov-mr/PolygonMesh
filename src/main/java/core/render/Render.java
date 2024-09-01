package core.render;

import core.camera.Camera;
import core.scene.Scene;


public class Render extends RenderSwitcher {
    //Class which returns Frame based on Render selected (selection happens in RenderSwitcher)

    private final RenderConfig renderConfig;
    private final Camera camera;

    public Render(RenderConfig renderConfig, Camera camera) {
        super(renderConfig,camera);
        this.renderConfig = renderConfig;
        this.camera=camera;
    }

    public Frame ProcessFrame(Scene scene){
        int[] colors = currentShader.run(scene);
        return new Frame(colors);
    }
}
