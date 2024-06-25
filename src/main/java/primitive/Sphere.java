package primitive;

import org.json.JSONObject;
import utils.Calculation;
import utils.line.Line;
import utils.vectors.Vector3D;

import java.awt.*;
import java.util.Map;

public class Sphere extends Primitive {
    public double x;
    public double y;
    public double z;
    public Color color;
    public double size;
    public Sphere(Point point){
        this(point.getX(),point.getY(),point.getZ(),point.isSelected() ? 0.2 :0.02,point.isSelected() ? new Color(0,120,215) :point.color);
    }

    public Sphere(double x, double y, double z){
        this(x,y,z,0.1,new Color(0,0,0));
    }
    public Sphere(Vector3D vector){
        this(vector.getX(), vector.getY(), vector.getZ(),0.1,new Color(0,0,0));
    }
    public Sphere(double x, double y, double z, int size){
        this(x,y,z,size,new Color(0,0,0));
    }
    public Sphere(Vector3D vector, int size){
        this(vector.getX(), vector.getY(), vector.getZ(),size,new Color(0,0,0));
    }

    public Sphere(double x, double y, double z,double size, Color color) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.color=color;
        this.size=size;
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

    @Override
    public JSONObject objectInSavingFormat(Map<Point, String> pointToIndexes){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("x",x);
        jsonObject.put("y",y);
        jsonObject.put("z",z);
        return jsonObject;
    }

    @Override
    public Point getIntersection(Line line) {
        Point pointT = Calculation.closestPointToLine(new Point(this),line);
        if(pointT==null){
            return null;
        }
        double distanceST=Calculation.getLengthBetweenTwoPoints(new Point(this),pointT);
        if(size*2-distanceST*2<0){
            return null;
        }
        double distanceT = Math.sqrt(size*2-distanceST*2);

        Point resultPoint = new Point(new Vector3D(pointT).add(line.directionVector.multiply(distanceT*-1)));
        return resultPoint;
    }
}
