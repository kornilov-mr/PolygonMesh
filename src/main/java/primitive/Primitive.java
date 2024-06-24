package primitive;

import org.json.JSONObject;
import utils.line.Line;


public abstract class Primitive {
    public boolean selected=false;
    public abstract Point getIntersection(Line line);
    public abstract JSONObject objectInSavingFormat();
    public void setSelected(Boolean selected){
        this.selected = selected;
    }
    public void changeSelection(){
        this.selected= !selected;
    }
}
