package core.tools.changes.changeTree;

import core.scene.Scene;
import core.tools.changes.Change;

import java.util.ArrayList;

public class NullNode extends ChangeNode{
    public NullNode(Scene scene) {
        super(new Change(new ArrayList<>(scene.getPrimitives()), new ArrayList<>()), null);
    }

    @Override
    public ChangeNode getUpNode() {
        return this;
    }
}
