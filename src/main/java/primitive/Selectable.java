package primitive;

import java.awt.*;

public abstract class Selectable extends Paintable {
    protected boolean selected = false;

    protected Selectable(Color color) {
        super(color);
    }

    public void setSelected(Boolean selected){
        this.selected = selected;
    }
    public void changeSelection(){
        this.selected= !selected;
    }
    public boolean isSelected(){
        return selected;
    }
}
