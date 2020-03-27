package src.commands;

import src.*;

/**
 * This class of add_if_min command. This class just call method from Control Unit.
 */
public class CommandExit implements Command {
    private final CollectionManager collectionManager;
    public static boolean isRunning = true;

    public CommandExit(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    /**
     * This overridden method just uses method from Control Unit.
     * @param str - arguments from console.
     */
    @Override
    public void execute(String[] str) {
        collectionManager.exit();
    }
}
