package core.UI.visuals.elements.toolPanel.toolInterface.icons;

import core.UI.visuals.elements.toolPanel.toolInterface.instructions.Instruction;
import core.UI.visuals.elements.toolPanel.toolInterface.instructions.creatingInstructions.PointCreatingInstruction;

import javax.swing.*;

public enum IconEnum {
    POINT_CREATION(new ImageIcon("src/main/java/core/UI/visuals/elements/toolPanel/toolInterface/icons/createPoint.png"), PointCreatingInstruction.class, SectionEnum.CREATION),
    POINT_CREATION1(new ImageIcon("src/main/java/core/UI/visuals/elements/toolPanel/toolInterface/icons/createPoint.png"), PointCreatingInstruction.class,  SectionEnum.CREATION),
    POINT_CREATION2(new ImageIcon("src/main/java/core/UI/visuals/elements/toolPanel/toolInterface/icons/createPoint.png"), PointCreatingInstruction.class,  SectionEnum.CREATION),
    POINT_CREATION3(new ImageIcon("src/main/java/core/UI/visuals/elements/toolPanel/toolInterface/icons/createPoint.png"), PointCreatingInstruction.class,  SectionEnum.CREATION),
    POINT_CREATION4(new ImageIcon("src/main/java/core/UI/visuals/elements/toolPanel/toolInterface/icons/createPoint.png"), PointCreatingInstruction.class,  SectionEnum.CREATION),
    POINT_CREATION5(new ImageIcon("src/main/java/core/UI/visuals/elements/toolPanel/toolInterface/icons/createPoint.png"), PointCreatingInstruction.class,  SectionEnum.CREATION),
    POINT_CREATION6(new ImageIcon("src/main/java/core/UI/visuals/elements/toolPanel/toolInterface/icons/createPoint.png"), PointCreatingInstruction.class,  SectionEnum.CREATION);
    private final ImageIcon image;
    private final Class<? extends Instruction> instuctionClass;
    private final SectionEnum sectionEnum;
    IconEnum(ImageIcon image, Class<? extends Instruction> instuctionClass, SectionEnum sectionEnum) {
        this.image = image;
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
