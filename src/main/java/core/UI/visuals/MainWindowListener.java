package core.UI.visuals;

import core.scene.resentProjects.ResentProjectManager;
import core.tools.keys.KeyBindRegister;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class MainWindowListener implements WindowListener {
    private final KeyBindRegister keyBindRegister;
    private final ResentProjectManager resentProjectManager;

    public MainWindowListener(KeyBindRegister keyBindRegister, ResentProjectManager resentProjectManager) {
        this.keyBindRegister = keyBindRegister;
        this.resentProjectManager = resentProjectManager;
    }

    @Override
    public void windowOpened(WindowEvent e) {
    }

    @Override
    public void windowClosing(WindowEvent e) {
        keyBindRegister.saveKeySetUp();
        resentProjectManager.saveResentData();
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
