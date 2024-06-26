package primitive.rendering;

import org.json.JSONObject;
import primitive.Primitive;
import primitive.calculation.Point;
import utils.Calculation;
import utils.line.Line;
import utils.vectors.Vector3D;

import java.awt.*;
import java.util.Map;

public class Sphere extends Point {
    public Sphere(Point point){
        this(point.getX(),point.getY(),point.getZ(),point.isSelected() ? 0.2 :0.02,point.isSelected() ? new Color(36, 100, 155) :point.color);
    }

    private Sphere(double x, double y, double z,double size, Color color) {
        super(x,y,z,size,color);
    }
}
