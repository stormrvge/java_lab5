package src.commands;

import src.*;

/**
 * This class of save command. This class just call method from Control Unit.
 */
public class Command_save implements Command {
    private final ControlUnit controlUnit;
    private Collection collection;

    public Command_save(ControlUnit controlUnit, Collection collection) {
        this.controlUnit = controlUnit;
        this.collection = collection;
    }

    /**
     * This overridden method just uses method from Control Unit.
     * @param args - arguments from console.
     * @throws BadNumOfArgsException - throws exception, if we have bad number of arguments from console.
     */
    public void execute(String... args) throws BadNumOfArgsException {
        if (args.length != 2) {
            throw new BadNumOfArgsException();
        } else {
            String path;
            try {
                path = args[1];
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Bad argument type!");
            }
            controlUnit.save(path, collection);
        }
    }
}
