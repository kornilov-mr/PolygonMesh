package core.tools.commands;

import core.tools.changes.ChangeManager;

public class CommandManager {

    private final ChangeManager changeManager;

    public CommandManager(ChangeManager changeManager) {
        this.changeManager = changeManager;
    }
    public void executeCommand(Command command){
        command.execute();
        changeManager.addChangeInStack(command.getChange());
    }
}
