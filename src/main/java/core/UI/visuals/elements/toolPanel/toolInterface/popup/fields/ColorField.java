package core.UI.visuals.elements.toolPanel.toolInterface.popup.fields;

import core.tools.managers.FocusTabManager;

import javax.swing.*;
import java.awt.*;

public class ColorField implements ArgumentField {
    private final ColorComponentField r;
    private final ColorComponentField g;
    private final ColorComponentField b;
    public ColorField(String title) {
        this.r= new ColorComponentField("R");
        this.g= new ColorComponentField("G");
        this.b= new ColorComponentField("B");
    }

    @Override
    public Object Field() {
        int rValue = (Integer) r.Field();
        int gValue = (Integer) g.Field();
        int bValue = (Integer) b.Field();
        return new Color(rValue,gValue,bValue);
    }

    @Override
    public JPanel createPanel(FocusTabManager focusTabManager) {
        JPanel jPanel = new JPanel();
        jPanel.add(r.createPanel(focusTabManager));
        jPanel.add(g.createPanel(focusTabManager));
        jPanel.add(b.createPanel(focusTabManager));
        return jPanel;
    }
}
