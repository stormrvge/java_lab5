package src.commands;

import java.io.IOException;

/**
 * Command interface with one method "execute".
 */
public interface Command {
    void execute(String[] str) throws IOException;
}