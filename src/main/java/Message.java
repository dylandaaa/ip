import java.util.ArrayList;

public class Message {
    public static String byeMessage() {
        return """
                            ____________________________________________________________
                            Bye, see you around!
                            ____________________________________________________________
                    """;
    }

    public static String listMessage(ArrayList<Task> taskList) {
        StringBuilder message = new StringBuilder();
        message.append("       ____________________________________________________________\n");

        if (taskList.isEmpty()) {  // Use .isEmpty() instead of counter check
            message.append("        Your task list is empty! Add some tasks to get started.\n");
        } else {
            message.append("        Here are the tasks in your list:\n");
            for (int i = 0; i < taskList.size(); i++) {
                message.append("        ")
                        .append((i + 1))
                        .append(".")
                        .append(taskList.get(i))
                        .append("\n");
            }
        }

        message.append("       ____________________________________________________________\n");
        return message.toString();
    }

    public static String addMessage(Task task, int counter) {
        return "       ____________________________________________________________\n" +
                "       Great! I've added this task:\n" +
                "       " + task + "\n" +
                "       Now you have " + counter + " tasks in the list\n" +
                "       ____________________________________________________________\n";
    }

    public static String deleteMessage(Task deletedTask, int totalTasks) {
        return "        ____________________________________________________________\n" +
                "        Alrighty, I've removed this task:\n" +
                "          " + deletedTask + "\n" +
                "        Now you have " + totalTasks + " task(s) in the list.\n" +
                "        ____________________________________________________________\n";
    }
}
