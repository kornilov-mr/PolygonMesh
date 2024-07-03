package primitive;

import org.json.JSONObject;
import primitive.calculation.Point;
import utils.line.Line;

import java.awt.*;


public abstract class Primitive extends Selectable {

    protected Primitive(Color color) {
        super(color);
    }
    public abstract Point getIntersection(Line line);
    public abstract JSONObject objectInSavingFormat();


}
