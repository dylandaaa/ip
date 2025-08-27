package wheezy.parser;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import wheezy.commandtype.CommandType;

public class ParserTest {
    @Test
    public void parseCommand_validCommand_success() {
        //bye command
        assertEquals(CommandType.BYE, Parser.parseCommand("bye"));

        //list command
        assertEquals(CommandType.LIST, Parser.parseCommand("list"));

        //mark command
        assertEquals(CommandType.MARK, Parser.parseCommand("mark 3"));

        assertEquals(CommandType.MARK, Parser.parseCommand("mark 10000"));

        //unmark command
        assertEquals(CommandType.UNMARK, Parser.parseCommand("unmark 4"));

        assertEquals(CommandType.UNMARK, Parser.parseCommand("unmark 219038"));

        //todo command
        assertEquals(CommandType.TODO, Parser.parseCommand("todo do something"));

        //event command
        assertEquals(CommandType.EVENT,
                Parser.parseCommand("event burger party " +
                "/from 2025-10-08 /to 2025-10-09"));

        //deadline command
        assertEquals(CommandType.DEADLINE, Parser.parseCommand("deadline work /by 2025-08-27"));

        //delete command
        assertEquals(CommandType.DELETE, Parser.parseCommand("delete 3"));

        assertEquals(CommandType.DELETE, Parser.parseCommand("delete 10239"));
    }

    @Test
    public void parseCommand_invalidCommand_invalidCommandType() {
        //invalid commands
        assertEquals(CommandType.INVALID, Parser.parseCommand("some random command here"));

        assertEquals(CommandType.INVALID, Parser.parseCommand("unmarj 3"));

        assertEquals(CommandType.INVALID, Parser.parseCommand("todi read book"));

        assertEquals(CommandType.INVALID, Parser.parseCommand("byr"));

        assertEquals(CommandType.INVALID, Parser.parseCommand("lisr"));

        assertEquals(CommandType.INVALID, Parser.parseCommand("todoread book"));

        assertEquals(CommandType.INVALID, Parser.parseCommand("deafline patty party"));
    }

    @Test
    public void extractTaskNumber_validString_success() {
        //mark and unmark commands
        assertEquals(0, Parser.extractTaskNumber("mark 1"));

        assertEquals(3, Parser.extractTaskNumber("mark 4"));

        assertEquals(2, Parser.extractTaskNumber("unmark 3"));

        assertEquals(5, Parser.extractTaskNumber("unmark 6"));
    }

    @Test
    public void extractTaskNumber_invalidString_exceptionThrown() {
        //invalid type <mark/unmark> <integer>
        try {
            assertEquals(0, Parser.extractTaskNumber("mark 1 random"));
            fail();
        } catch (NumberFormatException e) {
            assertEquals("Invalid Format", e.getMessage());
        }

        try {
            assertEquals(0, Parser.extractTaskNumber("unmark 3 random"));
            fail();
        } catch (NumberFormatException e) {
            assertEquals("Invalid Format", e.getMessage());
        }
    }
}


