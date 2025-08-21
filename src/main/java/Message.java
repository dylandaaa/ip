public class Message {
    public static String byeMessage() {
        return """
                            ____________________________________________________________
                            Bye, see you around!
                            ____________________________________________________________
                    """;
    }

    public static String listMessage(Task[] list, int counter) {
        StringBuilder message = new StringBuilder();
        message.append("       ____________________________________________________________\n");
        for (int i = 0; i < counter; i++) {
            message.append("        ")
                    .append((i + 1))
                    .append(".")
                    .append(list[i])
                    .append("\n");
        }
        return message.toString();
    }

    public static String addMessage(String input) {
        return "       ____________________________________________________________\n" +
                "       added: " + input + "\n" +
                "       ____________________________________________________________\n";
    }
}
