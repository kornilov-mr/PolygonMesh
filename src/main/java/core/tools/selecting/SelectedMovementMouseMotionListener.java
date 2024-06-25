package core.tools.selecting;

import core.UI.managers.MouseMotionManager;
import core.camera.Camera;
import primitive.Point;
import primitive.Primitive;
import primitive.faces.Face;
import primitive.faces.Polygon;
import utils.line.Line;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.Iterator;

public class SelectedMovementMouseMotionListener implements MouseMotionListener {
    private final Camera camera;
    private final SelectedObjectManager selectedObjectManager;
    private MouseMotionManager mouseMotionManager;
    private boolean working = true;

    public void setMouseMotionManager(MouseMotionManager mouseMotionManager) {
        this.mouseMotionManager = mouseMotionManager;
    }

    public SelectedMovementMouseMotionListener(Camera camera, SelectedObjectManager selectedObjectManager) {
        this.camera = camera;
        this.selectedObjectManager = selectedObjectManager;
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if(!working){
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

                ArrayList<Polygon> polygons = camera.getScene().getPointsToPolygon().get(point);

                for (Polygon polygon : polygons) {
                    polygon.calculateNormalVector();
                }
            }

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        if(this.mouseMotionManager!=null){
            mouseMotionManager.startCameraMotion();
        }
    }
}
