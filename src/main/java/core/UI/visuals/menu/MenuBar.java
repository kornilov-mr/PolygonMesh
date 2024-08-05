package core.UI.visuals.menu;

import core.scene.Scene;
import core.scene.sceneLoaders.Extensions;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Paths;

public class MenuBar extends JMenuBar {
    private final JMenu editMenu = new JMenu("edit");
    public MenuBar(Scene scene) {
        add(new FileMenu(scene));
        add(editMenu);


    }
}
