package core.tools.commands.editingCommands;

import core.scene.Scene;
import core.tools.changes.Change;
import core.tools.commands.Command;
import core.tools.selecting.SelectedObjectManager;
import primitive.Primitive;
import primitive.calculation.Counter;
import primitive.calculation.Point;
import primitive.calculation.faces.Polygon;

import java.util.ArrayList;

public class EditingCommand implements Command {
    private final  ArrayList<Primitive> primitives = new ArrayList<>();
    public EditingCommand() {
    }
    protected final void addPrimitiveToChange(Primitive primitive){
        primitives.add(primitive);
    }

    @Override
    public void execute(Scene scene, SelectedObjectManager selectedObjectManager) {

    }

    @Override
    public final Change getChange() {
        return new Change(new ArrayList<>(primitives),new ArrayList<>());
    }
}
