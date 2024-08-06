package core.UI.controller;

import core.UI.visuals.MainWindowListener;
import core.UI.visuals.elements.toolPanel.pointer.ObjectPanel;
import core.UI.visuals.elements.toolPanel.toolInterface.InstructionToolBar;
import core.UI.visuals.elements.toolPanel.toolInterface.instructions.InstructionManager;
import core.UI.visuals.elements.toolPanel.toolInterface.instructions.InstructionPanel;
import core.camera.Camera;
import core.camera.cameraControl.CameraKeyListener;
import core.camera.cameraControl.CameraMouseListener;
import core.render.RenderConfig;
import core.render.RenderSwitcher;
import core.scene.Scene;
import core.scene.SceneManipulator;
import core.tools.changes.ChangeManager;
import core.tools.commands.CommandManager;
import core.tools.keys.KeyBindRegister;
import core.tools.keys.MainKeyListener;
import core.tools.managers.FocusTabManager;
import core.tools.managers.UpdateManager;
import core.tools.selecting.PointMouseListener;
import core.tools.selecting.SelectedMovementMouseMotionListener;

public class WindowController {
    private final RenderConfig renderConfig;
    private final UpdateManager updateManager = new UpdateManager();
    private final ChangeManager changeManager;
    private final CommandManager commandManager;
    private final CameraKeyListener cameraKeyListener;
    private final CameraMouseListener cameraMouseListener;
    private final FocusTabManager focusTabManager;
    private final MainKeyListener mainKeyListener;
    private final PointMouseListener pointMouseListener;
    private final Camera camera;
    private final ObjectPanel objectPanel;
    private final Scene scene;
    private final InstructionPanel instructionPanel;
    private final InstructionManager instructionManager;
    private final InstructionToolBar instructionToolBar;
    private final KeyBindRegister keyBindRegister;
    private final SelectedMovementMouseMotionListener selectedMovement;
    private final MainWindowListener mainWindowListener;
    private final SceneManipulator sceneManipulator;
    public WindowController(RenderConfig renderConfig, Camera camera,Scene scene, RenderSwitcher renderSwitcher) {
        this.renderConfig = renderConfig;
        this.camera= camera;
        this.scene= scene;
        this.changeManager = new ChangeManager(scene.idManager, scene);
        this.focusTabManager=new FocusTabManager();
        this.commandManager = new CommandManager(scene, scene.selectedObjectManager, changeManager);

        this.cameraKeyListener = new CameraKeyListener(camera,renderConfig);
        this.cameraMouseListener = new CameraMouseListener(camera,renderConfig);

        this.instructionPanel= new InstructionPanel();

        this.instructionManager= new InstructionManager(focusTabManager,commandManager,instructionPanel);
        updateManager.addToUpdates(cameraKeyListener);
        updateManager.addToUpdates(cameraMouseListener);
        this.instructionToolBar= new InstructionToolBar(instructionManager);


        this.keyBindRegister= new KeyBindRegister();

        this.sceneManipulator= new SceneManipulator(commandManager,changeManager,renderSwitcher, instructionManager);
        this.mainKeyListener = new MainKeyListener(sceneManipulator, keyBindRegister);
        this.pointMouseListener=new PointMouseListener(camera,scene,focusTabManager, mainKeyListener,commandManager);
        this.objectPanel = new ObjectPanel();
        pointMouseListener.setObjectPanel(objectPanel);
        this.selectedMovement = new SelectedMovementMouseMotionListener(camera,scene.selectedObjectManager,objectPanel, commandManager, mainKeyListener);
        this.mainWindowListener = new MainWindowListener(keyBindRegister);
    }

    public SceneManipulator getSceneManipulator() {
        return sceneManipulator;
    }

    public MainWindowListener getMainWindowListener() {
        return mainWindowListener;
    }

    public KeyBindRegister getKeyBindRegister() {
        return keyBindRegister;
    }

    public SelectedMovementMouseMotionListener getSelectedMovement() {
        return selectedMovement;
    }

    public InstructionToolBar getInstructionToolBar() {
        return instructionToolBar;
    }

    public RenderConfig getRenderConfig() {
        return renderConfig;
    }

    public UpdateManager getUpdateManager() {
        return updateManager;
    }

    public ChangeManager getChangeManager() {
        return changeManager;
    }

    public CommandManager getCommandManager() {
        return commandManager;
    }

    public CameraKeyListener getCameraKeyListener() {
        return cameraKeyListener;
    }

    public CameraMouseListener getCameraMouseListener() {
        return cameraMouseListener;
    }
    public Camera getCamera() {
        return camera;
    }

    public FocusTabManager getFocusTabManager() {
        return focusTabManager;
    }

    public MainKeyListener getMainKeyListener() {
        return mainKeyListener;
    }

    public PointMouseListener getPointMouseListener() {
        return pointMouseListener;
    }

    public ObjectPanel getObjectPanel() {
        return objectPanel;
    }

    public Scene getScene() {
        return scene;
    }

    public InstructionPanel getInstructionPanel() {
        return instructionPanel;
    }

    public InstructionManager getInstructionManager() {
        return instructionManager;
    }
}
