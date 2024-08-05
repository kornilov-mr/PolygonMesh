package core;

import core.UI.visuals.MainWindow;
import core.render.Frame;

import javax.swing.*;

public class UIUpdateWorker extends SwingWorker<Void,Void> {
    private final Frame frame;
    private final MainWindow mainWindow;

    public UIUpdateWorker(Frame frame, MainWindow mainWindow) {
        this.frame = frame;
        this.mainWindow = mainWindow;
    }

    @Override
    public Void doInBackground() throws Exception {
        mainWindow.showOneFrame(frame);
        return null;
    }
}
