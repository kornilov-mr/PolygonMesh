package utils.vectors;

import utils.Calculation;

public class Vector2D {
    private final double x;
    private final double y;

    public Vector2D(double x, double y) {
        this.x = x;
        this.y = y;
    }
    public Vector2D add(Vector2D vector){
        return  new Vector2D(this.x+ vector.x, this.y + vector.y);
    }
    public Vector2D division(Vector2D vector){
        return new Vector2D(this.x/ vector.x,this.y/ vector.y);
    }

    public Vector2D division(double factor){
        return new Vector2D(this.x/factor,this.y/factor);
    }
    public Vector2D multiply(double factor){
        return new Vector2D(this.x*factor, this.y*factor);
    }
    public Vector2D multiply(Vector2D otherVector){
        return new Vector2D(this.x*otherVector.x,this.y*otherVector.y);
    }
    public Vector3D addZ(double z){
        return new Vector3D(this.x,this.y,z);
    }
    public double getLength(){
        return Math.sqrt(Math.pow(this.x,2)+ Math.pow(this.y,2));
    }

    public Vector2D getNormalized() {
        return this.division(this.getLength());
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
    public boolean vectorEquals(Vector2D otherVector){
        if(!Calculation.CompareWithRound(x,otherVector.getX(),5)){
            return false;
        }
        if(!Calculation.CompareWithRound(y,otherVector.getY(),5)){
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Vector2D{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
