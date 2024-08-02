package core.UI.visuals.elements.toolPanel.toolInterface.icons;

public enum SectionEnum {
    CREATION("Creation"),
    EDITION("Edition");
    private final String title;

    SectionEnum(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
