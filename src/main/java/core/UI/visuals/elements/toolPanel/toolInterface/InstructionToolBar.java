package core.UI.visuals.elements.toolPanel.toolInterface;

import core.UI.visuals.elements.toolPanel.toolInterface.icons.IconEnum;
import core.UI.visuals.elements.toolPanel.toolInterface.icons.SectionEnum;
import core.UI.visuals.elements.toolPanel.toolInterface.instructions.Instruction;
import core.UI.visuals.elements.toolPanel.toolInterface.instructions.InstructionManager;
import utils.layouts.WrapLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class InstructionToolBar extends JPanel {
    private final InstructionManager instructionManager;
    public InstructionToolBar(InstructionManager instructionManager) {
        setAlignmentY(Component.TOP_ALIGNMENT);

        this.instructionManager = instructionManager;
        setLayout(new BoxLayout(this,BoxLayout.Y_AXIS ));
        setPreferredSize(new Dimension(200,500));
        Map<SectionEnum,JPanel> section = new HashMap<>();
        for(SectionEnum sectionEnum: SectionEnum.values()){
            JPanel wrapper = new JPanel();
            wrapper.setLayout(new BoxLayout(wrapper, BoxLayout.Y_AXIS));
            wrapper.add(new JLabel(sectionEnum.getTitle()));
            JSeparator separator = new JSeparator();
            separator.setMaximumSize(new Dimension(Integer.MAX_VALUE, 20));
            wrapper.add(separator);

            JPanel panel = new JPanel();
            panel.setLayout(new WrapLayout());
//            panel.setPreferredSize(new Dimension(200,500));
            wrapper.add(panel);
//            separator = new JSeparator();
//            separator.setMaximumSize(new Dimension(Integer.MAX_VALUE, 20));
            add(wrapper);
//            panel.setLayout(new FlowLayout());
//            panel.setMaximumSize(new Dimension(getMaximumSize()));
            section.put(sectionEnum, panel);
        }

        for(IconEnum iconEnum : IconEnum.values()){
            JLabel label = new JLabel(iconEnum.getImage());
            label.setBorder(BorderFactory.createLineBorder(Color.GRAY,1));
            label.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    try {
                        instructionManager.queueInstruction((Instruction) iconEnum.getInstuctionClass().getConstructors()[0].newInstance());
                    } catch (InstantiationException ex) {
                        throw new RuntimeException(ex);
                    } catch (IllegalAccessException ex) {
                        throw new RuntimeException(ex);
                    } catch (InvocationTargetException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            });
            section.get(iconEnum.getSectionEnum()).add(label);
        }
    }
}
