package core.UI.elements.toolPanel.pointer.objectInfoPanels;

import core.UI.managers.FocusTabManager;
import core.tools.commands.CommandManager;
import core.tools.commands.changeParameters.color.ColorBChange;
import core.tools.commands.changeParameters.color.ColorGChange;
import core.tools.commands.changeParameters.color.ColorRChange;
import primitive.Primitive;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Objects;

public class ColorToneInfoPanel extends ObjectInfoPanel{
    private Color color;
    private final String title;
    private JTextField textArea;
    private final FocusTabManager focusTabManager;
    private final Primitive primitive;
    public ColorToneInfoPanel(ArrayList<ObjectInfoPanel> infoPanels, Color color, String title, int startValue, FocusTabManager focusTabManager, Primitive primitive, CommandManager commandManager) {
        super(infoPanels);
        this.color=color;
        this.title=title;
        this.primitive=primitive;
        this.focusTabManager=focusTabManager;
        this.jPanel = createJPanel(startValue,commandManager);
    }
    private JPanel createJPanel(int startValue,CommandManager commandManager){
        JPanel jPanel = new JPanel();
        jPanel.add(new JLabel(title+":"));
        textArea = new JTextField(String.valueOf(startValue));
        textArea.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent ke) {
                String value = textArea.getText();
                int l = value.length();
                if (ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9'||ke.getKeyChar()==KeyEvent.VK_BACK_SPACE|| ke.getKeyChar()==KeyEvent.VK_MINUS) {
                    textArea.setEditable(true);
                } else {
                    textArea.setEditable(false);
                }
            }
        });
        Action action = new AbstractAction()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                focusTabManager.setFocusToMainWindow();
                int intValue = Integer.parseInt(textArea.getText());
                if(intValue> 255){
                    return;
                }
                if(Objects.equals(title,"R")){
                    commandManager.executeCommand( new ColorRChange(primitive,Integer.parseInt(textArea.getText())));
                }
                if(Objects.equals(title,"G")){
                    commandManager.executeCommand( new ColorGChange(primitive,Integer.parseInt(textArea.getText())));
                }
                if(Objects.equals(title,"B")){
                    commandManager.executeCommand( new ColorBChange(primitive,Integer.parseInt(textArea.getText())));
                }

            }
        };

        textArea.addActionListener(action);
        jPanel.add(textArea);
        return jPanel;
    }
}
