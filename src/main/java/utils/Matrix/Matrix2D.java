package utils.Matrix;

public class Matrix2D {

    private final double[][] matrix;
    private final int size;
    public Matrix2D(double a1, double b1, double a2, double b2){
        this(new double[]{a1,b1}, new double[]{a2,b2});
    }
    public Matrix2D(double[] firstRow, double[] secondRow) {
        this(new double[][]{firstRow,secondRow});
    }

    public Matrix2D(double[][] matrix) {
        this.matrix = matrix;
        this.size =matrix.length;
    }

    public double determinant(){
        if(matrix.length==2){
            return matrix[0][0]*matrix[1][1]- matrix[1][0]*matrix[0][1];
        }
        //not implemented
        return -1;
    }
}
