package wheezy.ui;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import wheezy.task.Todo;

public class UiTest {
    @Test
    public void addMessage_validString_success() {
        assertEquals("       ____________________________________________________________\n" +
                "       Great! I've added this task:\n" +
                "       " + "[T][ ] read book" + "\n" +
                "       Now you have 1 tasks in the list\n" +
                "       ____________________________________________________________\n",
                Ui.addMessage(new Todo("read book"), 1));
    }

    @Test
    public void deleteMessage_validString_success() {
        assertEquals("        ____________________________________________________________\n" +
                "        Alrighty, I've removed this task:\n" +
                "          " + "[T][ ] read book" + "\n" +
                "        Now you have 0 task(s) in the list.\n" +
                "        ____________________________________________________________\n",
                Ui.deleteMessage(new Todo("read book"), 0));
    }
}
