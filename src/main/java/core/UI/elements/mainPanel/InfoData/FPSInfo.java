package core.UI.elements.mainPanel.InfoData;

import core.statistic.FPS.FPSTracker;

public class FPSInfo implements InfoData {

    public FPSInfo(){
    }
    @Override
    public String createNewLabelText() {
        String label ="MPF info<br>";
        label+=FPSTracker.toHTMLString();
        return label;
    }
}
