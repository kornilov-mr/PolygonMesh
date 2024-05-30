package primitive.faces;

import org.json.JSONObject;
import primitive.Point;
import primitive.Primitive;
import utils.Calculation;
import utils.line.Line;
import utils.vectors.Vector3D;

import java.awt.Color;

public class Polygon implements Primitive {

    private final Point pointA;
    private final Point pointB;
    private final Point pointC;

    private final Vector3D normalVector;
    private final CoordinateForm coordinateForm;
    private final Color color;

    public Polygon(Point pointA, Point pointB, Point pointC, Color color) {
        this.pointA = pointA;
        this.pointB = pointB;
        this.pointC = pointC;
        this.color = color;

        Vector3D directionVector1 = Calculation.VectorBetweenTwoPoints(pointA, pointB);
        Vector3D directionVector2 = Calculation.VectorBetweenTwoPoints(pointA, pointC);

        this.normalVector = directionVector1.crossMultiply(directionVector2);

        double D = -1 * normalVector.Multiply(new Vector3D(pointA)).getSum();
        this.coordinateForm = new CoordinateForm(normalVector, D);
    }

    @Override
    public Point getIntersection(Line line) {
        Point intersectionWithPlane = coordinateForm.getPointOnIntersection(line);

        if (intersectionWithPlane != null && Calculation.ifPointInTriangle(intersectionWithPlane, pointA, pointB, pointC)) {
            intersectionWithPlane.setColor(color);
            return intersectionWithPlane;
        }
        return null;
    }

    @Override
    public JSONObject objectInSavingFormat() {
        JSONObject obj = new JSONObject();
        obj.put("Class", "Polygon");
        obj.put("point1", pointA.toJSON());
        obj.put("point2", pointB.toJSON());
        obj.put("point3", pointC.toJSON());
        obj.put("color", color.getRGB());
        return obj;

    }
}
