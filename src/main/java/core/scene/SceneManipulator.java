package core.scene;

import core.UI.visuals.elements.toolPanel.toolInterface.instructions.InstructionManager;
import core.render.RenderSwitcher;
import core.tools.changes.ChangeManager;
import core.tools.commands.CommandManager;

public class SceneManipulator {
    private final CommandManager commandManager;
    private final ChangeManager changeManager;
    private final RenderSwitcher renderSwitcher;
    private final InstructionManager instructionManager;
    public SceneManipulator(CommandManager commandManager, ChangeManager changeManager, RenderSwitcher renderSwitcher, InstructionManager instructionManager) {
        this.commandManager =commandManager;
        this.changeManager =changeManager;
        this.renderSwitcher =renderSwitcher;
        this.instructionManager = instructionManager;
    }

    public InstructionManager getInstructionManager() {
        return instructionManager;
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
