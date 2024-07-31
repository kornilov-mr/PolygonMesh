package core.UI.visuals.elements.toolPanel.toolInterface.instructions;

import core.tools.commands.CommandManager;
import core.tools.managers.FocusTabManager;


public class InstructionManager {
    private final FocusTabManager focusTabManager;
    private final CommandManager commandManager;
    private final InstructionQueue instructionQueue;

    public InstructionManager(FocusTabManager focusTabManager, CommandManager commandManager, InstructionPanel instructionPanel) {
        this.focusTabManager = focusTabManager;
        this.commandManager = commandManager;
        this.instructionQueue= new InstructionQueue(instructionPanel);
    }
    public void queueInstruction(Instruction instruction){
        instructionQueue.addInstruction(instruction.createInstructionPanel(focusTabManager,commandManager,instructionQueue));
    }
}
