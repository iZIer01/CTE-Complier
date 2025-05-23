# What are compiler is?(The simplest way)
Is the process of converting a program written in one language( in our case it will be Java) into another language so it can run on a computer.

# Example 
Imagine writing a program/ a code in Java, so the computer doesn't understand it directly. So a complier will take that code and convert it into a machine code. 


# Now what each step does or should do.

1. Lexical Analysis (Scanner)

The compiler reads the program and breaks it into words (called tokens). It checks if these words are valid. Example if the keywords are spelled right and if there are any illegal characters.

## Our goal is than to check for 
-- Invalid characters like %, $, &
--Use of numbers (which are not allowed in this language)
--Misspelled keywords

2. Syntax Analysis (Parser)

So here we checks the grammar or structure of the code. It makes sure the order of words makes sense according to the language rules.

## Our goal is than to check for 

--Two operators used together (like +*, -+)
--Semicolons at the end of a line (not allowed)

3. Semantic Analysis
Now the compiler checks if the meaning of the code is correct. Even if the grammar is fine, the logic may not make sense.

## Example:
temp = <s%**h - j / w +d +*$&; 

The temp looks like it uses allowed symbols and operators, but it contains illegal characters like %, *, $, &. This is a Semantic Error.

4. Intermediate Code Representation (ICR)

Once the code passes all the checks, it is turned into a simpler, lower-level version. This is not binary yet, but it's easier for the computer to process later.

## Example:
A line like M = A / B + C would be turned into a form like:
t1 = A / B  
M = t1 + C

5. Code Generation
The intermediate code is turned into machine instructions — this looks closer to what a computer understands, but it’s not yet optimized or in binary.

## Example:
* LOAD A  
* DIV B  
* STORE t1  
* LOAD t1  
* ADD C  
* STORE M 


6. Code Optimization
The compiler improves the code to make it faster or use less memory. It looks for ways to make the instructions shorter or smarter.

## Example:
If two calculations do the same thing, the compiler might store the result and reuse it instead of repeating the work.

7. Target Machine Code (TMC) in Binary
Then the optimized code is turned into binary code and the 1s and 0s that the machine can run.
## Example:
The line M = A / B + C eventually becomes something like:
* 01010011 10100011 11000100 10100011
