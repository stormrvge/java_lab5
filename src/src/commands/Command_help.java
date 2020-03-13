package src.commands;

import src.*;

/**
 * This class of help command. This class just call method from Control Unit.
 */
public class Command_help implements Command {
    private final ControlUnit controlUnit;

    public Command_help(ControlUnit controlUnit) {
        this.controlUnit = controlUnit;
    }

    /**
     * This overridden method just uses method from Control Unit.
     * @param str - arguments from console.
     */
    @Override
    public void execute(String[] str) {
        controlUnit.help();
    }
}
