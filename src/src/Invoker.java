package src;

import src.commands.Command;

import java.util.HashMap;

/**
 * Invoker class which need for registering and executing
 * commands from cmd args.
 */
public class Invoker {
    private final HashMap<String, Command> commandMap = new HashMap<>();
    Invoker() {}

    /**
     * This method registering new commands to invoker.
     * @param commandName - command name in string data type.
     * @param command - instance of command.
     */
    public void register(String commandName, Command command) {
        commandMap.put(commandName, command);
    }

    /**
     *
     * @param commandName - Name of command from cmd arguments.
     */
    public void execute(String commandName) {
        try {
            if(commandName.equals("")) {
                throw new Exception();
            } else {
                String[] commandName_split = commandName.split(" ");
                Command  command = commandMap.get(commandName_split[0]);

                if (command == null) {
                    throw new Exception();
                } else {
                    try {
                        command.execute(commandName_split);
                    } catch (OutOfBoundsException e) {
                        System.err.println("Out Of Bounds! Check your field!");
                    }

                }
            }
        } catch (Exception e) {
            System.err.println("Calling unregistered command!");
        }
    }
}