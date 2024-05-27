package utils.vectors;

import primitive.Point;

public class Vector3D {
    private final double x;
    private final double y;
    private final double z;

    public Vector3D(Point point) {
        this(point.getX(), point.getY(), point.getZ());
    }

    public Vector3D(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Vector3D add(Vector3D vector3D) {
        return new Vector3D(this.x + vector3D.x, this.y + vector3D.y, this.z + vector3D.z);
    }

    public Vector3D Multiply(double factor) {
        return new Vector3D(this.x * factor, this.y * factor, this.z * factor);
    }

    public Vector3D Multiply(Vector3D otherVector) {
        return new Vector3D(this.x * otherVector.x, this.y * otherVector.y, this.z * otherVector.z);
    }

    public Vector3D crossMultiply(Vector3D otherVector) {
        return new Vector3D(this.y * otherVector.z - this.z * otherVector.y,
                this.z * otherVector.x - this.x * otherVector.z,
                this.x * otherVector.y - this.y * otherVector.x);
    }

    public Vector3D division(double factor) {
        return new Vector3D(this.x / factor, this.y / factor, this.z / factor);
    }

    public Vector3D normalized() {
        return this.division(this.getLength());
    }

    public Vector3D rotateX(double angle) {
        return new Vector3D(this.x,
                this.y * Math.cos(angle) - this.z * Math.sin(angle),
                this.y * Math.sin(angle) + this.z * Math.cos(angle));
    }

    public Vector3D rotateY(double angle) {
        return new Vector3D(this.x * Math.cos(angle) + this.z * Math.sin(angle),
                this.y,
                -this.x * Math.sin(angle) + this.z * Math.cos(angle));
    }

    public Vector3D rotateZ(double angle) {
        return new Vector3D(this.x * Math.cos(angle) - this.y * Math.sin(angle),
                this.x * Math.sin(angle) - this.y * Math.cos(angle),
                this.x);
    }

    public double getLength() {
        return Math.sqrt(Math.pow(this.x, 2) + Math.pow(this.y, 2) + Math.pow(this.z, 2));
    }

    public double getSum() {
        return this.x + this.y + this.z;
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
