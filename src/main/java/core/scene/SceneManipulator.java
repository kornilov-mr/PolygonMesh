package core.scene;

import core.render.RenderSwitcher;
import core.tools.changes.ChangeManager;
import core.tools.commands.CommandManager;

public class SceneManipulator {
    private final CommandManager commandManager;
    private final ChangeManager changeManager;
    private final RenderSwitcher renderSwitcher;
    public SceneManipulator(CommandManager commandManager, ChangeManager changeManager, RenderSwitcher renderSwitcher) {
        this.commandManager =commandManager;
        this.changeManager =changeManager;
        this.renderSwitcher =renderSwitcher;
    }

    public CommandManager getCommandManager() {
        return commandManager;
    }

    public ChangeManager getChangeManager() {
        return changeManager;
    }

    public RenderSwitcher getRenderSwitcher() {
        return renderSwitcher;
    }
}
