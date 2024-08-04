package core.tools.keys;


import core.tools.keys.oneTimeKeyBind.*;
import core.tools.keys.radioKeyBind.OnlyPolygonShaderBind;

public enum KeyBinds {
    CREATE_BOX(CreateBoxKeyBind.class,1,"createBox"),
    CREATE_POINT(CreatePointKeyBind.class,1,"createPoint"),
    CREATE_POLYGON(CreatePolygonKeyBind.class,1,"createPolygon"),
    ROTATE_SELECTED_OBJECTS(RotateSelectedObjectsKeyBInd.class,1,"createPolygon"),
    ONLY_POLYGON_SHADER(OnlyPolygonShaderBind.class,0,"createPolygon"),
    CREATE_POLYGON_BY_SELECTED_POINTS(CreatePolygonBySelectedPointsKeyBind.class,1,"createPolygonBySelectedPoints"),
    CNTRSHIFTZ(core.tools.keys.oneTimeKeyBind.CNTRSHIFTZ.class,1,"cntrShiftz"),
    CNTRZ(core.tools.keys.oneTimeKeyBind.CNTRZ.class,1,"cntrz");
    private final Class<?> keyBindClass;
    private final int oneTimeFlag;
    private final String nameInJson;

    KeyBinds(Class<?> keyBindClass, int oneTimeFlag, String nameInJson) {
        this.keyBindClass = keyBindClass;
        this.oneTimeFlag = oneTimeFlag;
        this.nameInJson = nameInJson;
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
}
