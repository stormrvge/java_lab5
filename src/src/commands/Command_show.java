package src.commands;

import src.*;
import java.util.ArrayList;

/**
 * This class of show command. This class just call method from Control Unit.
 */
public class Command_show implements Command {
    private final ControlUnit controlUnit;
    private ArrayList<Route> route;


    public Command_show(ControlUnit controlUnit, Collection collection) {
        this.controlUnit = controlUnit;
        this.route = collection.getCollection();
    }

    /**
     * This overridden method just uses method from Control Unit.
     * @param str - arguments from console.
     */
    @Override
    public void execute(String[] str) {
        controlUnit.show(route);
    }
}
