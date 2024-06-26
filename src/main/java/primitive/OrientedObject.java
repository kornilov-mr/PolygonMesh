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
