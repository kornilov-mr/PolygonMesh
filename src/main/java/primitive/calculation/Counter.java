package primitive.calculation;

import org.json.JSONObject;
import primitive.Primitive;
import primitive.calculation.Point;
import primitive.calculation.faces.Face;
import utils.line.Line;

import java.awt.*;
import java.util.Map;

public class Counter extends Primitive {

    private final Point pointA;
    private final Point pointB;
    private final double size;
    private final Line counterLine;
    private final Color color;

    public Counter(Point pointA, Point pointB) {
        this(pointA,pointB,0.1, new Color(0,0,0));
    }

    public Counter(Point pointA, Point pointB, double size) {
        this(pointA,pointB,size, new Color(0,0,0));
    }

    public Counter(Point pointA, Point pointB, double size, Color color) {
        this.pointA = pointA;
        this.pointB = pointB;
        this.size = size;
        this.color = color;
        this.counterLine = new Line(pointA,pointB);
    }

    @Override
    public Point getIntersection(Line line) {
        Point point =counterLine.getSoftIntersection(line,size);
        if(point==null){
            return null;
        }
        Face beneathFace = new Face(pointA,line.directionVector);
        Face aboveFace = new Face(pointB,line.directionVector);
        if(beneathFace.ifPointIsAboveFace(point)>=0 && aboveFace.ifPointIsAboveFace(point)<=-1){
            return point;
        }
        return null;
    }

    @Override
    public JSONObject objectInSavingFormat() {
        return null;
    }

    public Point getPointA() {
        return pointA;
    }

    public Point getPointB() {
        return pointB;
    }

    public double getSize() {
        return size;
    }

    public Color getColor() {
        return color;
    }
}
