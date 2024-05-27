package unitTests;

import org.junit.Assert;
import org.junit.Test;
import primitive.Point;
import utils.Calculation;

public class CalculationTests {

    @Test
    public void ifPointInTriangleTest(){
        boolean result= Calculation.ifPointInTriangle(new Point(4,2,0),
                new Point(0,0,0),
                new Point(4,4,0),
                new Point(8,0,0));
        Assert.assertTrue(result);
    }
}
