package src.commands;

import src.*;

/**
 * This class of add_if_min command. This class just call method from Control Unit.
 */
public class Command_add_if_min implements Command {
    private ControlUnit controlUnit;
    private Collection collection;


    public Command_add_if_min(ControlUnit controlUnit, Collection collection) {
        this.controlUnit = controlUnit;
        this.collection = collection;
    }

    /**
     * This overridden method just uses method from Control Unit.
     * @param args - arguments from console.
     */
    @Override
    public void execute(String ... args) {
            controlUnit.add_if_min(collection);
    }
}
