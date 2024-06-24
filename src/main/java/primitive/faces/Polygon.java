package primitive.faces;

import org.jocl.struct.Struct;
import org.json.JSONObject;
import primitive.Point;
import primitive.Primitive;
import utils.Calculation;
import utils.line.Line;
import utils.vectors.Vector3D;

import java.awt.Color;

public class Polygon extends Primitive {

    private Point pointA;
    private Point pointB;
    private Point pointC;

    private Vector3D normalVector;
    protected CoordinateForm coordinateForm;
    private Color mainColor;
    private Color contourColor;

    public Polygon(Point pointA, Point pointB, Point pointC){
        this(pointA,pointB,pointC,new Color(0,0,0));
    }
    public Polygon(Point pointA, Point pointB, Point pointC, Color mainColor) {
        this.pointA = pointA;
        this.pointB = pointB;
        this.pointC = pointC;
        this.mainColor = mainColor;

        calculateNormalVector();
    }
    public void calculateNormalVector(){
        Vector3D directionVector1 = Calculation.VectorBetweenTwoPoints(pointA, pointB);
        Vector3D directionVector2 = Calculation.VectorBetweenTwoPoints(pointA, pointC);

        this.normalVector = directionVector1.crossMultiply(directionVector2);

        double D = -1 * normalVector.multiply(new Vector3D(pointA)).getSum();
        this.coordinateForm = new CoordinateForm(normalVector, D);
    }
    @Override
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
        obj.put("point1", pointA.objectInSavingFormat());
        obj.put("point2", pointB.objectInSavingFormat());
        obj.put("point3", pointC.objectInSavingFormat());
        obj.put("mainColor", mainColor.getRGB());
        obj.put("contourColor", contourColor.getRGB());
        return obj;
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

    public void setPointA(Point pointA) {
        this.pointA = pointA;
        calculateNormalVector();
    }

    public void setPointB(Point pointB) {
        this.pointB = pointB;
        calculateNormalVector();
    }

    public Color getMainColorForRendering() {
        if(selected){
            return new Color(0,120,215) ;

        }
        return mainColor;
    }

    public Color getMainColor() {
        return mainColor;
    }

    public void setPointC(Point pointC) {
        this.pointC = pointC;
        calculateNormalVector();
    }

    public CoordinateForm getCoordinateForm() {
        return coordinateForm;
    }
    public void setRed(int red){
        this.mainColor = new Color(red,mainColor.getGreen(),mainColor.getBlue());
    }
    public void setGreen(int green){
        this.mainColor = new Color(mainColor.getRed(),green,mainColor.getBlue());
    }
    public void setBlue(int blue){
        this.mainColor = new Color(mainColor.getRed(),mainColor.getGreen(),blue);
    }

}
