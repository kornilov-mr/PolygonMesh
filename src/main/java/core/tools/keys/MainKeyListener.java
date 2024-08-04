package core.tools.keys;

import core.UI.visuals.elements.toolPanel.toolInterface.instructions.InstructionManager;
import core.render.RenderSwitcher;
import core.scene.SceneManipulator;
import core.tools.changes.ChangeManager;
import core.tools.commands.CommandManager;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashSet;

public class MainKeyListener implements KeyListener {
    private final SceneManipulator sceneManipulator;
    private final HashSet<Integer> keyPressed = new HashSet<>();
    private final KeyBindRegister keyBindRegister;
    private boolean shiftPressed = false;

    public MainKeyListener(CommandManager commandManager, ChangeManager changeManager, RenderSwitcher renderSwitcher, InstructionManager instructionManager, KeyBindRegister keyBindRegister) {
        this.keyBindRegister = keyBindRegister;
        this.sceneManipulator = new SceneManipulator(commandManager,changeManager,renderSwitcher, instructionManager);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        keyPressed.add(e.getKeyCode());
        if(e.getKeyCode()==KeyEvent.VK_SHIFT){
            shiftPressed=true;
        }
        keyBindRegister.loopThroughOneTimeBinds(keyPressed,sceneManipulator);
        keyBindRegister.loopThroughRadioKeyBindOn(keyPressed,sceneManipulator);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        keyPressed.remove(e.getKeyCode());
        if(e.getKeyCode()==KeyEvent.VK_SHIFT){
            shiftPressed=false;
        }
        keyBindRegister.loopThroughRadioKeyBindOff(keyPressed,sceneManipulator);
    }

    public boolean isShiftPressed() {
        return shiftPressed;
    }
}
