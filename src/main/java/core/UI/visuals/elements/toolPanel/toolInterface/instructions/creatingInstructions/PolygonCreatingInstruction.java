package core.UI.visuals.elements.toolPanel.toolInterface.instructions.creatingInstructions;

import core.UI.visuals.elements.toolPanel.toolInterface.instructions.Instruction;
import core.UI.visuals.elements.toolPanel.toolInterface.popup.fields.TypeEnum;
import core.tools.commands.CommandManager;
import core.tools.commands.creatingCommands.basic.PolygonCreationCommand;
import primitive.calculation.faces.Polygon;

public class PolygonCreatingInstruction extends Instruction {
    public PolygonCreatingInstruction() {
        super("Create polygon", new String[]{"new polygon"}, new TypeEnum[]{TypeEnum.POLYGON_FIELD});
    }
    @Override
    protected void executeInstruction(CommandManager commandManager, Object[] values) {
        commandManager.executeCommand(new PolygonCreationCommand((Polygon) values[0]));
    }
}
