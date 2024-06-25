package core.UI.elements.toolPanel.pointer;

import core.UI.managers.FocusTabManager;
import primitive.Point;
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
    public final JPanel jPanel;

    public CoordinateInfoPanel(ArrayList<ObjectInfoPanel> infoPanels,Point point, String title, double startValue,FocusTabManager focusTabManager){
        super(infoPanels);
        this.title=title;
        this.point=point;
        this.focusTabManager =focusTabManager;
        this.jPanel = createJPanel(startValue);
    }

    public JPanel createJPanel(double startValue){
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
                    point.setX(Double.parseDouble(textArea.getText()));
                    return;
                }
                if(Objects.equals(title,"y")){
                    point.setX(Double.parseDouble(textArea.getText()));
                    return;
                }
                if(Objects.equals(title,"z")){
                    point.setX(Double.parseDouble(textArea.getText()));
                    return;
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
