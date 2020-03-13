package src.commands;

/**
 * Command interface with one method "execute".
 */
public interface Command {
    void execute(String[] str) throws Exception;
}
