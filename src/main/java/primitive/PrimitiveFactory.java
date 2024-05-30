package primitive;

import org.json.JSONArray;
import org.json.JSONObject;
import primitive.faces.Face;
import primitive.faces.Polygon;

import java.awt.*;
import java.util.Objects;

public class PrimitiveFactory {
    public PrimitiveFactory(){

    }
    public Primitive createPrimitiveFromJson(JSONObject data){
        Primitive primitive=null;
        if(Objects.equals(data.get("Class"),"Face")){
            primitive = createFace(data);
        }
        if(Objects.equals(data.get("Class"),"Polygon")){
            primitive = createPolygon(data);
        }
        if(primitive == null){
            System.out.println("unknown type of primitive");
            throw new RuntimeException();
        }
        return primitive;
    }
    private Primitive createFace(JSONObject data){
        Primitive face = new Face(createPoint((JSONObject) data.get("point1")),
                createPoint((JSONObject) data.get("point2")),
                createPoint((JSONObject) data.get("point3")),
                createColor((int) data.get("color")));
        return face;
    }
    private Primitive createPolygon(JSONObject data){
        Primitive face = new Polygon(createPoint((JSONObject) data.get("point1")),
                createPoint((JSONObject) data.get("point2")),
                createPoint((JSONObject) data.get("point3")),
                createColor((int) data.get("color")));
        return face;
    }
    private Point createPoint(JSONObject data){
        Point point = new Point(parseDouble(data.get("x")),
                parseDouble(data.get("y")),
                parseDouble(data.get("z")));
        return point;
    }
    private Color createColor(int rgb){
        Color color = new Color(rgb);
        return color;
    }
    private Double parseDouble(Object presumableDouble){
        if(presumableDouble instanceof Double){
            return (Double) presumableDouble;
        }
        if(presumableDouble instanceof Integer){
            return Double.parseDouble(String.valueOf((Integer)presumableDouble));
        }
        return null;
    }
}
