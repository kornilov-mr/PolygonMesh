import core.AppBuilder;
import core.UI.App;
import core.render.RenderController;
import core.scene.Scene;
import primitive.calculation.Point;
import primitive.calculation.faces.Polygon;

import java.awt.*;

public class Runner {
    public static void main(String[] args) {
        Scene scene = new Scene();
        Point pointA = new Point(1,0,10);
        Point pointB = new Point(1,10,0);
        Point pointC = new Point(1,0,0);
        Point pointD = new Point(1,10,10);
        Point pointE = new Point(5,0,0);
//        scene.loadSceneFromFile(new File("src/main/Scenes/Scene4.json"));
        scene.addPolygon(new Polygon(pointA,
                pointB,
                pointC,
                new Color(0,255,0)));
        scene.addPolygon(new Polygon(pointA,
                pointB,
                pointD,
                new Color(255,255,0)));
        scene.addPoint(pointE);
//        scene.loadSceneFromFile(new File("src/main/Scenes/Scene32.json"));
        AppBuilder appBuilder = new AppBuilder();
        appBuilder.setScene(scene);
//        scene.saveScene();
        App app =appBuilder.buildApp();
        app.start();
        System.out.println(new Color(-65536));
    }
}
