package primitive;

import org.json.JSONObject;
import primitive.calculation.Point;
import primitive.calculation.faces.Polygon;

import java.awt.*;
import java.util.Map;
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
    public Polygon createPolygonFromJson(JSONObject data, Map<String, Point> indexesToPoint){
        Polygon polygon = null;
        if(Objects.equals(data.get("class"),"Polygon")){
            polygon = createPolygon(data,indexesToPoint);
        }
        return polygon;
    }
    public Primitive createPrimitiveFromJson(JSONObject data, Map<String, Point> indexesToPoint){
        Primitive primitive=null;
        if(Objects.equals(data.get("class"),"Point")){
            primitive = createPoint(data);
        }
        if(Objects.equals(data.get("class"),"Polygon")){
            primitive = createPolygon(data,indexesToPoint);
        }
        if(primitive == null){
            System.out.println("unknown type of primitive");
            throw new RuntimeException();
        }
        return primitive;
    }
    private Polygon createPolygon(JSONObject data, Map<String, Point> indexesToPoint){
        Polygon face = new Polygon(createPointFromId(data.getString("pointAId"), indexesToPoint),
                createPointFromId(data.getString("pointBId"), indexesToPoint),
                createPointFromId(data.getString("pointCId"), indexesToPoint),
                createColor(data.getInt("color")));
        return face;
    }
    private Point createPointFromId(String id, Map<String, Point> indexesToPoint){
        return indexesToPoint.get(id);
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

}
