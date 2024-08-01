package utils.triangle;

import primitive.calculation.Point;
import utils.vectors.Vector3D;

public class Triangle {

    private final Point pointA;
    private final Point pointB;
    private final Point pointC;

    public Triangle(Point pointA, Point pointB, Point pointC) {
        this.pointA = pointA;
        this.pointB = pointB;
        this.pointC = pointC;
    }
    public double area(){
        Vector3D vector1 = pointA.VectorToPoint(pointB);
        Vector3D vector2= pointA.VectorToPoint(pointC);
        double area = Math.abs(vector1.crossMultiply(vector2).getLength())/2;
        return area;
    }

    public Point getPointA() {
        return pointA;
    }

    public Point getPointB() {
        return pointB;
    }

    public Point getPointC() {
        return pointC;
    }
}
