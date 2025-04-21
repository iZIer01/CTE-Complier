package Scanner;

public enum TokenType {
    // Class containing the posable token we can have

    KEYWORD, // fot the int and float data types
    IDENTIFIER, // varible name begin on gone throught like name or num
    ASSIGNMENT_OPERATOR, // =
    INTEGER, // if the data being scan is a number like 25, 100
    SEMICOLON,
    OPERATOR, // ;
    UNKNOWN_TOKEN // Any character we don’t understand
}
