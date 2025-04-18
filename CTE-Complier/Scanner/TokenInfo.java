package Scanner;

// Represents a token with its type, value, and line number
public class TokenInfo {
    public final TokenType type;    // The type of the token (e.g., IDENTIFIER, KEYWORD, etc.)
    public final String value;      // The actual string value of the token
    public final int line;          // The line number where the token was found

    // Constructor to initialize the token with type, value, and line
    public TokenInfo(TokenType type, String value, int line) {
        this.type = type;
        this.value = value;
        this.line = line;
    }

    // String representation of the token for debugging
    @Override
    public String toString() {
        return type + " " + value + " (Line " + line + ")";
    }
}

