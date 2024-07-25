package core.UI.elements.toolPanel.pointer.objectInfoPanels;

import core.UI.managers.FocusTabManager;
import core.tools.commands.CommandManager;

public interface InfoPanelConvertible {
    ObjectInfoPanel toInfoPanel(FocusTabManager focusTabManager, CommandManager commandManager);
}
