package src.commands;

import src.*;

/**
 * This class of print_unique_distance command. This class just call method from Control Unit.
 */
public class CommandPrintUniqueDistance implements Command {
    private CollectionManager collectionManager;


    public CommandPrintUniqueDistance(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    /**
     * This overridden method just uses method from Control Unit.
     * @param args - arguments from console.
     */
    @Override
    public void execute(String ... args) {
        collectionManager.print_unique_distance();
    }
}
