package utils.line;

import primitive.calculation.Point;
import utils.Calculation;
import utils.vectors.Vector3D;


public class Line {


    public final Vector3D pointVector;
    public final Vector3D directionVector;
    public Line(Point pointA, Point pointB) {
        this(new Vector3D(pointA),new Vector3D(pointB).subtraction(new Vector3D(pointA)));
    }
    public Line(Vector3D pointVector, Vector3D directionVector) {
        this.pointVector = pointVector;
        this.directionVector = directionVector;
    }
    private Point getClosesPointBetweenLines(Line line){
        Vector3D cross = directionVector.crossMultiply(line.directionVector);
        double tLine= directionVector.crossMultiply(cross).multiply(line.pointVector.subtraction(pointVector)).getSum()/Math.pow(cross.getLength(),2);
        if(tLine<0){
            return null;
        }
        Vector3D point = line.pointVector.add(line.directionVector.multiply(tLine));
        return new Point(point);
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
        if(delta*delta-distance*distance<0){
            return null;
        }
        double distanceT = Math.sqrt(delta*delta-distance*distance);

        return new Point(new Vector3D(pointT).add(line.directionVector.multiply(distanceT*-1)));
    }
    public double getDistanceBetweenTwoLines(Line line){
        Vector3D cross = directionVector.crossMultiply(line.directionVector).normalized();
        return Math.abs(cross.multiply(line.pointVector.subtraction(pointVector)).getSum());
    }
}
