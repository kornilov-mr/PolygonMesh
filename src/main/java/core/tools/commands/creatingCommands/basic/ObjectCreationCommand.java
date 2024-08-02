package core.tools.commands.creatingCommands.basic;

import core.scene.Scene;
import core.tools.changes.Change;
import core.tools.commands.Command;
import core.tools.selecting.SelectedObjectManager;
import primitive.Primitive;
import primitive.calculation.Counter;
import primitive.calculation.Point;
import primitive.calculation.faces.Polygon;

import java.util.ArrayList;
import java.util.Arrays;

public abstract class ObjectCreationCommand implements Command {
    private final Primitive primitive;
    private final  ArrayList<Primitive> primitives = new ArrayList<>();;
    public ObjectCreationCommand(Primitive primitive) {
        this.primitive= primitive;
    }

    @Override
    public void execute(Scene scene, SelectedObjectManager selectedObjectManager) {
        if(primitive instanceof Polygon){
            Polygon polygon = (Polygon) primitive;
            if(!scene.getCounters().contains(polygon.getCounterA())){
                primitives.add(polygon.getCounterA());
            }
            if(!scene.getCounters().contains(polygon.getCounterB())){
                primitives.add(polygon.getCounterB());
            }
            if(!scene.getCounters().contains(polygon.getCounterC())){
                primitives.add(polygon.getCounterC());
            }
            if(!scene.getPoints().contains(polygon.getPointA())){
                primitives.add(polygon.getPointA());
            }
            if(!scene.getPoints().contains(polygon.getPointB())){
                primitives.add(polygon.getPointB());
            }
            if(!scene.getPoints().contains(polygon.getPointC())){
                primitives.add(polygon.getPointC());
            }
            primitives.add(polygon);
        }else if(primitive instanceof Counter){
            Counter counter = (Counter) primitive;
            if(!scene.getPoints().contains(counter.getPointB())){
                primitives.add(counter.getPointB());
            }
            if(!scene.getPoints().contains(counter.getPointA())){
                primitives.add(counter.getPointA());
            }
            primitives.add(counter);
        }else if(primitive instanceof Point){
            Point point = (Point) primitive;
            if(!scene.getPoints().contains(point)){
                primitives.add(point);
            }
        }
    }

    @Override
    public final Change getChange() {
        return new Change(new ArrayList<>(),new ArrayList<>(primitives));
    }
}
