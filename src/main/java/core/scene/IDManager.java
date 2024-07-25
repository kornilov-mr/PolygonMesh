package core.scene;

import primitive.Primitive;
import primitive.calculation.Counter;
import primitive.calculation.Point;
import primitive.calculation.faces.Polygon;

import java.util.HashMap;
import java.util.Map;

public class IDManager {

    private final Map<String, Point> indexesToPoints = new HashMap<>();
    private final Map<String, Counter> indexesToCounters = new HashMap<>();
    private final Map<String, Polygon> indexesToPolygons = new HashMap<>();
    private final Map<String, Primitive> indexesToPrimitive = new HashMap<>();

    public void putPoint(String id,Point point){
        indexesToPoints.put(id,point);
        indexesToPrimitive.put(id,point);
    }
    public void putCounter(String id,Counter counter){
        indexesToCounters.put(id,counter);
        indexesToPrimitive.put(id,counter);
    }
    public void putPolygon(String id,Polygon polygon){
        indexesToPolygons.put(id,polygon);
        indexesToPrimitive.put(id,polygon);
    }
    public Point getPointByID(String id){
        return indexesToPoints.get(id);
    }
    public Counter getCounterByID(String id){
        return indexesToCounters.get(id);
    }
    public Polygon getPolygonByID(String id){
        return indexesToPolygons.get(id);
    }
    public Primitive getPrimitiveByID(String id){
        return indexesToPrimitive.get(id);
    }
    public void deleteByID(String id){
        indexesToPolygons.remove(id);
        indexesToCounters.remove(id);
        indexesToPoints.remove(id);
        indexesToPrimitive.remove(id);
    }
}
