package core.tools.commands.changeParameters.color;

import core.scene.Scene;
import core.tools.commands.changeParameters.ParameterChangeCommand;
import core.tools.selecting.SelectedObjectManager;
import primitive.Primitive;

public class ColorRChange extends ParameterChangeCommand {
    private final Primitive primitive;
    private final int R;
    public ColorRChange(Primitive primitive, int R) {
        super(primitive);
        this.primitive=primitive;
        this.R=R;
    }

    @Override
    public void execute(Scene scene, SelectedObjectManager selectedObjectManager) {
        primitive.setRed(R);
    }
}
