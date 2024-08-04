package core.tools.selecting;

import core.UI.visuals.elements.toolPanel.pointer.ObjectPanel;
import core.tools.keys.MainKeyListener;
import core.tools.managers.MouseMotionManager;
import core.camera.Camera;
import core.tools.commands.CommandManager;
import core.tools.commands.changeParameters.point.PointMoveChange;
import primitive.calculation.Point;
import primitive.Primitive;
import primitive.calculation.faces.Face;
import utils.line.Line;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.Iterator;

public class SelectedMovementMouseMotionListener implements MouseMotionListener {
    private final Camera camera;
    private final SelectedObjectManager selectedObjectManager;
    private final ObjectPanel objectPanel;
    private MouseMotionManager mouseMotionManager;
    private final CommandManager commandManager;
    private final MainKeyListener mainKeyListener;
    private boolean dragged= false;
    private Point pointDragged;
    private boolean working = true;

    public void setMouseMotionManager(MouseMotionManager mouseMotionManager) {
        this.mouseMotionManager = mouseMotionManager;
    }

    public SelectedMovementMouseMotionListener(Camera camera, SelectedObjectManager selectedObjectManager, ObjectPanel objectPanel, CommandManager commandManager, MainKeyListener mainKeyListener) {
        this.camera = camera;
        this.selectedObjectManager = selectedObjectManager;
        this.objectPanel = objectPanel;
        this.commandManager = commandManager;
        this.mainKeyListener=mainKeyListener;
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if (!working) {
            return;
        }
        if (selectedObjectManager.isSelectedOnlyOnePoint()&& !mainKeyListener.isShiftPressed()) {
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
            pointDragged=new Point(point);
            dragged=true;
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {

        if (this.mouseMotionManager != null) {
            mouseMotionManager.startCameraMotion();
        }
        if(dragged){
            dragged=false;
            commandManager.executeCommand( new PointMoveChange(pointDragged));
        }
    }
}
