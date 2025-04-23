package SemanticScanner;

import Scanner.TokenInfo;
import Scanner.TokenType;
import java.util.*;

public class SemanticChecker {

    private final Set<String> declaredVariables = new HashSet<>();
    private final Set<String> validKeywords = new HashSet<>(
            Arrays.asList("BEGIN", "INTEGER", "LET", "INPUT", "WRITE", "END"));
    private final Set<String> forbiddenSymbols = new HashSet<>(Arrays.asList(
            "%", "$", "&", "<", ">", ";"));

    public void analyze(List<TokenInfo> tokens) {
        if (tokens == null || tokens.isEmpty()) {
            System.out.println("No tokens to analyze.");
            return;
        }

        Map<Integer, List<String>> lineErrors = new TreeMap<>();
        boolean isDeclaring = false;

        for (TokenInfo token : tokens) {
            int line = token.line;

            switch (token.type) {
                case KEYWORD:
                    if (!validKeywords.contains(token.value)) {
                        addError(lineErrors, line, "[Semantic Error] Invalid keyword -> " + token.value);
                    }
                    isDeclaring = token.value.equals("INTEGER");
                    break;

                case IDENTIFIER:
                    if (containsForbiddenSymbol(token.value)) {
                        addError(lineErrors, line,
                                "[Semantic Error] Identifier contains forbidden symbol -> " + token.value);
                        break;
                    }

                    if (isDeclaring) {
                        if (declaredVariables.contains(token.value)) {
                            addError(lineErrors, line, "[Semantic Error] Duplicate declaration -> " + token.value);
                        } else {
                            declaredVariables.add(token.value);
                        }
                    } else {
                        if (!declaredVariables.contains(token.value)) {
                            addError(lineErrors, line,
                                    "[Semantic Error] Variable used before declaration -> " + token.value);
                        }
                    }
                    break;

                case UNKNOWN_TOKEN:
                    if (forbiddenSymbols.contains(token.value)) {
                        addError(lineErrors, line, "[Semantic Error] Forbidden symbol used -> " + token.value);
                    }
                    break;

                default:
                    // Ignore other token types for semantic analysis
                    break;
            }
        }

        // Output Results
        System.out.println("\n----- Semantic Analysis Results -----");
        if (lineErrors.isEmpty()) {
            System.out.println("No semantic errors found.");
        } else {
            for (Map.Entry<Integer, List<String>> entry : lineErrors.entrySet()) {
                System.out.println("Line " + entry.getKey() + ":");
                for (String error : entry.getValue()) {
                    System.out.println("  " + error);
                }
            }
        }

        System.out.println("\nDeclared Variables: " + declaredVariables);
    }

    private boolean containsForbiddenSymbol(String value) {
        for (String symbol : forbiddenSymbols) {
            if (value.contains(symbol)) {
                return true;
            }
        }
        return false;
    }

    private void addError(Map<Integer, List<String>> errorMap, int line, String message) {
        errorMap.computeIfAbsent(line, k -> new ArrayList<>()).add(message);
    }
}
