package core.UI.visuals.elements.toolPanel.toolInterface.popup.fields;

import primitive.calculation.Point;

public enum TypeEnum {
    INT_FIELD(INTField.class, Integer.class),
    DOUBLE_FIELD(DoubleField.class, Double.class),
    POINT_FIELD(PointField.class, Point.class);
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