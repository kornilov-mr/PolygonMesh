#pragma OPENCL EXTENSION cl_khr_fp64 : enable
double square_root(double x)
{
    return sqrt(x);
}
double abs(const double a){
    if(a<0){
        return -1*a;
    }
    return a;
}
double calculate2MatrixDeterminant(const double x1,
                                   const double x2,
                                   const double y1,
                                   const double y2){
        return x1*y2-x2*y1;
}
int isPointAboveFace(const double A, const double B,const double C,const double D,
                                const double x,
                                const double y,
                                const double z){
    double sum = x*A+y*B+z*C+D;
    if(sum>0){
        return 1;
    }
    return -1;
}
double getDistance(
        const double x,
        const double y,
        const double z)
{
    return square_root(x*x+y*y+z*z);
}
void normalizeCus(double *x,
               double *y,
               double *z){
    double distance = getDistance(*x,*y,*z);
    *x=*x/distance;
    *y=*y/distance;
    *z=*z/distance;
}

double getDistanceFromTwoPoints(const double x1,
                                const double y1,
                                const double z1,

                                const double x2,
                                const double y2,
                                const double z2){
        return getDistance(x1-x2,y1-y2,z1-z2);
}
void crossMultiply(const double x1,
                     const double y1,
                     const double z1,

                     const double x2,
                     const double y2,
                     const double z2,
                     double *x3,
                     double *y3,
                     double *z3){
        *x3=y1*z2-z1*y2;
        *y3=z1*x2-x1*z2;
        *z3=x1*y2-y1*x2;
                     }
double crossProduct(
                     const double x1,
                     const double y1,
                     const double z1,

                     const double x2,
                     const double y2,
                     const double z2)
{
          return   getDistance(y1*z2-z1*y2,z1*x2-x1*z2,x1*y2-y1*x2);
}

double areaOfTriangle(
                    const double x1,
                    const double y1,
                    const double z1,

                    const double x2,
                    const double y2,
                    const double z2,

                    const double x3,
                    const double y3,
                    const double z3)
{
    return crossProduct(x2-x1,y2-y1,z2-z1,x3-x1,y3-y1,z3-z1)/2;
}
int roundNo(float num)
{
    return num < 0 ? num - 0.5 : num + 0.5;
}

bool ifPointInTriangle(
                    const double x1,
                    const double y1,
                    const double z1,

                    const double x2,
                    const double y2,
                    const double z2,

                    const double x3,
                    const double y3,
                    const double z3,

                    const double xPoint,
                    const double yPoint,
                    const double zPoint)
{
    double mainArea = areaOfTriangle(x1,y1,z1,x2,y2,z2,x3,y3,z3);
    double area1 = areaOfTriangle(xPoint,yPoint,zPoint,x2,y2,z2,x3,y3,z3);
    double area2 = areaOfTriangle(x1,y1,z1,xPoint,yPoint,zPoint,x3,y3,z3);
    double area3 = areaOfTriangle(x1,y1,z1,x2,y2,z2,xPoint,yPoint,zPoint);


    mainArea= roundNo(mainArea*10000);
    double newArea= roundNo((area1+area2+area3)*10000);
    if(mainArea==newArea){
        return true;
    }
    return false;
}

bool getIntersectionWithPane(
                        const double ACoordinateFrom,
                        const double BCoordinateFrom,
                        const double CCoordinateFrom,
                        const double DCoordinateFrom,

                         const double xCameraPosition,
                         const double yCameraPosition,
                         const double zCameraPosition,

                         const double xRay,
                         const double yRay,
                         const double zRay,

                         double *xPoint,
                         double *yPoint,
                         double *zPoint
                        ){
        __private  double r = 0;
        r += xRay * ACoordinateFrom;
        r += yRay * BCoordinateFrom;
        r += zRay * CCoordinateFrom;
        if(r==0){
             return false;
        }
        __private  double free = 0;
        free += xCameraPosition * ACoordinateFrom;
        free += yCameraPosition * BCoordinateFrom;
        free += zCameraPosition * CCoordinateFrom;
        free += DCoordinateFrom;

        r = (-1*free) / r;
        if(r<0){
              return false;
        }
        *xPoint=xRay*r+xCameraPosition;
        *yPoint=yRay*r+yCameraPosition;
        *zPoint=zRay*r+zCameraPosition;
        return true;
}
double getDistanceBetweenTwoLines(const double x1,
                                  const double y1,
                                  const double z1,

                                  const double x2,
                                  const double y2,
                                  const double z2,

                                  const double xRay,
                                  const double yRay,
                                  const double zRay,

                                  const double xCameraPosition,
                                  const double yCameraPosition,
                                  const double zCameraPosition){
       double xCross;
       double yCross;
       double zCross;
       crossMultiply(x2-x1,y2-y1,z2-z1,xRay,yRay,zRay,&xCross,&yCross,&zCross);
       normalizeCus(&xCross,&yCross,&zCross);
       return abs(xCross*(x1-xCameraPosition)+yCross*(y1-yCameraPosition)+zCross*(z1-zCameraPosition));
}

