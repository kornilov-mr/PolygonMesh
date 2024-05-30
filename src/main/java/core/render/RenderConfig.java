package core.render;

public class RenderConfig {
    public final int[] resolution = new int[] {480,320};
    public final double widthPow = Math.PI/2;
    public final double heightPow=Math.PI/4;
    public final double pseudoRectangleWidth= Math.tan(widthPow/2)*2;
    public final double pseudoRectangleHeight= Math.tan(heightPow/2)*2;


}
