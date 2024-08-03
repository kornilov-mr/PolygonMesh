package core.UI.visuals.elements.toolPanel.toolInterface.instructions.editingInstruction;

import core.UI.visuals.elements.toolPanel.toolInterface.instructions.Instruction;
import core.UI.visuals.elements.toolPanel.toolInterface.popup.fields.TypeEnum;
import core.tools.commands.CommandManager;
import core.tools.commands.editingCommands.RotatingSelectedObjects;

import java.util.ArrayList;

public class RotateInstruction extends Instruction {
    public RotateInstruction() {
        super("rotate selected objects", new String[]{"vertical angle","horizontal angle"}, new TypeEnum[]{TypeEnum.ANGLE_FIELD,TypeEnum.ANGLE_FIELD});
    }

    @Override
    protected void executeInstruction(CommandManager commandManager, Object[] values) {
        commandManager.executeCommand(new RotatingSelectedObjects((Double)values[0],(Double) values[1]));
    }
}
