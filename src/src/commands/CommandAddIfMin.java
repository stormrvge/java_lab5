package src.commands;

import src.*;

/**
 * This class of add_if_min command. This class just call method from Control Unit.
 */
public class CommandAddIfMin implements Command {
    private CollectionManager collectionManager;

    public CommandAddIfMin(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    /**
     * This overridden method just uses method from Control Unit.
     * @param args - arguments from console.
     */
    @Override
    public void execute(String ... args) {
            collectionManager.add_if_min();
    }
}
