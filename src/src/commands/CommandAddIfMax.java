package src.commands;

import src.*;

/**
 * This class of add_if_max command. This class just call method from Control Unit.
 */
public class CommandAddIfMax implements Command {
    private CollectionManager collectionManager;


    public CommandAddIfMax(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    /**
     * This overridden method just uses method from Control Unit.
     * @param args - arguments from console.
     */
    @Override
    public void execute(String ... args) {
            collectionManager.add_if_max();
    }
}
