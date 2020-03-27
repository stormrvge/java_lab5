package src.commands;

import src.*;

/**
 * This class of show command. This class just call method from Control Unit.
 */
public class CommandShow implements Command {
    private final CollectionManager collectionManager;


    public CommandShow(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    /**
     * This overridden method just uses method from Control Unit.
     * @param str - arguments from console.
     */
    @Override
    public void execute(String[] str) {
        collectionManager.show();
    }
}
