package Scanner;

public class TokenInfo {

    public TokenType type;
    public String value;
    public int line;

    public TokenInfo(TokenType type, String value) {
        this.type = type;
        this.value = value;
    }

    public String toString() {
        String info = type + " " + value;
        return info;
    }

}
