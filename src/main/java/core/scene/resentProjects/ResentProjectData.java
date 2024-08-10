package core.scene.resentProjects;

import core.scene.Scene;
import core.scene.sceneLoaders.SceneLoader;
import core.scene.sceneLoaders.SceneLoaderFactory;
import core.tools.managers.FocusTabManager;
import org.json.JSONObject;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public final class ResentProjectData {

    private final File sceneFile;
    private final ImageIcon imageIcon;
    private final File imageFile;
    private final String name;
    protected final long lastTime;
    private final LastImagesFolderManager lastImagesFolderManager = new LastImagesFolderManager(new File("src/main/java/core/data"));

    public ResentProjectData(JSONObject jsonObject){
        this.imageFile = new File(jsonObject.getString("imagePath"));
        this.sceneFile= new File(jsonObject.getString("sceneFile"));
        this.name = jsonObject.getString("name");
        this.lastTime = jsonObject.getLong("lastTime");
        this.imageIcon = new ImageIcon(imageFile.getAbsolutePath());
    }

    public ResentProjectData(File sceneFile, BufferedImage image, String name, Long lastTime) {
        this.sceneFile = sceneFile;
        this.imageIcon = new ImageIcon(image);
        this.name = name;
        this.lastTime =lastTime;
        this.imageFile = saveImage(image,name);


    }
    private File saveImage(BufferedImage image, String sceneName){
        File file = lastImagesFolderManager.getSaveFile(sceneName);
        Graphics2D g2 = image.createGraphics();
        try {
            ImageIO.write(image, "png", file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return file;
    }
    public JPanel loadPanel(Scene scene, FocusTabManager focusTabManager, int width, int height){
        JPanel panel = displayPanel(width,height,scene);
        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                scene.loadSceneFromFile(sceneFile);
                focusTabManager.setFocusToMainWindow();
            }
        });
        return panel;
    }
    private JPanel displayPanel(int width, int height, Scene scene){
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
        Image image = imageIcon.getImage();
        Image newimg = image.getScaledInstance(width, height,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
        panel.add(new JLabel(new ImageIcon(newimg)));
        panel.add(new JLabel(scene.getSceneName()));
        return panel;
    }
    public JPanel savePanel(Scene scene, FocusTabManager focusTabManager, int width, int height){
        JPanel panel = displayPanel(width,height,scene);
        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                scene.saveScene(sceneFile);
                focusTabManager.setFocusToMainWindow();
            }
        });
        return panel;
    }
    JSONObject createJson(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name",name);
        jsonObject.put("lastTime",lastTime);
        jsonObject.put("imagePath",imageFile.getAbsolutePath());
        jsonObject.put("sceneFile",sceneFile);
        return jsonObject;
    }
}
