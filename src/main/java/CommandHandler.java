public class CommandHandler {
    public static void handleMark(String input, Task[] list, int counter, boolean markAsDone) {
        int taskNumber = CommandParser.extractTaskNumber(input);
        if (taskNumber >= 0 && taskNumber < counter) {
            if (markAsDone) {
                list[taskNumber].markDone();
            } else {
                list[taskNumber].unmarkDone();
            }

            String action = markAsDone ? "done" : "not done yet";
            System.out.println("        ____________________________________________________________");
            System.out.println("        Okay, I've marked this task as" + action);
            System.out.println("        " + list[taskNumber]);
            System.out.println("        ____________________________________________________________");
        } else {
            System.out.println("        ____________________________________________________________");
            System.out.println("        Invalid Task Number");
            System.out.println("        ____________________________________________________________");
        }
    }

    public static void handleAdd(String input, Task[] list, int counter) {
        Task newTask = new Task(input);
        list[counter - 1] = newTask;
        System.out.println(Message.addMessage(input, newTask, counter));
    }

    public static void handleTodo(String input, Task[] list, int counter) {
        Task newTask = new Todo(input);
        list[counter - 1] = newTask;
        System.out.println(Message.addMessage(input, newTask, counter));
    }

    public static void handleDeadline(String input, Task[] list, int counter, String deadline) {
        Task newTask = new Deadline(input, deadline);
        list[counter - 1] = newTask;
        System.out.println(Message.addMessage(input, newTask, counter));
    }

    public static void handleEvent(String input, Task[] list, int counter, String from, String until) {
        Task newTask = new Event(input, from, until);
        list[counter - 1] = newTask;
        System.out.println(Message.addMessage(input, newTask, counter));
    }
}
