package core.tools.changes.PrimitivesSaves;

import primitive.Primitive;

public interface Saves<T extends Primitive> {
    void applySave(T primitive) throws Exception;
}
