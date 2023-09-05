package misc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;

public class SimpleEditorEnhanced {
    public static void main(String[] args) throws IOException {
        Stack<String> undoBuffer = new Stack<>();
        String content = "";

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int ops = Integer.parseInt(reader.readLine());
            for (int i = 0; i < ops; i++) {
                String[] tokens = reader.readLine().split(" ");
                switch (tokens[0]) {
                    case "1" -> { // append
                        undoBuffer.push(content);
                        content += tokens[1];
                    }
                    case "2" -> { // delete
                        undoBuffer.push(content);
                        content = content.substring(0, content.length() - Integer.parseInt(tokens[1]));
                    }
                    case "3" -> // print
                            System.out.println(content.charAt(Integer.parseInt(tokens[1]) - 1));
                    case "4" -> // undo
                            content = undoBuffer.pop();
                    default -> throw new IllegalArgumentException(Arrays.toString(tokens));
                }
            }
        }
    }
}
