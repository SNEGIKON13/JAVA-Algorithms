import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Вы забыли про К");
            return;
        }
        int k = Integer.parseInt(args[0]);
        if (k <= 0) {
            System.out.println("К неверно");
        }

        Scanner scanner = new Scanner(System.in);
        Queue<String> queue = new LinkedList<>();
        String[] strings = scanner.nextLine().split("\\s+");
        queue.addAll(Arrays.asList(strings));
        if (k > queue.size()) {
            System.out.println("К больше очереди");
        }
        else {
            for (int i = queue.size(); i > k; i--) {
                queue.poll();
            }
            String elementK = queue.poll();
            System.out.println(elementK);
        }
    }
}