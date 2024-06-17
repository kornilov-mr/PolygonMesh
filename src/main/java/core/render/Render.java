package core.render;

import core.camera.Camera;
import core.render.GPU.shaders.IntersectionShader;
import core.scene.Scene;
import utils.line.Line;

import java.awt.Color;
import java.io.File;

public class Render {

    private final RenderConfig renderConfig;
    private final Camera camera;
    private final IntersectionShader intersectionShader;

    public Render(RenderConfig renderConfig, Camera camera) {
        this.renderConfig = renderConfig;
        this.camera=camera;
        this.intersectionShader= new IntersectionShader(new File("src/main/java/core/render/GPU/kernels/getIntersection.c"),renderConfig,camera);
    }

    public Frame ProcessFrame(Scene scene){
        int[] colors = intersectionShader.colors(scene);
        return new Frame(colors);
    }
}
