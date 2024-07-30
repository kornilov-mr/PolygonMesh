package core.tools.changes;

import core.tools.changes.PrimitivesSaves.*;
import primitive.Primitive;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Change {
    private boolean isEmpty;
    final Map<String, PrimitiveSave> changedPrimitives = new HashMap<>();
    final Map<String, Primitive> addedPrimitives = new HashMap<>();

    public Change(ArrayList<Primitive> changed, ArrayList<Primitive> added) {
        this.isEmpty=true;
        for(Primitive primitive: changed){
            PrimitiveSave save = new PrimitiveSave(primitive);
            changedPrimitives.put(primitive.getId(), save);
            this.isEmpty=false;
        }
        for(Primitive primitive: added){
            addedPrimitives.put(primitive.getId(), primitive);
            this.isEmpty=false;
        }
    }

    public boolean isEmpty() {
        return isEmpty;
    }
}
