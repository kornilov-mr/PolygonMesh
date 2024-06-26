package primitive.rendering;

import primitive.calculation.Point;
import primitive.calculation.faces.Polygon;

import java.awt.*;

public class PolygonForRendering extends Polygon {
    public PolygonForRendering(Polygon polygon){
        this(polygon.getPointA(),polygon.getPointB(),polygon.getPointC(),polygon.isSelected() ? new Color(36, 100, 155) :polygon.getColor());
    }
    private PolygonForRendering(Point pointA, Point pointB, Point pointC, Color color) {
        super(pointA, pointB, pointC, color);
    }
}
