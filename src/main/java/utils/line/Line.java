package utils.line;

import primitive.Point;
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
}
