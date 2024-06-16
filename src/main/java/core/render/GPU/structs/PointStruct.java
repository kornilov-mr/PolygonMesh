package core.render.GPU.structs;

import org.jocl.struct.Struct;
import utils.vectors.Vector3D;

import java.awt.*;

public class PointStruct extends Struct {

    public double x;
    public double y;
    public double z;

    public PointStruct(Vector3D vector) {
        this(vector.getX(), vector.getY(), vector.getZ());
    }

    public PointStruct(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }
}
