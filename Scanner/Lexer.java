package Scanner;

import java.util.ArrayList;
import java.util.List;

// IN this class we  break the input code into small parts 
public class Lexer {

    // This will help us read the code
    private final CodeReader reader;

    // Creating another constructor that will give it the input and it prepares to
    // read it
    public Lexer(String input) {
        this.reader = new CodeReader(input);
    }
    /*----------------------------------------------------------------------------------------------------*/

    // A loop to go through the input and creates a list of tokens
    public List<TokenInfo> tokenize() {
        List<TokenInfo> tokens = new ArrayList<>();

        // Keep reading until there's no more input left
        while (!reader.isAtEnd()) {
            char c = reader.peek();

            // Here we skip spaces or newlines
            if (Character.isWhitespace(c)) {
                reader.next(); // move to next character
            }

            // If it's a letter we check if it's a keyword or identifier
            else if (Character.isLetter(c)) {
                tokens.add(readWord());
            }

            // checks If it's a number
            else if (Character.isDigit(c)) {
                tokens.add(readNumber());
            }

            // check if its an the equal sign
            else if (c == '=') {
                reader.next();
                tokens.add(new TokenInfo(TokenType.ASSIGNMENT_OPERATOR, "="));
            }

            // If it's a semicolon
            else if (c == ';') {
                reader.next();
                tokens.add(new TokenInfo(TokenType.SEMICOLON, ";"));
            }

            // Handle common math operators: + - * /
            else if (c == '+' || c == '-' || c == '*' || c == '/') {
                reader.next();
                tokens.add(new TokenInfo(TokenType.OPERATOR, String.valueOf(c)));
            }

            // incase the code has somthing anything else we don’t know
            else {
                tokens.add(new TokenInfo(TokenType.UNKNOWN_TOKEN, String.valueOf(reader.next())));
            }
        }

        return tokens;
    }

    /*----------------------------------------------------------------------------------------------------*/

    // This section we focus on reading a word like a variable name or a keyword
    private TokenInfo readWord() {
        StringBuilder wordBuilder = new StringBuilder();

        // Keep reading letters or numbers until we hit a non-word character
        while (!reader.isAtEnd() && Character.isLetterOrDigit(reader.peek())) {
            wordBuilder.append(reader.next());
        }

        String word = wordBuilder.toString();

        // Check if the word is a keyword
        if (isKeyword(word)) {
            return new TokenInfo(TokenType.KEYWORD, word);
        }

        // if not than it's an identifier like variable names
        return new TokenInfo(TokenType.IDENTIFIER, word);
    }

    // It reads for numbers
    private TokenInfo readNumber() {
        StringBuilder numberBuilder = new StringBuilder();

        while (!reader.isAtEnd() && Character.isDigit(reader.peek())) {
            numberBuilder.append(reader.next());
        }

        return new TokenInfo(TokenType.INTEGER, numberBuilder.toString());
    }

    /*----------------------------------------------------------------------------------------------------*/

    // Checks if a word is a known keyword in our language
    private boolean isKeyword(String word) {
        return word.equals("BEGIN") || word.equals("INTEGER") || word.equals("LET")
                || word.equals("INPUT") || word.equals("WRITE") || word.equals("END");
    }
}
