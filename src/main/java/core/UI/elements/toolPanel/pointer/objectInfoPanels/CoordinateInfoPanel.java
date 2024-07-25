package core.UI.elements.toolPanel.pointer.objectInfoPanels;

import core.UI.managers.FocusTabManager;
import core.tools.commands.CommandManager;
import core.tools.commands.changeParameters.point.PointXChange;
import core.tools.commands.changeParameters.point.PointYChange;
import core.tools.commands.changeParameters.point.PointZChange;
import primitive.calculation.Point;
import utils.Calculation;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Objects;
import java.awt.event.ActionEvent;


public class CoordinateInfoPanel extends ObjectInfoPanel{
    private JTextField textArea;
    private final String title;
    private final Point point;
    private final FocusTabManager focusTabManager;

    public CoordinateInfoPanel(ArrayList<ObjectInfoPanel> infoPanels,Point point, String title, double startValue,FocusTabManager focusTabManager, CommandManager commandManager){
        super(infoPanels);
        this.title=title;
        this.point=point;
        this.focusTabManager =focusTabManager;
        super.jPanel = createJPanel(startValue,commandManager);
    }
    private JPanel createJPanel(double startValue, CommandManager commandManager){
        JPanel jPanel = new JPanel();
        jPanel.add(new JLabel(title+":"));
        textArea = new JTextField(String.valueOf(Calculation.round(startValue,4)));
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
                if(Objects.equals(title,"x")){
                    commandManager.executeCommand(new PointXChange(point,Double.parseDouble(textArea.getText())));
                }
                if(Objects.equals(title,"y")){
                    commandManager.executeCommand(new PointYChange(point,Double.parseDouble(textArea.getText())));
                }
                if(Objects.equals(title,"z")){
                    commandManager.executeCommand(new PointZChange(point,Double.parseDouble(textArea.getText())));
                }

            }
        };

        textArea.addActionListener(action);
        jPanel.add(textArea);
        return jPanel;
    }
    public void update(){
        if(Objects.equals(title,"x")){
            textArea.setText(String.valueOf(Calculation.round(point.getX(),4)));
        }else if(Objects.equals(title,"y")){
            textArea.setText(String.valueOf(Calculation.round(point.getY(),4)));
        }else if(Objects.equals(title,"z")){
            textArea.setText(String.valueOf(Calculation.round(point.getZ(),4)));
        }
        jPanel.revalidate();
        jPanel.repaint();
    }
}
