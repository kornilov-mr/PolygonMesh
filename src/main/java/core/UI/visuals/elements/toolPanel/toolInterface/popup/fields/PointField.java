package core.UI.visuals.elements.toolPanel.toolInterface.popup.fields;

import core.tools.managers.FocusTabManager;
import primitive.calculation.Point;

import javax.swing.*;

public class PointField implements ArgumentField {
    private final DoubleField xField;
    private final DoubleField yField;
    private final DoubleField zField;
    private final String title;

    public PointField(String title) {
        this.title=title;
        this.xField = new DoubleField("x");
        this.yField = new DoubleField("y");
        this.zField = new DoubleField("z");
    }

    @Override
    public Object Field() {
        double x = (Double) xField.Field();
        double y = (Double) yField.Field();
        double z = (Double) zField.Field();
        return new Point(x,y,z);
    }

    @Override
    public JPanel createPanel(FocusTabManager focusTabManager) {
        JPanel jPanel = new JPanel();
        jPanel.add(new JLabel(title));
        jPanel.add(xField.createPanel(focusTabManager));
        jPanel.add(yField.createPanel(focusTabManager));
        jPanel.add(zField.createPanel(focusTabManager));
        return jPanel;
    }
}
