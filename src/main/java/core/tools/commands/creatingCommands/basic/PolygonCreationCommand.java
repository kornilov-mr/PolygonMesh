package core.tools.commands.creatingCommands.basic;

import core.scene.Scene;
import core.tools.selecting.SelectedObjectManager;
import primitive.calculation.faces.Polygon;

public class PolygonCreationCommand extends ObjectCreationCommand{
    private final Polygon polygon;
    public PolygonCreationCommand(Polygon polygon) {
        super(polygon);
        this.polygon=polygon;
    }

    @Override
    public void execute(Scene scene, SelectedObjectManager selectedObjectManager) {
        scene.addPolygon(polygon);
    }
}