package core.UI.visuals.elements.toolPanel.toolInterface.instructions;

import core.UI.visuals.elements.toolPanel.toolInterface.popup.InstructionPopup;
import core.UI.visuals.elements.toolPanel.toolInterface.popup.fields.TypeEnum;
import core.tools.commands.CommandManager;
import core.tools.managers.FocusTabManager;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public abstract class Instruction {
    private final InstructionPopup instructionPopup;
    public Instruction(String title, String[] argumentsNames, TypeEnum[] typeEnums) {
        this.instructionPopup= new InstructionPopup(title,argumentsNames,typeEnums);
    }
    public JPanel createInstructionPanel(FocusTabManager focusTabManager, CommandManager commandManager, InstructionQueue instructionQueue){
        JPanel panel = new JPanel();
        panel.add(instructionPopup.createLabel(focusTabManager));

        JButton button = new JButton("start");
        button.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                executeInstruction(commandManager, instructionPopup.getValues());
                instructionQueue.skipInstructionFirst();
                focusTabManager.setFocusToMainWindow();
            }
        });
        panel.add(button);
        return panel;
    }
    protected abstract void executeInstruction(CommandManager commandManager, Object[] values);
}
