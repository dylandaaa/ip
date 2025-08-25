import java.util.ArrayList;

public class Ui {
    private static void printWelcome() {
        String logo = """
                         __      __..__                                 \s
                        /  \\    /  \\  |__   ____   ____ __________.__.
                        \\   \\/\\/   /  |  \\_/ __ \\_/ __ \\\\____ |   |  |
                         \\        /|   Y  \\  ___/\\  ___/ /    /\\___  |
                          \\__/\\__/ |___|__/\\____/ \\____/ |____| /____|
                """;
        System.out.println("        __________________________________________________________");
        System.out.println("        Hello I'm\n" + logo);
        System.out.println("        What can I do for you?");
        System.out.println("        __________________________________________________________\n");
    }

    public static String byeMessage() {
        return """
                            ____________________________________________________________
                            Bye, see you around!
                            ____________________________________________________________
                    """;
    }

    public static String listMessage(TaskList taskList) {

        StringBuilder message = new StringBuilder();
        message.append("       ____________________________________________________________\n");

        if (taskList.isEmpty()) {
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

    public static void markAsDoneMessage(boolean markAsDone, Task task) {
        String action = markAsDone ? " done" : " not done yet";
        System.out.println("        ____________________________________________________________");
        System.out.println("        Nice! I've marked this task as" + action + ":");
        System.out.println("          " + task);
        System.out.println("        ____________________________________________________________");
    }
}
