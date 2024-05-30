import core.RenderController;
import core.scene.Scene;
import primitive.Point;
import primitive.faces.Polygon;

import java.awt.*;
import java.io.File;

public class Runner {
    public static void main(String[] args) {
        Scene scene = new Scene();
        scene.loadSceneFromFile(new File("src/main/Scenes/Scene4.json"));
//        scene.addPrimitive(new Polygon(new Point(-1,8,9),
//                new Point(1,8,9),
//                new Point(1,-10,9),
//                new Color(255,0,0)));
//        scene.saveScene();
        RenderController renderController = new RenderController();
        renderController.setScene(scene);
        renderController.startRender();
    }
}
