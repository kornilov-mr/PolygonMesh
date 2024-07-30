package core.tools.commands.changeParameters.point;

import core.scene.Scene;
import core.tools.commands.changeParameters.ParameterChangeCommand;
import core.tools.selecting.SelectedObjectManager;
import primitive.calculation.Point;

public class PointZChange extends ParameterChangeCommand {
    private final Point point;
    private final double z;
    public PointZChange(Point point, double z) {
        super(point);
        this.point=point;
        this.z=z;
    }

    @Override
    public void execute(Scene scene, SelectedObjectManager selectedObjectManager) {
        point.setZ(z);
    }
}