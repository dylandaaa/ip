import java.util.Objects;

public class Message {
    public static String sendMessage(String input) {
        if (Objects.equals(input, "bye")) {
            return """
                            ____________________________________________________________
                            Bye, see you around!
                            ____________________________________________________________
                    """;
        }
        else {
            return "       ____________________________________________________________\n" +
                    "       " + input + "\n" +
                    "       ____________________________________________________________";
        }
    }
}
