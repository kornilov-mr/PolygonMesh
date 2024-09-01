package core.tools.managers;

import javax.swing.*;

public class FocusTabManager {
    //Manager for setting focus to required tab
    private JFrame mainWindow;

    public void setMainWindow(JFrame mainWindow) {
        this.mainWindow = mainWindow;
    }
    public void setFocusToMainWindow(){
        if(mainWindow!=null){
            mainWindow.requestFocus();
        }
    }
}
