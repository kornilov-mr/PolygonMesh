package core.tools.commands;

import core.scene.Scene;
import core.tools.changes.Change;
import core.tools.selecting.SelectedObjectManager;

public interface Command {
    void execute(Scene scene, SelectedObjectManager selectedObjectManager);
    Change getChange();
}
