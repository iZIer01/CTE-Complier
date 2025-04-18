package Scanner;

import java.util.ArrayList;
import java.util.List;

// IN this class we  break the input code into small parts
public class Lexer {

    // This will help us read the code
    private final CodeReader reader;

    // Variable to track the current line number
    private int lineNumber = 1;

    // Creating another constructor that will give it the input and it prepares to read it
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

            // If it's a newline, increase the line number
            if (c == '\n') {
                lineNumber++; // Increase the line number when we encounter a newline
                reader.next(); // Move to the next character after the newline
            }

            // Here we skip spaces or newlines (except for counting newlines)
            else if (Character.isWhitespace(c)) {
                reader.next(); // Move to next character
            }

            // If it's a letter, we check if it's a keyword or identifier
            else if (Character.isLetter(c)) {
                tokens.add(readWord());
            }

            // Checks if it's a number
            else if (Character.isDigit(c)) {
                tokens.add(readNumber());
            }

            // Check if it's the equal sign 
            else if (c == '=') {
                reader.next();
                tokens.add(new TokenInfo(TokenType.ASSIGNMENT_OPERATOR, "=", lineNumber)); // Pass lineNumber here
            }

            // If it's a semicolon
            else if (c == ';') {
                reader.next();
                tokens.add(new TokenInfo(TokenType.SEMICOLON, ";", lineNumber)); // Pass lineNumber here
            }

            // In case the code has something we don’t recognize
            else {
                tokens.add(new TokenInfo(TokenType.UNKNOWN_TOKEN, String.valueOf(reader.next()), lineNumber)); // Pass lineNumber here
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
            return new TokenInfo(TokenType.KEYWORD, word, lineNumber); // Pass lineNumber here
        }

        // if not then it's an identifier like variable names
        return new TokenInfo(TokenType.IDENTIFIER, word, lineNumber); // Pass lineNumber here
    }

    // It reads for numbers 
    private TokenInfo readNumber() {
        StringBuilder numberBuilder = new StringBuilder();

        while (!reader.isAtEnd() && Character.isDigit(reader.peek())) {
            numberBuilder.append(reader.next());
        }

        return new TokenInfo(TokenType.INTEGER, numberBuilder.toString(), lineNumber); // Pass lineNumber here
    }

    /*----------------------------------------------------------------------------------------------------*/

    // Checks if a word is a known keyword in our language
    private boolean isKeyword(String word) {
        return word.equals("BEGIN") || word.equals("INTEGER") || word.equals("LET")
                || word.equals("INPUT") || word.equals("WRITE") || word.equals("END");
    }
}

