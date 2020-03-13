package src.commands;

import src.*;

/**
 * This class of add_if_min command. This class just call method from Control Unit.
 */
public class Command_exit implements Command {
    private final ControlUnit controlUnit;
    public static boolean isRunning = true;

    public Command_exit(ControlUnit controlUnit) {
        this.controlUnit = controlUnit;
    }

    /**
     * This overridden method just uses method from Control Unit.
     * @param str - arguments from console.
     */
    @Override
    public void execute(String[] str) {
        controlUnit.exit();
    }
}
