import core.RenderController;
import core.scene.Scene;
import primitive.Point;
import primitive.face.Face;

import java.awt.*;

public class Runner {
    public static void main(String[] args) {
        Scene scene = new Scene();
        scene.addFace(new Face(new Point(-1,8,9),
                new Point(1,8,9),
                new Point(1,-10,9),
                new Color(255,0,0)));

        RenderController renderController = new RenderController();
        renderController.setScene(scene);
        renderController.startRender();
    }
}
