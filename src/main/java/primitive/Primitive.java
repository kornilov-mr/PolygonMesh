package primitive;

import org.json.JSONObject;
import utils.line.Line;

import java.util.Map;


public abstract class Primitive {
    public boolean selected=false;
    public abstract Point getIntersection(Line line);
    public abstract JSONObject objectInSavingFormat(Map<Point, String> pointToIndexes);
    public void setSelected(Boolean selected){
        this.selected = selected;
    }
    public void changeSelection(){
        this.selected= !selected;
    }
}
