package core.tools.changes;

import core.scene.IDManager;
import primitive.Primitive;

public class ChangeReverser {
    private final IDManager idManager;

    public ChangeReverser(IDManager idManager) {
        this.idManager = idManager;
    }
    public void ReverseChange(Change change){
        for(String id: change.changedPrimitives.keySet()){
            Primitive primitive = idManager.getPrimitiveByID(id);
            try {
                change.changedPrimitives.get(id).applySave(primitive);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}
