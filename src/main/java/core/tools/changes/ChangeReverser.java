package core.tools.changes;

import core.scene.IDManager;
import core.scene.Scene;
import primitive.Primitive;

public class ChangeReverser {
    private final IDManager idManager;
    private final Scene scene;

    public ChangeReverser(IDManager idManager, Scene scene) {
        this.idManager = idManager;
        this.scene=scene;
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
        for(String id: change.addedPrimitives.keySet()){
            if(idManager.containPrimitiveById(id)){
                idManager.deleteByID(id);
            }else {
                scene.addPrimitive(change.addedPrimitives.get(id));
            }
        }
    }
}
