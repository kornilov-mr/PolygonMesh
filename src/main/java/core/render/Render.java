package core.render;

import core.camera.Camera;
import core.render.GPU.shaders.AllPrimitiveShader;
import core.scene.Scene;

import java.io.File;

public class Render {

    private final RenderConfig renderConfig;
    private final Camera camera;
    private final AllPrimitiveShader intersectionShader;

    public Render(RenderConfig renderConfig, Camera camera) {
        this.renderConfig = renderConfig;
        this.camera=camera;
        this.intersectionShader= new AllPrimitiveShader(new File("src/main/java/core/render/GPU/kernels/allPrimitiveShader.c"),renderConfig,camera);
    }

    public Frame ProcessFrame(Scene scene){
        int[] colors = intersectionShader.run(scene);
        return new Frame(colors);
    }
}
