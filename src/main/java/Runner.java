import core.RenderController;
import core.RenderFactory;
import core.scene.Scene;
import primitive.Point;
import primitive.faces.Polygon;

import java.awt.*;

public class Runner {
    public static void main(String[] args) {
        Scene scene = new Scene();
//        scene.loadSceneFromFile(new File("src/main/Scenes/Scene4.json"));
        scene.addPrimitive(new Polygon(new Point(1,0,10),
                new Point(1,10,0),
                new Point(1,0,0),
                new Color(0,255,0)));
        scene.addPrimitive(new Polygon(new Point(1,0,10),
                new Point(1,10,0),
                new Point(1,10,10),
                new Color(255,255,0)));

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
