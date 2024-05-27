package unitTests;

import org.junit.Assert;
import org.junit.Test;
import utils.vectors.Vector2D;

public class Vector2DTests {
    public boolean VectorEquals(Vector2D vector1, Vector2D vector2){
        if(vector2.getX()!=vector1.getX()){
            return false;
        }
        if(vector2.getY()!=vector1.getY()){
            return false;
        }
        return true;
    }
    @Test
    public void Vector2DDivisionFactorTest(){
        Vector2D vectorInput = new Vector2D(2,2);
        Vector2D vectorDesirable = new Vector2D(1,1);

        Vector2D vectorResult = vectorInput.division(2);
        Assert.assertTrue(VectorEquals(vectorResult,vectorDesirable));
    }
    @Test
    public void Vector2DDivisionVectorTest(){
        Vector2D vectorInput1 = new Vector2D(4,8);
        Vector2D vectorInput2 = new Vector2D(4,2);
        Vector2D vectorDesirable = new Vector2D(1,4);

        Vector2D vectorResult = vectorInput1.division(vectorInput2);
        Assert.assertTrue(VectorEquals(vectorResult,vectorDesirable));
    }
    @Test
    public void Vector2DGetLengthTest(){
        Vector2D vectorInput1 = new Vector2D(3,4);
        double lengthDesirable= 5;
        Assert.assertEquals(lengthDesirable,vectorInput1.getLength());
    }
    @Test
    public void Vector2DNormalizeTest(){
        Vector2D vectorInput = new Vector2D(3,4);
        Vector2D vectorDesirable = new Vector2D((double) 3 /5,(double) 4 /5);

        Vector2D vectorResult = vectorInput.getNormalized();
        Assert.assertTrue(VectorEquals(vectorResult,vectorDesirable));
    }

}
