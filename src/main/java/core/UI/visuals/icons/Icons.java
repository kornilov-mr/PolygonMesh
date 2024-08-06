package core.UI.visuals.icons;

import javax.swing.*;
import javax.swing.plaf.IconUIResource;
import java.io.File;

public enum Icons {
    RADIO_WORKING_ICON(new File("src/main/java/core/UI/visuals/icons/radio_working_icon.png")),
    FOLDER_ICON(new File("src/main/java/core/UI/visuals/icons/folder_icon.png"));

    private final File file;

    Icons(File file) {
        this.file = file;
    }
    public JLabel label(){
        JLabel l = new JLabel(new ImageIcon(file.getAbsolutePath()));
        return l;
    }
}
