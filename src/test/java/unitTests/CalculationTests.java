package unitTests;

import org.junit.Assert;
import org.junit.Test;
import primitive.calculation.Point;
import utils.Calculation;
import utils.line.Line;
import utils.triangle.Triangle;
import utils.vectors.Vector3D;

public class CalculationTests {

    @Test
    public void ifPointInTriangleTest(){
        Point point= new Point(4,2,0);
        boolean result= point.ifPointInTriangle(new Triangle(
                new Point(0,0,0),
                new Point(4,4,0),
                new Point(8,0,0)));
        Assert.assertTrue(result);
    }
    @Test
    public void QuaternionRation(){
        Vector3D endPointVectorDesirable = new Vector3D(1,Math.sqrt(2),1);

        Vector3D pointVector = new Vector3D(2,0,0);
        Vector3D axisVector = new Vector3D(Math.sqrt(2),0,Math.sqrt(2));
        double angle = Math.PI/2;

        Vector3D endPointVector = pointVector.rotateVectorAroundCertainAxis(axisVector,angle);

        System.out.println(endPointVector);
        Assert.assertTrue(endPointVector.vectorEquals(endPointVectorDesirable));
    }
    @Test
    public void getIntersectionBetweenTwoLinesTest(){
        Line line1 = new Line(new Vector3D(0,0,0), new Vector3D(0,1,0));
        Line line2 = new Line(new Vector3D(1,0,0), new Vector3D(-1,1,0));
        Point point = line1.getIntersectionWithLine(line2);
        boolean result = true;
        if(point.getX()!=0.0){
            result=false;
        }
        if(point.getY()!=1.0){
            result=false;
        }
        if(point.getX()!=0.0){
            result=false;
        }
        Assert.assertTrue(result);
    }
}
