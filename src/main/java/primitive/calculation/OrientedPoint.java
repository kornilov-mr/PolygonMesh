package primitive.calculation;

import primitive.OrientedObject;
import utils.vectors.Vector3D;

public class OrientedPoint extends OrientedObject {
    private final Point point;
    public OrientedPoint(Point point, double horizontalAngle, double verticalAngle) {
        super(new Vector3D(point), horizontalAngle, verticalAngle);
        this.point=point;
    }

    @Override
    public void moveUp(double distance) {
        point.movePointToOtherPointCoordinates(new Point(new Vector3D(point).add(aboveVector.multiply(distance))));
    }

    @Override
    public void moveRight(double distance) {
        point.movePointToOtherPointCoordinates(new Point(new Vector3D(point).add(rightVector.multiply(distance))));

    }

    @Override
    public void moveFront(double distance) {
        point.movePointToOtherPointCoordinates(new Point(new Vector3D(point).add(frontVector.multiply(distance))));

    }

    public Point getPoint() {
        return point;
    }
}
