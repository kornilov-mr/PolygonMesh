package core.tools.commands.editingCommands;

import core.scene.Scene;
import core.tools.selecting.SelectedObjectManager;
import primitive.calculation.OrientedPoint;
import primitive.calculation.Point;

import java.util.Set;

public class RotatingSelectedObjects extends EditingCommand{
    private final double verticalAngle;
    private final double horizontalAngle;
    public RotatingSelectedObjects(double verticalAngle, double horizontalAngle) {
        this.verticalAngle = verticalAngle;
        this.horizontalAngle = horizontalAngle;
    }

    @Override
    public void execute(Scene scene, SelectedObjectManager selectedObjectManager) {
        if(selectedObjectManager.getSelected().isEmpty()){
            return;
        }
        Point center = selectedObjectManager.getCenterOfSelectedObjects();
        Set<Point> points = selectedObjectManager.getOnlyPointsFromSelection();
        for(Point point : points){
            OrientedPoint orientedPoint = new OrientedPoint(point,0,0,center);
            orientedPoint.moveRightOnSphere(horizontalAngle);
            orientedPoint.moveUpOnSphere(verticalAngle);
            addPrimitiveToChange(point);
        }
    }
}
