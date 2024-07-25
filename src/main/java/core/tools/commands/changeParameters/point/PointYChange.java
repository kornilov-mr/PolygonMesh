package core.tools.commands.changeParameters.point;

import core.tools.commands.changeParameters.ParameterChangeCommand;
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
    public void execute() {
        point.setY(y);
    }
}
