package src.commands;

import src.CollectionManager;

/**
 * This class of add command, which uses method add in Control Unit.
 */
public class CommandAdd implements Command {
    private final CollectionManager collectionManager;

    public CommandAdd(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    /**
     * This overridden method just uses method from Control Unit.
     * @param str - arguments from console.
     */
    @Override
    public void execute(String[] str) {
        collectionManager.add();
    }
}
