package primitive;

import org.json.JSONObject;
import primitive.calculation.Point;
import utils.line.Line;

import java.awt.*;


public abstract class Primitive extends Selectable  {
    protected double size;
    protected Primitive(Color color, double size) {
        super(color);
        this.size=size;
    }
    public abstract Point getIntersection(Line line);
    public abstract JSONObject objectInSavingFormat();

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }
}
