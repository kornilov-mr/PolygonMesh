package core.tools.commands.creatingCommands.basic;

import core.scene.Scene;
import core.tools.changes.Change;
import core.tools.commands.Command;
import core.tools.selecting.SelectedObjectManager;
import primitive.Primitive;

import java.util.ArrayList;
import java.util.Arrays;

public abstract class ObjectCreationCommand implements Command {
    private final Primitive primitive;
    public ObjectCreationCommand(Primitive primitive) {
        this.primitive= primitive;
    }

    @Override
    public void execute(Scene scene, SelectedObjectManager selectedObjectManager) {

    }

    @Override
    public final Change getChange() {
        return new Change(new ArrayList<>(),new ArrayList<>(Arrays.asList(primitive)));
    }
}
