package primitive;

import primitive.calculation.Point;
import utils.Calculation;
import utils.vectors.Vector3D;

public abstract class OrientedObject {
    protected Vector3D position;
    protected double horizontalAngle;
    protected double verticalAngle;

    protected Vector3D frontVector;
    protected Vector3D aboveVector;
    protected Vector3D rightVector;

    protected final Point center;
    public OrientedObject(Vector3D position){
        this(position,0,0, new Point(0,0,0));
    }
    public OrientedObject(Vector3D position, double horizontalAngle, double verticalAngle) {
        this(position,horizontalAngle,verticalAngle,new Point(0,0,0));
    }
    public OrientedObject(Vector3D position, double horizontalAngle, double verticalAngle,Point center) {
        this.position = position;
        this.horizontalAngle = horizontalAngle;
        this.verticalAngle = verticalAngle;
        this.center=center;
        setVectorsFromAngles(horizontalAngle,verticalAngle);
    }
    public void moveUp(double distance){
        position=position.add(aboveVector.multiply(distance));
    }
    public void moveRight(double distance){
        position=position.add(rightVector.multiply(distance));
    }
    public void moveFront(double distance){
        position=position.add(frontVector.multiply(distance));
    }
    public void moveUpOnSphere(double angle){
        if(angle==0){
            return;
        }
        Vector3D radiosVector = center.VectorToPoint( new Point(position));
        radiosVector =radiosVector.rotateVectorAroundCertainAxis(rightVector,angle);
        position=new Vector3D(center).add(radiosVector);
        verticalAngle+=angle;
        setVectorsFromAngles(horizontalAngle,verticalAngle);

    }
    public void moveRightOnSphere(double angle){
        if(angle==0){
            return;
        }
        Vector3D radiosVector = center.VectorToPoint( new Point(position));
        radiosVector =radiosVector.rotateY(angle);
        position=new Vector3D(center).add(radiosVector);
        horizontalAngle+=angle;
        setVectorsFromAngles(horizontalAngle,verticalAngle);
    }
    public void rotateAroundTheCenter(double angle){
        Vector3D radiosVector = center.VectorToPoint( new Point(position));
        radiosVector =radiosVector.rotateY(angle);
        position=new Vector3D(center).add(radiosVector);
        horizontalAngle+=angle;
        setVectorsFromAngles(horizontalAngle,verticalAngle);
    }
    public void tiltAroundTheCenter(double angle){
        Vector3D radiosVector = center.VectorToPoint( new Point(position));
        radiosVector =radiosVector.rotateZ(angle);
        position=new Vector3D(center).add(radiosVector);
        horizontalAngle+=angle;
        setVectorsFromAngles(horizontalAngle,verticalAngle);
    }
    public void yawAroundTheCenter(double angle){
        Vector3D radiosVector = center.VectorToPoint( new Point(position));
        radiosVector =radiosVector.rotateX(angle);
        position=new Vector3D(center).add(radiosVector);
        horizontalAngle+=angle;
        setVectorsFromAngles(horizontalAngle,verticalAngle);
    }
    public void setRotation(double rotationAngle, double tiltingAngle, double yawingAngle){
        rotateAroundTheCenter(rotationAngle);
        tiltAroundTheCenter(tiltingAngle);
        yawAroundTheCenter(yawingAngle);
    }
    private void moveAroundSphereOnVerticalAngle(double verticalAngle){
        if(verticalAngle==0){
            return;
        }
        this.frontVector=frontVector.rotateVectorAroundCertainAxis(rightVector,verticalAngle);
        this.aboveVector=frontVector.rotateVectorAroundCertainAxis(rightVector,Math.PI/2);
//        setAnglesFromFrontVector();

    }

    private void moveAroundSphereOnHorizontalAngle(double horizontalAngle){
        if(horizontalAngle==0){
            return;
        }
        this.frontVector=frontVector.rotateVectorAroundCertainAxis(aboveVector,horizontalAngle*-1);
        this.rightVector=frontVector.rotateVectorAroundCertainAxis(aboveVector,Math.PI/2*-1);
//        setAnglesFromFrontVector();
    }

    public void setVectorsFromAngles(double horizontalAngle, double verticalAngle){
        this.frontVector = new Vector3D(1,0,0);
        this.aboveVector = new Vector3D(0,-1,0);
        this.rightVector = new Vector3D(0,0,-1);
        moveAroundSphereOnHorizontalAngle(horizontalAngle);
        moveAroundSphereOnVerticalAngle(verticalAngle);
    }
    private void setAnglesFromFrontVector(){
        if(frontVector.getY()<=0){
            this.verticalAngle = -1*Math.abs(Math.atan(frontVector.getY()/frontVector.getX()));
        }else{
            this.verticalAngle = Math.abs(Math.atan(frontVector.getY()/frontVector.getX()));
        }
        if(frontVector.getX()<=0){
            this.horizontalAngle = Math.PI+(Math.PI-Math.acos(frontVector.getZ()/frontVector.getLength()));
        }else{
            this.horizontalAngle = Math.acos(frontVector.getZ()/frontVector.getLength());
        }
    }

    public double getHorizontalAngle() {
        return horizontalAngle;
    }
    public double getVerticalAngle() {
        return verticalAngle;
    }
    public Vector3D getFrontVector() {
        return frontVector;
    }
    public Vector3D getAboveVector() {
        return aboveVector;
    }
    public Vector3D getRightVector() {
        return rightVector;
    }
    public Vector3D getPosition() {
        return position;
    }
}
