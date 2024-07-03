package core.render;

import core.camera.Camera;
import core.render.GPU.shaders.AllPrimitiveShader;
import core.render.GPU.shaders.OnlyPolygonShader;
import core.render.GPU.shaders.ShaderRunner;
import core.render.GPU.shaders.Shaders;
import core.scene.Scene;

import java.io.File;

public class Render {

    private final RenderConfig renderConfig;
    private final Camera camera;
    private final Shaders shaders;
    private ShaderRunner currentShaderRunner;

    public Render(RenderConfig renderConfig, Camera camera) {
        this.renderConfig = renderConfig;
        this.camera=camera;
        this.shaders = new Shaders(renderConfig,camera);
        this.currentShaderRunner = shaders.allPrimitiveShader;
    }

    public Frame ProcessFrame(Scene scene){
        int[] colors = currentShaderRunner.run(scene);
        return new Frame(colors);
    }
    public void useAllPrimitiveShader(){
        this.currentShaderRunner = shaders.allPrimitiveShader;
    }
    public void useOnlyPolygonShader(){
        this.currentShaderRunner = shaders.onlyPolygonShader;
    }
}
