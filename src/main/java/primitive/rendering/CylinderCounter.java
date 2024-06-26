package primitive.rendering;

import primitive.calculation.Counter;
import primitive.calculation.Point;

import java.awt.*;

public class CylinderCounter extends Counter {
    public CylinderCounter(Counter counter){
        this(counter.getPointA(),counter.getPointB(),counter.isSelected() ? 0.2 :0.02,counter.isSelected() ? new Color(36, 100, 155) :counter.getColor());
    }
    public CylinderCounter(Point pointA, Point pointB, double size, Color color) {
        super(pointA, pointB, size, color);
    }
}
