package core.render.GPU.shaders;

import core.camera.Camera;
import core.render.RenderConfig;

import java.io.File;

public class Shaders {
    public final ShaderRunner allPrimitiveShader;
    public final ShaderRunner onlyPolygonShader;

    public Shaders(RenderConfig renderConfig, Camera camera) {
        this.allPrimitiveShader= new AllPrimitiveShader(new File("src/main/java/core/render/GPU/kernels/allPrimitiveShader.c"),renderConfig,camera);
        this.onlyPolygonShader= new OnlyPolygonShader(new File("src/main/java/core/render/GPU/kernels/onlyPolygonShader.c"),renderConfig,camera);

    }
}
