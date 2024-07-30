package core.tools.selecting;

import primitive.calculation.Point;
import primitive.Primitive;

import java.util.*;

public class SelectedObjectManager {
    private final Set<Primitive> selected = new HashSet<>();
    public void setAsSelected(Primitive primitive){
        primitive.setSelected(true);
        selected.add(primitive);
    }
    public void setAsUnSelected(Primitive primitive){
        primitive.setSelected(false);
        selected.remove(primitive);
    }
    public void changeSelection(Primitive primitive){
        primitive.changeSelection();
        if(selected.contains(primitive)){
            selected.remove(primitive);
        }else{
            selected.add(primitive);

        }
    }
    public void changeSelectionWithRemove(Primitive primitive){
        if(selected.contains(primitive)){
            clearSelection();
        }else{
            clearSelection();
            setAsSelected(primitive);

        }
    }
    public void clearSelection(){
        Iterator<Primitive> it = selected.iterator();
        while(it.hasNext()){
            it.next().setSelected(false);
        }
        selected.clear();
    }

    public Set<Primitive> getSelected() {
        return selected;
    }
    public boolean isSelectedOnlyOnePoint(){
        if(selected.size()==1){
            Iterator<Primitive> it = selected.iterator();
            if(it.next() instanceof Point){
                return true;
            }
        }
        return false;
    }
    public boolean isSelectedOnlyPoints(){
        for(Primitive primitive: selected){
            if(!(primitive instanceof Point)){
                return false;
            }
        }
        return true;
    }
}
