# CTE-Complier
This is a Java-based compiler simulation that processes a custom pseudo-code written in V Language. The program reads source code, analyzes it, detects errors, and processes syntactically and semantically correct lines through seven classical compiler stages.


# Code Generation Steps
Lexical Analysis: The input code is tokenized into meaningful units (tokens) using the Lexer class.
Semantic Analysis: The tokens are analyzed for semantic correctness using the SemanticChecker class.
Intermediate Code Representation (ICR):
The IntermediateCodeRepresentation.generateICR() method converts expressions into Three-Address Code (TAC).
Each expression is parsed into a postfix notation, and TAC instructions are generated for operations and assignments.
Code Generation:
The CodeGenerator.generateCode() method takes the TAC and generates low-level instructions (e.g., LOAD, ADD, STORE).
Each TAC instruction is translated into a sequence of assembly-like instructions.

# Optimizations Made and Why
Postfix Conversion for Parsing:

The infix-to-postfix conversion simplifies parsing and ensures operator precedence is handled correctly.
This avoids ambiguity and reduces the complexity of generating TAC.
Temporary Variables for Intermediate Results:

Temporary variables (t1, t2, etc.) are used to store intermediate results.
This ensures that complex expressions are broken into smaller, manageable steps, improving readability and modularity.
Operator Precedence Handling:

The precedence() method ensures that higher-precedence operators (e.g., *, /) are evaluated before lower-precedence ones (e.g., +, -).
This avoids incorrect evaluations and ensures correctness.
Error Handling for Invalid Expressions:

The hasConsecutiveOperators() method checks for invalid operator sequences, preventing runtime errors during ICR generation.
This ensures robustness and provides meaningful error messages.
Instruction Mapping:

The operatorToInstruction() method maps operators (+, -, *, /) to corresponding low-level instructions (ADD, SUB, MUL, DIV).
This abstraction simplifies the code generation process and makes it extensible for additional operators.
Code Modularity:

The separation of concerns between IntermediateCodeRepresentation and CodeGenerator ensures that each class handles a specific task.
This improves maintainability and allows for easier debugging and future enhancements.