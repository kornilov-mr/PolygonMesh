package core.UI.visuals.windows;

import core.UI.visuals.icons.Icons;
import core.scene.Scene;
import core.scene.sceneLoaders.Extensions;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.*;
import java.io.File;

public class SaveWindow extends JFrame {
    private final JFrame thisFrame;

    public SaveWindow(Scene scene){
        this.thisFrame=this;
        setTitle("Saving window");
        setSize(500,500);


//        JComboBox<String> extensionComboBox = new JComboBox<>(new String[]{"test1","test2"});
        JComboBox<Extensions> extensionComboBox = new JComboBox<>(Extensions.values());
        extensionComboBox.setSelectedIndex(0);

        Button saveButton = new Button("Save");

        JTextField savePathField = new JTextField(30);
        savePathField.setText(new File("src/main/Scenes").getAbsolutePath());

        JLabel dirSelectorIcon = Icons.FOLDER_ICON.label();

        dirSelectorIcon.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
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
                    savePathField.setText(file.getAbsolutePath());
                }
            }
        });

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Extensions extension = (Extensions) extensionComboBox.getSelectedItem();
//                Extensions extension = Extensions.valueOf("."+name);
                File saveFile = new File(savePathField.getText());
                if(!saveFile.exists()){
                    JDialog d = new JDialog(thisFrame, "invalid path");
                    JLabel l = new JLabel("you selected non-existing file");
                    d.add(l);
                    d.setSize(200, 100);
                    d.setVisible(true);
                }else{
                    scene.saveInFileOrFolder(saveFile,extension);
                    thisFrame.dispatchEvent(new WindowEvent(thisFrame, WindowEvent.WINDOW_CLOSING));
                }
            }
        });
        JPanel inputPanel = new JPanel();

        JPanel comboWrapper = new JPanel();
        comboWrapper.add(extensionComboBox);
        JPanel comboPanel = new JPanel();
        JLabel compoLabel = new JLabel("Extension:");
        comboPanel.add(compoLabel,BorderLayout.PAGE_START);
        comboPanel.add(comboWrapper,BorderLayout.PAGE_START);

        JPanel pathChooserPanel = new JPanel();
        pathChooserPanel.add(savePathField, BorderLayout.PAGE_START);
        pathChooserPanel.add(dirSelectorIcon, BorderLayout.PAGE_START);

        JPanel inputPanelWrapper = new JPanel();

        inputPanel.setLayout(new BoxLayout(inputPanel,BoxLayout.Y_AXIS));
        inputPanel.add(comboPanel);
        inputPanel.add(pathChooserPanel);

        inputPanelWrapper.add(inputPanel);


        setLayout(new BorderLayout());
        add(inputPanelWrapper,BorderLayout.CENTER);
        add(saveButton,BorderLayout.PAGE_END);
    }
    public void setAsVisible(){
        setLocationByPlatform(true);
        setVisible(true);
        setResizable(false);
        requestFocus();
    }
}
