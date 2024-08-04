package core.tools.keys.radioKeyBind;

import core.render.GPU.shaders.ShaderEnum;
import core.scene.SceneManipulator;

import java.awt.event.KeyEvent;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class OnlyPolygonShaderBind extends RadioKeyBind{
    public OnlyPolygonShaderBind(Set<Integer> keysRequired) {
        super(keysRequired);
    }

    public OnlyPolygonShaderBind() {
        super(new HashSet<>(Arrays.asList(KeyEvent.VK_CONTROL)));
    }

    @Override
    protected void turnOff(SceneManipulator sceneManipulator) {
        sceneManipulator.getRenderSwitcher().unSetShader();
    }

    @Override
    protected void turnOn(SceneManipulator sceneManipulator) {
        sceneManipulator.getRenderSwitcher().setShader(ShaderEnum.OnlyPolygonShader);
    }
}
