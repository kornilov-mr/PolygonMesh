package core.UI.visuals.elements.toolPanel.toolInterface.popup.fields;

public class ColorComponentField extends TypeArgumentField{
    protected ColorComponentField(String title) {
        super(title, "[123456789-]");
    }

    @Override
    public Object Field() {
        if(textArea==null){
            throw new RuntimeException("textArea wasn't initialized");
        }
        if(textArea.getText().isEmpty()){
            return 0;
        }
        int value = Integer.parseInt(textArea.getText());
        if(value>=255){
            return 255;
        }else if(value <=0){
            return 0;
        }
        return Integer.parseInt(textArea.getText());
    }
}