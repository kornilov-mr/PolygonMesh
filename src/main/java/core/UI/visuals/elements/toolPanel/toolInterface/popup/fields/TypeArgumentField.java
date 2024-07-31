package core.UI.visuals.elements.toolPanel.toolInterface.popup.fields;

import core.tools.managers.FocusTabManager;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public abstract class TypeArgumentField implements ArgumentField{
    private final String title;
    private final String regexString;
    protected JTextField textArea;

    protected TypeArgumentField(String title, String regexString) {
        this.title = title;
        this.regexString = regexString;
    }
    @Override
    public JPanel createPanel(FocusTabManager focusTabManager){
        JPanel jPanel = new JPanel();
        jPanel.add(new JLabel());
        this.textArea = new JTextField("0");
        textArea.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent ke) {
                String value = textArea.getText();
                int l = value.length();
                if (String.valueOf(l).matches(regexString)||ke.getKeyChar()==KeyEvent.VK_BACK_SPACE) {
                    textArea.setEditable(true);
                } else {
                    textArea.setEditable(false);
                }
            }
        });
        textArea.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                focusTabManager.setFocusToMainWindow();
            }
        });
        jPanel.add(textArea);
        return jPanel;
    }
}
