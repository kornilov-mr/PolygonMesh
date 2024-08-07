package core.UI.visuals.windows;

import core.scene.Scene;
import core.scene.resentProjects.ResentProjectData;
import core.scene.resentProjects.ResentProjectManager;
import core.scene.sceneLoaders.Extensions;
import core.tools.managers.FocusTabManager;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

public class LoadWindow extends JFrame {
    private final JFrame thisFrame;

    public LoadWindow(Scene scene, ResentProjectManager resentProjectManager, FocusTabManager focusTabManager){
        this.thisFrame=this;
        setTitle("Loading window");
        setSize(500,500);


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
        setLayout(new BorderLayout());
        add(loadButton,BorderLayout.PAGE_END);
        JPanel resentProjectWrapper = new JPanel();
        resentProjectWrapper.setLayout(new BoxLayout(resentProjectWrapper,BoxLayout.Y_AXIS));

        JPanel resentProjectsPanel = new JPanel(new GridLayout(2,4));

        ArrayList<ResentProjectData> resentProjectDatas = resentProjectManager.getSortedResentProjects();
        for(int i=0;i<Math.min(8,resentProjectDatas.size());i++){
            JPanel loadPanel = resentProjectDatas.get(i).loadPanel(scene,focusTabManager,80,80);
            if(i==3){
                resentProjectsPanel.add(loadPanel);
            }else{
                resentProjectsPanel.add(loadPanel);
            }
        }
        resentProjectWrapper.add(new JLabel("load"));
        resentProjectWrapper.add(resentProjectsPanel);
        add(resentProjectWrapper, BorderLayout.CENTER);

    }
    public void setAsVisible(){
        setLocationByPlatform(true);
        setVisible(true);
        setResizable(false);
        requestFocus();
    }
}
