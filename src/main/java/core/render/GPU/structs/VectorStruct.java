package core.render.GPU.structs;

import org.jocl.struct.Struct;
import primitive.Point;
import utils.vectors.Vector3D;

public class VectorStruct extends Struct {
    public double x;
    public double y;
    public double z;

    public VectorStruct(Vector3D vector) {
        this(vector.getX(), vector.getY(), vector.getZ());
    }

    public VectorStruct(Point point) {
        this(point.getX(), point.getY(), point.getZ());
    }

    public VectorStruct(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public VectorStruct() {
    }

    @Override
    public String toString() {
        return "VectorStruct{" +
                "x=" + x +
                ", y=" + y +
                ", z=" + z +
                '}';
    }
}
