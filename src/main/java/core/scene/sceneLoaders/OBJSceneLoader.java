package core.scene.sceneLoaders;

import core.scene.Scene;
import core.scene.resentProjects.ResentProjectManager;
import org.json.JSONArray;
import org.json.JSONObject;
import primitive.PrimitiveFactory;
import primitive.calculation.Point;
import primitive.calculation.faces.Polygon;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class OBJSceneLoader extends SceneLoader{
    public OBJSceneLoader( ResentProjectManager resentProjectManager) {
        super(new File("src/main/Scenes"), resentProjectManager);
    }
    public OBJSceneLoader(File sceneFolder, ResentProjectManager resentProjectManager) {
        super(sceneFolder, resentProjectManager);
    }

    @Override
    protected void read(File file, Scene scene) {
        PrimitiveFactory primitiveFactory = new PrimitiveFactory();
        ArrayList<Point> points = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            while (reader.ready()) {
                String line = reader.readLine();
                if (line.charAt(0) != '#') {
                    String flag = line.split(" ")[0];
                    if (Objects.equals(flag, "v")) {
                        points.add(primitiveFactory.createPointFromOBJString(line));
                    } else if (Objects.equals(flag, "f")) {
                        String[] pointData = line.replaceFirst(flag+" ", "").split(" ");
                        if (pointData.length < 2) {
                            throw new RuntimeException("Vertexes with less than 3 vertices aren't supported");
                        }
                        if (pointData.length > 4) {
                            throw new RuntimeException("Vertexes with more than 4 vertices aren't supported");
                        }
                        int[] indexes = new int[pointData.length];
                        for (int i = 0; i < pointData.length; i++) {
                            String data = pointData[i];
                            String temp = data.split("/")[0];
                            indexes[i] = Integer.parseInt(temp)-1;
                        }
                        Polygon polygon = new Polygon(points.get(indexes[0]), points.get(indexes[1]), points.get(indexes[2]));
                        scene.addPolygon(polygon);
                        if (pointData.length == 4) {
                            Polygon polygon2 = new Polygon(points.get(indexes[2]), points.get(indexes[3]), points.get(indexes[0]));
                            scene.addPolygon(polygon2);
                        }
                    }
                }
            }
        }catch (IOException e) {
            System.out.println("problem with reading from load file");

        }
    }

    @Override
    protected void saving(File file, Scene scene) {

        try {
            PrintWriter printWriter = new PrintWriter(file);
            printWriter.println("mtllib untitled1.mtl");
            printWriter.println("o Cube");
            Map<Point, Integer> points = new HashMap<>();

            int i =0;
            for(Point point : scene.getPoints()){
                String saveString= "v";
                saveString+=" "+String.valueOf(Double.valueOf(point.getX()));
                saveString+=" "+String.valueOf(Double.valueOf(point.getY()));
                saveString+=" "+String.valueOf(Double.valueOf(point.getZ()));
                points.put(point,i);
                printWriter.println(saveString);
                i+=1;
            }
            for(Polygon polygon : scene.getPolygons()){
                String saveString= "vn";
                saveString+=" "+String.valueOf(Double.valueOf(polygon.getCoordinateForm().A));
                saveString+=" "+String.valueOf(Double.valueOf(polygon.getCoordinateForm().B));
                saveString+=" "+String.valueOf(Double.valueOf(polygon.getCoordinateForm().C));
                printWriter.println(saveString);
            }
            printWriter.println("s 0");

            printWriter.println("usemtl Material");
            i=0;
            for(Polygon polygon : scene.getPolygons()){
                String saveString= "f";
                saveString+=" "+(points.get(polygon.getPointA())+1)+"//"+(i+1);
                saveString+=" "+(points.get(polygon.getPointB())+1)+"//"+(i+1);
                saveString+=" "+(points.get(polygon.getPointC())+1)+"//"+(i+1);
                printWriter.println(saveString);
                i+=1;
            }
            printWriter.flush();
        } catch (FileNotFoundException e) {
            System.out.println("Save file isn't found");
        }
    }
}
