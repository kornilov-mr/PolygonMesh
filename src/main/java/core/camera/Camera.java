package core.camera;

import core.render.RenderConfig;
import core.scene.Scene;
import primitive.OrientedObject;
import primitive.calculation.Point;
import primitive.Primitive;
import utils.line.Line;
import utils.vectors.Vector3D;

import java.util.Iterator;

public class Camera extends OrientedObject {

    private Scene scene;
    private final RenderConfig renderConfig;

    public Camera(RenderConfig renderConfig,Scene scene) {
        this(new Vector3D(0,0,0), 0, 0,renderConfig,scene);
    }

    public Camera(Vector3D cameraPosition, double horizontalAngle, double verticalAngle, RenderConfig renderConfig, Scene scene) {
        super(cameraPosition,horizontalAngle,verticalAngle);
        this.renderConfig=renderConfig;
        this.scene=scene;
    }
    //function to calculate Ray line from camera from pixel i on width and j on height.
    public Line getRayLine(int i, int j) {
        Vector3D vu = new Vector3D(position);
        Vector3D ray= new Vector3D(frontVector);

        double fraction = ((double) j / renderConfig.resolution[1] - 0.5);
        ray=ray.add(aboveVector.multiply(fraction*renderConfig.pseudoRectangleHeight));

        Vector3D cameraRightVector= new Vector3D(this.rightVector.getX(),0,this.rightVector.getZ()).normalized();
        fraction = ((double) i / renderConfig.resolution[0] - 0.5);
        ray=ray.add(cameraRightVector.multiply(fraction*renderConfig.pseudoRectangleWidth));
        ray=ray.normalized();
        return new Line(vu, ray);
    }
    public void setScene(Scene scene){
        this.scene=scene;
    }

    //function to get first primitive shown on pixel i on width, j on heigth
    public Primitive getPrimitiveOnPixel(int i, int j){

        if(scene==null){
            return null;
        }
        Line rayLine= getRayLine(i,j);
        double minDistance =-1;
        Primitive closesPrimitive = null;
        Iterator<Primitive> it = scene.getPrimitives().iterator();
        while(it.hasNext()){
            Primitive primitive = it.next();
            Point point = primitive.getIntersection(rayLine);
            if(point==null){
                continue;
            }
            double distance = point.getDistanceToPoint(new Point(position));
            if(distance<minDistance||minDistance==-1){
                minDistance=distance;
                closesPrimitive=primitive;
            }
        }
        return closesPrimitive;
    }
}
