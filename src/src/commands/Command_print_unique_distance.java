package src.commands;

import src.*;

/**
 * This class of print_unique_distance command. This class just call method from Control Unit.
 */
public class Command_print_unique_distance implements Command {
    private ControlUnit controlUnit;
    private Collection collection;


    public Command_print_unique_distance(ControlUnit controlUnit, Collection collection) {
        this.controlUnit = controlUnit;
        this.collection = collection;
    }

    /**
     * This overridden method just uses method from Control Unit.
     * @param args - arguments from console.
     */
    @Override
    public void execute(String ... args) {
        controlUnit.print_unique_distance(collection);
    }
}
