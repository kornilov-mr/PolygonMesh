package core.scene;

import core.scene.sceneLoaders.Extensions;
import core.scene.sceneLoaders.JsonSceneLoader;
import core.scene.sceneLoaders.SceneLoader;
import core.scene.sceneLoaders.SceneLoaderFactory;
import core.tools.selecting.SelectedObjectManager;
import org.json.JSONArray;
import org.json.JSONObject;
import primitive.calculation.Counter;
import primitive.calculation.Point;
import primitive.Primitive;
import primitive.PrimitiveFactory;
import primitive.calculation.faces.Polygon;

import java.awt.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Scene {

    private final Set<Primitive> primitives = new HashSet<>();
    private final Set<Polygon> polygons = new HashSet<>();
    private final Set<Point> points = new HashSet<>();
    private final Set<Counter> counters = new HashSet<>();
    public final IDManager idManager = new IDManager();
    private final SceneLoader sceneLoader = new JsonSceneLoader(new File("src/main/Scenes"));
    private final File pathToSceneFolder = new File("src/main/Scenes");
    public final SelectedObjectManager selectedObjectManager;

    public Scene() {
        this.selectedObjectManager= new SelectedObjectManager();
    }

    public void createPolygon(String pointAId,String pointBId,String pointCId,Color color){

    }
    public void createPolygon(Point pointA,Point pointB,Point pointC, Color color){
        Polygon polygon = new Polygon(pointA,pointB,pointC,color);
    }
    public void addPolygon(Polygon polygon) {
        primitives.add(polygon);
        primitives.add(polygon.getPointA());
        primitives.add(polygon.getPointB());
        primitives.add(polygon.getPointC());
        primitives.add(polygon.getCounterA());
        primitives.add(polygon.getCounterB());
        primitives.add(polygon.getCounterC());

        polygons.add(polygon);
        points.add(polygon.getPointA());
        points.add(polygon.getPointB());
        points.add(polygon.getPointC());

        counters.add(polygon.getCounterA());
        counters.add(polygon.getCounterB());
        counters.add(polygon.getCounterC());

        idManager.putPoint(polygon.getPointA().getId(),polygon.getPointA());
        idManager.putPoint(polygon.getPointB().getId(),polygon.getPointB());
        idManager.putPoint(polygon.getPointC().getId(),polygon.getPointC());

        idManager.putCounter(polygon.getCounterA().getId(),polygon.getCounterA());
        idManager.putCounter(polygon.getCounterB().getId(),polygon.getCounterB());
        idManager.putCounter(polygon.getCounterC().getId(),polygon.getCounterC());

        idManager.putPolygon(polygon.getId(),polygon);

        polygon.getPointA().addPolygon(polygon);
        polygon.getPointB().addPolygon(polygon);
        polygon.getPointC().addPolygon(polygon);
    }

    public void saveScene(File file, Extensions extension) {
        SceneLoader sceneLoader = SceneLoaderFactory.createSceneLoaderFromExtension(file,extension);
        sceneLoader.saveScene(this);
    }
    public void loadSceneFromFile(File file){
        SceneLoader sceneLoader = SceneLoaderFactory.createSceneLoaderFromFile(file);
        sceneLoader.saveScene(this);;
    }

    public Set<Primitive> getPrimitives() {
        return primitives;
    }

    public Set<Polygon> getPolygons() {
        return polygons;
    }

    public Set<Point> getPoints() {
        return points;
    }

    public Set<Counter> getCounters() {
        return counters;
    }
}
