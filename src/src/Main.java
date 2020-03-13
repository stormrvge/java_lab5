package src;

import src.commands.*;

public class Main {
    public static void main(String[] args) {
        Invoker invoker = new Invoker();
        Client input = new Client();

        registerCommands(invoker);

        invoker.execute("load collection.json");
        System.out.println("Collection loaded. Type \"help\" for list of available commands.");
        while (Command_exit.isRunning) {
            input.readCommand();
            invoker.execute(input.getNextCommand());
        }
        input.closeInput();
    }

    private static void registerCommands(Invoker invoker) {
        ControlUnit e = new ControlUnit();
        Collection collection = new Collection();

        invoker.register("help", new Command_help(e));
        invoker.register("show", new Command_show(e, collection));
        invoker.register("info", new Command_info(e, collection));
        invoker.register("add", new Command_add(e, collection));
        invoker.register("update", new Command_update_id(e, collection));
        invoker.register("remove_by_id", new Command_remove_by_id(e, collection));
        invoker.register("clear", new Command_clear(e, collection));
        invoker.register("save", new Command_save(e, collection));
        invoker.register("load", new Command_load(e, collection));
        invoker.register("exit", new Command_exit(e));
        invoker.register("execute_script", new Command_execute_script(invoker));
        invoker.register("remove_at", new Command_remove_at(e, collection));
        invoker.register("add_if_max", new Command_add_if_max(e, collection));
        invoker.register("add_if_min", new Command_add_if_min(e, collection));
        invoker.register("count_by_distance", new Command_count_by_distance(e, collection));
        invoker.register("print_unique_distance", new Command_print_unique_distance(e, collection));
        invoker.register("print_field_ascending_distance", new Command_print_field_ascending_distance(e, collection));
    }
}