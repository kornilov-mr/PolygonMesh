package core.scene;

import org.json.JSONArray;
import org.json.JSONObject;
import primitive.Point;
import primitive.Primitive;
import primitive.PrimitiveFactory;
import primitive.faces.Polygon;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Scene {

    private ArrayList<Primitive> primitives = new ArrayList<>();
    private ArrayList<Polygon> polygons = new ArrayList<>();
    private Map<Point,ArrayList<Polygon>> pointToPolygon = new HashMap<>();
    private final File pathToSceneFolder = new File("src/main/Scenes");

    public Scene() {

    }
//    public void changePointInPolygon(Point point, String pointName, Polygon polygon){
//        for(Polygon polygonStored: polygons){
//            if(polygon==polygonStored){
//                if(Objects.equals(pointName,"a")){
//                    polygonStored.setPointA(point);
//                }
//                if(Objects.equals(pointName,"b")){
//                    polygonStored.setPointB(point);
//                }
//                if(Objects.equals(pointName,"c")){
//                    polygonStored.setPointC(point);
//                }
//            }
//        }
//    }
    public void addPolygon(Polygon polygon) {
        primitives.add(polygon);
        polygons.add(polygon);
        if(!pointToPolygon.containsKey(polygon.getPointA())){
            pointToPolygon.put(polygon.getPointA(),new ArrayList<>());
        }
        pointToPolygon.get(polygon.getPointA()).add(polygon);
        if(!pointToPolygon.containsKey(polygon.getPointB())){
            pointToPolygon.put(polygon.getPointB(),new ArrayList<>());
        }
        pointToPolygon.get(polygon.getPointB()).add(polygon);
        if(!pointToPolygon.containsKey(polygon.getPointC())){
            pointToPolygon.put(polygon.getPointC(),new ArrayList<>());
        }
        pointToPolygon.get(polygon.getPointC()).add(polygon);
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
}
