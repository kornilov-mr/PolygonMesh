package core.tools.commands;

import core.scene.Scene;
import core.tools.changes.ChangeManager;
import core.tools.selecting.SelectedObjectManager;

public class CommandManager {
    //Class, which executes Commands and adds Change to the Change tree
    private final Scene scene;
    private final SelectedObjectManager selectedObjectManager;
    private final ChangeManager changeManager;

    public CommandManager(Scene scene, SelectedObjectManager selectedObjectManager, ChangeManager changeManager) {
        this.scene = scene;
        this.selectedObjectManager = selectedObjectManager;
        this.changeManager = changeManager;
    }
    public void executeCommand(Command command){
        command.execute(scene,selectedObjectManager);
        changeManager.addChangeInStack(command.getChange());
    }

}
