package core.render.GPU.shaders;

import core.camera.Camera;
import core.render.RenderConfig;
import core.scene.Scene;
import org.jocl.Pointer;
import org.jocl.Sizeof;
import org.jocl.cl_mem;
import primitive.faces.Polygon;
import utils.line.Line;

import java.awt.*;
import java.io.File;

import static org.jocl.CL.*;

public class IntersectionShader extends ShaderRunner{
    private final RenderConfig renderConfig;
    private final Camera camera;
    private int[] RGBColors;
    private Pointer RGBColorsPt;
    private cl_mem RGBColorsMem;
    public IntersectionShader(File shaderFile, RenderConfig renderConfig, Camera camera) {
        super(shaderFile);
        this.renderConfig=renderConfig;
        this.camera = camera;
        this.RGBColors= new int[renderConfig.pixelCount];
        this.RGBColorsPt=  Pointer.to(RGBColors);
        this.RGBColorsMem= clCreateBuffer(context,
                CL_MEM_WRITE_ONLY,
                (long) Sizeof.cl_int * renderConfig.pixelCount, null, null);
    }
    public Color[][] colors(Line[][] lines,Scene scene){



        double[] xRay = new double[renderConfig.pixelCount];
        double[] yRay = new double[renderConfig.pixelCount];
        double[] zRay = new double[renderConfig.pixelCount];

        for(int i=0;i<renderConfig.resolution[0];i++){
            for(int j=0;j<renderConfig.resolution[1];j++){
                xRay[i*renderConfig.resolution[1]+j]=lines[i][j].directionVector.getX();
                yRay[i*renderConfig.resolution[1]+j]=lines[i][j].directionVector.getY();
                zRay[i*renderConfig.resolution[1]+j]=lines[i][j].directionVector.getZ();
            }
        }



        double[] ACoordinateFrom = new double[scene.getPolygons().size()];
        double[] BCoordinateFrom = new double[scene.getPolygons().size()];
        double[] CCoordinateFrom = new double[scene.getPolygons().size()];
        double[] DCoordinateFrom = new double[scene.getPolygons().size()];
        int[] polygonColor = new int[scene.getPolygons().size()];

        double[] x1 = new double[scene.getPolygons().size()];
        double[] y1 = new double[scene.getPolygons().size()];
        double[] z1 = new double[scene.getPolygons().size()];

        double[] x2 = new double[scene.getPolygons().size()];
        double[] y2 = new double[scene.getPolygons().size()];
        double[] z2 = new double[scene.getPolygons().size()];

        double[] x3 = new double[scene.getPolygons().size()];
        double[] y3 = new double[scene.getPolygons().size()];
        double[] z3 = new double[scene.getPolygons().size()];

        int polygonCount = scene.getPolygons().size();
        for(int i=0;i<polygonCount;i++){
            Polygon polygon = scene.getPolygons().get(i);
            ACoordinateFrom[i]=polygon.coordinateForm.A;
            BCoordinateFrom[i]=polygon.coordinateForm.B;
            CCoordinateFrom[i]=polygon.coordinateForm.C;
            DCoordinateFrom[i]=polygon.coordinateForm.D;
            polygonColor[i]=polygon.color.getRGB();

            x1[i]=polygon.pointA.getX();
            y1[i]=polygon.pointA.getY();
            z1[i]=polygon.pointA.getZ();

            x2[i]=polygon.pointB.getX();
            y2[i]=polygon.pointB.getY();
            z2[i]=polygon.pointB.getZ();

            x3[i]=polygon.pointC.getX();
            y3[i]=polygon.pointC.getY();
            z3[i]=polygon.pointC.getZ();
        }

        cl_mem xRayMem = clCreateBuffer(context,
                CL_MEM_READ_ONLY | CL_MEM_COPY_HOST_PTR,
                (long) Sizeof.cl_double * renderConfig.pixelCount, Pointer.to(xRay), null);
        cl_mem yRayMem = clCreateBuffer(context,
                CL_MEM_READ_ONLY | CL_MEM_COPY_HOST_PTR,
                (long) Sizeof.cl_double * renderConfig.pixelCount, Pointer.to(yRay), null);
        cl_mem zRayMem = clCreateBuffer(context,
                CL_MEM_READ_ONLY | CL_MEM_COPY_HOST_PTR,
                (long) Sizeof.cl_double * renderConfig.pixelCount, Pointer.to(zRay), null);

        cl_mem ACoordinateFromMem = clCreateBuffer(context,
                CL_MEM_READ_ONLY | CL_MEM_COPY_HOST_PTR,
                (long) Sizeof.cl_double * polygonCount, Pointer.to(ACoordinateFrom), null);
        cl_mem BCoordinateFromMem = clCreateBuffer(context,
                CL_MEM_READ_ONLY | CL_MEM_COPY_HOST_PTR,
                (long) Sizeof.cl_double * polygonCount, Pointer.to(BCoordinateFrom), null);
        cl_mem CCoordinateFromMem = clCreateBuffer(context,
                CL_MEM_READ_ONLY | CL_MEM_COPY_HOST_PTR,
                (long) Sizeof.cl_double * polygonCount, Pointer.to(CCoordinateFrom), null);
        cl_mem DCoordinateFromMem = clCreateBuffer(context,
                CL_MEM_READ_ONLY | CL_MEM_COPY_HOST_PTR,
                (long) Sizeof.cl_double * polygonCount, Pointer.to(DCoordinateFrom), null);
        cl_mem polygonColorMem = clCreateBuffer(context,
                CL_MEM_READ_ONLY | CL_MEM_COPY_HOST_PTR,
                (long) Sizeof.cl_int * polygonCount, Pointer.to(polygonColor), null);

        cl_mem x1Mem = clCreateBuffer(context,
                CL_MEM_READ_ONLY | CL_MEM_COPY_HOST_PTR,
                (long) Sizeof.cl_double * polygonCount, Pointer.to(x1), null);
        cl_mem y1Mem = clCreateBuffer(context,
                CL_MEM_READ_ONLY | CL_MEM_COPY_HOST_PTR,
                (long) Sizeof.cl_double * polygonCount, Pointer.to(y1), null);
        cl_mem z1Mem = clCreateBuffer(context,
                CL_MEM_READ_ONLY | CL_MEM_COPY_HOST_PTR,
                (long) Sizeof.cl_double * polygonCount, Pointer.to(z1), null);

        cl_mem x2Mem = clCreateBuffer(context,
                CL_MEM_READ_ONLY | CL_MEM_COPY_HOST_PTR,
                (long) Sizeof.cl_double * polygonCount, Pointer.to(x2), null);
        cl_mem y2Mem = clCreateBuffer(context,
                CL_MEM_READ_ONLY | CL_MEM_COPY_HOST_PTR,
                (long) Sizeof.cl_double * polygonCount, Pointer.to(y2), null);
        cl_mem z2Mem = clCreateBuffer(context,
                CL_MEM_READ_ONLY | CL_MEM_COPY_HOST_PTR,
                (long) Sizeof.cl_double * polygonCount, Pointer.to(z2), null);

        cl_mem x3Mem = clCreateBuffer(context,
                CL_MEM_READ_ONLY | CL_MEM_COPY_HOST_PTR,
                (long) Sizeof.cl_double * polygonCount, Pointer.to(x3), null);
        cl_mem y3Mem = clCreateBuffer(context,
                CL_MEM_READ_ONLY | CL_MEM_COPY_HOST_PTR,
                (long) Sizeof.cl_double * polygonCount, Pointer.to(y3), null);
        cl_mem z3Mem = clCreateBuffer(context,
                CL_MEM_READ_ONLY | CL_MEM_COPY_HOST_PTR,
                (long) Sizeof.cl_double * polygonCount, Pointer.to(z3), null);

        int a = 0;
        clSetKernelArg(kernel, a++, Sizeof.cl_double, Pointer.to(new double[]{camera.getPosition().getX()}));
        clSetKernelArg(kernel, a++, Sizeof.cl_double, Pointer.to(new double[]{camera.getPosition().getY()}));
        clSetKernelArg(kernel, a++, Sizeof.cl_double, Pointer.to(new double[]{camera.getPosition().getZ()}));

        clSetKernelArg(kernel, a++, Sizeof.cl_mem, Pointer.to(xRayMem));
        clSetKernelArg(kernel, a++, Sizeof.cl_mem, Pointer.to(yRayMem));
        clSetKernelArg(kernel, a++, Sizeof.cl_mem, Pointer.to(zRayMem));

        clSetKernelArg(kernel, a++, Sizeof.cl_mem, Pointer.to(ACoordinateFromMem));
        clSetKernelArg(kernel, a++, Sizeof.cl_mem, Pointer.to(BCoordinateFromMem));
        clSetKernelArg(kernel, a++, Sizeof.cl_mem, Pointer.to(CCoordinateFromMem));
        clSetKernelArg(kernel, a++, Sizeof.cl_mem, Pointer.to(DCoordinateFromMem));
        clSetKernelArg(kernel, a++, Sizeof.cl_mem, Pointer.to(polygonColorMem));

        clSetKernelArg(kernel, a++, Sizeof.cl_int, Pointer.to(new int[]{scene.getPolygons().size()}));


        clSetKernelArg(kernel, a++, Sizeof.cl_mem, Pointer.to(x1Mem));
        clSetKernelArg(kernel, a++, Sizeof.cl_mem, Pointer.to(y1Mem));
        clSetKernelArg(kernel, a++, Sizeof.cl_mem, Pointer.to(z1Mem));

        clSetKernelArg(kernel, a++, Sizeof.cl_mem, Pointer.to(x2Mem));
        clSetKernelArg(kernel, a++, Sizeof.cl_mem, Pointer.to(y2Mem));
        clSetKernelArg(kernel, a++, Sizeof.cl_mem, Pointer.to(z2Mem));

        clSetKernelArg(kernel, a++, Sizeof.cl_mem, Pointer.to(x3Mem));
        clSetKernelArg(kernel, a++, Sizeof.cl_mem, Pointer.to(y3Mem));
        clSetKernelArg(kernel, a++, Sizeof.cl_mem, Pointer.to(z3Mem));

        clSetKernelArg(kernel, a++, Sizeof.cl_mem, Pointer.to(RGBColorsMem));



        long global_work_size[] = new long[]{renderConfig.pixelCount};

        clEnqueueNDRangeKernel(commandQueue, kernel, 1, null,
                global_work_size, null, 0, null, null);

        clEnqueueReadBuffer(commandQueue, RGBColorsMem, CL_TRUE, 0,
                (long) Sizeof.cl_int * renderConfig.pixelCount, RGBColorsPt, 0, null, null);

        Color[][] colors = new Color[renderConfig.resolution[0]][renderConfig.resolution[1]];

        for(int pixel =0;pixel<renderConfig.pixelCount;pixel++){
            int i = pixel/renderConfig.resolution[1];
            int j = pixel%renderConfig.resolution[1];
            colors[i][j]= new Color(RGBColors[pixel]);
        }

        clReleaseMemObject(ACoordinateFromMem);
        clReleaseMemObject(BCoordinateFromMem);
        clReleaseMemObject(CCoordinateFromMem);
        clReleaseMemObject(DCoordinateFromMem);
        clReleaseMemObject(polygonColorMem);

        clReleaseMemObject(xRayMem);
        clReleaseMemObject(yRayMem);
        clReleaseMemObject(zRayMem);

        clReleaseMemObject(x1Mem);
        clReleaseMemObject(y1Mem);
        clReleaseMemObject(z1Mem);

        clReleaseMemObject(x2Mem);
        clReleaseMemObject(y2Mem);
        clReleaseMemObject(z2Mem);

        clReleaseMemObject(x3Mem);
        clReleaseMemObject(y3Mem);
        clReleaseMemObject(z3Mem);
        return colors;
    }
}
