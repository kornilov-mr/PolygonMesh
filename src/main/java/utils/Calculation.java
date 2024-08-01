package utils;

import primitive.calculation.Point;
import primitive.calculation.faces.CoordinateForm;
import utils.Matrix.Matrix2D;
import utils.line.Line;
import utils.quaternion.Quaternion;
import utils.vectors.Vector3D;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Calculation {


    public static double round(double value, int digits){
        BigDecimal bd = new BigDecimal(Double.toString(value));
        bd = bd.setScale(digits, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
    public static boolean CompareWithRound(double a,double b,int digits){
        if(round(a,digits)==round(b,digits)){
            return true;
        }
        return false;
    }
    public static Point getIntersectionBetweenTwoLines(Line line1, Line line2 ){
        double t;
        double denominator =  new Matrix2D(line1.directionVector.getX(),-1*line2.directionVector.getX(),
                line1.directionVector.getY(),-1*line2.directionVector.getY()).determinant();
        if(denominator!=0){
            t= (new Matrix2D(line2.pointVector.getX()-line1.pointVector.getX(), -1*line2.directionVector.getX(),
                            line2.pointVector.getY()-line1.pointVector.getY(), -1*line2.directionVector.getY()).determinant())/denominator;

        }else{
            denominator =  new Matrix2D(line1.directionVector.getZ(),-1*line2.directionVector.getZ(),
                    line1.directionVector.getX(),-1*line2.directionVector.getX()).determinant();

            t=(new Matrix2D(line2.pointVector.getZ()-line1.pointVector.getZ(), -1*line2.directionVector.getZ(),
                    line2.pointVector.getX()-line1.pointVector.getX(), -1*line2.directionVector.getX()).determinant())/denominator;
        }
        return new Point(line1.pointVector.add(line1.directionVector.multiply(t)));

    }

}
