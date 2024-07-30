package core.UI.visuals.elements.toolPanel.pointer.objectInfoPanels;

import core.tools.managers.FocusTabManager;
import core.tools.commands.CommandManager;

public interface InfoPanelConvertible {
    ObjectInfoPanel toInfoPanel(FocusTabManager focusTabManager, CommandManager commandManager);
}
