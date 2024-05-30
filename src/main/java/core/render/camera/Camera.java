package core.render.camera;

import core.render.RenderConfig;
import utils.Calculation;
import utils.line.Line;
import utils.vectors.Vector3D;

public class Camera {
    private double horizontalAngle;
    private double verticalAngle;

    private Vector3D frontVector;
    private Vector3D aboveVector;
    private Vector3D rightVector;

    private Vector3D cameraPosition;

    private final RenderConfig renderConfig;

    public Camera(RenderConfig renderConfig) {
        this(new Vector3D(0,0,0), 0, 0,renderConfig);
    }

    public Camera(Vector3D cameraPosition, double horizontalAngle, double verticalAngle, RenderConfig renderConfig) {
        this.cameraPosition = cameraPosition;
        this.horizontalAngle = horizontalAngle;
        this.verticalAngle = verticalAngle;
        this.renderConfig = renderConfig;
        calculateNewVectors();
    }

    public Line getRayLine(int i, int j) {
        Vector3D vu = new Vector3D(this.cameraPosition);
        Vector3D ray= new Vector3D(frontVector);

        double fraction = ((double) j / renderConfig.resolution[1] - 0.5);
        ray=ray.add(aboveVector.Multiply(fraction*renderConfig.pseudoRectangleHeight));

        fraction = ((double) i / renderConfig.resolution[0] - 0.5);
        ray=ray.add(rightVector.Multiply(-1*fraction*renderConfig.pseudoRectangleWidth));

        return new Line(vu, ray);
    }

    public void setHorizontalAngle(double horizontalAngle) {
        this.horizontalAngle = horizontalAngle;
        calculateNewVectors();
    }

    public void setVerticalAngle(double verticalAngle) {
        this.verticalAngle = verticalAngle;
        calculateNewVectors();
    }

    protected void changeHorizontalAngle(double angle){
        this.horizontalAngle+=angle;
        calculateNewVectors();
    }
    protected void changeVerticalAngle(double angle){
        this.verticalAngle+=angle;
        calculateNewVectors();
    }
    private void calculateNewVectors(){
        Vector3D frontVector = new Vector3D(0,0,1);
        frontVector=frontVector.rotateX(horizontalAngle);
        frontVector=frontVector.rotateY(verticalAngle);
        this.frontVector=frontVector;

        Vector3D aboveVector = new Vector3D(0,0,1);
        aboveVector=aboveVector.rotateX(horizontalAngle-Math.PI/2);
        aboveVector=aboveVector.rotateY(verticalAngle);
        this.aboveVector=aboveVector;

        Vector3D rightVector = new Vector3D(0,0,1);
        rightVector=rightVector.rotateX(horizontalAngle);
        rightVector=rightVector.rotateY(verticalAngle+Math.PI/2);
        this.rightVector=rightVector;
    }
    protected void moveCameraUp(double distance){
        this.cameraPosition=cameraPosition.add(aboveVector.Multiply(distance*-1));
    }
    protected void moveCameraRight(double distance){
        this.cameraPosition=cameraPosition.add(rightVector.Multiply(distance*-1));
    }
    protected void moveCameraFront(double distance){
        this.cameraPosition=cameraPosition.add(frontVector.Multiply(distance));
    }
    protected Vector3D getCameraPosition() {
        return cameraPosition;
    }

    public double getHorizontalAngle() {
        return horizontalAngle;
    }

    public double getVerticalAngle() {
        return verticalAngle;
    }
}
