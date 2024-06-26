package core.UI.elements.toolPanel.pointer;

import core.UI.elements.toolPanel.pointer.objectInfoPanels.ObjectInfoPanel;
import core.UI.managers.FocusTabManager;
import core.scene.Scene;
import primitive.calculation.Point;
import primitive.Primitive;
import primitive.calculation.faces.Polygon;

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
