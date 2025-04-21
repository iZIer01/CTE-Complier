
Intermediate Code Representation (ICR) - Java Compiler Stage

 Overview

This Java program implements the **Intermediate Code Representation (ICR)** phase of a compiler as part of the compiler development process. It takes in **valid lines** of a simple V-like language and generates their **Three-Address Code (TAC)**, a common form of intermediate code.

This module combines:
- ✅ Lexical Analysis (Stage 1)
- ✅ Syntax Analysis (Stage 2)
- ✅ Semantic Analysis (Stage 3)
- ✅ Intermediate Code Generation (Stage 4)


Purpose

The main goal is to simulate how a compiler processes **valid lines** through the early stages and transforms them into a simplified, abstract format that is easier to optimize and convert to machine code.


What is Three-Address Code (TAC)?

Three-Address Code is a common ICR format where each instruction contains:

<result> = <arg1> <operator> <arg2>


Example:
For the input:

N = G/H - I + a*B/c


The output TAC would be:

t1 = a * B
t2 = t1 / c
t3 = G / H
t4 = t3 - I
t5 = t4 + t2
N = t5


======================================================================================================

Compiler Stages in This Module

### 1. Lexical Analysis
- Splits a line into **tokens**.
- Example:
  
  LET G = a + c → [LET, G, =, a, +, c]
  

### 2. Syntax Analysis
- Checks the **structure** of the tokens.
- Ensures the format matches grammar rules like `E → E + E`.

### 3. Semantic Analysis
- Validates **meaning**, such as:
  - Identifiers must be declared.
  - Operations must be logically correct.

###4. Intermediate Code Generation
- Converts valid expressions into **TAC** using postfix conversion and stack evaluation.

Supported Valid Lines (from the V-language assignment)

These are the only lines passed through all stages:

LET G = a + c
M = A/B + C
N = G/H - I + a*B/c


==================================================

How the Code Works

Step 1:
Converts expressions like `a + b * c` to `abc*+` for easier processing.
Step 2: Stack-Based TAC Generation
- Uses a **stack** to evaluate postfix expressions.
- For every operation, generates a line of TAC:
  tX = operand1 OP operand2

Step 3: Final Assignment
- Once all temp variables are processed, assigns the result to the actual variable (`G`, `M`, or `N`).


========================================================================

Run the program:

java IntermediateCodeRepresentation

Sample Output

--------------------------------------------
ICR for G = a + c
t1 = a + c
G = t1

ICR for M = A/B+C
t2 = A / B
t3 = t2 + C
M = t3

ICR for N = G/H-I+a*B/c
t4 = a * B
t5 = t4 / c
t6 = G / H
t7 = t6 - I
t8 = t7 + t5
N = t8

===============================================================================================================

Resources Used to Create This ICR
The following books and websites were used to guide the creation of the Intermediate Code Representation (ICR) system:

Books
Compilers: Principles, Techniques, and Tools by Alfred V. Aho et al.

Advanced Compiler Design and Implementation by Steven Muchnick

Online Tutorials
GeeksforGeeks – Intermediate Code Generation in Compiler Design
https://www.geeksforgeeks.org/introduction-to-intermediate-representationir/

TutorialsPoint – Intermediate Code Generation
https://www.tutorialspoint.com/compiler_design/compiler_design_intermediate_code_generations.htm

TutorialsPoint – Types of Intermediate Code Representation
https://www.tutorialspoint.com/what-is-types-of-intermediate-code-representation

Video Lectures
YouTube – Compilers: Intermediate Representations
https://www.youtube.com/watch?v=D19P2l6WotA

YouTube – Intermediate Code Generation Introduction
https://www.youtube.com/watch?v=Pd9dqUiQ_Lk

=========================================================================================


Invalid lines (with syntax, lexical, or semantic errors) are excluded.

Emanuel Junior  
Mini Compiler Assignment – Stage 4: ICR
