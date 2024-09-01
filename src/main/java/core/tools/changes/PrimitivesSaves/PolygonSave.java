package core.tools.changes.PrimitivesSaves;

import primitive.calculation.faces.Polygon;

import java.awt.*;

public class PolygonSave implements Saves<Polygon> {
    //Class, which saves parameters of Polygon and with ability to reapply with params
    private final Color color;

    public PolygonSave(Polygon polygon) {
        this(polygon.getColor());
    }

    public PolygonSave(Color color) {
        this.color = new Color(color.getRGB());
    }

    @Override
    public void applySave(Polygon polygon){
        polygon.setColor(color);
    }
}
