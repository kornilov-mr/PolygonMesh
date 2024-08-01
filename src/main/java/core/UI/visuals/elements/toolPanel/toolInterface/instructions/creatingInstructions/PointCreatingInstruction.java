package core.UI.visuals.elements.toolPanel.toolInterface.instructions.creatingInstructions;

import core.UI.visuals.elements.toolPanel.toolInterface.instructions.Instruction;
import core.UI.visuals.elements.toolPanel.toolInterface.popup.fields.TypeEnum;
import core.tools.commands.CommandManager;
import core.tools.commands.creatingCommands.basic.PointCreationCommand;
import primitive.calculation.Point;

public class PointCreatingInstruction extends Instruction {
    public PointCreatingInstruction() {
        super("Create Point", new String[]{"new point"}, new TypeEnum[]{TypeEnum.POINT_FIELD});
    }
    @Override
    protected void executeInstruction(CommandManager commandManager, Object[] values) {
        PointCreationCommand command = new PointCreationCommand((Point) values[0]);
        commandManager.executeCommand(command);
    }
}
