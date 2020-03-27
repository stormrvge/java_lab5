package src.commands;

import src.*;

/**
 * This class of save command. This class just call method from Control Unit.
 */
public class CommandSave implements Command {
    private final CollectionManager collectionManager;

    public CommandSave(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    /**
     * This overridden method just uses method from Control Unit.
     * @param args - arguments from console.
     */
    public void execute(String... args) {
            collectionManager.save(args);
    }
}
