package core.tools.commands.changeParameters.color;

import core.scene.Scene;
import core.tools.commands.changeParameters.ParameterChangeCommand;
import core.tools.selecting.SelectedObjectManager;
import primitive.Primitive;

public class ColorBChange extends ParameterChangeCommand {
    private final Primitive primitive;
    private final int B;
    public ColorBChange(Primitive primitive, int B) {
        super(primitive);
        this.primitive=primitive;
        this.B=B;
    }

    @Override
    public void execute(Scene scene, SelectedObjectManager selectedObjectManager) {
        primitive.setBlue(B);
    }
}
