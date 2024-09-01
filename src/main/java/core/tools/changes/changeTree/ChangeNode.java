package core.tools.changes.changeTree;

import core.tools.changes.Change;

public class ChangeNode {
    //Class, which contains all of the changes after some action
    private final Change change;
    private final ChangeNode upNode;
    private ChangeNode NextMainNode;

    protected ChangeNode(Change change, ChangeNode upNode) {
        this.change = change;
        this.upNode = upNode;
    }

    public ChangeNode getUpNode() {
        return upNode;
    }

    public void setNextMainNode(ChangeNode nextMainNode) {
        NextMainNode = nextMainNode;
    }

    public Change getChange() {
        return change;
    }

    public ChangeNode getNextMainNode() {
        return NextMainNode;
    }
}
