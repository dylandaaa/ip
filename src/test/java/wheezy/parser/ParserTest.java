package wheezy.parser;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import wheezy.command.ByeCommand;
import wheezy.command.Command;
import wheezy.command.DeadlineCommand;
import wheezy.command.DeleteCommand;
import wheezy.command.EventCommand;
import wheezy.command.FindCommand;
import wheezy.command.InvalidCommand;
import wheezy.command.ListCommand;
import wheezy.command.MarkCommand;
import wheezy.command.TodoCommand;

public class ParserTest {
    @Test
    public void parseCommand_validCommand_success() {
        // bye command
        Command c1 = Parser.parseCommand("bye");
        assertTrue(c1 instanceof ByeCommand);

        // list command
        Command c2 = Parser.parseCommand("list");
        assertTrue(c2 instanceof ListCommand);

        // mark command
        Command c3 = Parser.parseCommand("mark 3");
        assertTrue(c3 instanceof MarkCommand);

        Command c4 = Parser.parseCommand("mark 10000");
        assertTrue(c4 instanceof MarkCommand);

        // unmark command
        Command c5 = Parser.parseCommand("unmark 4");
        assertTrue(c5 instanceof MarkCommand);

        Command c6 = Parser.parseCommand("unmark 219038");
        assertTrue(c6 instanceof MarkCommand);

        // todo command
        Command c7 = Parser.parseCommand("todo do something");
        assertTrue(c7 instanceof TodoCommand);

        // event command
        Command c8 = Parser.parseCommand("event burger party /from 2025-10-08 /to 2025-10-09");
        assertTrue(c8 instanceof EventCommand);

        // deadline command
        Command c9 = Parser.parseCommand("deadline work /by 2025-08-27");
        assertTrue(c9 instanceof DeadlineCommand);

        // delete command
        Command c10 = Parser.parseCommand("delete 3");
        assertTrue(c10 instanceof DeleteCommand);

        Command c11 = Parser.parseCommand("delete 10239");
        assertTrue(c11 instanceof DeleteCommand);

        // find command
        Command c12 = Parser.parseCommand("find something");
        assertTrue(c12 instanceof FindCommand);
    }

    @Test
    public void parseCommand_invalidCommand_invalidCommandType() {
        // invalid commands
        assertTrue(Parser.parseCommand("some random command here") instanceof InvalidCommand);
        assertTrue(Parser.parseCommand("unmarj 3") instanceof InvalidCommand);
        assertTrue(Parser.parseCommand("todi read book") instanceof InvalidCommand);
        assertTrue(Parser.parseCommand("byr") instanceof InvalidCommand);
        assertTrue(Parser.parseCommand("lisr") instanceof InvalidCommand);
        assertTrue(Parser.parseCommand("todoread book") instanceof InvalidCommand);
        assertTrue(Parser.parseCommand("deafline patty party") instanceof InvalidCommand);
    }

    @Test
    public void extractTaskNumber_validString_success() {
        // mark and unmark commands
        assertEquals(0, Parser.extractTaskNumber("mark 1"));
        assertEquals(3, Parser.extractTaskNumber("mark 4"));
        assertEquals(2, Parser.extractTaskNumber("unmark 3"));
        assertEquals(5, Parser.extractTaskNumber("unmark 6"));
    }

    @Test
    public void extractTaskNumber_invalidString_exceptionThrown() {
        // invalid type <mark/unmark> <integer>
        try {
            Parser.extractTaskNumber("mark 1 random");
            fail();
        } catch (NumberFormatException e) {
            assertEquals("Invalid Format", e.getMessage());
        }

        try {
            Parser.extractTaskNumber("unmark 3 random");
            fail();
        } catch (NumberFormatException e) {
            assertEquals("Invalid Format", e.getMessage());
        }
    }
}
