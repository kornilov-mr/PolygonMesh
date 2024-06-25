package core.tools.selecting;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashSet;

public class SelectedKeyListener implements KeyListener {
    private final HashSet<Integer> keyPressed = new HashSet<>();
    private boolean shiftPressed = false;
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        keyPressed.add(e.getKeyCode());
        if(e.getKeyCode()==KeyEvent.VK_SHIFT){
            shiftPressed=true;
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
