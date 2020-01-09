package compiler.assembly;

import java.math.BigInteger;
import java.util.Stack;

/**
 * Niech P1 zawsze będzie = 1
 * Niech P2 zawsze będzie = -1
 */
public class AssemblerCodeGenerator {

    private  StringBuilder stringBuilder = new StringBuilder();
    private int lastFreeCeil = 2;

    public void beginProgram(){
        stringBuilder.append("SUB 0").append('\n');
        stringBuilder.append("DEC").append('\n');
        stringBuilder.append("STORE 2").append('\n');
        stringBuilder.append("INC").append('\n');
        stringBuilder.append("INC").append('\n');
        stringBuilder.append("STORE 1").append('\n');
    }
    public String getValue(BigInteger num, int storeCeil){
        boolean verbose = false;
        long numberDeclarations = num.longValueExact();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("LOAD 1").append('\n');
        Stack<String> commands = new Stack<>();
        if (verbose)System.out.println("Value "+ numberDeclarations);
        while (numberDeclarations > 1){
            if (numberDeclarations % 2 == 1 ){
                commands.push("INC");
            }
            commands.push("SHIFT 1");
            numberDeclarations= numberDeclarations/2;
            if (verbose)System.out.println("Value "+ numberDeclarations);
        }

        while (commands.size() > 0)   stringBuilder.append(commands.pop()).append('\n');
        if (storeCeil > 0 ){
            stringBuilder.append("STORE ").append(storeCeil).append('\n');
        }
        return stringBuilder.toString();
    }

    /** Komórki mi potrzebne które są w uzyciu
     */
    public String multiply(BigInteger num, BigInteger num2, int  lines){

        StringBuilder stringBuilder = new StringBuilder();
        int num1MemoryPosition = ++this.lastFreeCeil;
        int num2MemoryPosition = ++this.lastFreeCeil;
        int resultInMemory = ++this.lastFreeCeil;

        if (num.compareTo(num2) >= 0){
            stringBuilder.append(this.getValue(num, num1MemoryPosition));
            stringBuilder.append(this.getValue(num2, num2MemoryPosition)); // smaller number is always in num2memor
        }else {
            stringBuilder.append(this.getValue(num2, num1MemoryPosition));
            stringBuilder.append(this.getValue(num, num2MemoryPosition));
        }


        String a = "LOAD 3  # ŁADUJEMY A\n" +
                "SHIFT 1 #PRZESUWAMY W PRAWO\n" +
                "SHIFT 2 #PRZESUWAMY W LEWO\n" +
                "SUB 3  # MAMY RÓŻNICE A I A'\n" +

                "JZERO 22 # MAMY 0 NA OSTATNIM BICIE (IF) +(WHILE)\n" +
                "LOAD 5 # WEŻ WYNIK\n" +
                "ADD 4 # DODAJ B\n" +
                "STORE 5 # ZAPISZ NOWY WYNIK (END IF)\n" +
                "LOAD 4 # WEZ B  # SKOCZEK Z W PRZYPADKU PRZESUNIĘCIA\n" +// B > A
                "SHIFT 2 # PRZESUN B W LEWO\n" +
                "STORE 4 # ZAPISZ B\n" +

                "LOAD 3 #WEZ A\n" +//
                "SHIFT 1 #PRZESUWAMY W PRAWO\n" +
                "STORE 3 # ZAPISZ NOWE A\n" +
                "JZERO 30 # JESLI WARTOŚ A = 0 KONCZ\n" +
                "JUMP 14 # {END WHILE}";

        stringBuilder.append("LOAD 1").append(num1MemoryPosition).append('\n');
        stringBuilder.append("DEC").append(num1MemoryPosition).append('\n');
        stringBuilder.append("STORE ").append(resultInMemory).append('\n').append("# RESULT MEMORY");
        stringBuilder.append("LOAD ").append(num2MemoryPosition).append('\n');
        stringBuilder.append("SHIFT 2").append(num2MemoryPosition).append('\n');
        stringBuilder.append("SHIFT 1").append(num2MemoryPosition).append('\n');
        stringBuilder.append("SUB ").append(num2MemoryPosition).append('\n');
        int jzeroJump =  stringBuilder.length(); // postion under
        // Ther should be JZERO XX but we have to know which line so remember this postion
        stringBuilder.append("JZERO XX").append(num2MemoryPosition).append('\n');
        stringBuilder.append("LOAD ").append(resultInMemory).append('\n');
        stringBuilder.append("ADD ").append(num1MemoryPosition).append('\n');
        stringBuilder.append("STORE ").append(resultInMemory).append('\n');
        stringBuilder.append("LOAD ").append(num1MemoryPosition).append('\n');
        stringBuilder.append("SHIFT 1").append('\n');
        stringBuilder.append("STORE ").append(num1MemoryPosition).append('\n');
        stringBuilder.append("LOAD ").append(num2MemoryPosition).append('\n');
        stringBuilder.append("SHIFT 2").append('\n');
        stringBuilder.append("STORE ").append(num2MemoryPosition).append('\n');
        stringBuilder.append("JZERO ").append("?").append('\n');
        stringBuilder.append("JUMP ").append("?").append('\n');
//        stringBuilder.
//        stringBuilder.append("STORE ").append(num2MemoryPosition).append('\n');
return null;


    }
}
