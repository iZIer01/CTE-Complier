package Scanner;

import java.util.List;

public class SyntaxChecker {

    // This method checks the order of the tokens to make sure the statement makes
    // sense
    public void check(List<TokenInfo> tokens) {
        int index = 0;

        // Start checking tokens one by one
        while (index < tokens.size()) {
            TokenInfo token = tokens.get(index);

            // If it starts with LET then we check if the rest of the line is correct
            if (token.type == TokenType.KEYWORD && token.value.equals("LET")) {
                index = index + 1; // move to next token

                // after LET we need a variable name (like A or total or x)
                if (!expect(tokens, index, TokenType.IDENTIFIER, "missing variable name after LET")) {
                    return;
                }
                index = index + 1;

                // after variable there must be an equal sign
                if (!expect(tokens, index, TokenType.ASSIGNMENT_OPERATOR, "expected '=' after variable name")) {
                    return;
                }
                index = index + 1;

                // now there should be another variable or number
                if (!expect(tokens, index, TokenType.IDENTIFIER, "expected something after '='")) {
                    return;
                }
                index = index + 1;

                // then comes a math operator like +, -, *, /
                if (!expect(tokens, index, TokenType.OPERATOR, "missing operator after value")) {
                    return;
                }
                index = index + 1;

                // then another variable or number
                if (!expect(tokens, index, TokenType.IDENTIFIER, "missing second value after operator")) {
                    return;
                }
                index = index + 1;

                // finally the line should end with a ;
                if (!expect(tokens, index, TokenType.SEMICOLON, "you forgot the semicolon at the end")) {
                    return;
                }
                index = index + 1;

                System.out.println("This LET line looks good.");
            }

            // if it doesn't start with LET we don’t know what to do (for now)
            else {
                System.out.println("Not a LET line or we don’t support this type of line yet: " + token.value);
                return;
            }
        }
    }

    // This helps us check if the token we expect is actually there and of the right
    // type
    private boolean expect(List<TokenInfo> tokens, int index, TokenType expected, String errorMessage) {
        if (index >= tokens.size()) {
            System.out.println("Error: " + errorMessage + " (but there is nothing more to check)");
            return false;
        }

        TokenInfo token = tokens.get(index);
        if (token.type != expected) {
            System.out.println("Error: " + errorMessage + " (got '" + token.value + "' instead)");
            return false;
        }

        // if everything is good, we just continue
        return true;
    }
}
