package core.render.GPU.shaders;

import core.camera.Camera;
import core.render.RenderConfig;

import java.io.File;
import java.util.HashMap;
import java.util.Map;


public class Shaders {
    //class to change shaders on fly
    private final ShaderRunner mainShader;
    private final Map<ShaderEnum, ShaderRunner> shaderRunners = new HashMap<>();

    public Shaders(RenderConfig renderConfig, Camera camera) {
        ShaderRunner allPrimitiveShader= new AllPrimitiveShader(new File("src/main/java/core/render/GPU/kernels/allPrimitiveShader.c"),renderConfig,camera);
        ShaderRunner onlyPolygonShader= new OnlyPolygonShader(new File("src/main/java/core/render/GPU/kernels/onlyPolygonShader.c"),renderConfig,camera);
        this.mainShader=allPrimitiveShader;
        shaderRunners.put(ShaderEnum.AllPrimitiveShader,allPrimitiveShader);
        shaderRunners.put(ShaderEnum.OnlyPolygonShader, onlyPolygonShader);
    }

    public ShaderRunner getMainShader() {
        return mainShader;
    }

    public Map<ShaderEnum, ShaderRunner> getShaderRunners() {
        return shaderRunners;
    }
}
