package core.UI.visuals.elements.toolPanel.toolInterface.popup;

import core.UI.visuals.elements.toolPanel.toolInterface.popup.fields.ArgumentField;
import core.UI.visuals.elements.toolPanel.toolInterface.popup.fields.TypeEnum;
import core.tools.managers.FocusTabManager;

import javax.swing.*;
import java.lang.reflect.InvocationTargetException;

public class InstructionPopup {
    private final String title;
    private final String[] argumentNames;
    private final TypeEnum[] typeEnums;
    private final ArgumentField[] argumentFields;
    public InstructionPopup(String title, String[] argumentsNames, TypeEnum[] typeEnums){
        if(argumentsNames.length!=typeEnums.length){
            throw new RuntimeException("arguments' names array and types array have differentLength");
        }
        this.title = title;
        this.argumentNames = argumentsNames;
        this.typeEnums = typeEnums;
        this.argumentFields=new ArgumentField[argumentNames.length];
    }
    public JPanel createLabel(FocusTabManager focusTabManager){
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
        for(int i=0;i< argumentNames.length;i++){
            String name = argumentNames[i];
            Class<? extends ArgumentField> argumentClass = typeEnums[i].getFieldClass();
            try {
                ArgumentField argumentField = (ArgumentField) argumentClass.getConstructors()[0].newInstance(name);
                this.argumentFields[i]=argumentField;
                JPanel arguments = new JPanel();
//                arguments.add(new JLabel(name+":"));
                arguments.add(argumentField.createPanel(focusTabManager));
                panel.add(arguments);
            } catch (InstantiationException e) {
                throw new RuntimeException(e);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            } catch (InvocationTargetException e) {
                throw new RuntimeException(e);
            }
        }
        JPanel labelWrapper = new JPanel();
        labelWrapper.add(new JLabel(title));
        labelWrapper.add(panel);
        return labelWrapper;
    }
    public Object[] getValues(){
        Object[] values = new Object[argumentNames.length];
        for(int i=0;i<argumentNames.length;i++){
            if(typeEnums[i].getArgumentClass().isInstance(argumentFields[i].Field())){
                values[i]=argumentFields[i].Field();
            }else{
                throw new RuntimeException("types and labels' arguments don't match");
            }
        }
        return values;
    }

}
