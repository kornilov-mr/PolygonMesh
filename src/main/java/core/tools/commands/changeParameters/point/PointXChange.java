package core.tools.commands.changeParameters.point;

import core.tools.commands.changeParameters.ParameterChangeCommand;
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
    public void execute() {
        point.setX(x);
    }
}
