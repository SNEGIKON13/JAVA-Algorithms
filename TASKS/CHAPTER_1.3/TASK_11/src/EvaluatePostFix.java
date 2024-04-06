import java.util.Scanner;
import java.util.Stack;
import java.util.StringTokenizer;
import java.util.EmptyStackException;

public class EvaluatePostFix {
    public static void main(String[] args) {
        Stack<Integer> digits = new Stack<>();

        Scanner in = new Scanner(System.in);
        String postfix = in.nextLine();

        int result;

        StringTokenizer tokenizer = new StringTokenizer(postfix, "+-*/ ", true);
        while (tokenizer.hasMoreTokens()) {
            String symbol = tokenizer.nextToken().trim();
            if (!symbol.isEmpty()) {
                if (symbol.matches("\\d+")) {
                    digits.push(Integer.parseInt(symbol));
                } else {
                    int operator;
                    if (symbol.equals("+")) {
                        operator = 1;
                    } else if (symbol.equals("-")) {
                        operator = 2;
                    } else if (symbol.equals("*")) {
                        operator = 3;
                    } else if (symbol.equals("/")) {
                        operator = 4;
                    } else {
                        System.out.println("Постфиксная запись неверна!");
                        return;
                    }
                    try {
                        result = performOperation(digits, operator);
                        digits.push(result);
                    } catch (EmptyStackException e) {
                        System.out.println("Вы неправильно ввели постфиксное выражение!");
                        return;
                    }
                }
            }
        }
        System.out.println(digits.pop());
    }
    public static int performOperation(Stack<Integer> digits, int operator) {
        int lastNumber;
        int secondLastNumber;
        try {
            lastNumber = digits.pop();
            secondLastNumber = digits.pop();
        }
        catch (EmptyStackException e) {
            throw new EmptyStackException();
        }
        if (operator == 1) {
            return secondLastNumber + lastNumber;
        } else if (operator == 2) {
            return secondLastNumber - lastNumber;
        } else if (operator == 3) {
            return secondLastNumber * lastNumber;
        } else {
            return secondLastNumber / lastNumber;
        }
    }
}