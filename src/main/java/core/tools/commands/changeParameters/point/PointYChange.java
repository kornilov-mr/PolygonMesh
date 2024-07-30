package core.tools.commands.changeParameters.point;

import core.scene.Scene;
import core.tools.commands.changeParameters.ParameterChangeCommand;
import core.tools.selecting.SelectedObjectManager;
import primitive.calculation.Point;

public class PointYChange extends ParameterChangeCommand {
    private final Point point;
    private final double y;
    public PointYChange(Point point, double y) {
        super(point);
        this.point=point;
        this.y=y;
    }

    @Override
    public void execute(Scene scene, SelectedObjectManager selectedObjectManager) {
        point.setY(y);
    }
}
