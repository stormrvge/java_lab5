package src.commands;

import src.*;

/**
 * This class of help command. This class just call method from Control Unit.
 */
public class CommandHelp implements Command {
    private final CollectionManager collectionManager;

    public CommandHelp(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    /**
     * This overridden method just uses method from Control Unit.
     * @param str - arguments from console.
     */
    @Override
    public void execute(String[] str) {
        collectionManager.help();
    }
}
