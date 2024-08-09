package primitive;

import core.scene.IDManager;
import org.json.JSONObject;
import primitive.calculation.Point;
import primitive.calculation.faces.Polygon;

import java.awt.*;
import java.util.Objects;

public class PrimitiveFactory {
    public PrimitiveFactory(){

    }
    public Point createPointFromJson(JSONObject data){
        Point point = null;
        if(Objects.equals(data.get("class"),"Point")){
            point = createPoint(data);
        }
        return point;
    }
    public Polygon createPolygonFromJson(JSONObject data, IDManager idManager){
        Polygon polygon = null;
        if(Objects.equals(data.get("class"),"Polygon")){
            polygon = createPolygon(data,idManager);
        }
        return polygon;
    }
    public Primitive createPrimitiveFromJson(JSONObject data, IDManager idManager){
        Primitive primitive=null;
        if(Objects.equals(data.get("class"),"Point")){
            primitive = createPoint(data);
        }
        if(Objects.equals(data.get("class"),"Polygon")){
            primitive = createPolygon(data,idManager);
        }
        if(primitive == null){
            System.out.println("unknown type of primitive");
            throw new RuntimeException();
        }
        return primitive;
    }
    private Polygon createPolygon(JSONObject data, IDManager idManager){
        Polygon face = new Polygon(createPointFromId(data.getString("pointAId"), idManager),
                createPointFromId(data.getString("pointBId"), idManager),
                createPointFromId(data.getString("pointCId"), idManager),
                createColor(data.getInt("color")));
        return face;
    }
    private Point createPointFromId(String id, IDManager idManager){
        return idManager.getPointByID(id);
    }
    private Point createPoint(JSONObject data){
        Point point = new Point(data.getDouble("x"),
                data.getDouble("y"),
                data.getDouble("z"),
                data.getDouble("size"),
                new Color(data.getInt("color")));
        return point;
    }
    private Color createColor(int rgb){
        Color color = new Color(rgb);
        return color;
    }
    public Point createPointFromOBJString(String string){
        String[] values = string.split(" ");
        if(!Objects.equals(values[0],"v")){
            throw  new RuntimeException("invalid flag in .obj file");
        }
        double[] valuesInt = new double[3];
        for (int i=0;i<3;i++){
            double temp=0;
            try {
                temp = Double.parseDouble(values[i+1]);
            }catch (NumberFormatException e){
                throw  new RuntimeException("invalid value in .obj file");
            }
            valuesInt[i]=temp;
        }
        return new Point(valuesInt[0],valuesInt[1],valuesInt[2]);
    }

}
