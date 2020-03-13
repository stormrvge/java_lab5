package src.commands;

import src.*;

/**
 * This class of count_by_distance command. This class just call method from Control Unit.
 */
public class Command_count_by_distance implements Command {
    private ControlUnit controlUnit;
    private Collection collection;


    public Command_count_by_distance(ControlUnit controlUnit, Collection collection) {
        this.controlUnit = controlUnit;
        this.collection = collection;
    }

    /**
     * This overridden method just uses method from Control Unit. Command takes additional argument in console.
     * @param args - arguments from console.
     * @throws BadNumOfArgsException - throws exception, if we have bad number of arguments in console.
     */
    @Override
    public void execute(String ... args) throws BadNumOfArgsException {
        if (args.length != 2) {
            throw new BadNumOfArgsException();
        } else {
            float distance;
            try {
                distance = Float.parseFloat(args[1]);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Bad argument type!");
            }
            controlUnit.count_by_distance(distance, collection);
        }
    }
}
