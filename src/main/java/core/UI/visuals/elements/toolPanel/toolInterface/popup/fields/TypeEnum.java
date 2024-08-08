package core.UI.visuals.elements.toolPanel.toolInterface.popup.fields;

import primitive.calculation.Counter;
import primitive.calculation.Point;
import primitive.calculation.faces.Polygon;

import java.awt.*;


public enum TypeEnum {
    INT_FIELD(INTField.class, Integer.class),
    DOUBLE_FIELD(DoubleField.class, Double.class),
    POINT_FIELD(PointField.class, Point.class),
    COLOR_FIELD(ColorField.class, Color.class),
    POLYGON_FIELD(PolygonField.class, Polygon.class),
    ANGLE_FIELD(AngleField.class, Double.class),
    COUNTER_FIELD(CounterField.class, Counter.class);
//    ENUM_FIELD(EnumField.class, );
    private final Class<? extends ArgumentField> fieldClass;
    private final Class<?> argumentClass;
    TypeEnum(Class<? extends ArgumentField> fieldClass, Class<?> argumentClass){
        this.fieldClass=fieldClass;
        this.argumentClass=argumentClass;
    }

    public Class<? extends ArgumentField> getFieldClass() {
        return fieldClass;
    }

    public Class<?> getArgumentClass() {
        return argumentClass;
    }
}
