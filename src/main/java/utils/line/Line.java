package utils.line;

import primitive.calculation.Point;
import primitive.calculation.faces.Face;
import utils.Calculation;
import utils.vectors.Vector3D;

public class Line {


    public final Vector3D pointVector;
    public final Vector3D directionVector;
    public Line(Point pointA, Point pointB) {
        this(new Vector3D(pointA),new Vector3D(pointB));
    }
    public Line(Vector3D pointVector, Vector3D directionVector) {
        this.pointVector = pointVector;
        this.directionVector = directionVector;
    }
    private Point getClosesPointBetweenLines(Line line){
        Vector3D cross = directionVector.crossMultiply(line.directionVector);
        Face face = new Face(new Point(pointVector),cross,directionVector);
        Point point = face.getIntersection(line);
        return point;
    }
    public Point getHardIntersection(Line line){
        double distance = getDistanceBetweenTwoLines(line);
        if(Calculation.round(distance,6)!=0){
            return null;
        }
        return getClosesPointBetweenLines(line);
    }
    public Point getSoftIntersection(Line line, double delta){
        double distance = getDistanceBetweenTwoLines(line);
        if(distance>delta){
            return null;
        }

        Point pointT = getClosesPointBetweenLines(line);
        if(pointT==null){
            return null;
        }
        if(delta*2-distance*2<0){
            return null;
        }
        double distanceT = Math.sqrt(delta*2-distance*2);

        return new Point(new Vector3D(pointT).add(line.directionVector.multiply(distanceT*-1)));
    }
    public double getDistanceBetweenTwoLines(Line line){
        Vector3D cross = directionVector.crossMultiply(line.directionVector);
        cross = cross.normalized();
        return cross.multiply(pointVector.subtraction(line.pointVector)).getLength();
    }
}
