package core.UI.visuals.elements.toolPanel.toolInterface.instructions.creatingInstructions;

import core.UI.visuals.elements.toolPanel.toolInterface.instructions.Instruction;
import core.UI.visuals.elements.toolPanel.toolInterface.popup.fields.DoubleField;
import core.UI.visuals.elements.toolPanel.toolInterface.popup.fields.TypeEnum;
import core.tools.commands.CommandManager;
import core.tools.commands.creatingCommands.basic.PointCreationCommand;

public class PointCreatingInstruction extends Instruction {
    public PointCreatingInstruction() {
        super("Create Point", new String[]{"x","y","z"}, new TypeEnum[]{TypeEnum.DOUBLE_FIELD,TypeEnum.DOUBLE_FIELD,TypeEnum.DOUBLE_FIELD});
    }
    @Override
    protected void executeInstruction(CommandManager commandManager, Object[] values) {
        PointCreationCommand command = new PointCreationCommand((Double) values[0],(Double) values[1],(Double) values[2]);
        commandManager.executeCommand(command);
    }
}
