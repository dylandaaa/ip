package wheezy.parser;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import wheezy.priority.Priority;

public class ParserExtractorsTest {

    @Test
    public void extractTodoDescription_handlesPrioritySuffix() {
        assertEquals("read book", Parser.extractTodoDescription("todo read book"));
        assertEquals("read book", Parser.extractTodoDescription("todo read book /priority high"));
        assertEquals("multi word desc", Parser.extractTodoDescription("todo   multi word desc   /priority medium"));
    }

    @Test
    public void extractDeadlineDescription_and_Date_withAndWithoutPriority() {
        String inputNoPriority = "deadline finish project /by 2025-12-01";
        assertEquals("finish project", Parser.extractDeadlineDescription(inputNoPriority));
        assertEquals("2025-12-01", Parser.extractDeadlineDate(inputNoPriority));

        String inputWithPriority = "deadline finish project /by 2025-12-01 /priority low";
        assertEquals("finish project", Parser.extractDeadlineDescription(inputWithPriority));
        assertEquals("2025-12-01", Parser.extractDeadlineDate(inputWithPriority));
    }

    @Test
    public void extractDeadline_throwsOnMissingBy() {
        String bad = "deadline missing by token 2025-12-01";
        assertThrows(IllegalArgumentException.class, () -> Parser.extractDeadlineDescription(bad));
        assertThrows(IllegalArgumentException.class, () -> Parser.extractDeadlineDate(bad));
    }

    @Test
    public void extractEventDescription_Start_End_withAndWithoutPriority() {
        String input = "event meetup /from 2025-10-08 /to 2025-10-09";
        assertEquals("meetup", Parser.extractEventDescription(input));
        assertEquals("2025-10-08", Parser.extractEventStartTime(input));
        assertEquals("2025-10-09", Parser.extractEventEndTime(input));

        String inputWithPriority = "event meetup /from 2025-10-08 /to 2025-10-09 /priority high";
        assertEquals("meetup", Parser.extractEventDescription(inputWithPriority));
        assertEquals("2025-10-08", Parser.extractEventStartTime(inputWithPriority));
        assertEquals("2025-10-09", Parser.extractEventEndTime(inputWithPriority));
    }

    @Test
    public void extractEvent_throwsOnMissingTokens() {
        String missingFrom = "event meetup /to 2025-10-09";
        assertThrows(IllegalArgumentException.class, () -> Parser.extractEventDescription(missingFrom));
        assertThrows(IllegalArgumentException.class, () -> Parser.extractEventStartTime(missingFrom));

        String missingTo = "event meetup /from 2025-10-08";
        assertThrows(IllegalArgumentException.class, () -> Parser.extractEventStartTime(missingTo));
        assertThrows(IllegalArgumentException.class, () -> Parser.extractEventEndTime(missingTo));
    }

    @Test
    public void extractFindDescription_basic() {
        assertEquals("keyword", Parser.extractFindDescription("find keyword"));
        assertEquals("two words", Parser.extractFindDescription("find two words"));
    }

    @Test
    public void extractPriority_parsesValuesOrReturnsNull() {
        assertEquals(Priority.HIGH, Parser.extractPriority("todo task /priority high"));
        assertEquals(Priority.MEDIUM, Parser.extractPriority("deadline task /by 2025-01-01 /priority medium"));
        assertEquals(Priority.LOW, Parser.extractPriority("event task /from 2025-01-01 /to 2025-01-02 /priority low"));
        assertNull(Parser.extractPriority("todo task without priority"));
    }

    @Test
    public void extractPriority_throwsOnInvalidValue() {
        assertThrows(IllegalArgumentException.class, () -> Parser.extractPriority("todo t /priority urgent"));
    }
}
