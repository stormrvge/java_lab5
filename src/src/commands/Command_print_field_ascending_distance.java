package src.commands;

import src.*;

/**
 * This class of print_field_ascending_distance command. This class just call method from Control Unit.
 */
public class Command_print_field_ascending_distance implements Command {
    private Collection collection;
    private ControlUnit controlUnit;

    public Command_print_field_ascending_distance(ControlUnit controlUnit, Collection collection) {
        this.collection = collection;
        this.controlUnit = controlUnit;
    }

    /**
     * This overridden method just uses method from Control Unit.
     * @param args - arguments from console.
     */
    @Override
    public void execute(String ... args) {
        controlUnit.print_field_ascending_distance(collection);
    }
}
