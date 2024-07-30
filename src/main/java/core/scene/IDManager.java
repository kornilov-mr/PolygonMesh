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
    private void putCounter(String id,Counter counter){
        indexesToCounters.put(id,counter);
        indexesToPrimitive.put(id,counter);
    }
    private void putPolygon(String id,Polygon polygon){
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
    public boolean containPrimitiveById(String id){
        return indexesToPrimitive.containsKey(id);
    }
    protected void registerPolygon(Polygon polygon){
        putPoint(polygon.getPointA().getId(),polygon.getPointA());
        putPoint(polygon.getPointB().getId(),polygon.getPointB());
        putPoint(polygon.getPointC().getId(),polygon.getPointC());

        putCounter(polygon.getCounterA().getId(),polygon.getCounterA());
        putCounter(polygon.getCounterB().getId(),polygon.getCounterB());
        putCounter(polygon.getCounterC().getId(),polygon.getCounterC());

        putPolygon(polygon.getId(),polygon);
    }
    protected void registerPoint(Point point){
        putPoint(point.getId(), point);
    }
    protected void registerCounter(Counter counter){
        putPoint(counter.getPointA().getId(),counter.getPointA());
        putPoint(counter.getPointB().getId(),counter.getPointB());

        putCounter(counter.getId(), counter);
    }
}
