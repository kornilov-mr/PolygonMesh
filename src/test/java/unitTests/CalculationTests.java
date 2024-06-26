package unitTests;

import org.junit.Assert;
import org.junit.Test;
import primitive.calculation.Point;
import utils.Calculation;
import utils.vectors.Vector3D;

public class CalculationTests {

    @Test
    public void ifPointInTriangleTest(){
        boolean result= Calculation.ifPointInTriangle(new Point(4,2,0),
                new Point(0,0,0),
                new Point(4,4,0),
                new Point(8,0,0));
        Assert.assertTrue(result);
    }
    @Test
    public void QuaternionRation(){
        Vector3D endPointVectorDesirable = new Vector3D(1,Math.sqrt(2),1);

        Vector3D pointVector = new Vector3D(2,0,0);
        Vector3D axisVector = new Vector3D(Math.sqrt(2),0,Math.sqrt(2));
        double angle = Math.PI/2;

        Vector3D endPointVector = Calculation.rotateVectorAroundCertainAxis(pointVector,axisVector,angle);

        System.out.println(endPointVector);
        Assert.assertTrue(endPointVector.vectorEquals(endPointVectorDesirable));
    }
}
