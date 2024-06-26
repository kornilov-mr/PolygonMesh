package primitive;

import org.json.JSONObject;
import primitive.calculation.Point;
import utils.line.Line;

import java.util.Map;


public abstract class Primitive extends Selected{
    public abstract Point getIntersection(Line line);
    public abstract JSONObject objectInSavingFormat(Map<Point, String> pointToIndexes);

}
