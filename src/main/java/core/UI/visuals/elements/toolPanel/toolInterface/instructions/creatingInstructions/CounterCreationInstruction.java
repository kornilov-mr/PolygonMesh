package core.UI.visuals.elements.toolPanel.toolInterface.instructions.creatingInstructions;

import core.UI.visuals.elements.toolPanel.toolInterface.instructions.Instruction;
import core.UI.visuals.elements.toolPanel.toolInterface.popup.fields.CounterField;
import core.UI.visuals.elements.toolPanel.toolInterface.popup.fields.TypeEnum;
import core.tools.commands.CommandManager;
import core.tools.commands.creatingCommands.basic.CounterCreatingCommand;
import primitive.calculation.Counter;

public class CounterCreationInstruction extends Instruction {
    public CounterCreationInstruction() {
        super("Create Counter", new String[]{"Counter"}, new TypeEnum[]{TypeEnum.COUNTER_FIELD});
    }
    @Override
    protected void executeInstruction(CommandManager commandManager, Object[] values) {
        commandManager.executeCommand(new CounterCreatingCommand((Counter) values[0]));
    }
}

