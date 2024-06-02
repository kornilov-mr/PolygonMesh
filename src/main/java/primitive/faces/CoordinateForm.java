package primitive.faces;

import primitive.Point;
import utils.line.Line;
import utils.vectors.Vector3D;

public class CoordinateForm {
    private final double A;
    private final double B;
    private final double C;
    private final double D;


    public CoordinateForm(Vector3D vector, double d) {
        A = vector.getX();
        B = vector.getY();
        C = vector.getZ();
        D = d;
    }

    public CoordinateForm(double a, double b, double c, double d) {
        this(new Vector3D(a,b,c),d);
    }

    protected Point getPointOnIntersection(Line line) {
        double r = 0;
        r += line.directionVector.getX() * A;
        r += line.directionVector.getY() * B;
        r += line.directionVector.getZ() * C;
        if(r==0){
            return null;
        }
        double free = 0;
        free += line.pointVector.getX() * A;
        free += line.pointVector.getY() * B;
        free += line.pointVector.getZ() * C;
        free += D;

        r = (-1*free) / r;
        if(r<0){
            return null;
        }
        return new Point(line.directionVector.multiply(r).add(line.pointVector));

    }

}
