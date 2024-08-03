package core.tools.commands.editingCommands;

import core.scene.Scene;
import core.tools.selecting.SelectedObjectManager;
import primitive.calculation.OrientedPoint;
import primitive.calculation.Point;

import java.util.Set;

public class RotatingSelectedObjects extends EditingCommand{
    private final double tiltingAngle;
    private final double rotatingAngle;
    private final double yawingAngle;
    public RotatingSelectedObjects(double tiltingAngle, double rotatingAngle, double yawingAngle) {
        this.tiltingAngle = tiltingAngle;
        this.rotatingAngle = rotatingAngle;
        this.yawingAngle = yawingAngle;
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
            orientedPoint.setRotation(rotatingAngle,tiltingAngle,yawingAngle);
            addPrimitiveToChange(point);
        }
    }
}
