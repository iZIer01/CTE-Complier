package ICR;

import java.util.*;

public class IntermediateCodeRepresentation {

    private static int tempVarCounter = 1;

    public static class ThreeAddressCode {
        public String result, arg1, operator, arg2; // Make fields public

        public ThreeAddressCode(String result, String arg1, String operator, String arg2) {
            this.result = result;
            this.arg1 = arg1;
            this.operator = operator;
            this.arg2 = arg2;
        }

        @Override
        public String toString() {
            if (operator.isEmpty()) {
                return result + " = " + arg1;
            }
            return result + " = " + arg1 + " " + operator + " " + arg2;
        }

        public String operatorToInstruction() { // Make method public
            switch (operator) {
                case "+": return "ADD";
                case "-": return "SUB";
                case "*": return "MUL";
                case "/": return "DIV";
                default: throw new IllegalArgumentException("Unknown operator: " + operator);
            }
        }
    }

    public static List<ThreeAddressCode> generateICR(String expression, String target) {
        List<ThreeAddressCode> codeList = new ArrayList<>();
        try {
            String postfix = infixToPostfix(expression);
            Stack<String> evalStack = new Stack<>();

            for (String token : tokenizePostfix(postfix)) {
                if (isOperand(token)) {
                    evalStack.push(token);
                } else if (isOperator(token)) {
                    if (evalStack.size() < 2) {
                        throw new IllegalArgumentException("Not enough operands for operator '" + token +
                                "' in expression '" + expression + "'");
                    }
                    String op2 = evalStack.pop();
                    String op1 = evalStack.pop();
                    String temp = "t" + (tempVarCounter++);
                    codeList.add(new ThreeAddressCode(temp, op1, token, op2));
                    evalStack.push(temp);
                }
            }

            if (evalStack.isEmpty()) {
                throw new IllegalArgumentException("No result produced from expression '" + expression + "'");
            }

            String finalTemp = evalStack.pop();
            codeList.add(new ThreeAddressCode(target, finalTemp, "", ""));
        } catch (Exception e) {
            throw new IllegalArgumentException("Failed to generate ICR for expression '" + expression + "': " + e.getMessage());
        }
        return codeList;
    }

    // Helper Methods

    public static boolean isOperator(String token) {
        return "+-*/".indexOf(token) != -1;
    }

    private static boolean isOperand(String token) {
        return !isOperator(token) && !token.isEmpty();
    }

    private static int precedence(char c) {
        switch (c) {
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
            default:
                return -1;
        }
    }

    private static String infixToPostfix(String expr) {
        StringBuilder result = new StringBuilder();
        Stack<Character> stack = new Stack<>();

        StringTokenizer tokenizer = new StringTokenizer(expr, "+-*/() ", true); // Tokenize based on operators and parentheses
        while (tokenizer.hasMoreTokens()) {
            String token = tokenizer.nextToken().trim();
            if (token.isEmpty()) continue;

            if (Character.isLetterOrDigit(token.charAt(0))) {
                result.append(token).append(" "); // Add operand to result
            } else if (isOperator(token)) {
                while (!stack.isEmpty() && precedence(stack.peek()) >= precedence(token.charAt(0))) {
                    result.append(stack.pop()).append(" ");
                }
                stack.push(token.charAt(0));
            } else if (token.equals("(")) {
                stack.push('(');
            } else if (token.equals(")")) {
                while (!stack.isEmpty() && stack.peek() != '(') {
                    result.append(stack.pop()).append(" ");
                }
                stack.pop(); // Remove '(' from the stack
            }
        }

        while (!stack.isEmpty()) {
            result.append(stack.pop()).append(" ");
        }

        return result.toString().trim();
    }

    private static List<String> tokenizePostfix(String postfix) {
        List<String> tokens = new ArrayList<>();
        StringTokenizer tokenizer = new StringTokenizer(postfix, " ");
        while (tokenizer.hasMoreTokens()) {
            tokens.add(tokenizer.nextToken());
        }
        return tokens;
    }

    public static void main(String[] args) {
        String[] expressions = {
            "a + c",
            "A/B+C",
            "G/H-I+a*B/c",
            "B = A */ M"  // Invalid expression (will trigger exception)
        };

        for (String expression : expressions) {
            try {
                String target = "result"; // Example target variable
                System.out.println("Processing expression: " + expression);
                List<ThreeAddressCode> codeList = generateICR(expression, target);
                for (ThreeAddressCode tac : codeList) {
                    System.out.println(tac);
                }
            } catch (Exception e) {
                System.out.println("Error processing expression \"" + expression + "\": " + e.getMessage());
            }
            System.out.println();
        }
    }
}
