package src.commands;

import src.io.Parse;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Class of execute script command.
 */
public class CommandExecuteScript implements Command {
    private Invoker invoker;
    private ArrayList<String> file_already_run;

    private static final int max_recursion_depth = 100;
    private int current_depth;
    /**
     * Constructor of execute script command.
     * @param invoker - class with HashMap of registered commands.
     */
    public CommandExecuteScript(Invoker invoker) {
        this.invoker = invoker;
        this.file_already_run = new ArrayList<>();
        this.current_depth = 0;
    }

    /**
     *
     * @param args - arguments from command line.
     * number of arguments.
     * @throws IOException - throws exception, if method don't have permission
     * to file or file not found.
     */
    @Override
    public void execute(String... args) throws IOException {
        if (args.length != 2) {
            System.err.println("Bad number of arguments!");
        } else if (max_recursion_depth == this.current_depth) {
            System.err.println("Maximum recursive depth reached!");
            System.err.println("Ignore execute script command!");
        }
        else {
            try {
                boolean isFounded = false;
                String path = args[1];
                String[] commands = Parse.executeStrings(path);

                for(String file_run : this.file_already_run) {
                    if(file_run.equals(args[1])) {
                        System.out.println("Recursion detected!");
                        System.out.println("Current recursion depth: " + ++this.current_depth);
                        System.out.println("Max recursion depth: " + max_recursion_depth);
                        isFounded = true;
                        break;
                    }
                }
                if(!isFounded) {file_already_run.add(args[1]);}

                for (String command : commands) {
                    this.invoker.execute(command);
                }
                --this.current_depth;
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Bad argument type!");
            }
        }
    }
}