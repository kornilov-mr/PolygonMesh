package core.render.GPU.shaders;

import core.camera.Camera;
import core.render.GPU.structs.PolygonStruct;
import core.render.GPU.structs.VectorStruct;
import core.render.RenderConfig;
import core.scene.Scene;
import org.jocl.Pointer;
import org.jocl.Sizeof;
import org.jocl.cl_mem;
import org.jocl.struct.Buffers;
import org.jocl.struct.SizeofStruct;
import primitive.faces.Polygon;
import utils.line.Line;

import java.awt.*;
import java.io.File;
import java.nio.ByteBuffer;

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


        int structSize = SizeofStruct.sizeof(VectorStruct.class);
        VectorStruct[] cameraPos = new VectorStruct[]{camera.getPosition().toStruct()};
        ByteBuffer cameraPositionBuffer = Buffers.allocateBuffer(cameraPos);

        Buffers.writeToBuffer(cameraPositionBuffer, camera.getPosition().toStruct());

        cl_mem cameraPositionMem = clCreateBuffer(context,
                CL_MEM_READ_WRITE | CL_MEM_USE_HOST_PTR,
                (long) structSize * 1, Pointer.to(cameraPositionBuffer), null);


        VectorStruct[] rays = new VectorStruct[renderConfig.pixelCount];

        for(int i=0;i<renderConfig.resolution[0];i++){
            for(int j=0;j<renderConfig.resolution[1];j++){
                rays[i*renderConfig.resolution[1]+j]= lines[i][j].directionVector.toStruct();
            }
        }
        structSize = SizeofStruct.sizeof(VectorStruct.class);

        ByteBuffer raysBuffer = Buffers.allocateBuffer(rays);

        Buffers.writeToBuffer(raysBuffer, rays);

        cl_mem raysMem = clCreateBuffer(context,
                CL_MEM_READ_WRITE | CL_MEM_USE_HOST_PTR,
                (long) structSize * renderConfig.pixelCount, Pointer.to(raysBuffer), null);

        int polygonCount = scene.getPolygons().size();
        PolygonStruct[] polygonStructs = new PolygonStruct[polygonCount];
        for(int i=0;i<polygonCount;i++){
            Polygon polygon = scene.getPolygons().get(i);
            polygonStructs[i]=polygon.toStruct();
        }

        structSize = SizeofStruct.sizeof(PolygonStruct.class);
        ByteBuffer polygonBuffer = Buffers.allocateBuffer(polygonStructs);

        Buffers.writeToBuffer(polygonBuffer, polygonStructs);

        cl_mem polygonMem = clCreateBuffer(context,
                CL_MEM_READ_WRITE | CL_MEM_USE_HOST_PTR,
                (long) structSize * polygonCount, Pointer.to(polygonBuffer), null);

        int a = 0;

        clSetKernelArg(kernel, a++, Sizeof.cl_mem, Pointer.to(cameraPositionMem));

        clSetKernelArg(kernel, a++, Sizeof.cl_mem, Pointer.to(raysMem));
        clSetKernelArg(kernel, a++, Sizeof.cl_mem, Pointer.to(polygonMem));
        clSetKernelArg(kernel, a++, Sizeof.cl_int, Pointer.to(new int[]{polygonCount}));

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

        clReleaseMemObject(polygonMem);
        clReleaseMemObject(raysMem);
        return colors;
    }
}
