package CodeGenerator;

import ICR.IntermediateCodeRepresentation.ThreeAddressCode;
import java.util.List;

public class CodeGenerator {

    public static void generateCode(List<ThreeAddressCode> icrList) {
        System.out.println("----- Generated Code -----");
        for (ThreeAddressCode tac : icrList) {
            if (tac.operator.isEmpty()) {
                // Handle assignment
                System.out.println("LOAD " + tac.arg1);
                System.out.println("STORE " + tac.result);
            } else {
                // Handle operations
                System.out.println("LOAD " + tac.arg1);
                System.out.println(tac.operatorToInstruction() + " " + tac.arg2);
                System.out.println("STORE " + tac.result);
            }
        }
    }
}
