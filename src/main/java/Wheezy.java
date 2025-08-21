import java.util.Scanner;

public class Wheezy {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Task[] list = new Task[100];
        int counter = 0;

        String logo = """
                         __      __.__                                \s
                        /  \\    /  \\  |__   ____   ____ ___________.__.
                        \\   \\/\\/   /  |  \\_/ __ \\_/ __ \\\\___   |   |  |
                         \\        /|   Y  \\  ___/\\  ___/ /    /_\\___  |
                          \\__/\\__/ |___|__/\\____/ \\____/ |_____| /____|
                """;
        System.out.println("        ____________________________________________________________");
        System.out.println("        Hello I'm\n" + logo);
        System.out.println("        What can I do for you?");
        System.out.println("        ____________________________________________________________\n");

        while (true) {
            String input = scanner.nextLine();
            CommandType commandType = CommandParser.parseCommand(input);

            switch (commandType) {
                case BYE -> {
                    System.out.println(Message.byeMessage());
                    break;
                }
                case LIST -> {
                    System.out.println(Message.listMessage(list, counter));
                }
                case MARK -> {
                    CommandHandler.handleMark(input, list, counter, true);
                }
                case UNMARK -> {
                    CommandHandler.handleMark(input, list, counter, false);
                }
                case ADD_TASK -> {
                    CommandHandler.handleAdd(input, list, counter);
                    counter++;
                }
                case INVALID -> {
                    System.out.println("        ____________________________________________________________");
                    System.out.println("        Invalid Command");
                    System.out.println("        ____________________________________________________________");
                }
            }

            if (commandType == CommandType.BYE) {
                break;
            }
        }
    }
}