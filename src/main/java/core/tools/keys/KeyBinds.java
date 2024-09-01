package core.tools.keys;


import core.tools.keys.oneTimeKeyBind.*;
import core.tools.keys.radioKeyBind.OnlyPolygonShaderBind;

import java.util.HashMap;
import java.util.Map;

public enum KeyBinds {
    CREATE_BOX(new CreateBoxKeyBind(),1,"createBox","Create Box"),
    CREATE_POINT(new CreatePointKeyBind(),1,"createPoint", "Create Point"),
    CREATE_POLYGON(new CreatePolygonKeyBind(),1,"createPolygon", "Create Polygon"),
    ROTATE_SELECTED_OBJECTS(new RotateSelectedObjectsKeyBInd(),1,"rotateSelectedObjects", "Rotate"),
    ONLY_POLYGON_SHADER(new OnlyPolygonShaderBind(),0,"onlyPolygonShader", "Only Polygons"),
    CREATE_POLYGON_BY_SELECTED_POINTS(new CreatePolygonBySelectedPointsKeyBind(),1,"createPolygonBySelectedPoints", "Polygon vie points"),
    CNTRSHIFTZ(new CNTRSHIFTZ(),1,"cntrShiftz", "Undo reverse"),
    CNTRZ(new CNTRZ(),1,"cntrz", "Back");
    private final KeyBind keyBind;
    private final int oneTimeFlag;
    private final String nameInJson;
    private final String nameForUser;

    KeyBinds(KeyBind keyBind, int oneTimeFlag, String nameInJson, String nameForUser) {
        this.keyBind = keyBind;
        this.oneTimeFlag = oneTimeFlag;
        this.nameInJson = nameInJson;
        this.nameForUser = nameForUser;
    }

    public KeyBind getKeyBind() {
        return keyBind;
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
