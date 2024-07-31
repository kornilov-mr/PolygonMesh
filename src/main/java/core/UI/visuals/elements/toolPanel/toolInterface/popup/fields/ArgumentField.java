package core.UI.visuals.elements.toolPanel.toolInterface.popup.fields;

import core.tools.managers.FocusTabManager;

import javax.swing.*;

public interface ArgumentField {
    Object Field();
    JPanel createPanel(FocusTabManager focusTabManager);
}
