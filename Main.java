import java.util.List;

import Scanner.Lexer;
import Scanner.TokenInfo;

public class Main {
    public static void main(String[] args) {
        String code = 
            "BEGIN\n" +
            "INTEGER A, B, C, E, M, N, G, H, I, a, c\n" +
            "INPUT A, B, C\n" +
            "LET B = A */ M\n" +
            "LET G = a + c\n" +
            "temp = <s%**h - j / w +d +*$&;\n" +
            "M = A/B+C\n" +
            "N = G/H-I+a*B/c\n" +
            "WRITE M\n" +
            "WRITEE F;\n" +
            "END";
      
    Lexer lexer = new Lexer(code);       // Pass code to scanner
    List<TokenInfo> tokens = lexer.tokenize();  // Get token list

        for (TokenInfo token : tokens) {
            System.out.println(token);  // Print each token
        }
}
}