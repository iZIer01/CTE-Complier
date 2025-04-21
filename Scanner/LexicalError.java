package Scanner;

// creating our own error message
public class LexicalError extends RuntimeException {
    public LexicalError(String message) {
        super(message);
    }
}
