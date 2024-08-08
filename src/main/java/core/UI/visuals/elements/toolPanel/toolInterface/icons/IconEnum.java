package core.UI.visuals.elements.toolPanel.toolInterface.icons;

import core.UI.visuals.elements.toolPanel.toolInterface.instructions.Instruction;
import core.UI.visuals.elements.toolPanel.toolInterface.instructions.creatingInstructions.BoxCreatingInstruction;
import core.UI.visuals.elements.toolPanel.toolInterface.instructions.creatingInstructions.CounterCreationInstruction;
import core.UI.visuals.elements.toolPanel.toolInterface.instructions.creatingInstructions.PointCreatingInstruction;
import core.UI.visuals.elements.toolPanel.toolInterface.instructions.creatingInstructions.PolygonCreatingInstruction;
import core.UI.visuals.elements.toolPanel.toolInterface.instructions.editingInstruction.RotateInstruction;

import javax.swing.*;
import java.awt.*;

public enum IconEnum {
    POINT_CREATION(new ImageIcon("src/main/java/core/UI/visuals/elements/toolPanel/toolInterface/icons/createPoint.png"), PointCreatingInstruction.class, SectionEnum.CREATION),
    POlYGON_CREATION(new ImageIcon("src/main/java/core/UI/visuals/elements/toolPanel/toolInterface/icons/createPolygon.png"), PolygonCreatingInstruction.class,  SectionEnum.CREATION),
    COUNTER_CREATION(new ImageIcon("src/main/java/core/UI/visuals/elements/toolPanel/toolInterface/icons/createCounter.png"), CounterCreationInstruction.class,  SectionEnum.CREATION),
    CUBE_CREATION(new ImageIcon("src/main/java/core/UI/visuals/elements/toolPanel/toolInterface/icons/createCube.png"), BoxCreatingInstruction.class,  SectionEnum.CREATION),
    ROTATION(new ImageIcon("src/main/java/core/UI/visuals/elements/toolPanel/toolInterface/icons/rotate.png"), RotateInstruction.class,  SectionEnum.EDITION);
    private final ImageIcon image;
    private final Class<? extends Instruction> instuctionClass;
    private final SectionEnum sectionEnum;
    IconEnum(ImageIcon imageIcon, Class<? extends Instruction> instuctionClass, SectionEnum sectionEnum) {
        Image image = imageIcon.getImage();
        Image newimg = image.getScaledInstance(32, 32,  java.awt.Image.SCALE_SMOOTH);
        this.image = new ImageIcon(newimg);
        this.instuctionClass = instuctionClass;
        this.sectionEnum = sectionEnum;
    }

    public ImageIcon getImage() {
        return image;
    }

    public Class<? extends Instruction> getInstuctionClass() {
        return instuctionClass;
    }

    public SectionEnum getSectionEnum() {
        return sectionEnum;
    }
}
