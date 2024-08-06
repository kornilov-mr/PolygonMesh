package core.tools.keys;


import core.tools.keys.oneTimeKeyBind.*;
import core.tools.keys.radioKeyBind.OnlyPolygonShaderBind;

import java.util.HashMap;
import java.util.Map;

public enum KeyBinds {
    CREATE_BOX(CreateBoxKeyBind.class,1,"createBox","Create Box"),
    CREATE_POINT(CreatePointKeyBind.class,1,"createPoint", "Create Point"),
    CREATE_POLYGON(CreatePolygonKeyBind.class,1,"createPolygon", "Create Polygon"),
    ROTATE_SELECTED_OBJECTS(RotateSelectedObjectsKeyBInd.class,1,"rotateSelectedObjects", "Rotate"),
    ONLY_POLYGON_SHADER(OnlyPolygonShaderBind.class,0,"onlyPolygonShader", "Only Polygons"),
    CREATE_POLYGON_BY_SELECTED_POINTS(CreatePolygonBySelectedPointsKeyBind.class,1,"createPolygonBySelectedPoints", "Polygon vie points"),
    CNTRSHIFTZ(core.tools.keys.oneTimeKeyBind.CNTRSHIFTZ.class,1,"cntrShiftz", "Undo reverse"),
    CNTRZ(core.tools.keys.oneTimeKeyBind.CNTRZ.class,1,"cntrz", "Back");
    private final Class<?> keyBindClass;
    private final int oneTimeFlag;
    private final String nameInJson;
    private final String nameForUser;

    KeyBinds(Class<?> keyBindClass, int oneTimeFlag, String nameInJson, String nameForUser) {
        this.keyBindClass = keyBindClass;
        this.oneTimeFlag = oneTimeFlag;
        this.nameInJson = nameInJson;
        this.nameForUser = nameForUser;
    }

    public Class<?> getKeyBindClass() {
        return keyBindClass;
    }

    public int getOneTimeFlag() {
        return oneTimeFlag;
    }

    public String getNameInJson() {
        return nameInJson;
    }

    public String getNameForUser() {
        return nameForUser;
    }
    private static final Map<String,KeyBinds> JsonNameToKeyBind;
    static {
        JsonNameToKeyBind = new HashMap<String,KeyBinds>();
        for (KeyBinds v : KeyBinds.values()) {
            JsonNameToKeyBind.put(v.nameInJson, v);
        }
    }
    public static KeyBinds findByNameForJson(String name) {
        return JsonNameToKeyBind.get(name);
    }
}
