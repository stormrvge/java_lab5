package src.commands;

import src.*;

/**
 * This class of update_id command. This class just call method from Control Unit.
 */
public class CommandUpdateId implements Command {
    private CollectionManager collectionManager;


    public CommandUpdateId(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    /**
     * This overridden method just uses method from Control Unit.
     * @param args - arguments from console.
     */
    @Override
    public void execute(String ... args) { collectionManager.update_id(args); }
}
