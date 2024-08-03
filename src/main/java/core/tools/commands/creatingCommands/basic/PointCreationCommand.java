package core.tools.commands.creatingCommands.basic;

import core.scene.Scene;
import core.tools.selecting.SelectedObjectManager;
import primitive.calculation.Point;

import java.util.ArrayList;
import java.util.Arrays;

public class PointCreationCommand extends ObjectCreationCommand{
    private final Point point;
    public PointCreationCommand(Point point) {
        super(new ArrayList<>(Arrays.asList(point)));
        this.point=point;
    }
    @Override
    public void execute(Scene scene, SelectedObjectManager selectedObjectManager) {
        scene.addPoint(point);
        super.execute(scene,selectedObjectManager);
    }
}
