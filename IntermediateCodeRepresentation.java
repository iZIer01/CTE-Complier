import java.util.*;

public class IntermediateCodeRepresentation {

    static int tempVarCounter = 1;

    static class ThreeAddressCode {
        String result, arg1, operator, arg2;

        public ThreeAddressCode(String result, String arg1, String operator, String arg2) {
            this.result = result;
            this.arg1 = arg1;
            this.operator = operator;
            this.arg2 = arg2;
        }

        public String toString() {
            return result + " = " + arg1 + " " + operator + " " + arg2;
        }
    }

    public static List<ThreeAddressCode> generateICR(String expression, String target) {
        Stack<String> operands = new Stack<>();
        Stack<Character> operators = new Stack<>();
        List<ThreeAddressCode> codeList = new ArrayList<>();

        String postfix = infixToPostfix(expression);
        Stack<String> evalStack = new Stack<>();

        for (char ch : postfix.toCharArray()) {
            if (Character.isLetter(ch)) {
                evalStack.push(String.valueOf(ch));
            } else if (isOperator(ch)) {
                String op2 = evalStack.pop();
                String op1 = evalStack.pop();
                String temp = "t" + (tempVarCounter++);
                codeList.add(new ThreeAddressCode(temp, op1, String.valueOf(ch), op2));
                evalStack.push(temp);
            }
        }

        String finalTemp = evalStack.pop();
        codeList.add(new ThreeAddressCode(target, finalTemp, "", ""));

        return codeList;
    }

    // Helper methods

    static boolean isOperator(char c) {
        return "+-*/".indexOf(c) != -1;
    }

    static int precedence(char c) {
        return switch (c) {
            case '+', '-' -> 1;
            case '*', '/' -> 2;
            default -> -1;
        };
    }

    static String infixToPostfix(String expr) {
        StringBuilder result = new StringBuilder();
        Stack<Character> stack = new Stack<>();

        for (char c : expr.replaceAll(" ", "").toCharArray()) {
            if (Character.isLetter(c)) {
                result.append(c);
            } else if (isOperator(c)) {
                while (!stack.isEmpty() && precedence(stack.peek()) >= precedence(c)) {
                    result.append(stack.pop());
                }
                stack.push(c);
            }
        }
        while (!stack.isEmpty()) result.append(stack.pop());
        return result.toString();
    }

    public static void main(String[] args) {
        String[] lines = {
                "a + c:G",
                "A/B+C:M",
                "G/H-I+a*B/c:N"
        };

        for (String line : lines) {
            String[] parts = line.split(":");
            String expr = parts[0];
            String target = parts[1];

            System.out.println("ICR for " + target + " = " + expr);
            List<ThreeAddressCode> code = generateICR(expr, target);
            code.forEach(System.out::println);
            System.out.println();
        }
    }
}
