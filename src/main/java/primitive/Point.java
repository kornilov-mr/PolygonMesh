package primitive;

import core.UI.elements.toolPanel.pointer.objectInfoPanels.CoordinateInfoPanel;
import core.UI.elements.toolPanel.pointer.objectInfoPanels.InfoPanelConvertible;
import core.UI.elements.toolPanel.pointer.objectInfoPanels.ObjectInfoPanel;
import core.UI.managers.FocusTabManager;
import org.json.JSONObject;
import primitive.faces.Polygon;
import utils.Calculation;
import utils.line.Line;
import utils.vectors.Vector3D;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

public class Point extends Primitive implements InfoPanelConvertible {

    private double x;
    private double y;
    private double z;
    public Color color;
    public double size;

    private ArrayList<Polygon> belongToPolygon = new ArrayList<>();

    public Point(Sphere sphere){
        this(sphere.x,sphere.y,sphere.z);
    }
    public Point(double x, double y, double z){
        this(x,y,z,1,new Color(0,0,0));
    }
    public Point(Vector3D vector){
        this(vector.getX(), vector.getY(), vector.getZ(),0.1,new Color(0,0,0));
    }
    public Point(double x, double y, double z, int size){
        this(x,y,z,size,new Color(0,0,0));
    }
    public Point(Vector3D vector, int size){
        this(vector.getX(), vector.getY(), vector.getZ(),size,new Color(0,0,0));
    }

    public Point(double x, double y, double z,double size, Color color) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.color=color;
        this.size=size;
    }

    public void setX(double x) {
        this.x = x;
        notifyAllPolygonsToChange();
    }

    public void setY(double y) {
        this.y = y;
        notifyAllPolygonsToChange();
    }

    public void setZ(double z) {
        this.z = z;
        notifyAllPolygonsToChange();
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getZ() {
        return z;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public JSONObject objectInSavingFormat(Map<Point, String> pointToIndexes){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("class","Point");
        jsonObject.put("x",x);
        jsonObject.put("y",y);
        jsonObject.put("z",z);
        jsonObject.put("size",size);
        jsonObject.put("color",color.getRGB());
        return jsonObject;
    }
    @Override
    public Point getIntersection(Line line) {
        Point pointT = Calculation.closestPointToLine(this,line);
        if(pointT==null){
            return null;
        }
        double distanceST=Calculation.getLengthBetweenTwoPoints(this,pointT);
        if(size*2-distanceST*2<0){
            return null;
        }
        double distanceT = Math.sqrt(size*2-distanceST*2);

        Point resultPoint = new Point(new Vector3D(pointT).add(line.directionVector.multiply(distanceT*-1)));
        return resultPoint;
    }

    public boolean isSelected() {
        return selected;
    }
    public void movePointToOtherPointCoordinates(Point point){
        this.x=point.x;
        this.y=point.y;
        this.z=point.z;
        notifyAllPolygonsToChange();
    }

    public void addPolygon(Polygon polygon){
        belongToPolygon.add(polygon);
    }

    public ArrayList<Polygon> getBelongToPolygon() {
        return belongToPolygon;
    }

    public void notifyAllPolygonsToChange(){
        for(Polygon polygon: belongToPolygon){
            polygon.calculateNormalVector();
        }
    }

    @Override
    public ObjectInfoPanel toInfoPanel(FocusTabManager focusTabManager) {
        CoordinateInfoPanel panelX = new CoordinateInfoPanel(new ArrayList<>(),this,"x",x,focusTabManager);
        CoordinateInfoPanel panelY = new CoordinateInfoPanel(new ArrayList<>(),this,"y",y,focusTabManager);
        CoordinateInfoPanel panelZ = new CoordinateInfoPanel(new ArrayList<>(),this,"z",z,focusTabManager);
        ObjectInfoPanel objectInfoPanel = new ObjectInfoPanel(new ArrayList<>(Arrays.asList(panelX,panelY,panelZ))) {
            @Override
            public JPanel createJPanel() {
                JPanel titlePanel = new JPanel();
                titlePanel.setLayout(new BoxLayout(titlePanel,BoxLayout.Y_AXIS));
                titlePanel.add(new JLabel("Point"));


                JPanel jPanel = new JPanel();
                jPanel.setLayout(new BoxLayout(jPanel,BoxLayout.X_AXIS));
                jPanel.add(panelX.jPanel);
                jPanel.add(panelY.jPanel);
                jPanel.add(panelZ.jPanel);

                titlePanel.setBorder(BorderFactory.createLineBorder(Color.black));
                titlePanel.add(jPanel);
                return titlePanel;
            }

        };
        return objectInfoPanel;
    }
}
