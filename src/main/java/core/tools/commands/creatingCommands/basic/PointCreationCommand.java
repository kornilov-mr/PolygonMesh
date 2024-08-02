package core.tools.commands.creatingCommands.basic;

import core.scene.Scene;
import core.tools.selecting.SelectedObjectManager;
import primitive.calculation.Point;

public class PointCreationCommand extends ObjectCreationCommand{
    private final Point point;
    public PointCreationCommand(Point point) {
        super(point);
        this.point=point;
    }
    @Override
    public void execute(Scene scene, SelectedObjectManager selectedObjectManager) {
        super.execute(scene,selectedObjectManager);
        scene.addPoint(point);
    }
}
