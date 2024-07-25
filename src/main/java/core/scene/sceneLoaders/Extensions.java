package core.scene.sceneLoaders;

public enum Extensions {
    JSON(".Json");
    private Extensions(String realExtension){
        this.realExtension =realExtension;
    }
    private final String realExtension;

    public String getRealExtension() {
        return realExtension;
    }
}
