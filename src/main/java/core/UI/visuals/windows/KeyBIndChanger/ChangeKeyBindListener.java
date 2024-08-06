package core.UI.visuals.windows.KeyBIndChanger;


import core.tools.keys.KeyBindsPreset;
import core.tools.managers.FocusTabManager;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashSet;
import java.util.Set;

public class ChangeKeyBindListener extends MouseAdapter implements KeyListener {
    private final KeyBindsPreset preset;
    private final String storedName;
    private final Set<Integer> currentKeyPressed = new HashSet<>();
    private final FocusTabManager focusTabManager;
    private final JPanel panel;
    private boolean mousePressed = false;

    public ChangeKeyBindListener(KeyBindsPreset preset, String storedName, FocusTabManager focusTabManager, JPanel panel) {
        this.preset = preset;
        this.storedName = storedName;
        this.focusTabManager = focusTabManager;
        this.panel = panel;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        panel.requestFocus();
        mousePressed=true;
        currentKeyPressed.clear();
        super.mousePressed(e);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        mousePressed=false;
        currentKeyPressed.clear();
        super.mouseReleased(e);
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println("test");
        if(!mousePressed){
            return;
        }
        currentKeyPressed.add(e.getKeyCode());
        preset.changeRequiredKey(storedName,currentKeyPressed);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(!mousePressed){
            return;
        }
        currentKeyPressed.remove(e.getKeyCode());
        preset.changeRequiredKey(storedName,currentKeyPressed);

    }
}
