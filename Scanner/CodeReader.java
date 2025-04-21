package Scanner;

public class CodeReader {

    // This will contain the string we want to read
    public String inputText;

    private int position = 0; // keeping track of where we are in the text

    // Creating a constructor that will runs when a new code reader is created
    public CodeReader(String text) {
        inputText = text;
    }

    // Here we are just looking at the current character in the text without moving
    // forward.

    public char peek() {
        return inputText.charAt(position);
    }

    // Now we returning the current character and moves to the next one
    public char next() {
        char currentChar = inputText.charAt(position);
        position = position + 1;
        return currentChar;
    }

    // isAtEnd() just helps us checks if we are at the end of the text
    public boolean isAtEnd() {
        return position >= inputText.length();
    }

}
