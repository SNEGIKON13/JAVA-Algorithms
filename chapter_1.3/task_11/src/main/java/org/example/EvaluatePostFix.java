package org.example;

import java.util.*;

public class EvaluatePostFix {
    public static void main(String[] args) {
        Deque<Integer> digits = new LinkedList<>();

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
    public static int performOperation(Deque<Integer> digits, int operator) {
        int lastNumber;
        int secondLastNumber;
        try {
            lastNumber = digits.pop();
            secondLastNumber = digits.pop();
        } catch (EmptyStackException e) {
            throw new EmptyStackException();
        }
        int result;
        switch (operator) {
            case 1 -> result = secondLastNumber + lastNumber;
            case 2 -> result = secondLastNumber - lastNumber;
            case 3 -> result = secondLastNumber * lastNumber;
            default -> result = secondLastNumber / lastNumber;
        }
        return result;
    }
}