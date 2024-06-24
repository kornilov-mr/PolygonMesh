package core.render.GPU.shaders;

import core.camera.Camera;
import core.render.RenderConfig;
import core.scene.Scene;
import org.jocl.Pointer;
import org.jocl.Sizeof;
import org.jocl.cl_mem;
import primitive.Point;
import primitive.Sphere;
import primitive.faces.Polygon;
import utils.line.Line;
import utils.vectors.Vector3D;

import java.awt.*;
import java.io.File;
import java.util.Date;
import java.util.Iterator;

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
                CL_MEM_WRITE_ONLY|CL_MEM_ALLOC_HOST_PTR ,
                (long) Sizeof.cl_int * renderConfig.pixelCount, null, null);
    }
    public int[] colors(Scene scene){

        int polygonCount = scene.getPolygons().size();

        double[] ACoordinateFrom = new double[polygonCount];
        double[] BCoordinateFrom = new double[polygonCount];
        double[] CCoordinateFrom = new double[polygonCount];
        double[] DCoordinateFrom = new double[polygonCount];
        int[] polygonColor = new int[polygonCount];

        double[] x1 = new double[polygonCount];
        double[] y1 = new double[polygonCount];
        double[] z1 = new double[polygonCount];

        double[] x2 = new double[polygonCount];
        double[] y2 = new double[polygonCount];
        double[] z2 = new double[polygonCount];

        double[] x3 = new double[polygonCount];
        double[] y3 = new double[polygonCount];
        double[] z3 = new double[polygonCount];

        for(int i=0;i<polygonCount;i++){
            Polygon polygon = scene.getPolygons().get(i);
            ACoordinateFrom[i]=polygon.getCoordinateForm().A;
            BCoordinateFrom[i]=polygon.getCoordinateForm().B;
            CCoordinateFrom[i]=polygon.getCoordinateForm().C;
            DCoordinateFrom[i]=polygon.getCoordinateForm().D;
            polygonColor[i]=polygon.mainColor.getRGB();

            x1[i]=polygon.getPointA().getX();
            y1[i]=polygon.getPointA().getY();
            z1[i]=polygon.getPointA().getZ();

            x2[i]=polygon.getPointB().getX();
            y2[i]=polygon.getPointB().getY();
            z2[i]=polygon.getPointB().getZ();

            x3[i]=polygon.getPointC().getX();
            y3[i]=polygon.getPointC().getY();
            z3[i]=polygon.getPointC().getZ();
        }

        int pointCount = scene.getPoints().size();

        double[] xSphere = new double[pointCount];
        double[] ySphere = new double[pointCount];
        double[] zSphere = new double[pointCount];
        double[] SphereSize = new double[pointCount];

        int[] SphereColor = new int[pointCount];

        Iterator<Point> it = scene.getPoints().values().iterator();
        for(int i=0;i<pointCount;i++){
            Point point = it.next();
            Sphere sphere = new Sphere(point);

            xSphere[i]= sphere.x;
            ySphere[i]= sphere.y;
            zSphere[i]= sphere.z;
            SphereSize[i]= sphere.size;
            SphereColor[i]=sphere.getColor().getRGB();
        }

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

        cl_mem xSphereMem = clCreateBuffer(context,
                CL_MEM_READ_ONLY | CL_MEM_COPY_HOST_PTR,
                (long) Sizeof.cl_double * pointCount, Pointer.to(xSphere), null);
        cl_mem ySphereMem = clCreateBuffer(context,
                CL_MEM_READ_ONLY | CL_MEM_COPY_HOST_PTR,
                (long) Sizeof.cl_double * pointCount, Pointer.to(ySphere), null);
        cl_mem zSphereMem = clCreateBuffer(context,
                CL_MEM_READ_ONLY | CL_MEM_COPY_HOST_PTR,
                (long) Sizeof.cl_double * pointCount, Pointer.to(zSphere), null);
        cl_mem SphereSizeMem = clCreateBuffer(context,
                CL_MEM_READ_ONLY | CL_MEM_COPY_HOST_PTR,
                (long) Sizeof.cl_double * pointCount, Pointer.to(SphereSize), null);
        cl_mem SphereColorMem = clCreateBuffer(context,
                CL_MEM_READ_ONLY | CL_MEM_COPY_HOST_PTR,
                (long) Sizeof.cl_int * pointCount, Pointer.to(SphereColor), null);

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
        clSetKernelArg(kernel, a++, Sizeof.cl_double, Pointer.to(new double[]{camera.getFrontVector().getX()}));
        clSetKernelArg(kernel, a++, Sizeof.cl_double, Pointer.to(new double[]{camera.getFrontVector().getY()}));
        clSetKernelArg(kernel, a++, Sizeof.cl_double, Pointer.to(new double[]{camera.getFrontVector().getZ()}));

        clSetKernelArg(kernel, a++, Sizeof.cl_double, Pointer.to(new double[]{camera.getAboveVector().getX()}));
        clSetKernelArg(kernel, a++, Sizeof.cl_double, Pointer.to(new double[]{camera.getAboveVector().getY()}));
        clSetKernelArg(kernel, a++, Sizeof.cl_double, Pointer.to(new double[]{camera.getAboveVector().getZ()}));

        Vector3D cameraRightVector= new Vector3D(camera.getRightVector().getX(),0,camera.getRightVector().getZ()).normalized();

        clSetKernelArg(kernel, a++, Sizeof.cl_double, Pointer.to(new double[]{cameraRightVector.getX()}));
        clSetKernelArg(kernel, a++, Sizeof.cl_double, Pointer.to(new double[]{cameraRightVector.getY()}));
        clSetKernelArg(kernel, a++, Sizeof.cl_double, Pointer.to(new double[]{cameraRightVector.getZ()}));

        clSetKernelArg(kernel, a++, Sizeof.cl_int, Pointer.to(new int[]{renderConfig.resolution[0]}));
        clSetKernelArg(kernel, a++, Sizeof.cl_int, Pointer.to(new int[]{renderConfig.resolution[1]}));

        clSetKernelArg(kernel, a++, Sizeof.cl_double, Pointer.to(new double[]{renderConfig.pseudoRectangleWidth}));
        clSetKernelArg(kernel, a++, Sizeof.cl_double, Pointer.to(new double[]{renderConfig.pseudoRectangleHeight}));

        clSetKernelArg(kernel, a++, Sizeof.cl_double, Pointer.to(new double[]{camera.getPosition().getX()}));
        clSetKernelArg(kernel, a++, Sizeof.cl_double, Pointer.to(new double[]{camera.getPosition().getY()}));
        clSetKernelArg(kernel, a++, Sizeof.cl_double, Pointer.to(new double[]{camera.getPosition().getZ()}));

        clSetKernelArg(kernel, a++, Sizeof.cl_mem, Pointer.to(ACoordinateFromMem));
        clSetKernelArg(kernel, a++, Sizeof.cl_mem, Pointer.to(BCoordinateFromMem));
        clSetKernelArg(kernel, a++, Sizeof.cl_mem, Pointer.to(CCoordinateFromMem));
        clSetKernelArg(kernel, a++, Sizeof.cl_mem, Pointer.to(DCoordinateFromMem));
        clSetKernelArg(kernel, a++, Sizeof.cl_mem, Pointer.to(polygonColorMem));

        clSetKernelArg(kernel, a++, Sizeof.cl_int, Pointer.to(new int[]{polygonCount}));

        clSetKernelArg(kernel, a++, Sizeof.cl_mem, Pointer.to(xSphereMem));
        clSetKernelArg(kernel, a++, Sizeof.cl_mem, Pointer.to(ySphereMem));
        clSetKernelArg(kernel, a++, Sizeof.cl_mem, Pointer.to(zSphereMem));
        clSetKernelArg(kernel, a++, Sizeof.cl_mem, Pointer.to(SphereSizeMem));
        clSetKernelArg(kernel, a++, Sizeof.cl_mem, Pointer.to(SphereColorMem));

        clSetKernelArg(kernel, a++, Sizeof.cl_int, Pointer.to(new int[]{pointCount}));

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
        clFinish(commandQueue);
        clEnqueueReadBuffer(commandQueue, RGBColorsMem, CL_TRUE, 0,
                (long) Sizeof.cl_int * renderConfig.pixelCount, RGBColorsPt, 0, null, null);
        clReleaseMemObject(ACoordinateFromMem);
        clReleaseMemObject(BCoordinateFromMem);
        clReleaseMemObject(CCoordinateFromMem);
        clReleaseMemObject(DCoordinateFromMem);
        clReleaseMemObject(polygonColorMem);

        clReleaseMemObject(x1Mem);
        clReleaseMemObject(y1Mem);
        clReleaseMemObject(z1Mem);

        clReleaseMemObject(x2Mem);
        clReleaseMemObject(y2Mem);
        clReleaseMemObject(z2Mem);

        clReleaseMemObject(x3Mem);
        clReleaseMemObject(y3Mem);
        clReleaseMemObject(z3Mem);

        return RGBColors;
    }
}
