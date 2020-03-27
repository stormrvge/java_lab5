package src.commands;

import src.commands.Command;

import java.io.IOException;
import java.util.HashMap;

/**
 * Invoker class which need for registering and executing
 * commands from cmd args.
 */
public class Invoker {
    private final HashMap<String, Command> commandMap = new HashMap<>();
    public Invoker() {}

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
                throw new NullPointerException();
            } else {
                commandName = commandName.replace("\t", " ").trim();
                String[] commandName_split = commandName.split(" ");
                Command  command = commandMap.get(commandName_split[0]);
                if (command == null) {
                    throw new NullPointerException();
                } else {
                    command.execute(commandName_split);
                }
            }
        } catch (NullPointerException e) {
            System.err.println("Calling unregistered command!");
        } catch (IOException e) {
            System.err.println("IOException!");
        }
    }
}