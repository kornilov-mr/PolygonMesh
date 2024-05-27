package unitTests;

import org.junit.Assert;
import org.junit.Test;
import utils.vectors.Vector3D;

public class Vector3DTests {
    public boolean VectorEquals(Vector3D vector1, Vector3D vector2){
        if(vector2.getX()!=vector1.getX()){
            return false;
        }
        if(vector2.getY()!=vector1.getY()){
            return false;
        }
        if(vector2.getZ()!= vector1.getZ()){
            return false;
        }
        return true;
    }
    @Test
    public void Vector3DDivisionFactorTest(){
        Vector3D vectorInput = new Vector3D(2,2,2);
        Vector3D vectorDesirable = new Vector3D(1,1,1);

        Vector3D vectorResult = vectorInput.division(2);
        Assert.assertTrue(VectorEquals(vectorResult,vectorDesirable));
    }
    @Test
    public void Vector3DGetLengthTest(){
        Vector3D vectorInput1 = new Vector3D(3,4,5);
        double lengthDesirable= Math.sqrt(50);
        Assert.assertEquals(lengthDesirable,vectorInput1.getLength());
    }
    @Test
    public void Vector3DNormalizeTest(){
        Vector3D vectorInput = new Vector3D(3,4,5);
        double length = vectorInput.getLength();
        Vector3D vectorDesirable = new Vector3D((double) 3 /length,(double) 4 /length,(double) 5/length);

        Vector3D vectorResult = vectorInput.normalized();
        Assert.assertTrue(VectorEquals(vectorResult,vectorDesirable));
    }
    @Test
    public void Vector3DCrossMultiplyTest(){

    }
}
