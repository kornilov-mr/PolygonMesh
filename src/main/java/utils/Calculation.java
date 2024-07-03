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
    public static Vector3D rotateVectorAroundCertainAxis(Vector3D pointVector1, Vector3D axisVector1,double angle){
        Vector3D pointVector = new Vector3D(pointVector1);
        Vector3D axisVector = new Vector3D(axisVector1);
        angle = angle/2;
        axisVector=axisVector.normalized();
        Quaternion pointQuaternion= new Quaternion(pointVector);
        Quaternion axisQuaternion= (new Quaternion(Math.cos(angle),axisVector.multiply(Math.sin(angle))));

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
    public static Point closestPointToLine(Point point, Line line){
        CoordinateForm coordinateForm = new CoordinateForm(line.directionVector,
                new Vector3D(point).multiply(line.directionVector).multiply(-1).getSum());
        Point pointT = coordinateForm.getPointOnIntersection(line);

        return pointT;
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
