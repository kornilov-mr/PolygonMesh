package core.tools.commands.creatingCommands.basic;

import core.scene.Scene;
import core.tools.selecting.SelectedObjectManager;
import primitive.calculation.Counter;

public class CounterCreatingCommand extends ObjectCreationCommand{
    private final Counter counter;
    public CounterCreatingCommand(Counter counter) {
        super(counter);
        this.counter=counter;
    }

    @Override
    public void execute(Scene scene, SelectedObjectManager selectedObjectManager) {
        scene.addCounter(counter);
    }
}
