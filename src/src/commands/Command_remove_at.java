package src.commands;

import src.*;

/**
 * This class of remove_at command. This class just call method from Control Unit.
 */
public class Command_remove_at implements Command {
    private ControlUnit controlUnit;
    private Collection collection;

    public Command_remove_at(ControlUnit controlUnit, Collection collection) {
        this.controlUnit = controlUnit;
        this.collection = collection;
    }

    /**
     * This overridden method just uses method from Control Unit.
     * @param args - arguments from console.
     * @throws BadNumOfArgsException - throws exception, if we have bad number of arguments.
     */
    @Override
    public void execute(String ... args) throws BadNumOfArgsException {
        if (args.length != 2) {
            throw new BadNumOfArgsException();
        } else {
            int index;
            try {
                index = Integer.parseInt(args[1]);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Bad argument type!");
            }
            controlUnit.remove_at(index, collection);
        }
    }
}
