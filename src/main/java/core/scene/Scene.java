package core.scene;

import org.json.JSONArray;
import org.json.JSONObject;
import primitive.PrimitiveFactory;
import primitive.faces.Polygon;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Scene {

    private ArrayList<Polygon> polygons = new ArrayList<>();
    private final File pathToSceneFolder = new File("src/main/Scenes");

    public Scene() {

    }

    public void addPolygon(Polygon polygon) {
        polygons.add(polygon);
    }

    public void saveScene() {
        File saveFile = getSaveFile();
        try {
            PrintWriter printWriter = new PrintWriter(saveFile);
            JSONArray jsonArray = new JSONArray();
            for (Polygon polygon : polygons) {
                jsonArray.put(polygon.objectInSavingFormat());
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
                Polygon polygon =primitiveFactory.createPrimitiveFromJson((JSONObject) jsonArray.get(i));
                polygons.add(polygon);
            }
        } catch (IOException e) {
            System.out.println("problem with reading from load file");

        }
    }

    public ArrayList<Polygon> getPolygons() {
        return polygons;
    }
}
