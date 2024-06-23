package core.UI.elements.toolPanel.pointer;

import core.UI.managers.FocusTabManager;
import primitive.Point;
import primitive.Primitive;
import primitive.faces.Polygon;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.lang.reflect.Field;
import java.util.Objects;

public class ObjectPanelFactory {

    private final FocusTabManager focusTabManager;

    public ObjectPanelFactory(FocusTabManager focusTabManager) {
        this.focusTabManager = focusTabManager;
    }

    public JPanel createObjectPanel(Primitive primitive){
        JPanel jPanel = null;
        if(primitive instanceof Polygon){
            Polygon polygon = (Polygon) primitive;
            jPanel = createPolygonPanel(polygon);
        }
        jPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        return jPanel;
    }
    private JPanel createPolygonPanel(Polygon polygon){
        JPanel jPanel = new JPanel();
        jPanel.setLayout(new BoxLayout(jPanel,BoxLayout.Y_AXIS));
        jPanel.add(new JLabel("Polygon"));
        jPanel.add(createPointPanel(polygon.getPointA(),polygon));
        jPanel.add(createPointPanel(polygon.getPointB(),polygon));
        jPanel.add(createPointPanel(polygon.getPointC(),polygon));
        jPanel.add(createColorPanel(polygon));
        jPanel.setBorder(BorderFactory.createLineBorder(Color.black));

        return jPanel;
    }
    private JPanel createPointPanel(Point point,Polygon polygon){
        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new BoxLayout(titlePanel,BoxLayout.Y_AXIS));
        titlePanel.add(new JLabel("Point"));


        JPanel jPanel = new JPanel();
        jPanel.setLayout(new BoxLayout(jPanel,BoxLayout.X_AXIS));

        try {
            jPanel.add(createdCoordinatePanel(point.x,"x",point.getClass().getField("x"),point,polygon));
            jPanel.add(createdCoordinatePanel(point.y,"y",point.getClass().getField("y"),point,polygon));
            jPanel.add(createdCoordinatePanel(point.z,"z",point.getClass().getField("z"),point,polygon));
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        }


        titlePanel.setBorder(BorderFactory.createLineBorder(Color.black));
        titlePanel.add(jPanel);
        return titlePanel;
    }
    private JPanel createdCoordinatePanel(double d, String title, Field field, Point point, Polygon polygon){
        JPanel jPanel = new JPanel();
        jPanel.add(new JLabel(title+":"));
        JTextField jTextArea = new JTextField(String.valueOf(d));
        jTextArea.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent ke) {
                String value = jTextArea.getText();
                int l = value.length();
                if (ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9'||ke.getKeyChar()==KeyEvent.VK_BACK_SPACE|| ke.getKeyChar()==KeyEvent.VK_MINUS) {
                    jTextArea.setEditable(true);
                } else {
                    jTextArea.setEditable(false);
                }
            }
        });
        Action action = new AbstractAction()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
//                jTextArea.setFocusable(false);
                focusTabManager.setFocusToMainWindow();
                String value = jTextArea.getText();
                double doubleValue = Double.valueOf(value);
                try {
                    field.set(point,doubleValue);
                } catch (IllegalAccessException ex) {
                    throw new RuntimeException(ex);
                }
                polygon.calculateNormalVector();

            }
        };

        jTextArea.addActionListener(action);
        jPanel.add(jTextArea);
        return jPanel;
    }
    private JPanel createColorPickerPanel(int d, String title,Polygon polygon){
        Color color = polygon.mainColor;
        JPanel jPanel = new JPanel();
        jPanel.add(new JLabel(title+":"));
        JTextField jTextArea = new JTextField(String.valueOf(d));
        jTextArea.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent ke) {
                String value = jTextArea.getText();
                int l = value.length();
                if (ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9'||ke.getKeyChar()==KeyEvent.VK_BACK_SPACE) {
                    jTextArea.setEditable(true);
                } else {
                    jTextArea.setEditable(false);
                }
            }
        });
        Action action = new AbstractAction()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                focusTabManager.setFocusToMainWindow();
                String value = jTextArea.getText();
                int doubleValue = Integer.valueOf(value);
                if(doubleValue<=255) {
                    if (Objects.equals("R", title)) {
                        polygon.setRed(doubleValue);
                    }
                    if (Objects.equals("G", title)) {
                        polygon.setGreen(doubleValue);
                    }
                    if (Objects.equals("B", title)) {
                        polygon.setBlue(doubleValue);
                    }
                }
            }
        };

        jTextArea.addActionListener(action);
        jPanel.add(jTextArea);
        return jPanel;
    }
    private JPanel createColorPanel(Polygon polygon){
        Color mainColor = polygon.mainColor;
        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new BoxLayout(titlePanel,BoxLayout.Y_AXIS));
        titlePanel.add(new JLabel("Color"));

        JPanel jPanel = new JPanel();
        jPanel.setLayout(new BoxLayout(jPanel,BoxLayout.X_AXIS));
        jPanel.add(createColorPickerPanel(mainColor.getRed(),"R",polygon));
        jPanel.add(createColorPickerPanel(mainColor.getGreen(),"G",polygon));
        jPanel.add(createColorPickerPanel(mainColor.getBlue(),"B",polygon));
        titlePanel.setBorder(BorderFactory.createLineBorder(Color.black));
        titlePanel.add(jPanel);
        return titlePanel;
    }
}
