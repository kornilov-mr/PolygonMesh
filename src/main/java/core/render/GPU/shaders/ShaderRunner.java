package core.render.GPU.shaders;

import core.scene.Scene;
import org.jocl.*;

import java.io.*;

import static org.jocl.CL.*;
import static org.jocl.CL.clCreateCommandQueueWithProperties;

public abstract class ShaderRunner {
    //Abstract class, witch sets up kernel and context from openCl
    private void setUpKernel(File shaderFile){
        String source =
                readFile(shaderFile.getAbsolutePath());

        // Create the program
        cl_program program = clCreateProgramWithSource(context, 1,
                new String[]{ source }, null, null);

        clBuildProgram(program, 0, null, null, null, null);

        cl_kernel kernel = clCreateKernel(program, "calculatePointVector", null);
        this.kernel=kernel;
    }
    private void setUpContextAndCommandQueue(){
        final int platformIndex = 0;
        final long deviceType = CL_DEVICE_TYPE_ALL;
        final int deviceIndex = 0;

        CL.setExceptionsEnabled(true);

        int numPlatformsArray[] = new int[1];
        clGetPlatformIDs(0, null, numPlatformsArray);
        int numPlatforms = numPlatformsArray[0];

        cl_platform_id platforms[] = new cl_platform_id[numPlatforms];
        clGetPlatformIDs(platforms.length, platforms, null);
        cl_platform_id platform = platforms[platformIndex];

        cl_context_properties contextProperties = new cl_context_properties();
        contextProperties.addProperty(CL_CONTEXT_PLATFORM, platform);

        int numDevicesArray[] = new int[1];
        clGetDeviceIDs(platform, deviceType, 0, null, numDevicesArray);
        int numDevices = numDevicesArray[0];

        cl_device_id devices[] = new cl_device_id[numDevices];
        clGetDeviceIDs(platform, deviceType, numDevices, devices, null);
        cl_device_id device = devices[deviceIndex];

        cl_context context = clCreateContext(
                contextProperties, 1, new cl_device_id[]{device},
                null, null, null);
        this.context=context;

        cl_queue_properties properties = new cl_queue_properties();
        cl_command_queue commandQueue = clCreateCommandQueueWithProperties(
                context, device, properties, null);
        this.commandQueue=commandQueue;
    }
    protected cl_kernel kernel;
    protected cl_context context;
    protected cl_command_queue commandQueue;

    public ShaderRunner(File shaderFile){
        setUpContextAndCommandQueue();
        setUpKernel(shaderFile);
    }

    public abstract int[] run(Scene scene);
    private String readFile(String fileName)
    {
        BufferedReader br = null;
        try
        {
            br = new BufferedReader(
                    new InputStreamReader(new FileInputStream(fileName)));
            StringBuffer sb = new StringBuffer();
            String line = null;
            while (true)
            {
                line = br.readLine();
                if (line == null)
                {
                    break;
                }
                sb.append(line).append("\n");
            }
            return sb.toString();
        }
        catch (IOException e)
        {
            e.printStackTrace();
            System.exit(1);
            return null;
        }
        finally
        {
            if (br != null)
            {
                try
                {
                    br.close();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        }
    }
}
