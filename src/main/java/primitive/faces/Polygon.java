package primitive.faces;

import core.UI.managers.FocusTabManager;
import org.json.JSONObject;
import core.UI.elements.toolPanel.pointer.objectInfoPanels.ObjectInfoPanel;
import core.UI.elements.toolPanel.pointer.objectInfoPanels.InfoPanelConvertible;
import primitive.Point;
import primitive.Primitive;
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

    private Point pointA;
    private Point pointB;
    private Point pointC;

    private Vector3D normalVector;
    protected CoordinateForm coordinateForm;
    private Color color;

    public Polygon(Point pointA, Point pointB, Point pointC){
        this(pointA,pointB,pointC,new Color(0,0,0));
    }
    public Polygon(Point pointA, Point pointB, Point pointC, Color color) {
        this.pointA = pointA;
        this.pointB = pointB;
        this.pointC = pointC;
        this.color = color;

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
    public JSONObject objectInSavingFormat(Map<Point, String> pointToIndexes) {
        JSONObject obj = new JSONObject();
        obj.put("class", "Polygon");
        obj.put("pointAId", pointToIndexes.get(pointA));
        obj.put("pointBId", pointToIndexes.get(pointB));
        obj.put("pointCId", pointToIndexes.get(pointC));
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

    public void setPointA(Point pointA) {
        this.pointA = pointA;
        calculateNormalVector();
    }

    public void setPointB(Point pointB) {
        this.pointB = pointB;
        calculateNormalVector();
    }

    public Color getMainColorForRendering() {
        if(selected){
            return new Color(0,120,215) ;
        }
        return color;
    }

    public Color getColor() {
        return color;
    }

    public void setPointC(Point pointC) {
        this.pointC = pointC;
        calculateNormalVector();
    }

    public CoordinateForm getCoordinateForm() {
        return coordinateForm;
    }
    public void setRed(int red){
        this.color = new Color(red, color.getGreen(), color.getBlue());
    }
    public void setGreen(int green){
        this.color = new Color(color.getRed(),green, color.getBlue());
    }
    public void setBlue(int blue){
        this.color = new Color(color.getRed(), color.getGreen(),blue);
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
