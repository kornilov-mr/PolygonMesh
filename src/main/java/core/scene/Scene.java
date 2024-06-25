package core.scene;

import core.tools.selecting.SelectedObjectManager;
import org.json.JSONArray;
import org.json.JSONObject;
import primitive.Point;
import primitive.Primitive;
import primitive.PrimitiveFactory;
import primitive.faces.Polygon;

import java.awt.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Scene {

    private final Set<Primitive> primitives = new HashSet<>();
    private final Set<Polygon> polygons = new HashSet<>();
    private final Set<Point> points = new HashSet<>();
    private final Map<String, Point> indexesToPoints = new HashMap<>();
    private final Map<Point, String> pointToIndexes = new HashMap<>();

    private final Map<Polygon, ArrayList<Point>> polygonToPoints = new HashMap<>();
    private final Map<Point, ArrayList<Polygon>> pointsToPolygon = new HashMap<>();
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

        polygons.add(polygon);
        points.add(polygon.getPointA());
        points.add(polygon.getPointB());
        points.add(polygon.getPointC());

        String id = "";
        id=UUID.randomUUID().toString();
        indexesToPoints.put(id,polygon.getPointA());
        pointToIndexes.put(polygon.getPointA(),id);

        id=UUID.randomUUID().toString();
        indexesToPoints.put(id,polygon.getPointB());
        pointToIndexes.put(polygon.getPointB(),id);

        id=UUID.randomUUID().toString();
        indexesToPoints.put(id,polygon.getPointC());
        pointToIndexes.put(polygon.getPointC(),id);

        if(!pointsToPolygon.containsKey(polygon.getPointA())){
            pointsToPolygon.put(polygon.getPointA(),new ArrayList<>());
        }
        pointsToPolygon.get(polygon.getPointA()).add(polygon);

        if(!pointsToPolygon.containsKey(polygon.getPointB())){
            pointsToPolygon.put(polygon.getPointB(),new ArrayList<>());
        }
        pointsToPolygon.get(polygon.getPointB()).add(polygon);

        if(!pointsToPolygon.containsKey(polygon.getPointC())){
            pointsToPolygon.put(polygon.getPointC(),new ArrayList<>());
        }
        pointsToPolygon.get(polygon.getPointC()).add(polygon);

        ArrayList<Point> points = new ArrayList<>();
        points.add(polygon.getPointA());
        points.add(polygon.getPointB());
        points.add(polygon.getPointC());
        polygonToPoints.put(polygon,points);
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
                pointsJson.put(wrapPointJsonWithIndex(point, pointToIndexes.get(point)));
            }
            JSONArray polygonJson = new JSONArray();
            for(Polygon polygon : polygons){
                polygonJson.put(polygon.objectInSavingFormat(pointToIndexes));
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
        wrappedPointJson.put("point",point.objectInSavingFormat(pointToIndexes));
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
                this.pointToIndexes.put(point,id);
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

    public Map<Polygon, ArrayList<Point>> getPolygonToPoints() {
        return polygonToPoints;
    }

    public Map<Point, ArrayList<Polygon>> getPointsToPolygon() {
        return pointsToPolygon;
    }
    public ArrayList<Polygon> getPolygonNextToPoint(Point point){
        return pointsToPolygon.get(point);
    }
    public ArrayList<Point> getPointOfPolygon(Polygon polygon){
        return polygonToPoints.get(polygon);
    }
}
