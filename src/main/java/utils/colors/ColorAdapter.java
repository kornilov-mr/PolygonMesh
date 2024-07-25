package utils.colors;

import core.UI.elements.toolPanel.pointer.objectInfoPanels.ColorToneInfoPanel;
import core.UI.elements.toolPanel.pointer.objectInfoPanels.InfoPanelConvertible;
import core.UI.elements.toolPanel.pointer.objectInfoPanels.ObjectInfoPanel;
import core.UI.managers.FocusTabManager;
import core.tools.commands.CommandManager;
import primitive.Primitive;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class ColorAdapter implements InfoPanelConvertible {
    private Color color;
    private final Primitive primitive;

    public ColorAdapter(Color color, Primitive primitive) {
        this.color = color;
        this.primitive = primitive;
    }

    @Override
    public ObjectInfoPanel toInfoPanel(FocusTabManager focusTabManager, CommandManager commandManager) {
        ColorToneInfoPanel panelR = new ColorToneInfoPanel(new ArrayList<>(),color,"R", color.getRed(),focusTabManager,primitive, commandManager);
        ColorToneInfoPanel panelG = new ColorToneInfoPanel(new ArrayList<>(),color,"G", color.getGreen(),focusTabManager,primitive, commandManager);
        ColorToneInfoPanel panelB = new ColorToneInfoPanel(new ArrayList<>(),color,"B", color.getBlue(),focusTabManager,primitive,commandManager);
        ObjectInfoPanel objectInfoPanel = new ObjectInfoPanel(new ArrayList<>(Arrays.asList(panelR,panelG,panelB))) {
            @Override
            public JPanel createJPanel() {
                JPanel titlePanel = new JPanel();
                titlePanel.setLayout(new BoxLayout(titlePanel,BoxLayout.Y_AXIS));
                titlePanel.add(new JLabel("Point"));


                JPanel jPanel = new JPanel();
                jPanel.setLayout(new BoxLayout(jPanel,BoxLayout.X_AXIS));
                jPanel.add(panelR.jPanel);
                jPanel.add(panelG.jPanel);
                jPanel.add(panelB.jPanel);

                titlePanel.setBorder(BorderFactory.createLineBorder(Color.black));
                titlePanel.add(jPanel);
                return titlePanel;
            }
        };
        return objectInfoPanel;
    }
}
