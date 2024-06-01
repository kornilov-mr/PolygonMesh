import core.RenderController;
import core.scene.Scene;
import primitive.Point;
import primitive.faces.Face;
import primitive.faces.Polygon;

import java.awt.*;

public class Runner {
    public static void main(String[] args) {
        Scene scene = new Scene();
//        scene.loadSceneFromFile(new File("src/main/Scenes/Scene4.json"));
        scene.addPrimitive(new Polygon(new Point(1,0,10),
                new Point(1,10,0),
                new Point(1,0,0),
                new Color(255,0,0)));
        scene.addPrimitive(new Face(new Point(-1,8,9),
                new Point(-1,3,9),
                new Point(-1,-10,15     ),
                new Color(0,0,0)));
//        scene.saveScene();
        RenderController renderController = new RenderController();
        renderController.setScene(scene);
        renderController.startRender();
    }
}
