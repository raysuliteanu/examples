package misc;

import java.util.Arrays;
import java.util.Scanner;
import java.util.Stack;

public class SimpleEditor {
    enum Operation {
        Append,
        Delete,
        Print,
        Undo
    }

    private static class Command {
        Operation op;
        String arg;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in).useDelimiter("\n");
        int ops = scanner.nextInt();
        scanner.skip("\n");

        Command[] commands = new Command[ops];
        for (int i = 0; i < ops && scanner.hasNext(); i++) {
            String[] tokens = scanner.nextLine().split(" ");
            commands[i] = new Command();
            switch (tokens[0]) {
                case "1":
                    commands[i].op = Operation.Append;
                    commands[i].arg = tokens[1];
                    break;
                case "2":
                    commands[i].op = Operation.Delete;
                    commands[i].arg = tokens[1];
                    break;
                case "3":
                    commands[i].op = Operation.Print;
                    commands[i].arg = tokens[1];
                    break;
                case "4":
                    commands[i].op = Operation.Undo;
                    break;
                default:
                    throw new IllegalArgumentException(Arrays.toString(tokens));
            }
        }

        System.out.println(processCommands(commands));
    }

    private static String processCommands(final Command[] commands) {
        Stack<String> undoBuffer = new Stack<>();
        undoBuffer.ensureCapacity(commands.length);

        StringBuilder builder = new StringBuilder();

        for (Command command : commands) {
            switch (command.op) {
                case Append:
                    undoBuffer.push(builder.toString());
                    builder.append(command.arg);
                    break;
                case Delete:
                    undoBuffer.push(builder.toString());
                    builder.setLength(builder.length() - Integer.parseInt(command.arg));
                    break;
                case Print:
                    final int start = Integer.parseInt(command.arg);
                    System.out.println(builder.charAt(start - 1));
                    break;
                case Undo:
                    builder = new StringBuilder(undoBuffer.pop());
                    break;
            }
        }

        return builder.toString();
    }
}
