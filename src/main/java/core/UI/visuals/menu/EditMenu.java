package core.UI.visuals.menu;

import core.UI.visuals.windows.KeyBIndChanger.KeyBindsChangeWindow;
import core.scene.SceneManipulator;
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

public class EditMenu extends JMenu {
    public EditMenu(KeyBindRegister keyBindRegister, MainKeyListener mainKeyListener, SceneManipulator sceneManipulator, FocusTabManager focusTabManager) {
        super("Edit");
        KeyBindsPreset preset= keyBindRegister.getPreset();
        for(String name : preset.getOneTimeKeyBindMap().keySet()){
            OneTimeKeyBind oneTimeKeyBind = preset.getOneTimeKeyBindMap().get(name);
            JMenuItem item = new JMenuItem();

            JLabel itemLabel = new JLabel(KeyBinds.findByNameForJson(name).getNameForUser());
            JPanel itemWrapper = new JPanel(new FlowLayout(FlowLayout.LEFT));
            itemWrapper.add(itemLabel);

            JPanel keyLabel = oneTimeKeyBind.getKeyLabel();
            JPanel keyWrapper = new JPanel(new FlowLayout(FlowLayout.RIGHT));

            keyWrapper.add(keyLabel);

            item.setPreferredSize(new Dimension(300,40));

            JPanel wrapper = new JPanel();
            wrapper.setLayout(new BoxLayout(wrapper, BoxLayout.X_AXIS));

            wrapper.add(itemWrapper,BorderLayout.LINE_START);
            wrapper.add(Box.createHorizontalGlue());
            wrapper.add(keyWrapper, BorderLayout.LINE_END);
            item.add(wrapper);

            item.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    oneTimeKeyBind.run(sceneManipulator);
                }
            });
            add(item);
        }
        for(String name : preset.getRadioKeyBindMap().keySet()){
            RadioKeyBind radioKeyBind = preset.getRadioKeyBindMap().get(name);

            JMenuItem item = new JMenuItem();

            JLabel itemLabel = new JLabel(KeyBinds.findByNameForJson(name).getNameForUser());
            JPanel itemWrapper = new JPanel(new FlowLayout(FlowLayout.LEFT));
            itemWrapper.add(itemLabel);

            JPanel keyLabel = radioKeyBind.getKeyLabel();
            JPanel keyWrapper = new JPanel(new FlowLayout(FlowLayout.RIGHT));

            keyWrapper.add(keyLabel);

            item.setPreferredSize(new Dimension(200,40));

            JPanel wrapper = new JPanel();
            wrapper.setLayout(new BoxLayout(wrapper, BoxLayout.X_AXIS));

            wrapper.add(itemWrapper,BorderLayout.LINE_START);
            wrapper.add(Box.createHorizontalGlue());
            wrapper.add(keyWrapper, BorderLayout.LINE_END);
            item.add(wrapper);
            item.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    radioKeyBind.changeActivation(sceneManipulator);
                }
            });
            add(item);
        }
        JMenuItem item = new JMenuItem("Edit key-binds");
        item.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                KeyBindsChangeWindow window = new KeyBindsChangeWindow(keyBindRegister,mainKeyListener,focusTabManager);
                window.setAsVisible();
            }
        });
        add(item);
    }
}
