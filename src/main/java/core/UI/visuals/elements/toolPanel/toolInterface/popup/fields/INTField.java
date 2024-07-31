package core.UI.visuals.elements.toolPanel.toolInterface.popup.fields;

public class INTField extends TypeArgumentField{
    protected INTField(String title) {
        super(title, "[123456789-]");
    }

    @Override
    public Object Field() {
        if(textArea==null){
            throw new RuntimeException("textArea wasn't initialized");
        }
        return Integer.parseInt(textArea.getText());
    }
}
