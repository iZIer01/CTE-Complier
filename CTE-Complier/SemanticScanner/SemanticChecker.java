import Scanner.TokenInfo;
import Scanner.TokenType;
import java.util.*;


public class SemanticChecker {
    private final Set<String> declaredVariables = new HashSet<>();
    private final Set<String> validKeywords = Set.of("BEGIN", "INTEGER", "LET", "INPUT", "WRITE", "END");
    private final Set<String> forbiddenSymbols = Set.of("%", "$", "&", "<", ">", ";");

    public void analyze(List<TokenInfo> tokens) {
        if (tokens == null || tokens.isEmpty()) {
            System.out.println("No tokens to analyze.");
            return;
        }

        Map<Integer, List<String>> lineErrors = new TreeMap<>();
        boolean declaring = false;

        for (TokenInfo token : tokens) {
            int line = token.line;

            switch (token.type) {
                case KEYWORD:
                    if (!validKeywords.contains(token.value)) {
                        addError(lineErrors, line, "[Semantic Error] Invalid keyword -> " + token.value);
                    }
                    declaring = token.value.equals("INTEGER");
                    break;

                case IDENTIFIER:
                    if (containsForbiddenSymbol(token.value)) {
                        addError(lineErrors, line, "[Semantic Error] Identifier contains forbidden symbol -> " + token.value);
                    }

                    if (declaring) {
                        if (declaredVariables.contains(token.value)) {
                            addError(lineErrors, line, "[Semantic Error] Duplicate declaration -> " + token.value);
                        } else {
                            declaredVariables.add(token.value);
                        }
                    } else {
                        if (!declaredVariables.contains(token.value)) {
                            addError(lineErrors, line, "[Semantic Error] Variable used before declaration -> " + token.value);
                        }
                    }
                    break;

                case UNKNOWN_TOKEN:
                    if (forbiddenSymbols.contains(token.value)) {
                        addError(lineErrors, line, "[Semantic Error] Forbidden symbol used -> " + token.value);
                    }
                    break;

                default:
                    // Other tokens are ignored for semantic analysis
                    break;
            }
        }

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

    private boolean containsForbiddenSymbol(String s) {
        for (String symbol : forbiddenSymbols) {
            if (s.contains(symbol)) {
                return true;
            }
        }
        return false;
    }

    private void addError(Map<Integer, List<String>> map, int line, String error) {
        map.computeIfAbsent(line, k -> new ArrayList<>()).add(error);
    }
}
