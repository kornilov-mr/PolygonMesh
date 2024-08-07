package core.tools.commands.changeParameters.point;

import core.scene.Scene;
import core.tools.commands.changeParameters.ParameterChangeCommand;
import core.tools.selecting.SelectedObjectManager;
import primitive.calculation.Point;

public class PointXChange extends ParameterChangeCommand {
    private final Point point;
    private final double x;
    public PointXChange(Point point, double x) {
        super(point);
        this.point=point;
        this.x=x;
    }

    @Override
    public void execute(Scene scene, SelectedObjectManager selectedObjectManager) {
        point.setX(x);
    }
}
