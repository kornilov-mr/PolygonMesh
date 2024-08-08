package core.tools.keys.radioKeyBind;

import core.render.GPU.shaders.ShaderEnum;
import core.scene.SceneManipulator;
import core.tools.keys.KeyBinds;

import java.awt.event.KeyEvent;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class OnlyPolygonShaderBind extends RadioKeyBind{

    public OnlyPolygonShaderBind() {
        super(new HashSet<>(Arrays.asList(KeyEvent.VK_CONTROL)));
    }

    @Override
    protected void turnOff(SceneManipulator sceneManipulator) {
        sceneManipulator.getRenderSwitcher().unSetShader();
    }

    @Override
    public KeyBinds getKeyBind() {
        return KeyBinds.ONLY_POLYGON_SHADER;
    }

    @Override
    protected void turnOn(SceneManipulator sceneManipulator) {
        sceneManipulator.getRenderSwitcher().setShader(ShaderEnum.OnlyPolygonShader);
    }
}
