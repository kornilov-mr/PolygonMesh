#pragma OPENCL EXTENSION cl_khr_fp64 : enable
struct Vector {
  double x;
  double y;
  double z;
};

struct CoordinateForm{
  double A;
  double B;
  double C;
  double D;
};
struct Polygon{
    struct Vector PointA;
    struct Vector PointB;
    struct Vector PointC;
    struct CoordinateForm coordinateForm;
    int mainColor;
    int contourColor;
};

double getDistance(const struct Vector vector)
{
    return sqrt(pow(vector.x,2)+pow(vector.y,2)+pow(vector.z,2));
}


double getDistanceFromTwoPoints(const struct Vector vector1,
                                const struct Vector vector2){
        struct Vector vector ={vector1.x-vector2.x,vector1.x-vector2.x,vector1.x-vector2.x};
        return getDistance(vector);
}
double crossProduct(const struct Vector vector1,
                    const struct Vector vector2)
{
          struct Vector vector ={vector1.y*vector2.z-vector1.z*vector2.y,
                                           vector1.z*vector2.x-vector1.x*vector2.z,
                                           vector1.x*vector2.y-vector1.y*vector2.x};

          return  getDistance(vector);
}
struct Vector VectorDivision(const struct Vector vector1,
                             const struct Vector vector2)
{
      struct Vector vector ={vector1.x-vector2.x,vector1.y-vector2.y,vector1.z-vector2.z};
      return vector;
}
double areaOfTriangle(
                   const struct Vector vector1,
                   const struct Vector vector2,
                   const struct Vector vector3)
{
    return crossProduct(VectorDivision(vector2,vector1),VectorDivision(vector3,vector1))/2;
}
int roundNo(float num)
{
    return num < 0 ? num - 0.5 : num + 0.5;
}

bool ifPointInTriangle(
                    const struct Vector vector1,
                    const struct Vector vector2,
                    const struct Vector vector3,

                    const struct Vector pointVector,
                    int mainColor,
                    int contourColor,
                    int *color)
{
    double mainArea = areaOfTriangle(vector1,vector2,vector3);
    double area1 = areaOfTriangle(pointVector,vector2,vector3);
    double area2 = areaOfTriangle(vector1,pointVector,vector3);
    double area3 = areaOfTriangle(vector1,vector2,pointVector);


    double mainAreaTemp= roundNo(mainArea*100000);
    double newAreaTemp= roundNo((area1+area2+area3)*100000);
    if(mainAreaTemp==newAreaTemp){
        *color=mainColor;
        return true;
    }
    mainArea= roundNo(mainArea);
    double newArea= roundNo((area1+area2+area3));
    if(mainArea==newArea){
        *color=contourColor;
        return true;
    }
    return false;
}

bool getIntersectionWithPane(
                        const struct CoordinateForm coordinateForm,
                        const struct Vector cameraPosition,
                        const struct Vector ray,

                        struct Vector *Point
                        ){
        double r = 0;
        r += ray.x * coordinateForm.A;
        r += ray.y * coordinateForm.B;
        r += ray.z * coordinateForm.C;
        if(r==0){
             return false;
        }
        double free = 0;
        free += cameraPosition.x *  coordinateForm.A;
        free += cameraPosition.y *  coordinateForm.B;
        free += cameraPosition.z *  coordinateForm.C;
        free +=  coordinateForm.D;

        r = (-1*free) / r;
        if(r<0){
              return false;
        }
        struct Vector tempVector = {ray.x*r+cameraPosition.x,ray.y*r+cameraPosition.y,ray.z*r+cameraPosition.z};
        *Point=tempVector;
        return true;
}
__kernel void calculatePointVector(
                    __global struct Vector *cameraPosition,
                    __global struct Vector *rays,
                    __global struct Polygon *polygons,

                    const int polygonCount,

                    __global int *RGB)
{
    int index = get_global_id(0);

    int RGBForPoint=-1;

    double minDistance=-1;

    int color=0;

    struct Vector point;
    for(int i =0;i<polygonCount;i++){
        bool result = getIntersectionWithPane(polygons[i].coordinateForm,
                                            cameraPosition[0],
                                            rays[index],
                                            &point);
        if(result==false){
            continue;
        }

        result = ifPointInTriangle(polygons[i].PointA,
                                   polygons[i].PointB,
                                   polygons[i].PointC,
                                   point,polygons[i].mainColor,
                                   polygons[i].contourColor,&color);
        if(result == false){
            continue;
        }
        double distance= getDistanceFromTwoPoints(cameraPosition[0],
                                                   point);
        if(distance<minDistance || minDistance==-1){
            minDistance=distance;
            RGBForPoint=color;
        }
    }
    RGB[index]=RGBForPoint;
    return;
}