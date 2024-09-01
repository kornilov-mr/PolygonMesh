package core.render;

import core.camera.Camera;
import core.render.GPU.shaders.ShaderEnum;
import core.render.GPU.shaders.ShaderRunner;
import core.render.GPU.shaders.Shaders;

public abstract class RenderSwitcher {
    //Abstract class, which switches shaders for rendering
    protected ShaderRunner currentShader;
    private final Shaders shaders;

    protected RenderSwitcher(RenderConfig renderConfig, Camera camera) {
        this.shaders = new Shaders(renderConfig,camera);
        currentShader=shaders.getMainShader();
    }
    public final void switchShader(ShaderEnum shaderEnum){
        if(shaderEnum.getShaderRunnerClass().isInstance(currentShader)){
            currentShader=shaders.getMainShader();
        }else{
            currentShader=shaders.getShaderRunners().get(shaderEnum);
        }
    }
    public final void setShader(ShaderEnum shaderEnum){
        currentShader=shaders.getShaderRunners().get(shaderEnum);
    }
    public final void unSetShader(){
        currentShader=shaders.getMainShader();
    }
}
