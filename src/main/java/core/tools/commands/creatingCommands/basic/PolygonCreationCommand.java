package core.tools.commands.creatingCommands.basic;

import core.scene.Scene;
import core.tools.selecting.SelectedObjectManager;
import primitive.calculation.faces.Polygon;

import java.util.ArrayList;
import java.util.Arrays;

public class PolygonCreationCommand extends ObjectCreationCommand{
    private final Polygon polygon;
    public PolygonCreationCommand(Polygon polygon) {
        super(new ArrayList<>(Arrays.asList(polygon)));
        this.polygon=polygon;
    }

    @Override
    public void execute(Scene scene, SelectedObjectManager selectedObjectManager) {
        super.execute(scene,selectedObjectManager);
        scene.addPolygon(polygon);
    }
}