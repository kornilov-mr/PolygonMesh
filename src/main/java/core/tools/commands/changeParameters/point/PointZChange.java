package core.tools.commands.changeParameters.point;

import core.tools.commands.changeParameters.ParameterChangeCommand;
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
    public void execute() {
        point.setZ(z);
    }
}