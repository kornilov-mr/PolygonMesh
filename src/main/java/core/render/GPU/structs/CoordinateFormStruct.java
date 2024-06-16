package core.render.GPU.structs;

import org.jocl.struct.Struct;
import utils.vectors.Vector3D;

public class CoordinateFormStruct extends Struct {
    public double A;
    public double B;
    public double C;
    public double D;


    public CoordinateFormStruct() {
    }

    public CoordinateFormStruct(Vector3D vector, double d) {
        A = vector.getX();
        B = vector.getY();
        C = vector.getZ();
        D = d;
    }

    public CoordinateFormStruct(double a, double b, double c, double d) {
        this(new Vector3D(a, b, c), d);
    }
}
