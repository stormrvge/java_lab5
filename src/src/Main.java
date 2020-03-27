package src;

import src.commands.*;
import src.io.Client;

public class Main {
    public static void main(String[] args) {
        String path = System.getenv("lab5");
        Invoker invoker = new Invoker();
        Client input = new Client();

        registerCommands(invoker);

        invoker.execute("load " + path);
        System.out.println("Type \"help\" for list of available commands.");
        while (CommandExit.isRunning) {
            input.readCommand();
            invoker.execute(input.getNextCommand());
        }
        input.closeInput();
    }

    private static void registerCommands(Invoker invoker) {
        CollectionManager e = new CollectionManager();

        invoker.register("help", new CommandHelp(e));
        invoker.register("show", new CommandShow(e));
        invoker.register("info", new CommandInfo(e));
        invoker.register("add", new CommandAdd(e));
        invoker.register("update_id", new CommandUpdateId(e));
        invoker.register("remove_by_id", new CommandRemoveById(e));
        invoker.register("clear", new CommandClear(e));
        invoker.register("save", new CommandSave(e));
        invoker.register("load", new CommandLoad(e));
        invoker.register("exit", new CommandExit(e));
        invoker.register("execute_script", new CommandExecuteScript(invoker));
        invoker.register("remove_at", new CommandRemoveAt(e));
        invoker.register("add_if_max", new CommandAddIfMax(e));
        invoker.register("add_if_min", new CommandAddIfMin(e));
        invoker.register("count_by_distance", new CommandCountByDistance(e));
        invoker.register("print_unique_distance", new CommandPrintUniqueDistance(e));
        invoker.register("print_field_ascending_distance", new CommandPrintFieldAscendingDistance(e));
    }
}