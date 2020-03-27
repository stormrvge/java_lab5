package src.commands;

import src.*;

/**
 * This class of clear command. This class just call method from Control Unit.
 */
public class CommandClear implements Command {
    private CollectionManager collectionManager;

    public CommandClear(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    /**
     * This overridden method just uses method from Control Unit.
     * @param args - arguments from console.
     */
    @Override
    public void execute(String ... args) {
        collectionManager.clear();
    }
}
