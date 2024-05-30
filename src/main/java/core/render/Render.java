package core.render;

import core.render.camera.Camera;
import core.scene.Scene;
import javafx.util.Pair;
import primitive.Point;
import primitive.Primitive;
import utils.Calculation;
import utils.line.Line;

import java.awt.Color;
import java.util.ArrayList;
import java.util.concurrent.CancellationException;

public class Render {

    private final RenderConfig renderConfig;
    private final Camera camera;

    public Render(RenderConfig renderConfig, Camera camera) {
        this.renderConfig = renderConfig;
        this.camera=camera;
    }
    public Frame ProcessFrame(Scene scene){
        ArrayList<ArrayList<Color>> colors = new ArrayList<>();

        for (int i = 0; i < renderConfig.resolution[0]; i++) {
            colors.add(new ArrayList<>());
            for (int j = 0; j < renderConfig.resolution[1]; j++) {
                Color colorOfPixel = renderOneVector(i,j,scene);
                colors.get(i).add(colorOfPixel);
            }
        }
        return new Frame(colors);
    }
    private Color renderOneVector(int i, int j, Scene scene) {
        Line rayLine = camera.getRayLine(i,j);
        return getColorOfFirstIntersection(rayLine,scene);
    }
    private Color getColorOfFirstIntersection(Line rayLine, Scene scene){
        ArrayList<Pair<Point, Color>> points= getAllCollision(rayLine,scene);
        double minDistance = Double.MAX_VALUE;
        Color nearestColor= null;
        for(Pair<Point,Color> pointWithColor : points){
            double distance = Calculation.getLengthBetweenTwoPoints(new Point(0,0,0),pointWithColor.getKey());
            if(minDistance> distance){
                minDistance=distance;
                nearestColor=pointWithColor.getValue();
            }
        }
        if (nearestColor == null) {
            return new Color(255,255,255);
        }
        return nearestColor;
    }
    private ArrayList<Pair<Point, Color>> getAllCollision(Line rayLine, Scene scene) {
        ArrayList<Pair<Point, Color>> points = new ArrayList<>();
        for (Primitive primitive : scene.getFaces()) {
            Pair<Point, Color> intersectionPointWithColor = primitive.getIntersection(rayLine);
            if (intersectionPointWithColor != null) {
                points.add(intersectionPointWithColor);
            }
        }
        return points;
    }
}
