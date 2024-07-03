package core.tools.selecting;

import core.UI.elements.toolPanel.pointer.ObjectPanel;
import core.UI.managers.MouseMotionManager;
import core.camera.Camera;
import primitive.calculation.Point;
import primitive.Primitive;
import primitive.calculation.faces.Face;
import primitive.calculation.faces.Polygon;
import utils.line.Line;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.Iterator;

public class SelectedMovementMouseMotionListener implements MouseMotionListener {
    private final Camera camera;
    private final SelectedObjectManager selectedObjectManager;
    private final ObjectPanel objectPanel;
    private MouseMotionManager mouseMotionManager;
    private boolean working = true;

    public void setMouseMotionManager(MouseMotionManager mouseMotionManager) {
        this.mouseMotionManager = mouseMotionManager;
    }

    public SelectedMovementMouseMotionListener(Camera camera, SelectedObjectManager selectedObjectManager, ObjectPanel objectPanel) {
        this.camera = camera;
        this.selectedObjectManager = selectedObjectManager;
        this.objectPanel = objectPanel;
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if (!working) {
            return;
        }
        if (selectedObjectManager.isSelectedOnlyOnePoint()) {
            if (this.mouseMotionManager != null) {
                mouseMotionManager.stopCameraMotion();
            }
            Iterator<Primitive> it = selectedObjectManager.getSelected().iterator();
            Point point = (Point) it.next();
            Face pointFace = new Face(point, camera.getRightVector(), camera.getAboveVector());

            Line line = camera.getRayLine(e.getX(), e.getY());
            Point newPointPosition = pointFace.getIntersection(line);

            point.movePointToOtherPointCoordinates(newPointPosition);
            objectPanel.update();
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {

        if (this.mouseMotionManager != null) {
            mouseMotionManager.startCameraMotion();
        }
    }
}
