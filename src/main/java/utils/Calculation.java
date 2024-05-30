package utils;

import primitive.Point;
import utils.quaternion.Quaternion;
import utils.vectors.Vector3D;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Calculation {

    public static Vector3D VectorBetweenTwoPoints(Point pointA, Point pointB){
        return new Vector3D(pointB.getX()-pointA.getX(),pointB.getY()-pointA.getY(),pointB.getZ()-pointA.getZ());
    }
    public static boolean ifPointInTriangle(Point point, Point edge1, Point edge2, Point edge3){
        double mainArea = areaOfTriangle(edge1,edge2,edge3);
        double subArea1 = areaOfTriangle(point,edge2,edge3);
        double subArea2 = areaOfTriangle(edge1,point,edge3);
        double subArea3 = areaOfTriangle(edge1,edge2,point);
        if(round(mainArea,10)==round(subArea1+subArea2+subArea3,10)){
            return true;
        }
        return false;
    }
    public static double areaOfTriangle(Point edge1, Point edge2, Point edge3){
        Vector3D vector1 = VectorBetweenTwoPoints(edge1,edge2);
        Vector3D vector2= VectorBetweenTwoPoints(edge1,edge3);
        double area = Math.abs(vector1.crossMultiply(vector2).getLength())/2;
        return area;
    }
    public static double round(double value, int digits){

        BigDecimal bd = new BigDecimal(Double.toString(value));
        bd = bd.setScale(digits, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    public static double getLengthBetweenTwoPoints(Point pointA,Point pointB){
        return VectorBetweenTwoPoints(pointA,pointB).getLength();
    }
    public static Vector3D rotateVectorAroundCertainAxis(Vector3D pointVector, Vector3D axisVector,double angle){
        angle = angle/2;
        axisVector=axisVector.normalized();
        Quaternion pointQuaternion= new Quaternion(pointVector);
        Quaternion axisQuaternion= (new Quaternion(Math.cos(angle),axisVector.Multiply(Math.sin(angle))));

        Quaternion pointDelta = axisQuaternion.crossMultiply(pointQuaternion);
        Quaternion reversed= axisQuaternion.reversed();
        Quaternion endPoint= pointDelta.crossMultiply(reversed);

        return endPoint.getQuaternionVector();
    }
    public static boolean CompareWithRound(double a,double b,int digits){
        if(round(a,digits)==round(b,digits)){
            return true;
        }
        return false;
    }

}
