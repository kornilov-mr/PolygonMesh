package primitive;

import utils.Calculation;
import utils.vectors.Vector3D;

public abstract class OrientedObject {
    protected Vector3D position;
    protected double horizontalAngle;
    protected double verticalAngle;

    protected Vector3D frontVector;
    protected Vector3D aboveVector;
    protected Vector3D rightVector;


    public OrientedObject(Vector3D position, double horizontalAngle, double verticalAngle) {
        this.position = position;
        this.horizontalAngle = horizontalAngle;
        this.verticalAngle = verticalAngle;
        calculateVectorFromAngles(horizontalAngle,verticalAngle);
    }
    public void moveCameraUp(double distance){
        position=position.add(aboveVector.Multiply(distance));
    }
    public void moveCameraRight(double distance){
        position=position.add(rightVector.Multiply(distance));
    }
    public void moveCameraFront(double distance){
        position=position.add(frontVector.Multiply(distance));
    }

    public void moveAroundSphereOnVerticalAngle(double verticalAngle){
        if(verticalAngle==0){
            return;
        }
        this.frontVector=Calculation.rotateVectorAroundCertainAxis(frontVector,rightVector,verticalAngle);
        this.aboveVector=Calculation.rotateVectorAroundCertainAxis(frontVector,rightVector,Math.PI/2);
        setAnglesFromFrontVector();

    }

    public void moveAroundSphereOnHorizontalAngle(double horizontalAngle){
        if(horizontalAngle==0){
            return;
        }
        this.frontVector=Calculation.rotateVectorAroundCertainAxis(frontVector,aboveVector,horizontalAngle*-1);
        this.rightVector=Calculation.rotateVectorAroundCertainAxis(frontVector,aboveVector,Math.PI/2*-1);
        setAnglesFromFrontVector();
    }

    private void calculateVectorFromAngles(double horizontalAngle, double verticalAngle){
        this.frontVector = new Vector3D(0,0,1);
        this.aboveVector = new Vector3D(1,0,0);
        this.rightVector = new Vector3D(0,1,0);
        moveAroundSphereOnHorizontalAngle(horizontalAngle);
        moveAroundSphereOnVerticalAngle(verticalAngle);
    }

    private void setAnglesFromFrontVector(){
        this.horizontalAngle = Math.atan(frontVector.getY()/frontVector.getX());
        this.verticalAngle = Math.acos(frontVector.getZ()/frontVector.getLength());
    }
    public void setHorizontalAngle(double horizontalAngle) {
        this.horizontalAngle = horizontalAngle;
        calculateVectorFromAngles(horizontalAngle,verticalAngle);
    }

    public void setVerticalAngle(double verticalAngle) {
        this.verticalAngle = verticalAngle;
        calculateVectorFromAngles(horizontalAngle,verticalAngle);

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
