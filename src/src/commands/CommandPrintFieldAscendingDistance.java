package src.commands;

import src.*;

/**
 * This class of print_field_ascending_distance command. This class just call method from Control Unit.
 */
public class CommandPrintFieldAscendingDistance implements Command {
    private CollectionManager collectionManager;

    public CommandPrintFieldAscendingDistance(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    /**
     * This overridden method just uses method from Control Unit.
     * @param args - arguments from console.
     */
    @Override
    public void execute(String ... args) {
        collectionManager.print_field_ascending_distance();
    }
}
