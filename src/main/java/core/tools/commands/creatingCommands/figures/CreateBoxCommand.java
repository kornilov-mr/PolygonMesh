package core.tools.commands.creatingCommands.figures;

import core.scene.Scene;
import core.tools.commands.creatingCommands.basic.ObjectCreationCommand;
import core.tools.selecting.SelectedObjectManager;
import primitive.Primitive;
import primitive.calculation.OrientedPoint;
import primitive.calculation.Point;
import primitive.calculation.faces.Polygon;
import utils.vectors.Vector3D;

import java.awt.*;
import java.util.ArrayList;

public class CreateBoxCommand extends ObjectCreationCommand {
    private final Point center;
    private final double width;
    private final double horizontalAngle;
    private final double verticalAngle;
    private final Color color;
    public CreateBoxCommand(Point center, double width) {
        this(center,width,0,0, new Color(0,0,0));
    }
    public CreateBoxCommand(Point center,double width, double horizontalAngle, double verticalAngle,Color color) {
        super(new ArrayList<>());
        this.center=center;
        this.width=width;
        this.horizontalAngle=horizontalAngle;
        this.verticalAngle=verticalAngle;
        this.color=color;
    }
    @Override
    public void execute(Scene scene, SelectedObjectManager selectedObjectManager) {
        OrientedPoint orientedCenter = new OrientedPoint(center,horizontalAngle,verticalAngle);
        Point pointA = new Point(new Vector3D(orientedCenter.getPoint())
                .add(orientedCenter.getFrontVector().multiply(width))
                .add(orientedCenter.getRightVector().multiply(width))
                .add(orientedCenter.getAboveVector().multiply(width)));

        Point pointB = new Point(new Vector3D(orientedCenter.getPoint())
                .add(orientedCenter.getFrontVector().multiply(-1*width))
                .add(orientedCenter.getRightVector().multiply(width))
                .add(orientedCenter.getAboveVector().multiply(width)));

        Point pointC = new Point(new Vector3D(orientedCenter.getPoint())
                .add(orientedCenter.getFrontVector().multiply(width))
                .add(orientedCenter.getRightVector().multiply(-1*width))
                .add(orientedCenter.getAboveVector().multiply(width)));

        Point pointD = new Point(new Vector3D(orientedCenter.getPoint())
                .add(orientedCenter.getFrontVector().multiply(-1*width))
                .add(orientedCenter.getRightVector().multiply(-1*width))
                .add(orientedCenter.getAboveVector().multiply(width)));

        Point pointAH = new Point(new Vector3D(orientedCenter.getPoint())
                .add(orientedCenter.getFrontVector().multiply(width))
                .add(orientedCenter.getRightVector().multiply(width))
                .add(orientedCenter.getAboveVector().multiply(-1*width)));

        Point pointBH = new Point(new Vector3D(orientedCenter.getPoint())
                .add(orientedCenter.getFrontVector().multiply(-1*width))
                .add(orientedCenter.getRightVector().multiply(width))
                .add(orientedCenter.getAboveVector().multiply(-1*width)));

        Point pointCH = new Point(new Vector3D(orientedCenter.getPoint())
                .add(orientedCenter.getFrontVector().multiply(width))
                .add(orientedCenter.getRightVector().multiply(-1*width))
                .add(orientedCenter.getAboveVector().multiply(-1*width)));

        Point pointDH = new Point(new Vector3D(orientedCenter.getPoint())
                .add(orientedCenter.getFrontVector().multiply(-1*width))
                .add(orientedCenter.getRightVector().multiply(-1*width))
                .add(orientedCenter.getAboveVector().multiply(-1*width)));

        Polygon polygonAbove1 = new Polygon(pointA,pointB,pointC,color);
        Polygon polygonAbove2 = new Polygon(pointB,pointD,pointC,color);

        Polygon polygonRight1 = new Polygon(pointC,pointD,pointCH,color);
        Polygon polygonRight2 = new Polygon(pointCH,pointD,pointDH,color);

        Polygon polygonLeft1 = new Polygon(pointA,pointB,pointAH,color);
        Polygon polygonLeft2 = new Polygon(pointAH,pointB,pointBH,color);

        Polygon polygonFront1 = new Polygon(pointA,pointC,pointAH,color);
        Polygon polygonFront2 = new Polygon(pointAH,pointC,pointCH,color);

        Polygon polygonBehind1 = new Polygon(pointB,pointD,pointBH,color);
        Polygon polygonBehind2 = new Polygon(pointBH,pointD,pointDH,color);

        Polygon polygonBelow1 = new Polygon(pointAH,pointBH,pointCH,color);
        Polygon polygonBelow2 = new Polygon(pointBH,pointDH,pointCH,color);

        addPrimitiveToChange(polygonAbove1);
        addPrimitiveToChange(polygonAbove2);

        addPrimitiveToChange(polygonRight1);
        addPrimitiveToChange(polygonRight2);

        addPrimitiveToChange(polygonLeft1);
        addPrimitiveToChange(polygonLeft2);

        addPrimitiveToChange(polygonFront1);
        addPrimitiveToChange(polygonFront2);
//
        addPrimitiveToChange(polygonBehind1);
        addPrimitiveToChange(polygonBehind2);
//
        addPrimitiveToChange(polygonBelow1);
        addPrimitiveToChange(polygonBelow2);

        scene.addPolygon(polygonAbove1);
        scene.addPolygon(polygonAbove2);

        scene.addPolygon(polygonRight1);
        scene.addPolygon(polygonRight2);

        scene.addPolygon(polygonLeft1);
        scene.addPolygon(polygonLeft2);

        scene.addPolygon(polygonFront1);
        scene.addPolygon(polygonFront2);

        scene.addPolygon(polygonBehind1);
        scene.addPolygon(polygonBehind2);

        scene.addPolygon(polygonBelow1);
        scene.addPolygon(polygonBelow2);

        super.execute(scene, selectedObjectManager);
    }
}
