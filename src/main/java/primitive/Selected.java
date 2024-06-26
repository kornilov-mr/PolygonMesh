package primitive;

public abstract class Selected {
    protected boolean selected = false;
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
