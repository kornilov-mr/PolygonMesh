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
        if(textArea.getText().isEmpty()){
            return 0.0;
        }
        return Double.parseDouble(textArea.getText());
    }
}
