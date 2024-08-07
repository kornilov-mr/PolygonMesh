package core.UI.visuals.menu;

import core.UI.controller.WindowController;
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
    public MenuBar(WindowController windowController) {
        add(new FileMenu(windowController.getScene(), windowController.getResentProjectManager(), windowController.getFocusTabManager()));
        add(new EditMenu(windowController.getKeyBindRegister(), windowController.getMainKeyListener(), windowController.getSceneManipulator(), windowController.getFocusTabManager()));
    }
}
