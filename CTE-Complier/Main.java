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

        // Stage 1: Lexical Analysis
        Lexer lexer = new Lexer(code);
        List<TokenInfo> tokens = lexer.tokenize();

        System.out.println("----- Tokens from Lexical Analysis -----");
        for (TokenInfo token : tokens) {
            System.out.println(token);
        }

        // Stage 2 & 3: Semantic Analysis
        SemanticChecker semanticChecker = new SemanticChecker();
        semanticChecker.analyze(tokens);
    }
}
