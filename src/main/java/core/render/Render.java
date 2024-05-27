package core.render;

import core.scene.Scene;
import javafx.util.Pair;
import primitive.Point;
import primitive.face.Face;
import utils.Calculation;
import utils.line.Line;
import utils.vectors.Vector2D;
import utils.vectors.Vector3D;

import java.awt.Color;
import java.util.ArrayList;

public class Render {

    private final RenderConfig renderConfig;

    public Render(RenderConfig renderConfig) {
        this.renderConfig = renderConfig;
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
    private Line getRayLine(int i, int j){
        Vector2D vu = new Vector2D(i, j).division(new Vector2D(renderConfig.resolution[0], renderConfig.resolution[1]));
        Vector3D ray = vu.addZ(10);

        double fraction = ((double) j / renderConfig.resolution[1] - 0.5);
        double angle = fraction * renderConfig.pow;
        ray=ray.rotateX(angle);

        fraction = ((double) i / renderConfig.resolution[0] - 0.5);
        angle = fraction * renderConfig.heightPow;
        ray=ray.rotateY(angle);

        return new Line(vu.addZ(0), ray);
    }
    private Color renderOneVector(int i, int j, Scene scene) {
        Line rayLine = getRayLine(i,j);
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
        for (Face face : scene.getFaces()) {
            Pair<Point, Color> intersectionPointWithColor = face.getIntersection(rayLine);
            if (intersectionPointWithColor != null) {
                points.add(intersectionPointWithColor);
            }
        }
        return points;
    }
}
