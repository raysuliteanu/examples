package misc;

public class EditorCommandsGenerator {
    public static void main(String[] args) {
        System.out.println(50_000 + "");
        for (int i = 0; i < 10_000; i++) {
            System.out.println("1 a");
        }
        for (int i = 1; i <= 10_000; i++) {
            System.out.println("3 " + i);
        }
        for (int i = 0; i < 10_000; i++) {
            System.out.println("2 1");
        }
        for (int i = 0; i < 20_000 - 1; i++) {
            System.out.println("4");
        }
    }
}
