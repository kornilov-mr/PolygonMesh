package core.tools.changes;

import core.tools.changes.PrimitivesSaves.*;
import primitive.Primitive;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Change {
    final Map<String, PrimitiveSave> changedPrimitives = new HashMap<>();
    public Change(ArrayList<Primitive> primitives) {
        for(Primitive primitive: primitives){
            PrimitiveSave save = new PrimitiveSave(primitive);
            changedPrimitives.put(primitive.getId(), save);
        }
    }
}
