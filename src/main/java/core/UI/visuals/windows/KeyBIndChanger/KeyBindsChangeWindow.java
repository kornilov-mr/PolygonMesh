package core.UI.visuals.windows.KeyBIndChanger;

import core.tools.keys.KeyBindRegister;
import core.tools.keys.KeyBinds;
import core.tools.keys.KeyBindsPreset;
import core.tools.keys.MainKeyListener;
import core.tools.keys.oneTimeKeyBind.OneTimeKeyBind;
import core.tools.keys.radioKeyBind.RadioKeyBind;
import core.tools.managers.FocusTabManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class KeyBindsChangeWindow extends JFrame {
    private final MainKeyListener mainKeyListener;
    private final KeyBindsPreset preset;
    public KeyBindsChangeWindow(KeyBindRegister keyBindRegister, MainKeyListener mainKeyListener, FocusTabManager focusTabManager) {
        this.mainKeyListener=mainKeyListener;
        setTitle("Edit key-binds");
        setSize(500, 500);
        JPanel keySetWrapper = new JPanel();
        JPanel keySet = new JPanel();
        keySet.setLayout(new BoxLayout(keySet,BoxLayout.Y_AXIS));
        keySetWrapper.add(keySet);

        this.preset= keyBindRegister.getPreset();
        for(String storedName : preset.getOneTimeKeyBindMap().keySet()){
            OneTimeKeyBind oneTimeKeyBind = preset.getOneTimeKeyBindMap().get(storedName);
            String nameForUser = KeyBinds.findByNameForJson(storedName).getNameForUser();
            JPanel panel = createPanelForKeyBind(nameForUser, storedName,oneTimeKeyBind.getKeyLabel(),focusTabManager);
            keySet.add(panel);
        }
        for(String storedName : preset.getRadioKeyBindMap().keySet()){
            RadioKeyBind radioKeyBind = preset.getRadioKeyBindMap().get(storedName);
            String nameForUser = KeyBinds.findByNameForJson(storedName).getNameForUser();
            JPanel panel = createPanelForKeyBind(nameForUser, storedName,radioKeyBind.getKeyLabel(),focusTabManager);
            keySet.add(panel);
        }
        setLayout(new BorderLayout());

        add(keySet,BorderLayout.PAGE_START);

        JButton button = new JButton("set to Default");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                keyBindRegister.setToDefault();
            }
        });
        add(button);
    }
    public JPanel createPanelForKeyBind(String nameForUser, String storedName, JPanel keyLabel, FocusTabManager focusTabManager){
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel,BoxLayout.X_AXIS));

        JLabel nameLabel = new JLabel(nameForUser);
        JPanel nameWrapper = new JPanel();
        nameWrapper.setLayout(new FlowLayout(FlowLayout.LEFT));
        nameWrapper.add(nameLabel);

        JPanel keyWrapper = new JPanel();
        keyWrapper.setLayout(new FlowLayout(FlowLayout.RIGHT));
        keyWrapper.add(keyLabel);

        panel.add(nameWrapper);
        panel.add(Box.createHorizontalGlue());
        panel.add(keyWrapper);

        ChangeKeyBindListener listener = new ChangeKeyBindListener(preset,storedName, focusTabManager, panel);
        panel.addKeyListener(listener);
        panel.addMouseListener(listener);
        return panel;
    }
    public void setAsVisible() {
        setLocationByPlatform(true);
        setVisible(true);
        setResizable(false);
        requestFocus();
    }
}
