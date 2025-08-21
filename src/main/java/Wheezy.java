import java.util.Scanner;

public class Wheezy {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Task[] list = new Task[100];
        int counter = 0;

        String input = "";
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

        while (!input.equals("bye")) {
            input = scanner.nextLine();
            if (input.contains("mark")) {

            }
            if (!input.equals("list")) {
                list[counter] = new Task(input);
                counter++;
            }

        }
    }
}