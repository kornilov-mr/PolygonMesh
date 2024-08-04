package core.UI.visuals;

import core.tools.keys.KeyBindRegister;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class MainWindowListener implements WindowListener {
    private final KeyBindRegister keyBindRegister;

    public MainWindowListener(KeyBindRegister keyBindRegister) {
        this.keyBindRegister = keyBindRegister;
    }

    @Override
    public void windowOpened(WindowEvent e) {
    }

    @Override
    public void windowClosing(WindowEvent e) {
        keyBindRegister.saveKeySetUp();
    }

    @Override
    public void windowClosed(WindowEvent e) {

    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowDeactivated(WindowEvent e) {

    }
}
