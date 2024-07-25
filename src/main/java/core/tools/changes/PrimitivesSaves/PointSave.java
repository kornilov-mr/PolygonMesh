package core.tools.changes.PrimitivesSaves;

import primitive.calculation.Point;

import java.awt.*;

public class PointSave implements Saves<Point> {
    private final double x;
    private final double y;
    private final double z;
    private final double size;
    private final Color color;

    public PointSave(Point point) {
        this(point.getX(), point.getY(), point.getZ(), point.getSize(), point.getColor());
    }

    public PointSave(double x, double y, double z, double size, Color color) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.size = size;
        this.color = new Color(color.getRed());
    }

    @Override
    public void applySave(Point point){
        point.movePointToOtherPointCoordinates(new Point(x, y, z));
        point.setColor(color);
        point.setSize(size);
    }
}
