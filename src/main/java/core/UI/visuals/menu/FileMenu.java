package core.UI.visuals.menu;

import core.scene.Scene;
import core.scene.sceneLoaders.Extensions;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class FileMenu extends JMenu {
    public FileMenu(Scene scene) {
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
                final JFileChooser fileChooser;
                fileChooser = new JFileChooser(new File("src/main/Scenes").getAbsolutePath());
                fileChooser.setMultiSelectionEnabled(false);
                fileChooser.removeChoosableFileFilter(fileChooser.getAcceptAllFileFilter());
                for(Extensions extension: Extensions.values()){
                    fileChooser.setFileFilter(new FileNameExtensionFilter("Files ending in "+extension.getRealExtension(), extension.getRealExtension().replace(".","")));
                }
                int returnVal = fileChooser.showOpenDialog(null);
                File file = fileChooser.getSelectedFile();

                if (file!=null) {
                    scene.saveScene(file);
                }
            }
        });
        JMenuItem loadButton=new JMenuItem("load");
        loadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                final JFileChooser fileChooser;
                fileChooser = new JFileChooser(new File("src/main/Scenes").getAbsolutePath());
                fileChooser.setMultiSelectionEnabled(false);
                fileChooser.removeChoosableFileFilter(fileChooser.getAcceptAllFileFilter());
                for(Extensions extension: Extensions.values()){
                    fileChooser.setFileFilter(new FileNameExtensionFilter("Files ending in "+extension.getRealExtension(), extension.getRealExtension().replace(".","")));
                }
                int returnVal = fileChooser.showOpenDialog(null);
                File file = fileChooser.getSelectedFile();

                if (file!=null) {
                    scene.loadSceneFromFile(file);
                }
            }
        });
        add(saveButton);
        add(saveHowButton);
        add(loadButton);
    }
}
