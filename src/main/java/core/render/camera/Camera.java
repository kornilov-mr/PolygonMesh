package core.render.camera;

import core.render.RenderConfig;
import primitive.OrientedObject;
import utils.line.Line;
import utils.vectors.Vector3D;

public class Camera extends OrientedObject {



    private final RenderConfig renderConfig;

    public Camera(RenderConfig renderConfig) {
        this(new Vector3D(0,0,0), Math.PI/2, Math.PI/2,renderConfig);
    }

    public Camera(Vector3D cameraPosition, double horizontalAngle, double verticalAngle, RenderConfig renderConfig) {
        super(cameraPosition,horizontalAngle,verticalAngle);
        this.renderConfig=renderConfig;
    }

    public Line getRayLine(int i, int j) {
        Vector3D vu = new Vector3D(position);
        Vector3D ray= new Vector3D(frontVector);

        double fraction = ((double) j / renderConfig.resolution[1] - 0.5);
        ray=ray.add(aboveVector.Multiply(fraction*renderConfig.pseudoRectangleHeight));

        fraction = ((double) i / renderConfig.resolution[0] - 0.5);
        ray=ray.add(rightVector.Multiply(fraction*renderConfig.pseudoRectangleWidth));

        return new Line(vu, ray);
    }


}
