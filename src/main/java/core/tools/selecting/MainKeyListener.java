package core.tools.selecting;

import core.tools.changes.ChangeManager;
import core.tools.commands.CommandManager;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashSet;

public class MainKeyListener implements KeyListener {
    private final CommandManager commandManager;
    private final ChangeManager changeManager;

    private final HashSet<Integer> keyPressed = new HashSet<>();
    private boolean shiftPressed = false;

    public MainKeyListener(CommandManager commandManager, ChangeManager changeManager) {
        this.commandManager = commandManager;
        this.changeManager = changeManager;
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
        if(keyPressed.contains(KeyEvent.VK_Z)&&keyPressed.contains(KeyEvent.VK_CONTROL)&&keyPressed.contains(KeyEvent.VK_SHIFT)){
            changeManager.reverseNextChange();
        }else if(keyPressed.contains(KeyEvent.VK_Z)&&keyPressed.contains(KeyEvent.VK_CONTROL)){
            changeManager.reversePreviousChange();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        keyPressed.remove(e.getKeyCode());
        if(e.getKeyCode()==KeyEvent.VK_SHIFT){
            shiftPressed=false;
        }
    }

    public boolean isShiftPressed() {
        return shiftPressed;
    }
}