bool getClosestPointBetweenLines(const double x1,
                                 const double y1,
                                 const double z1,

                                 const double x2,
                                 const double y2,
                                 const double z2,

                                 const double xRay,
                                 const double yRay,
                                 const double zRay,

                                 const double xCameraPosition,
                                 const double yCameraPosition,
                                 const double zCameraPosition,

                                 double *xPoint,
                                 double *yPoint,
                                 double *zPoint){
       double xCross;
       double yCross;
       double zCross;
       crossMultiply(x2-x1,y2-y1,z2-z1,xRay,yRay,zRay,&xCross,&yCross,&zCross);
       double xCrossE;
       double yCrossE;
       double zCrossE;
       crossMultiply(x2-x1,y2-y1,z2-z1,xCross,yCross,zCross,&xCrossE,&yCrossE,&zCrossE);

       double length = getDistance(xCross,yCross,zCross);

       double tLine = (xCrossE*(xCameraPosition-x1) + yCrossE*(yCameraPosition-y1) + zCrossE*(zCameraPosition-z1))/(length*length);
       if(tLine<0){
        return false;
       }
       *xPoint=xCameraPosition+tLine*xRay;
       *yPoint=yCameraPosition+tLine*yRay;
       *zPoint=zCameraPosition+tLine*zRay;
       return true;
}


bool getIntersectionWithCounter(const double x1,
                                const double y1,
                                const double z1,

                                const double x2,
                                const double y2,
                                const double z2,

                                const double size,

                                const double xRay,
                                const double yRay,
                                const double zRay,

                                const double xCameraPosition,
                                const double yCameraPosition,
                                const double zCameraPosition,
                                double *xPoint,
                                double *yPoint,
                                double *zPoint){
       double distance = getDistanceBetweenTwoLines(x1,y1,z1,x2,y2,z2,xRay,yRay,zRay,xCameraPosition,yCameraPosition,zCameraPosition);
       if(distance>size){
            return false;
       }
       double xPointT;
       double yPointT;
       double zPointT;
       bool result = getClosestPointBetweenLines(x1,y1,z1,x2,y2,z2,xRay,yRay,zRay,xCameraPosition,yCameraPosition,zCameraPosition, &xPointT,&yPointT,&zPointT);
       if(result==false){
            return false;
       }
       double distanceNoSqrt = size*size-distance*distance;
       if(distanceNoSqrt<0){
            return false;
       }
       double distanceT = sqrt(distanceNoSqrt);
       *xPoint = xPointT-xRay*distanceT;
       *yPoint = yPointT-yRay*distanceT;
       *zPoint = zPointT-zRay*distanceT;

       double d1 = ((x2-x1)*x1+ (y2-y1)*y1+ (z2-z1)*z1)*-1;
       double d2 = ((x2-x1)*x2+ (y2-y1)*y2+ (z2-z1)*z2)*-1;
       int result1 = isPointAboveFace(x2-x1,y2-y1,z2-z1,d1,*xPoint,*yPoint,*zPoint);
       int result2 = isPointAboveFace(x2-x1,y2-y1,z2-z1,d2,*xPoint,*yPoint,*zPoint);
       if(result1*result2==-1){
        return true;
       }
       if(getDistance(x1-*xPoint,y1-*yPoint,z1-*zPoint)<=size||getDistance(x2-*xPoint,y2-*yPoint,z2-*zPoint)<=size){
        return true;
       }
       return false;
}



bool getIntersectionWithSphere(
                        const double xSphere,
                        const double ySphere,
                        const double zSphere,
                        const double size,

                         const double xCameraPosition,
                         const double yCameraPosition,
                         const double zCameraPosition,

                         const double xRay,
                         const double yRay,
                         const double zRay,

                         double *xPoint,
                         double *yPoint,
                         double *zPoint
                        ){
        double xPointT;
        double yPointT;
        double zPointT;
        double factorD=-1*(xSphere*xRay+ySphere*yRay+zSphere*zRay);
        bool result = getIntersectionWithPane(xRay,yRay,zRay,factorD,
                                                    xCameraPosition,yCameraPosition,zCameraPosition,
                                                    xRay,yRay,zRay,
                                                    &xPointT,&yPointT,&zPointT);
        if(result==false){
            return false;
        }
        double distanceST=getDistanceFromTwoPoints(xSphere,ySphere,zSphere,xPointT,yPointT,zPointT);
        double distance = size*2-distanceST*2;
        if(distance<0){
            return false;
        }
        double distanceT =sqrt(distance)*-1;

        *xPoint=xRay*distanceT+xPointT;
        *yPoint=yRay*distanceT+yPointT;
        *zPoint=zRay*distanceT+zPointT;
        return true;
}


