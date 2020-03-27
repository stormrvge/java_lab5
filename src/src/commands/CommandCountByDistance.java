package src.commands;

import src.*;

/**
 * This class of count_by_distance command. This class just call method from Control Unit.
 */
public class CommandCountByDistance implements Command {
    private CollectionManager collectionManager;

    public CommandCountByDistance(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    /**
     * This overridden method just uses method from Control Unit. Command takes additional argument in console.
     * @param args - arguments from console.
     */
    @Override
    public void execute(String ... args) {
            collectionManager.count_by_distance(args);
    }
}
