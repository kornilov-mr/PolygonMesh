package core.UI.visuals.elements.toolPanel.toolInterface.popup.fields;

public class AngleField extends TypeArgumentField{
    public AngleField(String title ) {
        super(title, "[1234567890.-]");
    }

    @Override
    public Object Field() {
        if(textArea==null){
            throw new RuntimeException("textArea wasn't initialized");
        }
        if(textArea.getText().isEmpty()){
            return 0.0;
        }
        return Double.parseDouble(textArea.getText())/180*Math.PI;
    }
}
