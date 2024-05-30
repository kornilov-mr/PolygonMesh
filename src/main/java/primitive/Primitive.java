package primitive;

import javafx.util.Pair;
import org.json.JSONObject;
import utils.line.Line;

import java.awt.*;

public interface Primitive {
    public Pair<Point, Color> getIntersection(Line line);
    public JSONObject objectInSavingFormat();
}
