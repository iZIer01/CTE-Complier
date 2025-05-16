package BinaryConverter;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import ICR.IntermediateCodeRepresentation;

import java.util.HashMap;
import java.io.PrintStream;

// If IntermediateCodeRepresentation is in another package, import it here, for example:
// import your.package.IntermediateCodeRepresentation;

public class BinaryGenerator {
    private static final Map<String, String> instructionMap = new HashMap<>();
    private static final Map<String, String> variableMap = new HashMap<>();

    public BinaryGenerator() {
    }

    public static void generateBinary(List<IntermediateCodeRepresentation.ThreeAddressCode> var0) {
        System.out.println("----- Generated Binary Code -----");
        Iterator var1 = var0.iterator();

        while (var1.hasNext()) {
            IntermediateCodeRepresentation.ThreeAddressCode var2 = (IntermediateCodeRepresentation.ThreeAddressCode) var1
                    .next();
            if (var2.operator.isEmpty()) {
                generateInstruction("LOAD", var2.arg1);
                generateInstruction("STORE", var2.result);
            } else {
                generateInstruction("LOAD", var2.arg1);
                generateInstruction(var2.operatorToInstruction(), var2.arg2);
                generateInstruction("STORE", var2.result);
            }
        }
    }

    private static void generateInstruction(String var0, String var1) {
        String var2 = (String) instructionMap.getOrDefault(var0, "XXXX");
        String var3 = (String) variableMap.getOrDefault(var1, "XXXXX");
        PrintStream var10000 = System.out;
        var10000.println(var2 + " " + var3);
    }

    static {
        instructionMap.put("ADD", "0001");
        instructionMap.put("SUB", "0010");
        instructionMap.put("MUL", "0011");
        instructionMap.put("DIV", "0100");
        instructionMap.put("LOAD", "0101");
        instructionMap.put("STORE", "0110");

        variableMap.put("A", "00001");
        variableMap.put("B", "00010");
        variableMap.put("C", "00011");
        variableMap.put("G", "00111");
        variableMap.put("H", "01000");
        variableMap.put("I", "01001");
        variableMap.put("M", "01101");
        variableMap.put("N", "01110");
    }
}
