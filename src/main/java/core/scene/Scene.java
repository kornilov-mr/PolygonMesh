package core.scene;

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
    private final Map<String, Point> indexesToPoints = new HashMap<>();
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

        String id = "";
        id=UUID.randomUUID().toString();
        indexesToPoints.put(id,polygon.getPointA());
        polygon.getPointA().setId(id);

        id=UUID.randomUUID().toString();
        indexesToPoints.put(id,polygon.getPointB());
        polygon.getPointB().setId(id);

        id=UUID.randomUUID().toString();
        indexesToPoints.put(id,polygon.getPointC());
        polygon.getPointC().setId(id);

        polygon.getPointA().addPolygon(polygon);
        polygon.getPointB().addPolygon(polygon);
        polygon.getPointC().addPolygon(polygon);
    }

    private File getSaveFile() {
        if (!pathToSceneFolder.exists()) {
            pathToSceneFolder.mkdir();
        }
        int copyCount = 0;
        File saveFile = pathToSceneFolder.toPath().resolve("Scene" + copyCount+".json").toFile();
        while (saveFile.exists()) {
            copyCount += 1;
            saveFile = pathToSceneFolder.toPath().resolve("Scene" + copyCount+".json").toFile();
        }
        return saveFile;
    }

    public void saveScene() {
        File saveFile = getSaveFile();
        try {
            PrintWriter printWriter = new PrintWriter(saveFile);
            JSONObject jsonObject = new JSONObject();

            JSONArray pointsJson = new JSONArray();
            for (Point point : points) {
                pointsJson.put(wrapPointJsonWithIndex(point, point.getId()));
            }
            JSONArray polygonJson = new JSONArray();
            for(Polygon polygon : polygons){
                polygonJson.put(polygon.objectInSavingFormat());
            }
            jsonObject.put("points",pointsJson);
            jsonObject.put("polygons",polygonJson);
            printWriter.println(jsonObject.toString());
            printWriter.flush();
        } catch (FileNotFoundException e) {
            System.out.println("Save file isn't found");
        }
    }
    public JSONObject wrapPointJsonWithIndex(Point point,String id){
        JSONObject wrappedPointJson = new JSONObject();
        wrappedPointJson.put("id",id);
        wrappedPointJson.put("point",point.objectInSavingFormat());
        return wrappedPointJson;
    }
    public void loadSceneFromFile(File file) {
        PrimitiveFactory primitiveFactory = new PrimitiveFactory();
        try {
            String content = new String(Files.readAllBytes(Paths.get(file.toURI())));
            JSONObject jsonObject = new JSONObject(content);

            JSONArray points = jsonObject.getJSONArray("points");
            for(int i=0;i<points.length();i++){
                JSONObject pointJsonWithIndex = points.getJSONObject(i);

                JSONObject pointData = pointJsonWithIndex.getJSONObject("point");
                String id = pointJsonWithIndex.getString("id");
                Point point = primitiveFactory.createPointFromJson(pointData);
                this.primitives.add(point);
                this.points.add(point);
                this.indexesToPoints.put(id,point);
                point.setId(id);
            }

            JSONArray polygons = jsonObject.getJSONArray("polygons");
            for(int i =0;i<polygons.length();i++){
                JSONObject polygonData = polygons.getJSONObject(i);

                Polygon polygon = primitiveFactory.createPolygonFromJson(polygonData,indexesToPoints);
                addPolygon(polygon);
            }
        } catch (IOException e) {
            System.out.println("problem with reading from load file");

        }
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
