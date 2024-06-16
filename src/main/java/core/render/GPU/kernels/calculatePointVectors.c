



__kernel void calculatePointVector(
                     const double xFront,
                     const double yFront,
                     const double zFront,

                     const double xAbove,
                     const double yAbove,
                     const double zAbove,

                     const double xRight,
                     const double yRight,
                     const double zRight,

                     const int screenWidth,
                     const int screenHeight,

                     const double pseudoWidth,
                     const double pseudoHeight,

                    __global double *xRay,
                    __global double *yRay,
                    __global double *zRay)
{

        int i = get_global_id(0)/screenHeight;
        int j = get_global_id(0)%screenHeight;


        double fraction = (double)j / (double)screenHeight - 0.5;

        double xTemp = xFront;
        double yTemp = yFront;
        double zTemp = zFront;

        xTemp+= xAbove*fraction*pseudoHeight;
        yTemp+= yAbove*fraction*pseudoHeight;
        zTemp+= zAbove*fraction*pseudoHeight;

        fraction = (double)i / (double)screenWidth - 0.5;

        xTemp+= xRight*fraction*pseudoWidth;
        yTemp+= yRight*fraction*pseudoWidth;
        zTemp+= zRight*fraction*pseudoWidth;

        xRay[get_global_id(0)]=xTemp;
        yRay[get_global_id(0)]=yTemp;
        zRay[get_global_id(0)]=zTemp;
}