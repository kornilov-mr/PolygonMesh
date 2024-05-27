package primitive;

import utils.vectors.Vector3D;

public class Point {

    private final double x;
    private final double y;
    private final double z;
    public Point(Vector3D vector){
        this(vector.getX(), vector.getY(), vector.getZ());
    }
    public Point(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getZ() {
        return z;
    }
}
