package core.tools.commands.changeParameters;

import core.tools.changes.Change;
import core.tools.commands.Command;
import primitive.Primitive;

import java.util.ArrayList;
import java.util.Arrays;

public abstract class ParameterChangeCommand implements Command {
    private final Primitive primitive;
    protected ParameterChangeCommand(Primitive primitive) {
        this.primitive= primitive;
    }

    @Override
    public final Change getChange() {
        return new Change(new ArrayList<>(Arrays.asList(primitive)), new ArrayList<>());
    }
}
