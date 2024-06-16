package core.render;

import core.camera.Camera;
import core.render.GPU.shaders.IntersectionShader;
import core.render.GPU.shaders.VectorCalculationShader;
import core.scene.Scene;
import utils.line.Line;

import java.awt.Color;
import java.io.File;

public class Render {

    private final RenderConfig renderConfig;
    private final Camera camera;
    private final VectorCalculationShader vectorCalculationShader;
    private final IntersectionShader intersectionShader;

    public Render(RenderConfig renderConfig, Camera camera) {
        this.renderConfig = renderConfig;
        this.camera=camera;
        this.vectorCalculationShader = new VectorCalculationShader(new File("src/main/java/core/render/GPU/kernels/calculatePointVectors.c"),camera,renderConfig);
        this.intersectionShader= new IntersectionShader(new File("src/main/java/core/render/GPU/kernels/getIntersection.c"),renderConfig,camera);
    }

    public Frame ProcessFrame(Scene scene){
        Line[][] pointLines = vectorCalculationShader.RayLines();
        Color[][] colors = intersectionShader.colors(pointLines,scene);
        return new Frame(colors);
    }
}
