package src.commands;

import src.Collection;
import src.ControlUnit;

/**
 * This class of add command, which uses method add in Control Unit.
 */
public class Command_add implements Command {
    private final ControlUnit controlUnit;
    private Collection collection;

    public Command_add(ControlUnit controlUnit, Collection collection) {
        this.controlUnit = controlUnit;
        this.collection = collection;
    }

    /**
     * This overridden method just uses method from Control Unit.
     * @param str - arguments from console.
     */
    @Override
    public void execute(String[] str) {
        controlUnit.add(collection);
    }
}
