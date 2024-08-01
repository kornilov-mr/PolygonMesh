package core.tools.commands.creatingCommands.polygon;

import core.scene.Scene;
import core.tools.changes.Change;
import core.tools.commands.Command;
import core.tools.selecting.SelectedObjectManager;
import primitive.Primitive;
import primitive.calculation.Point;
import primitive.calculation.faces.Polygon;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class CreatePolygonByPointsSelected implements Command {
    private final Set<Primitive> primitives = new HashSet<>();
    public CreatePolygonByPointsSelected() {
    }

    @Override
    public void execute(Scene scene, SelectedObjectManager selectedObjectManager) {
        if(!selectedObjectManager.isSelectedOnlyPoints()){
            return;
        }
        if(selectedObjectManager.getSelected().size()!=3){
            return;
        }
        ArrayList<Point> points = new ArrayList<>();
        for(Primitive primitive : selectedObjectManager.getSelected()){
            points.add((Point) primitive);
        }
        Polygon polygon= new Polygon(points.get(0),points.get(1),points.get(2));
        if(!scene.getCounters().contains(polygon.getCounterA())){
            primitives.add(polygon.getCounterA());
        }
        if(!scene.getCounters().contains(polygon.getCounterB())){
            primitives.add(polygon.getCounterB());
        }
        if(!scene.getCounters().contains(polygon.getCounterC())){
            primitives.add(polygon.getCounterC());
        }
        scene.addPolygon(polygon);
        primitives.add(polygon);
    }
    @Override
    public final Change getChange() {
        return new Change(new ArrayList<>(),new ArrayList<>(primitives));
    }

}
