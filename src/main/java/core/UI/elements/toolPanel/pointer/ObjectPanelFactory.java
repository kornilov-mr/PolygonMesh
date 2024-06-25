package core.UI.elements.toolPanel.pointer;

import core.UI.managers.FocusTabManager;
import core.scene.Scene;
import primitive.Point;
import primitive.Primitive;
import primitive.faces.Polygon;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Objects;

public class ObjectPanelFactory {

    private final FocusTabManager focusTabManager;
    private final Scene scene;

    public ObjectPanelFactory(FocusTabManager focusTabManager, Scene scene) {
        this.focusTabManager = focusTabManager;
        this.scene = scene;
    }

    public ObjectInfoPanel createObjectPanel(Primitive primitive){
        ObjectInfoPanel objectInfoPanel = null;
        if(primitive instanceof Polygon){
            Polygon polygon = (Polygon) primitive;
            objectInfoPanel = polygon.toInfoPanel(focusTabManager);
        }
        if(primitive instanceof Point){
            Point point = (Point) primitive;
            objectInfoPanel = point.toInfoPanel(focusTabManager);
        }
        return objectInfoPanel;
    }
}
