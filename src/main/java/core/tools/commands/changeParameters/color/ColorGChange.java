package core.tools.commands.changeParameters.color;

import core.scene.Scene;
import core.tools.commands.changeParameters.ParameterChangeCommand;
import core.tools.selecting.SelectedObjectManager;
import primitive.Primitive;

public class ColorGChange extends ParameterChangeCommand {
    private final Primitive primitive;
    private final int G;
    public ColorGChange(Primitive primitive, int G) {
        super(primitive);
        this.primitive=primitive;
        this.G=G;
    }

    @Override
    public void execute(Scene scene, SelectedObjectManager selectedObjectManager) {
        primitive.setGreen(G);
    }
}