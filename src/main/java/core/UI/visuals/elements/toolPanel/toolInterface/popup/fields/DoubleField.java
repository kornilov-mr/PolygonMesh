package core.UI.visuals.elements.toolPanel.toolInterface.popup.fields;

public class DoubleField extends TypeArgumentField{
    public DoubleField(String title) {
        super(title, "[123456789-]");
    }

    @Override
    public Object Field() {
        if(textArea==null){
            throw new RuntimeException("textArea wasn't initialized");
        }
        return Double.parseDouble(textArea.getText());
    }
}
