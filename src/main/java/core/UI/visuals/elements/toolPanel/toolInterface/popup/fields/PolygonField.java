package core.UI.visuals.elements.toolPanel.toolInterface.popup.fields;

import core.tools.managers.FocusTabManager;
import primitive.calculation.Point;
import primitive.calculation.faces.Polygon;

import javax.swing.*;
import java.awt.*;

public class PolygonField implements ArgumentField{
    private final PointField pointAField;
    private final PointField pointBField;
    private final PointField pointCField;
    private final ColorField colorField;
    public PolygonField(String title) {
        this.pointAField = new PointField(title);
        this.pointBField = new PointField(title);
        this.pointCField = new PointField(title);
        this.colorField = new ColorField(title);
    }

    @Override
    public Object Field() {
        Point pointA = (Point) pointAField.Field();
        Point pointB = (Point) pointBField.Field();
        Point pointC = (Point) pointCField.Field();
        Color color = (Color) colorField.Field();
        return new Polygon(pointA,pointB,pointC,color);
    }

    @Override
    public JPanel createPanel(FocusTabManager focusTabManager) {
        JPanel jPanel = new JPanel();
        jPanel.setLayout(new BoxLayout(jPanel,BoxLayout.Y_AXIS));
        jPanel.add(pointAField.createPanel(focusTabManager));
        jPanel.add(pointBField.createPanel(focusTabManager));
        jPanel.add(pointCField.createPanel(focusTabManager));
        jPanel.add(colorField.createPanel(focusTabManager));
        return jPanel;
    }
}
