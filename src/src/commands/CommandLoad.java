package src.commands;

import src.*;

/**
 * This class of load command. This class just call method from Control Unit.
 */
public class CommandLoad implements Command {
    private CollectionManager collectionManager;

    public CommandLoad(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    /**
     * This overridden method just uses method from Control Unit.
     * @param args - arguments from console.
     */
    @Override
    public void execute(String... args) {
            collectionManager.load(args);
        }
}
