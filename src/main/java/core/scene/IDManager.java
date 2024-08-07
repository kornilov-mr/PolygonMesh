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
    private final Scene scene;

    public IDManager(Scene scene) {
        this.scene = scene;
    }

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
        scene.getPolygons().remove(indexesToPolygons.remove(id));
        scene.getCounters().remove(indexesToCounters.remove(id));
        scene.getPoints().remove(indexesToPoints.remove(id));
        scene.getPrimitives().remove(indexesToPrimitive.remove(id));
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
    public void deletePrimitive(Primitive primitive, String id){
        scene.getPrimitives().remove(primitive);
        indexesToPrimitive.remove(id);
        if(primitive instanceof Polygon){
            deletePolygon((Polygon) primitive,id);
        }else if(primitive instanceof Point){
            deletePoint((Point) primitive,id);
        }else if(primitive instanceof Counter){
            deleteCounter((Counter) primitive,id);
        }
    }
    public void deletePoint(Point point,String id){
        scene.getPoints().remove(point);
        indexesToPrimitive.remove(id);
    }
    public void deleteCounter(Counter counter,String id){
        scene.getCounters().remove(counter);
        indexesToCounters.remove(id);

    }
    public void deletePolygon(Polygon polygon,String id){
        scene.getPolygons().remove(polygon);
        indexesToPolygons.remove(polygon);

        polygon.getPointA().getBelongToPolygon().remove(polygon);
        if(polygon.getPointA().getBelongToPolygon().isEmpty()){
            scene.getPoints().remove(polygon.getPointA());
        }

        polygon.getPointB().getBelongToPolygon().remove(polygon);
        if(polygon.getPointB().getBelongToPolygon().isEmpty()){
            scene.getPoints().remove(polygon.getPointB());
        }

        polygon.getPointC().getBelongToPolygon().remove(polygon);
        if(polygon.getPointC().getBelongToPolygon().isEmpty()){
            scene.getPoints().remove(polygon.getPointC());
        }

        polygon.getCounterA().getBelongToPolygon().remove(polygon);
        if(polygon.getCounterA().getBelongToPolygon().isEmpty()){
            scene.getCounters().remove(polygon.getCounterA());
        }

        polygon.getCounterB().getBelongToPolygon().remove(polygon);
        if(polygon.getCounterB().getBelongToPolygon().isEmpty()){
            scene.getCounters().remove(polygon.getCounterB());
        }

        polygon.getCounterC().getBelongToPolygon().remove(polygon);
        if(polygon.getCounterC().getBelongToPolygon().isEmpty()){
            scene.getCounters().remove(polygon.getCounterC());
        }
    }
    protected void clear(){
        indexesToPrimitive.clear();
        indexesToPolygons.clear();
        indexesToCounters.clear();;
        indexesToPoints.clear();
    }
}
