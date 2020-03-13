package src.commands;

import src.*;

/**
 * This class of clear command. This class just call method from Control Unit.
 */
public class Command_clear implements Command {
    private ControlUnit controlUnit;
    private Collection collection;

    public Command_clear(ControlUnit controlUnit, Collection collection) {
        this.controlUnit = controlUnit;
        this.collection = collection;
    }

    /**
     * This overridden method just uses method from Control Unit.
     * @param args - arguments from console.
     */
    @Override
    public void execute(String ... args) {
        controlUnit.clear(collection);
    }
}