void calculateRayVector(
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

                    double *xRay,
                    double *yRay,
                    double *zRay)
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
        normalizeCus(&xTemp,&yTemp,&zTemp);
        xRay[0]=xTemp;
        yRay[0]=yTemp;
        zRay[0]=zTemp;
}
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

                     double xCameraPosition,
                     double yCameraPosition,
                     double zCameraPosition,

                    __constant   double *ACoordinateFrom,
                    __constant   double *BCoordinateFrom,
                    __constant   double *CCoordinateFrom,
                    __constant   double *DCoordinateFrom,
                    __constant   int *PolygonColor,

                    __constant   double *x1,
                    __constant   double *y1,
                    __constant   double *z1,

                    __constant   double *x2,
                    __constant   double *y2,
                    __constant   double *z2,

                    __constant   double *x3,
                    __constant   double *y3,
                    __constant   double *z3,

                    const int polygonCount,

                    __constant double *xSphere,
                    __constant double *ySphere,
                    __constant double *zSphere,
                    __constant double *size,
                    __constant int *sphereColor,

                    const int pointCount,

                    __constant   double *counterSize,
                    __constant   int *counterColor,

                    __constant   double *xCounter1,
                    __constant   double *yCounter1,
                    __constant   double *zCounter1,

                    __constant   double *xCounter2,
                    __constant   double *yCounter2,
                    __constant   double *zCounter2,

                    const int counterCount,

                    __global int *RGB)
{

    int index = get_global_id(0);
    double xRay;
    double yRay;
    double zRay;

    calculateRayVector(xFront,yFront,zFront,
    xAbove,yAbove,zAbove,
    xRight,yRight,zRight,
    screenWidth,screenHeight,
    pseudoWidth,pseudoHeight,
    &xRay,&yRay,&zRay);

    __private int RGBForPoint=-1;

    __private double minDistance=-1;

    __private double xPoint;
    __private double yPoint;
    __private double zPoint;
    for(int i =0;i<polygonCount;i++){
        bool result = getIntersectionWithPane(ACoordinateFrom[i],BCoordinateFrom[i],CCoordinateFrom[i],DCoordinateFrom[i],
                                            xCameraPosition,yCameraPosition,zCameraPosition,
                                            xRay,yRay,zRay,
                                            &xPoint,&yPoint,&zPoint);
        if(result==false){
            continue;
        }

        result = ifPointInTriangle(x1[i],y1[i],z1[i],
                                   x2[i],y2[i],z2[i],
                                   x3[i],y3[i],z3[i],
                                   xPoint,yPoint,zPoint);
        if(result == false){
            continue;
        }
        __private double distance= getDistanceFromTwoPoints(xCameraPosition,yCameraPosition,zCameraPosition,
                                                   xPoint,yPoint,zPoint);
        if(distance<minDistance || minDistance==-1){
            minDistance=distance;
            RGBForPoint=PolygonColor[i];
        }
    }
    for(int i =0;i<pointCount;i++){
            bool result = getIntersectionWithSphere(xSphere[i],ySphere[i],zSphere[i],size[i],
                                                xCameraPosition,yCameraPosition,zCameraPosition,
                                                xRay,yRay,zRay,
                                                &xPoint,&yPoint,&zPoint);
            if(result==false){
                continue;
            }

            __private double distance= getDistanceFromTwoPoints(xCameraPosition,yCameraPosition,zCameraPosition,
                                                       xPoint,yPoint,zPoint);
            if(distance<minDistance || minDistance==-1){
                minDistance=distance;
                RGBForPoint=sphereColor[i];
            }
    }
    for(int i =0;i<counterCount;i++){
         bool result = getIntersectionWithCounter(xCounter1[i],yCounter1[i],zCounter1[i],
                                             xCounter2[i],yCounter2[i],zCounter2[i],counterSize[i],
                                             xRay,yRay,zRay,
                                             xCameraPosition,yCameraPosition,zCameraPosition,
                                             &xPoint,&yPoint,&zPoint);
         if(result==false){
             continue;
         }

         __private double distance= getDistanceFromTwoPoints(xCameraPosition,yCameraPosition,zCameraPosition,
                                                    xPoint,yPoint,zPoint);
         if(distance<minDistance || minDistance==-1){
             minDistance=distance;
             RGBForPoint=counterColor[i];
         }
    }
    RGB[index]=RGBForPoint;
    return;
}