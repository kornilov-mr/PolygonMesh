package core.tools.changes.PrimitivesSaves;

import primitive.Primitive;
import primitive.calculation.Counter;
import primitive.calculation.Point;
import primitive.calculation.faces.Polygon;

public class PrimitiveSave implements Saves<Primitive>{
    //Class, which saves parameters of Primitive and with ability to reapply with params
    private final Saves<? extends Primitive> save;

    public PrimitiveSave(Primitive primitive) {
        if (primitive instanceof Counter){
            save = new CounterSave((Counter) primitive);
        }else if (primitive instanceof Point){
            save = new PointSave((Point) primitive);
        }else if (primitive instanceof Polygon){
            save = new PolygonSave((Polygon) primitive);
        }else{
            throw new RuntimeException("invalid type of Primitive");
        }

    }

    @Override
    public void applySave(Primitive primitive) throws Exception {
        if (primitive instanceof Counter){
            ((Saves<Counter>)save).applySave((Counter)primitive);

        }else if (primitive instanceof Point){
            ((Saves<Point>)save).applySave((Point)primitive);

        }else if (primitive instanceof Polygon){
            ((Saves<Polygon>)save).applySave((Polygon)primitive);
        }else{
            throw new RuntimeException("invalid type of Primitive");
        }
    }
}
