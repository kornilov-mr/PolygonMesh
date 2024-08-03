package core.UI.visuals.elements.toolPanel.toolInterface.popup.fields;

public class INTField extends TypeArgumentField{
    protected INTField(String title) {
        super(title, "[1234567890-]");
    }

    @Override
    public Object Field() {
        if(textArea==null){
            throw new RuntimeException("textArea wasn't initialized");
        }
        if(textArea.getText().isEmpty()){
            return 0;
        }
        return Integer.parseInt(textArea.getText());
    }
}
