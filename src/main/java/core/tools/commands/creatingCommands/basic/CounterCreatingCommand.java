package core.tools.commands.creatingCommands.basic;

import core.scene.Scene;
import core.tools.selecting.SelectedObjectManager;
import primitive.calculation.Counter;

import java.util.ArrayList;
import java.util.Arrays;

public class CounterCreatingCommand extends ObjectCreationCommand{
    private final Counter counter;
    public CounterCreatingCommand(Counter counter) {
        super(new ArrayList<>(Arrays.asList(counter)));
        this.counter=counter;
    }

    @Override
    public void execute(Scene scene, SelectedObjectManager selectedObjectManager) {
        scene.addCounter(counter);
    }
}
