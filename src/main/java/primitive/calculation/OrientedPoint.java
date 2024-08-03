package primitive.calculation;

import primitive.OrientedObject;
import utils.vectors.Vector3D;

public class OrientedPoint extends OrientedObject {
    private final Point point;
    public OrientedPoint(Point point, double horizontalAngle, double verticalAngle) {
        this(point,horizontalAngle,verticalAngle,new Point(0,0,0));
    }
    public OrientedPoint(Point point, double horizontalAngle, double verticalAngle,Point center) {
        super(new Vector3D(point), horizontalAngle, verticalAngle,center);
        this.point=point;
    }

    @Override
    public void moveUp(double distance) {
        super.moveUp(distance);
        point.movePointToOtherPointCoordinates(new Point(position));
    }

    @Override
    public void moveRight(double distance) {
        super.moveRight(distance);
        point.movePointToOtherPointCoordinates(new Point(position));

    }

    @Override
    public void moveFront(double distance) {
        super.moveFront(distance);
        point.movePointToOtherPointCoordinates(new Point(position));

    }

    @Override
    public void moveUpOnSphere(double angle) {
        super.moveUpOnSphere(angle);
        point.movePointToOtherPointCoordinates(new Point(position));
    }

    @Override
    public void moveRightOnSphere(double angle) {
        super.moveRightOnSphere(angle);
        point.movePointToOtherPointCoordinates(new Point(position));
    }

    @Override
    public void setRotation(double rotationAngle, double tiltingAngle, double yawingAngle) {
        super.setRotation(rotationAngle, tiltingAngle, yawingAngle);
        point.movePointToOtherPointCoordinates(new Point(position));

    }

    public Point getPoint() {
        return point;
    }
}
