package core.UI.visuals.elements.toolPanel.toolInterface.popup.fields;

import core.tools.managers.FocusTabManager;
import primitive.calculation.Counter;
import primitive.calculation.Point;

import javax.swing.*;
import java.awt.*;

public class CounterField implements ArgumentField {
    private final PointField pointAField;
    private final PointField pointBField;
    private final ColorField colorField;

    public CounterField(String title) {
        this.pointAField = new PointField("PointA");
        this.pointBField = new PointField("PointB");
        this.colorField = new ColorField("Color");
    }

    @Override
    public Object Field() {
        Point pointA = (Point) pointAField.Field();
        Point pointB = (Point) pointBField.Field();
        Color color = (Color) colorField.Field();
        return new Counter(pointA, pointB, color);
    }

    @Override
    public JPanel createPanel(FocusTabManager focusTabManager) {
        JPanel jPanel = new JPanel();
        jPanel.setLayout(new BoxLayout(jPanel, BoxLayout.Y_AXIS));
        jPanel.add(pointAField.createPanel(focusTabManager));
        jPanel.add(pointBField.createPanel(focusTabManager));
        jPanel.add(colorField.createPanel(focusTabManager));
        return jPanel;
    }
}
