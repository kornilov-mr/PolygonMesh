package primitive;

import java.awt.*;

public abstract class Paintable  {
    protected Color color;
    protected Paintable(Color color) {
        this.color = color;
    }
    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void setRed(int red){
        this.color = new Color(red, color.getGreen(), color.getBlue());
    }
    public void setGreen(int green){
        this.color = new Color(color.getRed(),green, color.getBlue());
    }
    public void setBlue(int blue){
        this.color = new Color(color.getRed(), color.getGreen(),blue);
    }

}
