package primitive.faces;

import primitive.Point;
import utils.Calculation;
import utils.line.Line;
import utils.vectors.Vector3D;

public class Face {
    private Point pointA;
    private Point pointB;
    private Point pointC;

    private Vector3D normalVector;
    protected CoordinateForm coordinateForm;
    public Face(Point pointA, Vector3D directionVector1, Vector3D directionVector2){
        this(pointA, new Point(new Vector3D(pointA).add(directionVector1)),new Point(new Vector3D(pointA).add(directionVector2)));
    }
    public Face(Point pointA, Point pointB, Point pointC) {
        this.pointA = pointA;
        this.pointB = pointB;
        this.pointC = pointC;

        calculateNormalVector();
    }
    public void calculateNormalVector(){
        Vector3D directionVector1 = Calculation.VectorBetweenTwoPoints(pointA, pointB);
        Vector3D directionVector2 = Calculation.VectorBetweenTwoPoints(pointA, pointC);

        this.normalVector = directionVector1.crossMultiply(directionVector2);

        double D = -1 * normalVector.multiply(new Vector3D(pointA)).getSum();
        this.coordinateForm = new CoordinateForm(normalVector, D);
    }
    public Point getIntersection(Line line) {
        Point intersectionWithPlane = coordinateForm.getPointOnIntersection(line);

        return intersectionWithPlane;
    }



}
