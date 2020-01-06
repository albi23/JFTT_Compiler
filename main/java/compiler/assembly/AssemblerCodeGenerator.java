package compiler.assembly;

import java.util.Stack;

public class AssemblerCodeGenerator {

    public void assignValue(){
        boolean verbose = false;
        long[] numberDeclarations = new long[]{7L,12L,28L,5_111_111_111L};
//        Arrays.sort(numberDeclarations);
//        System.out.println(Arrays.toString(numberDeclarations));
        Stack<String> commands = new Stack<>();
        if (verbose)System.out.println("Value "+ numberDeclarations[3]);
        while (numberDeclarations[3] > 1){
            if (numberDeclarations[3] % 2 == 1 ){
                commands.push("INC");
            }
            commands.push("SHIFT 1");
            numberDeclarations[3] = numberDeclarations[3]/2;
            if (verbose)System.out.println("Value "+ numberDeclarations[3]);
        }
        commands.push("STORE 1");
        commands.push("INC");
        commands.push("SUB 0");
        commands.add(0,"PUT");
        commands.add(0,"HALT");
        while (commands.size() > 0){
            System.out.println(commands.pop());
        }
    }
}
