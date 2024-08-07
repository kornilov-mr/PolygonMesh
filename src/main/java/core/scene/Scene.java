package core.scene;

import core.UI.visuals.elements.mainPanel.MainRenderPlane;
import core.scene.resentProjects.ResentProjectManager;
import core.scene.sceneLoaders.Extensions;
import core.scene.sceneLoaders.SceneLoader;
import core.scene.sceneLoaders.SceneLoaderFactory;
import core.tools.selecting.SelectedObjectManager;
import primitive.calculation.Counter;
import primitive.calculation.Point;
import primitive.Primitive;
import primitive.calculation.faces.Polygon;

import java.awt.image.BufferedImage;
import java.io.*;
import java.util.*;

public class Scene {
    private String sceneName = "Scene";
    private final Set<Primitive> primitives = new HashSet<>();
    private final Set<Polygon> polygons = new HashSet<>();
    private final Set<Point> points = new HashSet<>();
    private final Set<Counter> counters = new HashSet<>();
    public final IDManager idManager = new IDManager(this);
    public final SelectedObjectManager selectedObjectManager;
    private final ResentProjectManager resentProjectManager;
    private MainRenderPlane mainRenderPlane;
    public Scene() {
        this.selectedObjectManager= new SelectedObjectManager();
        this.resentProjectManager= new ResentProjectManager();
    }

    public void addPrimitive(Primitive primitive){
        if(primitive instanceof Polygon){
            addPolygon((Polygon) primitive);
        }else if(primitive instanceof Point){
            addPoint((Point) primitive);
        }else if(primitive instanceof Counter){
            addCounter((Counter) primitive);
        }
    }
    public void addPoint(Point point){
        points.add(point);
        primitives.add(point);
        idManager.registerPoint(point);
    }
    public void addCounter(Counter counter){
        counters.add(counter);
        points.add(counter.getPointA());
        points.add(counter.getPointB());
        primitives.add(counter);
        primitives.add(counter.getPointA());
        primitives.add(counter.getPointB());

        idManager.registerCounter(counter);
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

        idManager.registerPolygon(polygon);

        polygon.getPointA().addPolygon(polygon);
        polygon.getPointB().addPolygon(polygon);
        polygon.getPointC().addPolygon(polygon);
        polygon.getCounterA().addPolygon(polygon);
        polygon.getCounterB().addPolygon(polygon);
    }
    public void saveInFileOrFolder(File file, Extensions extension){
        SceneLoader sceneLoader = SceneLoaderFactory.createSceneLoaderFromExtension(extension, resentProjectManager);
        sceneLoader.saveSceneInDirOrFile(this,file,extension);
    }
    public void saveScene(File file) {
        SceneLoader sceneLoader = SceneLoaderFactory.createSceneLoaderFromFile(file,resentProjectManager);
        sceneLoader.saveScene(this,file);
    }
    public void fastSaveScene(){
        SceneLoader sceneLoader = SceneLoaderFactory.createSceneLoaderFromExtension(Extensions.JSON,resentProjectManager);
        sceneLoader.fastSaveScene(this);
    }
    public void loadSceneFromFile(File file){
        SceneLoader sceneLoader = SceneLoaderFactory.createSceneLoaderFromFile(file,resentProjectManager);
        sceneLoader.readScene(file,this);
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

    public String getSceneName() {
        return sceneName;
    }

    public void setSceneName(String sceneName) {
        this.sceneName = sceneName;
    }

    public ResentProjectManager getResentProjectManager() {
        return resentProjectManager;
    }

    public BufferedImage getCanvas() {
        return mainRenderPlane.getCanvas();
    }

    public void setMainRenderPlane(MainRenderPlane mainRenderPlane) {
        this.mainRenderPlane = mainRenderPlane;
    }
}
