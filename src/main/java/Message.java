public class Message {
    public static String sendMessage(String input, Task[] list, int counter) {
        if (input.equals("bye")) {
            return """
                            ____________________________________________________________
                            Bye, see you around!
                            ____________________________________________________________
                    """;
        } else if (!input.equals("list")) {
            return "       ____________________________________________________________\n" +
                    "       added: " + input + "\n" +
                    "       ____________________________________________________________\n";
        } else {
            StringBuilder message = new StringBuilder("       ____________________________________________________________\n");

            for (int i = 0; i < counter; i++) {
                message.append((i + 1))
                        .append(". ")
                        .append(list[i])
                        .append("\n");
            }
            message.append("       ____________________________________________________________\n");
            return message.toString();
        }
    }
}
