package core.UI.visuals.elements.toolPanel.toolInterface.instructions.creatingInstructions;

import core.UI.visuals.elements.toolPanel.toolInterface.instructions.Instruction;
import core.UI.visuals.elements.toolPanel.toolInterface.popup.fields.TypeEnum;
import core.tools.commands.CommandManager;
import core.tools.commands.creatingCommands.figures.CreateBoxCommand;
import primitive.calculation.Point;

import java.awt.*;

public class BoxCreatingInstruction  extends Instruction {
    public BoxCreatingInstruction() {
        super("Create Box", new String[]{"center","width","horizontal angle","vertical angle","Color"}, new TypeEnum[]{TypeEnum.POINT_FIELD,TypeEnum.DOUBLE_FIELD,TypeEnum.DOUBLE_FIELD,TypeEnum.DOUBLE_FIELD,TypeEnum.COLOR_FIELD});
    }
    @Override
    protected void executeInstruction(CommandManager commandManager, Object[] values) {
        commandManager.executeCommand(new CreateBoxCommand((Point) values[0],(Double) values[1],(Double) values[2],(Double) values[3],(Color) values[4]));
    }
}
