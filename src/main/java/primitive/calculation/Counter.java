package primitive.calculation;

import core.UI.elements.toolPanel.pointer.objectInfoPanels.InfoPanelConvertible;
import core.UI.elements.toolPanel.pointer.objectInfoPanels.ObjectInfoPanel;
import core.UI.managers.FocusTabManager;
import core.tools.commands.CommandManager;
import org.json.JSONObject;
import primitive.Primitive;
import primitive.calculation.faces.Face;
import utils.colors.ColorAdapter;
import utils.line.Line;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Counter extends Primitive implements InfoPanelConvertible {

    private final Point pointA;
    private final Point pointB;
    private final Line counterLine;

    public Counter(Point pointA, Point pointB) {
        this(pointA,pointB,0.1, new Color(0,0,0));
    }

    public Counter(Point pointA, Point pointB, double size) {
        this(pointA,pointB,size, new Color(0,0,0));
    }

    public Counter(Point pointA, Point pointB, double size, Color color) {
        super(color,size);
        this.pointA = pointA;
        this.pointB = pointB;
        this.counterLine = new Line(pointA,pointB);
    }

    @Override
    public Point getIntersection(Line line) {
        Point point =counterLine.getSoftIntersection(line,size);
        if(point==null){
            return null;
        }
        Face beneathFace = new Face(pointA,counterLine.directionVector);
        Face aboveFace = new Face(pointB,counterLine.directionVector);
        if(beneathFace.ifPointIsAboveFace(point)*aboveFace.ifPointIsAboveFace(point)==-1){
            return point;
        }
        return null;
    }

    @Override
    public JSONObject objectInSavingFormat() {
        return null;
    }

    public Point getPointA() {
        return pointA;
    }

    public Point getPointB() {
        return pointB;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Counter){
            Counter counter = (Counter) obj;
            if(counter.pointA!=pointA){
                return false;
            }
            if(counter.getPointB()!=pointB){
                return false;
            }
            return true;
        }
        return false;
    }

    @Override
    public ObjectInfoPanel toInfoPanel(FocusTabManager focusTabManager, CommandManager commandManager) {
        ObjectInfoPanel pointAPanel = pointA.toInfoPanel(focusTabManager,commandManager);
        ObjectInfoPanel pointBPanel = pointB.toInfoPanel(focusTabManager,commandManager);
        ObjectInfoPanel colorPanel = new ColorAdapter(color, this).toInfoPanel(focusTabManager,commandManager);
        ObjectInfoPanel objectInfoPanel = new ObjectInfoPanel(new ArrayList<>(Arrays.asList(pointAPanel,pointBPanel,colorPanel))) {
            @Override
            public JPanel createJPanel() {
                JPanel jPanel = new JPanel();
                jPanel.setLayout(new BoxLayout(jPanel,BoxLayout.Y_AXIS));
                jPanel.add(new JLabel("Counter"));

                jPanel.add(pointAPanel.jPanel);
                jPanel.add(pointBPanel.jPanel);
                jPanel.add(colorPanel.jPanel);
//                jPanel.add(createColorPanel(polygon));
                jPanel.setBorder(BorderFactory.createLineBorder(Color.black));
                return jPanel;
            }
        };
        return objectInfoPanel;
    }

}
