package utils.quaternion;

import utils.Calculation;
import utils.vectors.Vector3D;

import java.util.Queue;

public class Quaternion {
    private final double s;
    private final Vector3D quaternionVector;

    public Quaternion(double x, double y, double z) {
        this(0,new Vector3D( x, y, z));
    }

    public Quaternion(Vector3D vector) {
        this(0, vector);
    }

    public Quaternion(double s, Vector3D vector) {
        this.s=s;
        this.quaternionVector=vector;
    }


    public Quaternion add(Quaternion other) {
        return new Quaternion(s + other.s, quaternionVector.add(other.quaternionVector));
    }

    public Quaternion multiply(double p) {
        return new Quaternion(s * p,quaternionVector.Multiply(p));
    }
    public Quaternion division(double p){
        return new Quaternion(s/p,quaternionVector.division(p));
    }
    public Quaternion multiply(Quaternion other) {
        return new Quaternion(s * other.s, quaternionVector.Multiply(other.quaternionVector));
    }

    public Vector3D getQuaternionVector() {
        return quaternionVector;
    }

    public Quaternion crossMultiply(Quaternion other) {
        return new Quaternion(s*other.s - quaternionVector.Multiply(other.quaternionVector).getSum(),
                other.quaternionVector.Multiply(s)
                        .add(quaternionVector.Multiply(other.s))
                        .add(quaternionVector.crossMultiply(other.quaternionVector)));
    }
    public Quaternion reversed(){

        return this.reverseQuaternionVector().division(this.length());
    }
    public Quaternion normalize(){
        return new Quaternion(s,quaternionVector.normalized());
    }
    public double length(){
        return Math.sqrt(Math.pow(s,2)+Math.pow(quaternionVector.getLength(),2));
    }
    public Quaternion reverseQuaternionVector(){
        return new Quaternion(s,quaternionVector.Multiply(-1));
    }
    public boolean quaternionEquals(Quaternion otherQuaternion){
        if(!Calculation.CompareWithRound(s,otherQuaternion.s,5)){
            return false;
        }
        if(!quaternionVector.vectorEquals(otherQuaternion.quaternionVector)){
            return false;
        }
        return true;
    }
}
