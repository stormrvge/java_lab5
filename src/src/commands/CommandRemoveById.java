package src.commands;

import src.*;

/**
 * This class of remove_by_id command. This class just call method from Control Unit.
 */
public class CommandRemoveById implements Command {
    private CollectionManager collectionManager;

    public CommandRemoveById(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    /**
     * This overridden method just uses method from Control Unit.
     * @param args - arguments from console.
     */
    @Override
    public void execute(String ... args) {
            collectionManager.remove_by_id(args);
    }
}

