import java.util.Stack;
public class Main {
    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<Integer>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        ConcurrentStackIterator<Integer> iterator = new ConcurrentStackIterator<Integer>(stack);
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
            stack.pop();
        }
    }
}