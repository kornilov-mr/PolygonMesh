package core.tools.commands.changeParameters.point;

import core.scene.Scene;
import core.tools.commands.changeParameters.ParameterChangeCommand;
import core.tools.selecting.SelectedObjectManager;
import primitive.calculation.Point;

public class PointMoveChange extends ParameterChangeCommand {
    public PointMoveChange(Point point) {
        super(point);
    }

    @Override
    public void execute(Scene scene, SelectedObjectManager selectedObjectManager) {

    }
}
