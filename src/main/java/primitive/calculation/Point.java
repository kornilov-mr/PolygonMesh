package primitive.calculation;

import core.UI.visuals.elements.toolPanel.pointer.objectInfoPanels.CoordinateInfoPanel;
import core.UI.visuals.elements.toolPanel.pointer.objectInfoPanels.InfoPanelConvertible;
import core.UI.visuals.elements.toolPanel.pointer.objectInfoPanels.ObjectInfoPanel;
import core.tools.managers.FocusTabManager;
import core.tools.commands.CommandManager;
import org.json.JSONObject;
import primitive.Primitive;
import primitive.calculation.faces.CoordinateForm;
import primitive.rendering.Sphere;
import primitive.calculation.faces.Polygon;
import utils.Calculation;
import utils.line.Line;
import utils.triangle.Triangle;
import utils.vectors.Vector3D;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Point extends Primitive implements InfoPanelConvertible {

    protected double x;
    protected double y;
    protected double z;

    private final ArrayList<Polygon> belongToPolygon = new ArrayList<>();
    public Point(Point point){
        this(point.getX(),point.getY(),point.getZ(), point.getSize(), point.getColor(),point.getId());
    }
    public Point(Sphere sphere){
        this(sphere.x,sphere.y,sphere.z);
    }
    public Point(double x, double y, double z){
        this(x,y,z,0.2,new Color(0,0,0));
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
        super(color,size);
        this.x = x;
        this.y = y;
        this.z = z;
    }
    public Point(double x, double y, double z,double size, Color color, String id) {
        super(color,size);
        this.x = x;
        this.y = y;
        this.z = z;
        setId(id);
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


    @Override
    public JSONObject objectInSavingFormat(){
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
        Point pointT =closestPointOnLine(line);
        if(pointT==null){
            return null;
        }
        double distanceST=getDistanceToPoint(pointT);
        if(size*2-distanceST*2<0){
            return null;
        }
        double distanceT = Math.sqrt(size*2-distanceST*2);

        Point resultPoint = new Point(new Vector3D(pointT).add(line.directionVector.multiply(distanceT*-1)));
        return resultPoint;
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
    public ObjectInfoPanel toInfoPanel(FocusTabManager focusTabManager, CommandManager commandManager) {
        CoordinateInfoPanel panelX = new CoordinateInfoPanel(new ArrayList<>(),this,"x",x,focusTabManager, commandManager);
        CoordinateInfoPanel panelY = new CoordinateInfoPanel(new ArrayList<>(),this,"y",y,focusTabManager, commandManager);
        CoordinateInfoPanel panelZ = new CoordinateInfoPanel(new ArrayList<>(),this,"z",z,focusTabManager, commandManager);
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

    public Vector3D VectorToPoint(Point pointB){
        return new Vector3D(pointB.getX()-getX(),pointB.getY()-getY(),pointB.getZ()-getZ());
    }
    public double getDistanceToPoint(Point pointB){
        return VectorToPoint(pointB).getLength();
    }
    public boolean ifPointInTriangle(Triangle triangle){
        double mainArea = triangle.area();
        double subArea1 = new Triangle(this,triangle.getPointB(), triangle.getPointC()).area();
        double subArea2 = new Triangle(triangle.getPointA(),this, triangle.getPointC()).area();
        double subArea3 = new Triangle(triangle.getPointA(),triangle.getPointB(), this).area();
        if(Calculation.round(mainArea,10)==Calculation.round(subArea1+subArea2+subArea3,10)){
            return true;
        }
        return false;
    }
    public Point closestPointOnLine(Line line){
        CoordinateForm coordinateForm = new CoordinateForm(line.directionVector,
                new Vector3D(this).multiply(line.directionVector).multiply(-1).getSum());
        Point pointT = coordinateForm.getPointOnIntersection(line);

        return pointT;
    }
    @Override
    public String toString() {
        return "Point{" +
                "x=" + x +
                ", y=" + y +
                ", z=" + z +
                '}';
    }
}
