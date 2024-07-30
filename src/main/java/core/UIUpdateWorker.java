package core;

import core.UI.visuals.Window;
import core.render.Frame;

import javax.swing.*;

public class UIUpdateWorker extends SwingWorker<Void,Void> {
    private final Frame frame;
    private final Window window;

    public UIUpdateWorker(Frame frame, Window window) {
        this.frame = frame;
        this.window = window;
    }

    @Override
    public Void doInBackground() throws Exception {
        window.showOneFrame(frame);
        return null;
    }
}
