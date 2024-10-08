package core.render;

public class RenderConfig {
    public final int[] resolution = new int[] {800,600};
    public final int pixelCount= resolution[1]*resolution[0];

    public final double widthPow = Math.PI/3*2;
    public final double heightPow=Math.PI/2;
    public final double pseudoRectangleWidth= Math.tan(widthPow/2)*2;
    public final double pseudoRectangleHeight= Math.tan(heightPow/2)*2;


}
