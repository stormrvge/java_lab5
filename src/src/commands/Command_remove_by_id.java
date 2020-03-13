package src.commands;

import src.*;

/**
 * This class of remove_by_id command. This class just call method from Control Unit.
 */
public class Command_remove_by_id implements Command {
    private ControlUnit controlUnit;
    private Collection collection;

    public Command_remove_by_id(ControlUnit controlUnit, Collection collection) {
        this.controlUnit = controlUnit;
        this.collection = collection;
    }

    /**
     * This overridden method just uses method from Control Unit.
     * @param args - arguments from console.
     * @throws Exception - throws exception, if we have bad number of arguments or element with such id not found.
     */
    @Override
    public void execute(String ... args) throws Exception {
        if (args.length != 2) {
            throw new BadNumOfArgsException();
        } else {
            int id;
            try {
                id = Integer.parseInt(args[1]);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Bad argument type!");
            }
            controlUnit.remove_by_id(id, collection);
        }
    }
}

