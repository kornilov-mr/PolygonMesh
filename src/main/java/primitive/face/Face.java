package primitive.face;

import javafx.util.Pair;
import primitive.Point;
import utils.Calculation;
import utils.line.Line;
import utils.vectors.Vector3D;

import java.awt.Color;

public class Face {

    private final Point pointA;
    private final Point pointB;
    private final Point pointC;

    private final Vector3D normalVector;
    private final CoordinateForm coordinateForm;
    private final Color color;

    public Face(Point pointA, Point pointB, Point pointC, Color color) {
        this.pointA = pointA;
        this.pointB = pointB;
        this.pointC = pointC;
        this.color= color;

        Vector3D directionVector1= Calculation.VectorBetweenTwoPoints(pointA,pointB);
        Vector3D directionVector2= Calculation.VectorBetweenTwoPoints(pointA,pointC);

        this.normalVector= directionVector1.crossMultiply(directionVector2);

        double D = -1*normalVector.Multiply(new Vector3D(pointA)).getSum();
        this.coordinateForm = new CoordinateForm(normalVector,D);
    }
    public Pair<Point,Color> getIntersection(Line line){
        Point IntersectionWithPlane = coordinateForm.getPointOnIntersection(line);

        if(IntersectionWithPlane!=null && Calculation.ifPointInTriangle(IntersectionWithPlane, pointA,pointB,pointC)){
            return new Pair<>(IntersectionWithPlane,color);
        }
        return null;
    }
}
