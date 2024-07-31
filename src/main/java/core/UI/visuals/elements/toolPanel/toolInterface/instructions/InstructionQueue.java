package core.UI.visuals.elements.toolPanel.toolInterface.instructions;

import core.tools.commands.CommandManager;
import core.tools.managers.FocusTabManager;

import javax.swing.*;
import java.util.LinkedList;
import java.util.Queue;

public class InstructionQueue {
    private final InstructionPanel instructionPanel;
    private final Queue<JPanel> queue = new LinkedList<>();

    public InstructionQueue(InstructionPanel instructionPanel, FocusTabManager focusTabManager, CommandManager commandManager) {
        this.instructionPanel = instructionPanel;
    }

    protected void addInstruction(JPanel jPanel){
        queue.add(jPanel);
        if(!instructionPanel.isLoad()){
            instructionPanel.loadInstructionPanel(jPanel);
        }
    }
    protected void skipInstructionFirst(){
        queue.poll();
        if(queue.peek()!=null){
            instructionPanel.loadInstructionPanel(queue.peek());
        }else{
            instructionPanel.unload();
        }

    }
}
