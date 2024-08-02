package core.tools.changes;

import core.tools.changes.PrimitivesSaves.*;
import primitive.Primitive;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Change {
    private boolean isEmpty;
    final Map<String, PrimitiveSave> changedPrimitives;
    final Map<String, Primitive> addedPrimitives;

    public Change(Change currentChange, Change lastChange){
        this(lastChange.changedPrimitives,currentChange.addedPrimitives);
    }
    private Change(Map<String, PrimitiveSave> changedPrimitives, Map<String, Primitive> addedPrimitives){
        this.changedPrimitives=changedPrimitives;
        this.addedPrimitives=addedPrimitives;
    }

    public Change(ArrayList<Primitive> changed, ArrayList<Primitive> added) {
        this.isEmpty=true;
        this.changedPrimitives= new HashMap<>();
        this.addedPrimitives= new HashMap<>();
        for(Primitive primitive: changed){
            PrimitiveSave save = new PrimitiveSave(primitive);
            changedPrimitives.put(primitive.getId(), save);
            this.isEmpty=false;
        }
        for(Primitive primitive: added){
            System.out.println(primitive.getId());
            addedPrimitives.put(primitive.getId(), primitive);
            this.isEmpty=false;
        }
    }

    public boolean isEmpty() {
        return isEmpty;
    }
}
