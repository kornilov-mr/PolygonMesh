package core.tools.keys;

import java.awt.event.KeyEvent;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public enum KeyBindDefault {
    CREATE_BOX(new KeyBindData(KeyBinds.CREATE_BOX,new HashSet<>(Arrays.asList(KeyEvent.VK_B, KeyEvent.VK_SHIFT)))),
    CREATE_POINT(new KeyBindData(KeyBinds.CREATE_POINT,new HashSet<>(Arrays.asList(KeyEvent.VK_P, KeyEvent.VK_SHIFT)))),
    CREATE_POLYGON(new KeyBindData(KeyBinds.CREATE_POLYGON,new HashSet<>(Arrays.asList(KeyEvent.VK_OPEN_BRACKET, KeyEvent.VK_SHIFT)))),
    ROTATE_SELECTED_OBJECTS(new KeyBindData(KeyBinds.ROTATE_SELECTED_OBJECTS,new HashSet<>(Arrays.asList(KeyEvent.VK_R, KeyEvent.VK_SHIFT)))),
    ONLY_POLYGON_SHADER(new KeyBindData(KeyBinds.ONLY_POLYGON_SHADER,new HashSet<>(Arrays.asList(KeyEvent.VK_CONTROL)))),
    CREATE_POLYGON_BY_SELECTED_POINTS(new KeyBindData(KeyBinds.CREATE_POLYGON_BY_SELECTED_POINTS,new HashSet<>(Arrays.asList(KeyEvent.VK_A, KeyEvent.VK_SHIFT)))),
    CNTRSHIFTZ(new KeyBindData(KeyBinds.CNTRSHIFTZ,new HashSet<>(Arrays.asList(KeyEvent.VK_CONTROL, KeyEvent.VK_SHIFT,KeyEvent.VK_Z)))),
    CNTRZ(new KeyBindData(KeyBinds.CNTRZ,new HashSet<>(Arrays.asList(KeyEvent.VK_CONTROL,KeyEvent.VK_Z))));

    private final KeyBindData keyBindData;

    KeyBindDefault(KeyBindData keyBindData) {
        this.keyBindData = keyBindData;
    }

    public KeyBindData getKeyBindData() {
        return keyBindData;
    }
}
