package core.scene;

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
import java.util.List;

public class Scene {

    private ArrayList<Primitive> primitives = new ArrayList<>();
    private final ArrayList<Polygon> polygons = new ArrayList<>();
    private final Map<String, Point> points = new HashMap<>();
    private final Map<Polygon, ArrayList<Point>> polygonToPoints = new HashMap<>();
    private final Map<Point, ArrayList<Polygon>> pointsToPolygon = new HashMap<>();
    private final File pathToSceneFolder = new File("src/main/Scenes");

    public Scene() {

    }
    public void createPolygons(ArrayList<Point> points,ArrayList<String> pointsID,Color color){
        for(String Id: pointsID){
            points.add(this.points.get(Id));
        }
        if(points.size()<3){
            System.out.println("Not enough points");
        }
        for(int i=0;i<points.size()-2;i++){
            createPolygon(points.get(i),points.get(i+1),points.get(i+2),color);
        }

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
        points.put(UUID.randomUUID().toString(),polygon.getPointA());
        points.put(UUID.randomUUID().toString(),polygon.getPointB());
        points.put(UUID.randomUUID().toString(),polygon.getPointC());
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

    public void saveScene() {
        File saveFile = getSaveFile();
        try {
            PrintWriter printWriter = new PrintWriter(saveFile);
            JSONArray jsonArray = new JSONArray();
            for (Primitive primitive : primitives) {
                jsonArray.put(primitive.objectInSavingFormat());
            }
            printWriter.println(jsonArray.toString());
            printWriter.flush();
        } catch (FileNotFoundException e) {
            System.out.println("Save file isn't found");
        }
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

    public void loadSceneFromFile(File file) {
        PrimitiveFactory primitiveFactory = new PrimitiveFactory();
        try {
            String content = new String(Files.readAllBytes(Paths.get(file.toURI())));
            JSONArray jsonArray = new JSONArray(content);
            for(int i=0;i<jsonArray.length();i++){
                Primitive primitive =primitiveFactory.createPrimitiveFromJson((JSONObject) jsonArray.get(i));
                primitives.add(primitive);
            }
        } catch (IOException e) {
            System.out.println("problem with reading from load file");

        }
    }

    public ArrayList<Primitive> getPrimitives() {
        return primitives;
    }

    public ArrayList<Polygon> getPolygons() {
        return polygons;
    }

    public Map<String, Point> getPoints() {
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
