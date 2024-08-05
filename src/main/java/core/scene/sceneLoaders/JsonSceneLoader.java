package core.scene.sceneLoaders;

import core.scene.Scene;
import org.json.JSONArray;
import org.json.JSONObject;
import primitive.PrimitiveFactory;
import primitive.calculation.Point;
import primitive.calculation.faces.Polygon;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;

public class JsonSceneLoader extends SceneLoader {
    public JsonSceneLoader() {
        super(new File("src/main/Scenes"));
    }
    public JsonSceneLoader(File sceneFolder) {
        super(sceneFolder);
    }
    public JSONObject wrapPointJsonWithIndex(Point point,String id){
        JSONObject wrappedPointJson = new JSONObject();
        wrappedPointJson.put("id",id);
        wrappedPointJson.put("point",point.objectInSavingFormat());
        return wrappedPointJson;
    }
    @Override
    public void readScene(File file, Scene scene) {
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
                scene.getPrimitives().add(point);
                scene.getPoints().add(point);
                scene.idManager.putPoint(id,point);
                point.setId(id);
            }

            JSONArray polygons = jsonObject.getJSONArray("polygons");
            for(int i =0;i<polygons.length();i++){
                JSONObject polygonData = polygons.getJSONObject(i);

                Polygon polygon = primitiveFactory.createPolygonFromJson(polygonData, scene.idManager);
                scene.addPolygon(polygon);
            }
        } catch (IOException e) {
            System.out.println("problem with reading from load file");

        }
    }

    @Override
    public void saveScene(Scene scene, File file) {
        try {
            PrintWriter printWriter = new PrintWriter(file);
            JSONObject jsonObject = new JSONObject();

            JSONArray pointsJson = new JSONArray();
            for (Point point : scene.getPoints()) {
                pointsJson.put(wrapPointJsonWithIndex(point, point.getId()));
            }
            JSONArray polygonJson = new JSONArray();
            for(Polygon polygon : scene.getPolygons()){
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
}
