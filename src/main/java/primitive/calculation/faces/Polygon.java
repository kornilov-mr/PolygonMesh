package primitive.calculation.faces;

import core.UI.managers.FocusTabManager;
import org.json.JSONObject;
import core.UI.elements.toolPanel.pointer.objectInfoPanels.ObjectInfoPanel;
import core.UI.elements.toolPanel.pointer.objectInfoPanels.InfoPanelConvertible;
import primitive.Primitive;
import primitive.calculation.Counter;
import primitive.calculation.Point;
import utils.Calculation;
import utils.colors.ColorAdapter;
import utils.line.Line;
import utils.vectors.Vector3D;

import javax.swing.*;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

public class Polygon extends Primitive implements InfoPanelConvertible{

    private final Point pointA;
    private final Point pointB;
    private final Point pointC;
    private final Counter counterA;
    private final Counter counterB;
    private final Counter counterC;

    private Vector3D normalVector;
    protected CoordinateForm coordinateForm;

    public Polygon(Point pointA, Point pointB, Point pointC){
        this(pointA,pointB,pointC,new Color(0,0,0));
    }
    public Polygon(Point pointA, Point pointB, Point pointC, Color color) {
        super(color);
        this.pointA = pointA;
        this.pointB = pointB;
        this.pointC = pointC;

        this.counterA= new Counter(pointA,pointB);
        this.counterB= new Counter(pointB,pointC);
        this.counterC= new Counter(pointC,pointA);
        calculateNormalVector();
    }
    public void calculateNormalVector(){
        Vector3D directionVector1 = Calculation.VectorBetweenTwoPoints(pointA, pointB);
        Vector3D directionVector2 = Calculation.VectorBetweenTwoPoints(pointA, pointC);

        this.normalVector = directionVector1.crossMultiply(directionVector2);

        double D = -1 * normalVector.multiply(new Vector3D(pointA)).getSum();
        this.coordinateForm = new CoordinateForm(normalVector, D);
    }
    @Override
    public Point getIntersection(Line line) {
        Point intersectionWithPlane = coordinateForm.getPointOnIntersection(line);

        if (intersectionWithPlane != null && Calculation.ifPointInTriangle(intersectionWithPlane, pointA, pointB, pointC)) {
            intersectionWithPlane.setColor(color);
            return intersectionWithPlane;
        }
        return null;
    }
    @Override
    public JSONObject objectInSavingFormat() {
        JSONObject obj = new JSONObject();
        obj.put("class", "Polygon");
        obj.put("pointAId", pointA.getId());
        obj.put("pointBId", pointB.getId());
        obj.put("pointCId", pointC.getId());
        obj.put("color", color.getRGB());
        return obj;
    }
    public Point getPointA() {
        return pointA;
    }

    public Point getPointB() {
        return pointB;
    }

    public Point getPointC() {
        return pointC;
    }

    public Counter getCounterA() {
        return counterA;
    }

    public Counter getCounterB() {
        return counterB;
    }

    public Counter getCounterC() {
        return counterC;
    }

    public void setPointA(Point point) {
        pointA.movePointToOtherPointCoordinates(point);
        calculateNormalVector();
    }

    public void setPointB(Point point) {
        pointB.movePointToOtherPointCoordinates(point);
        calculateNormalVector();
    }

    public void setPointC(Point point) {
        pointC.movePointToOtherPointCoordinates(point);
        calculateNormalVector();
    }

    public CoordinateForm getCoordinateForm() {
        return coordinateForm;
    }

    @Override
    public ObjectInfoPanel toInfoPanel(FocusTabManager focusTabManager) {
        ObjectInfoPanel pointAPanel = pointA.toInfoPanel(focusTabManager);
        ObjectInfoPanel pointBPanel = pointB.toInfoPanel(focusTabManager);
        ObjectInfoPanel pointCPanel = pointC.toInfoPanel(focusTabManager);
        ObjectInfoPanel colorPanel = new ColorAdapter(color, this).toInfoPanel(focusTabManager);
        ObjectInfoPanel objectInfoPanel = new ObjectInfoPanel(new ArrayList<>(Arrays.asList(pointAPanel,pointCPanel,pointBPanel,colorPanel))) {
            @Override
            public JPanel createJPanel() {
                JPanel jPanel = new JPanel();
                jPanel.setLayout(new BoxLayout(jPanel,BoxLayout.Y_AXIS));
                jPanel.add(new JLabel("Polygon"));

                jPanel.add(pointAPanel.jPanel);
                jPanel.add(pointBPanel.jPanel);
                jPanel.add(pointCPanel.jPanel);
                jPanel.add(colorPanel.jPanel);
//                jPanel.add(createColorPanel(polygon));
                jPanel.setBorder(BorderFactory.createLineBorder(Color.black));
                return jPanel;
            }
        };
        return objectInfoPanel;
    }
}
