package core.UI.visuals;

import javax.swing.*;

public class MenuBar extends JMenuBar {
    private final JMenu fileMenu = new JMenu("file");
    private final JMenu editMenu = new JMenu("edit");
    public MenuBar() {
        JMenuItem saveButton=new JMenuItem("save");
        JMenuItem loadButton=new JMenuItem("load");
        fileMenu.add(saveButton);
        fileMenu.add(loadButton);

        add(fileMenu);
        add(editMenu);


    }
}
