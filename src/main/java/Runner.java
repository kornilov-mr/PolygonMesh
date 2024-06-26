import core.RenderController;
import core.RenderFactory;
import core.scene.Scene;
import primitive.calculation.Point;

import java.awt.*;
import java.io.File;

public class Runner {
    public static void main(String[] args) {
        Scene scene = new Scene();
        Point pointA = new Point(1,0,10);
        Point pointB = new Point(1,10,0);
        Point pointC = new Point(1,0,0);
        Point pointD = new Point(1,10,10);
//        scene.loadSceneFromFile(new File("src/main/Scenes/Scene4.json"));
//        scene.addPolygon(new Polygon(pointA,
//                pointB,
//                pointC,
//                new Color(0,255,0)));
//        scene.addPolygon(new Polygon(pointA,
//                pointB,
//                pointD,
//                new Color(255,255,0)));
        scene.loadSceneFromFile(new File("src/main/Scenes/Scene32.json"));
        RenderFactory renderFactory = new RenderFactory();
        renderFactory.setScene(scene);
//        scene.saveScene();
        RenderController renderController =renderFactory.buildRenderController();
        renderController.startRender();
//        Color color = new Color(255,0,0);
//        System.out.println(color.getRGB());
        System.out.println(new Color(-65536));
    }
}
