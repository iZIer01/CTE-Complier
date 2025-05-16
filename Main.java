import java.util.List;

import BinaryConverter.BinaryGenerator;
import ICR.IntermediateCodeRepresentation;
import ICR.IntermediateCodeRepresentation.ThreeAddressCode;
import SemanticScanner.SemanticChecker;
import Scanner.Lexer;
import Scanner.TokenInfo;

public class Main {
    public static void main(String[] args) {
        String code = "BEGIN\n" +
                "INTEGER A, B, C, E, M, N, G, H, I, a, c\n" +
                "INPUT A, B, C\n" +
                "LET B = A */ M\n" + // Intentionally broken
                "LET G = a + c\n" +
                "temp = <s%**h - j / w +d +*$&;\n" + // Junk line to trigger errors
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

        // Stage 2: Semantic Analysis
        SemanticChecker semanticChecker = new SemanticChecker();
        semanticChecker.analyze(tokens);

        // Stage 3 & 4: Intermediate Code Generation & Binary Code
        System.out.println("\n----- Intermediate Code Representation (Three Address Code) -----");

        String[] lines = code.split("\n");
        for (String line : lines) {
            if (line.startsWith("LET")) {
                String[] parts = line.split("=");
                if (parts.length == 2) {
                    String expr = parts[1].trim();
                    String target = parts[0].split(" ")[1].trim();

                    System.out.println("ICR for " + target + " = " + expr);

                    try {
                        if (hasConsecutiveOperators(expr)) {
                            System.out.println(
                                    "  [ICR Error] Invalid expression: consecutive operators in '" + expr + "'");
                            continue;
                        }

                        List<ThreeAddressCode> codeList = IntermediateCodeRepresentation.generateICR(expr, target);

                        for (ThreeAddressCode tac : codeList) {
                            System.out.println(tac);
                        }

                        // Stage 4: Binary Code Generation
                        BinaryGenerator.generateBinary(codeList);

                    } catch (Exception e) {
                        System.out
                                .println("  [ICR Error] Failed to generate ICR for '" + expr + "': " + e.getMessage());
                    }

                    System.out.println();
                }
            }
        }
    }

    private static boolean hasConsecutiveOperators(String expr) {
        for (int i = 0; i < expr.length() - 1; i++) {
            char c1 = expr.charAt(i);
            char c2 = expr.charAt(i + 1);
            if (IntermediateCodeRepresentation.isOperator(String.valueOf(c1)) &&
                    IntermediateCodeRepresentation.isOperator(String.valueOf(c2))) {
                return true;
            }
        }
        return false;
    }
}
