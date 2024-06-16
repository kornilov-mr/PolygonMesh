package primitive.faces;

import core.render.GPU.structs.VectorStruct;
import org.jocl.struct.Struct;
import org.json.JSONObject;
import primitive.Point;
import core.render.GPU.structs.PolygonStruct;
import utils.Calculation;
import utils.line.Line;
import utils.vectors.Vector3D;

import java.awt.Color;

public class Polygon{

    public final Point pointA;
    public final Point pointB;
    public final Point pointC;

    public final Vector3D normalVector;
    public final CoordinateForm coordinateForm;
    public final Color mainColor;
    public final Color contourColor;

    public Polygon(Point pointA, Point pointB, Point pointC, Color mainColor){
        this(pointA,pointB,pointC,mainColor,new Color(32,32,32));
    }
    public Polygon(Point pointA, Point pointB, Point pointC, Color mainColor,Color contourColor) {
        this.pointA = pointA;
        this.pointB = pointB;
        this.pointC = pointC;
        this.contourColor=contourColor;
        this.mainColor = mainColor;

        Vector3D directionVector1 = Calculation.VectorBetweenTwoPoints(pointA, pointB);
        Vector3D directionVector2 = Calculation.VectorBetweenTwoPoints(pointA, pointC);

        this.normalVector = directionVector1.crossMultiply(directionVector2);

        double D = -1 * normalVector.multiply(new Vector3D(pointA)).getSum();
        this.coordinateForm = new CoordinateForm(normalVector, D);
    }

    public Point getIntersection(Line line) {
        Point intersectionWithPlane = coordinateForm.getPointOnIntersection(line);

        if (intersectionWithPlane != null && Calculation.ifPointInTriangle(intersectionWithPlane, pointA, pointB, pointC)) {
            intersectionWithPlane.setColor(mainColor);
            return intersectionWithPlane;
        }
        return null;
    }
    public JSONObject objectInSavingFormat() {
        JSONObject obj = new JSONObject();
        obj.put("Class", "Polygon");
        obj.put("point1", pointA.toJSON());
        obj.put("point2", pointB.toJSON());
        obj.put("point3", pointC.toJSON());
        obj.put("mainColor", mainColor.getRGB());
        obj.put("contourColor", contourColor.getRGB());
        return obj;
    }
    public PolygonStruct toStruct(){
        return new PolygonStruct(new VectorStruct(pointA),
                new VectorStruct(pointB),
                new VectorStruct(pointC),
                                coordinateForm.toStruct(),
                                mainColor.getRGB(),
                                contourColor.getRGB());
    }
}
