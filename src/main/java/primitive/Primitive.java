package primitive;

import org.json.JSONObject;
import utils.line.Line;


public interface Primitive {
    public Point getIntersection(Line line);
    public JSONObject objectInSavingFormat();
}
