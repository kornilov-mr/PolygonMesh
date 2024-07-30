package core.render.GPU.shaders;

public enum ShaderEnum {
    OnlyPolygonShader(core.render.GPU.shaders.OnlyPolygonShader.class),
    AllPrimitiveShader(core.render.GPU.shaders.OnlyPolygonShader.class);
    ShaderEnum(Class<? extends ShaderRunner> shaderRunnerClass){
        this.shaderRunnerClass=shaderRunnerClass;
    }
    private  final Class<? extends ShaderRunner> shaderRunnerClass;

    public Class<? extends ShaderRunner> getShaderRunnerClass() {
        return shaderRunnerClass;
    }
}
