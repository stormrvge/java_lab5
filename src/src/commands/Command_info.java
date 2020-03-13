package src.commands;

import src.*;

/**
 * This class of info command. This class just call method from Control Unit.
 */
public class Command_info implements Command {
    private final ControlUnit controlUnit;
    private Collection collection;

    public Command_info(ControlUnit controlUnit, Collection collection) {
        this.controlUnit = controlUnit;
        this.collection = collection;
    }

    /**
     * This overridden method just uses method from Control Unit.
     * @param str - arguments from console.
     */
    @Override
    public void execute(String[] str) {
        controlUnit.info(collection);
    }
}
