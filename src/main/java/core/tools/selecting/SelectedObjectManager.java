package core.tools.selecting;

import primitive.Primitive;

import java.util.HashMap;
import java.util.Map;

public class SelectedObjectManager {
    private final Map<Primitive,Boolean> selected = new HashMap<>();
    public void setAsSelected(Primitive primitive){
        primitive.setSelected(true);
        if(selected.containsKey(primitive)){
            selected.remove(primitive);
            selected.put(primitive,true);
        }else{
            selected.put(primitive,true);
        }
    }
    public void setAsUnSelected(Primitive primitive){
        primitive.setSelected(false);
        if(selected.containsKey(primitive)){
            selected.remove(primitive);
            selected.put(primitive,false);
        }else{
            selected.put(primitive,false);
        }
    }
    public void changeSelection(Primitive primitive){
        primitive.changeSelection();
        if(selected.containsKey(primitive)){
            boolean temp = selected.get(primitive);
            selected.remove(primitive);
            selected.put(primitive,!temp);
        }else{
            selected.put(primitive,true);

        }
    }
    public void changeSelectionWithRemove(Primitive primitive){
        if(selected.containsKey(primitive)){
            boolean temp = selected.get(primitive);
            clearSelection();
            selected.put(primitive,!temp);
            primitive.setSelected(!temp);
        }else{
            clearSelection();
            primitive.setSelected(true);
            selected.put(primitive,true);

        }
    }
    private void clearSelection(){
        for(Primitive primitive: selected.keySet()){
            primitive.setSelected(false);
        }
        selected.clear();
    }

}
