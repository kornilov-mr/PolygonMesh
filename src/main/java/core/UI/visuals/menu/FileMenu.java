package core.UI.visuals.menu;

import core.UI.visuals.windows.LoadWindow;
import core.UI.visuals.windows.SaveWindow;
import core.scene.Scene;
import core.scene.resentProjects.ResentProjectManager;
import core.scene.sceneLoaders.Extensions;
import core.tools.managers.FocusTabManager;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class FileMenu extends JMenu {
    public FileMenu(Scene scene, ResentProjectManager resentProjectManager, FocusTabManager focusTabManager) {
        super("File");
        JMenuItem saveButton=new JMenuItem("save");
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                scene.fastSaveScene();
            }
        });

        JMenuItem saveHowButton=new JMenuItem("save how");
        saveHowButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SaveWindow saveWindow = new SaveWindow(scene,resentProjectManager,focusTabManager);
                saveWindow.setAsVisible();
            }
        });
        JMenuItem loadButton=new JMenuItem("load");
        loadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LoadWindow loadWindow= new LoadWindow(scene,resentProjectManager,focusTabManager);
                loadWindow.setAsVisible();
            }
        });
        add(saveButton);
        add(saveHowButton);
        add(loadButton);
    }
}
