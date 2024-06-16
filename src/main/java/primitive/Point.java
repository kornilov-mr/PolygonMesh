package primitive;

import org.jocl.struct.Struct;
import org.json.JSONObject;
import core.render.GPU.structs.PointStruct;
import utils.vectors.Vector3D;

import java.awt.*;

public class Point {

    private final double x;
    private final double y;
    private final double z;
    private Color color;
    public Point(double x, double y, double z){
        this(x,y,z,new Color(0,0,0));
    }
    public Point(Vector3D vector){
        this(vector.getX(), vector.getY(), vector.getZ(), new Color(0,0,0));
    }
    public Point(double x, double y, double z,Color color) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.color=color;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getZ() {
        return z;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public JSONObject toJSON(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("x",x);
        jsonObject.put("y",y);
        jsonObject.put("z",z);
        return jsonObject;
    }
    public PointStruct toStruct(){
        return new PointStruct(x,y,z);
    }
}
