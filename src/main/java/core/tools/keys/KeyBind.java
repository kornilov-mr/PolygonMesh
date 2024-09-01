package core.tools.keys;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.util.Set;

public abstract class KeyBind {
    //abstract class, which encapsulates required keys and creates label with them
    private final JPanel keyLabel = new JPanel();
    protected final Set<Integer> keysRequired;

    public KeyBind(Set<Integer> keysRequired) {
        this.keysRequired = keysRequired;
        this.keyLabel.add(new JLabel(createKeyString()));
    }

    public void setNewKeys(Set<Integer> keysRequired) {
        this.keysRequired.clear();
        this.keysRequired.addAll(keysRequired);
        updateKeyLabel();
    }

    private void updateKeyLabel() {
        this.keyLabel.removeAll();
        this.keyLabel.add(new JLabel(createKeyString()));
        this.keyLabel.repaint();
        this.keyLabel.revalidate();
    }

    protected abstract KeyBinds getKeyBind();

    public KeyBindData getKeyBindData() {
        return new KeyBindData(getKeyBind(), keysRequired);
    }

    public String createKeyString() {
        String keys = new String();
        int i = 0;
        for (Integer key : keysRequired) {
            if (i != 0) {
                keys += "+";
            }
            keys += KeyEvent.getKeyText(key);
            i++;
        }
        return keys;
    }

    public JPanel getKeyLabel() {
        return keyLabel;
    }
}
