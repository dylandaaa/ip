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
        list[counter] = new Task(input);
        System.out.println(Message.addMessage(input));
    }
}
