package core.render.GPU.shaders;

import core.camera.Camera;
import core.render.RenderConfig;
import org.jocl.*;
import utils.line.Line;
import utils.vectors.Vector3D;

import java.io.*;

import static org.jocl.CL.*;

public class VectorCalculationShader extends ShaderRunner {

    private final Camera camera;
    private final RenderConfig renderConfig;


    private double[] xPoint;
    private double[] yPoint;
    private double[] zPoint;
    private Pointer xPointPt;
    private Pointer yPointPt;
    private Pointer zPointPt;

    private cl_mem xPointMen;
    private cl_mem yPointMen;
    private cl_mem zPointMen;

    public VectorCalculationShader(File shaderFile,Camera camera, RenderConfig renderConfig) {
        super(shaderFile);
        this.camera = camera;
        this.renderConfig = renderConfig;
        this.xPoint= new double[renderConfig.resolution[0]*renderConfig.resolution[1]];
        this.yPoint= new double[renderConfig.resolution[0]*renderConfig.resolution[1]];
        this.zPoint= new double[renderConfig.resolution[0]*renderConfig.resolution[1]];
        this.xPointPt=  Pointer.to(xPoint);
        this.yPointPt=  Pointer.to(yPoint);
        this.zPointPt=  Pointer.to(zPoint);

        this.xPointMen= clCreateBuffer(context,
                CL_MEM_READ_WRITE,
                (long) Sizeof.cl_double * renderConfig.pixelCount, null, null);
        this.yPointMen= clCreateBuffer(context,
                CL_MEM_READ_WRITE,
                (long) Sizeof.cl_double * renderConfig.pixelCount, null, null);
        this.zPointMen= clCreateBuffer(context,
                CL_MEM_READ_WRITE,
                (long) Sizeof.cl_double * renderConfig.pixelCount, null, null);
    }

    public Line[][] RayLines(){

        int a = 0;
        clSetKernelArg(kernel, a++, Sizeof.cl_double, Pointer.to(new double[]{camera.getFrontVector().getX()}));
        clSetKernelArg(kernel, a++, Sizeof.cl_double, Pointer.to(new double[]{camera.getFrontVector().getY()}));
        clSetKernelArg(kernel, a++, Sizeof.cl_double, Pointer.to(new double[]{camera.getFrontVector().getZ()}));

        clSetKernelArg(kernel, a++, Sizeof.cl_double, Pointer.to(new double[]{camera.getAboveVector().getX()}));
        clSetKernelArg(kernel, a++, Sizeof.cl_double, Pointer.to(new double[]{camera.getAboveVector().getY()}));
        clSetKernelArg(kernel, a++, Sizeof.cl_double, Pointer.to(new double[]{camera.getAboveVector().getZ()}));

        clSetKernelArg(kernel, a++, Sizeof.cl_double, Pointer.to(new double[]{camera.getRightVector().getX()}));
        clSetKernelArg(kernel, a++, Sizeof.cl_double, Pointer.to(new double[]{camera.getRightVector().getY()}));
        clSetKernelArg(kernel, a++, Sizeof.cl_double, Pointer.to(new double[]{camera.getRightVector().getZ()}));

        clSetKernelArg(kernel, a++, Sizeof.cl_int, Pointer.to(new int[]{renderConfig.resolution[0]}));
        clSetKernelArg(kernel, a++, Sizeof.cl_int, Pointer.to(new int[]{renderConfig.resolution[1]}));

        clSetKernelArg(kernel, a++, Sizeof.cl_double, Pointer.to(new double[]{renderConfig.pseudoRectangleWidth}));
        clSetKernelArg(kernel, a++, Sizeof.cl_double, Pointer.to(new double[]{renderConfig.pseudoRectangleHeight}));

        clSetKernelArg(kernel, a++, Sizeof.cl_mem, Pointer.to(xPointMen));
        clSetKernelArg(kernel, a++, Sizeof.cl_mem, Pointer.to(yPointMen));
        clSetKernelArg(kernel, a++, Sizeof.cl_mem, Pointer.to(zPointMen));


        long global_work_size[] = new long[]{renderConfig.pixelCount};

        clEnqueueNDRangeKernel(commandQueue, kernel, 1, null,
                global_work_size, null, 0, null, null);

        clEnqueueReadBuffer(commandQueue, xPointMen, CL_TRUE, 0,
                (long) Sizeof.cl_double * renderConfig.pixelCount, xPointPt, 0, null, null);

        clEnqueueReadBuffer(commandQueue, yPointMen, CL_TRUE, 0,
                (long) Sizeof.cl_double * renderConfig.pixelCount, yPointPt, 0, null, null);

        clEnqueueReadBuffer(commandQueue, zPointMen, CL_TRUE, 0,
                (long) Sizeof.cl_double * renderConfig.pixelCount, zPointPt, 0, null, null);

        Line[][] lines = new Line[renderConfig.resolution[0]][renderConfig.resolution[1]];

        for(int pixel =0;pixel<renderConfig.pixelCount;pixel++){
            int i = pixel/renderConfig.resolution[1];
            int j = pixel%renderConfig.resolution[1];
            lines[i][j]= new Line(camera.getPosition(),new Vector3D(xPoint[pixel],yPoint[pixel],zPoint[pixel]));
        }

        return lines;
    }

}
