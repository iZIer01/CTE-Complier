import java.util.List;
import ICR.IntermediateCodeRepresentation;
import ICR.IntermediateCodeRepresentation.ThreeAddressCode;
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

        // Stage 4: Intermediate Code Generation
        System.out.println("\n----- Intermediate Code Representation (Three Address Code) -----");

        // Process the full code to extract expressions from "LET" statements and generate ICR
        String[] lines = code.split("\n");
        for (String line : lines) {
            if (line.startsWith("LET")) {
                String[] parts = line.split("=");
                if (parts.length == 2) {
                    String expr = parts[1].trim(); // The right-hand side expression
                    String target = parts[0].split(" ")[1].trim(); // The target variable from the LET statement

                    System.out.println("ICR for " + target + " = " + expr);
                    List<ThreeAddressCode> codeList = IntermediateCodeRepresentation.generateICR(expr, target);
                    for (ThreeAddressCode tac : codeList) {
                        System.out.println(tac);
                    }
                    System.out.println();
                }
            }
        }
    }
}
