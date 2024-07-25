package core.tools.commands;

import core.tools.changes.Change;

public interface Command {
    void execute();
    Change getChange();
}
