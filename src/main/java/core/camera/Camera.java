package core.camera;

import core.render.RenderConfig;
import primitive.OrientedObject;
import utils.line.Line;
import utils.vectors.Vector3D;

public class Camera extends OrientedObject {



    private final RenderConfig renderConfig;

    public Camera(RenderConfig renderConfig) {
        this(new Vector3D(0,0,0), 0, 0,renderConfig);
    }

    public Camera(Vector3D cameraPosition, double horizontalAngle, double verticalAngle, RenderConfig renderConfig) {
        super(cameraPosition,horizontalAngle,verticalAngle);
        this.renderConfig=renderConfig;
    }
    public Line getRayLine(int i, int j) {
        Vector3D vu = new Vector3D(position);
        Vector3D ray= new Vector3D(frontVector);

        double fraction = ((double) j / renderConfig.resolution[1] - 0.5);
        ray=ray.add(aboveVector.multiply(fraction*renderConfig.pseudoRectangleHeight));

        Vector3D cameraRightVector= new Vector3D(this.rightVector.getX(),0,this.rightVector.getZ()).normalized();
        fraction = ((double) i / renderConfig.resolution[0] - 0.5);
        ray=ray.add(cameraRightVector.multiply(fraction*renderConfig.pseudoRectangleWidth));

        return new Line(vu, ray);
    }
}
