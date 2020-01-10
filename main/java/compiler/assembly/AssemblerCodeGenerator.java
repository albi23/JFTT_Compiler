package compiler.assembly;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Niech P1 zawsze będzie = 1
 * Niech P2 zawsze będzie = -1
 */
public class AssemblerCodeGenerator {

    private String outFileName;
    private List<String> code = new ArrayList<>();
    private int lastFreeCeil = 2;

    public AssemblerCodeGenerator(String outFileName) {
        this.outFileName = outFileName;
//        this.outFileName = "";
    }

    private String appendLine(String toAppend) {
        return toAppend.concat("\n");
    }

    public void beginProgram() { //testing
        code.add(appendLine("SUB 0"));
        code.add(appendLine("DEC"));
        code.add(appendLine("STORE 2"));
        code.add(appendLine("INC"));
        code.add(appendLine("INC"));
        code.add(appendLine("STORE 1"));
        code.addAll(divide(new BigInteger("100"), new BigInteger("-2"), code.size()));
        code.add(appendLine("HALT"));
        this.saveToFile(code);
    }

    public void saveToFile(List<String> code) {
        try (FileWriter fileWriter = new FileWriter("/home/albert/IdeaProjects/JFTT_Complier/src/main/java/tests/outcode/"+this.outFileName)) {
            PrintWriter printWriter = new PrintWriter(fileWriter);
            for (String s : code) {
                printWriter.print(s);
            }
            printWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<String> getValue(BigInteger num, int storeCeil) {
        long numberDeclarations = num.longValueExact();
        boolean signChange = false;
        List<String> innerCode = new ArrayList<>();
        Stack<String> commands = new Stack<>();

        if (num.signum() == 0){
            innerCode.add(appendLine("LOAD 1"));
            innerCode.add(appendLine("DEC"));
            if (storeCeil > 0){
                innerCode.add(appendLine("STORE ".concat(String.valueOf(storeCeil)).concat("  # there is value : "+num)));
            }
            return innerCode;
        }
        if (num.signum() < 0) {
            numberDeclarations = - numberDeclarations;
            signChange = true;
        }
        innerCode.add(appendLine("LOAD 1"));
        while (numberDeclarations > 1) {
            if (numberDeclarations % 2 == 1) {
                commands.push("INC");
            }
            commands.push("SHIFT 1");
            numberDeclarations = numberDeclarations / 2;
        }

        while (commands.size() > 0) innerCode.add(appendLine(commands.pop()));
        if (signChange){
            int ceil = (storeCeil > 0) ? storeCeil : lastFreeCeil+1;
            innerCode.add(appendLine("STORE ".concat(String.valueOf(ceil))));
            innerCode.add(appendLine("LOAD 1"));
            innerCode.add(appendLine("DEC"));
            innerCode.add(appendLine("SUB ".concat(String.valueOf(ceil)).concat("  # there is reversion of number")));
        }
        if (storeCeil > 0) {
            innerCode.add(appendLine("STORE ".concat(String.valueOf(storeCeil)).concat("  # there is value : "+num)));
        }
        return innerCode;
    }

    public List<String> divide(BigInteger num1, BigInteger num2, int lineOffSet){
        List<String> commands = new ArrayList<>();

        int num1MemoryPosition = ++this.lastFreeCeil;
        int num2MemoryPosition = ++this.lastFreeCeil;
        int resultMemoryPosition = ++this.lastFreeCeil;
        int tempKMemoryPosition = ++this.lastFreeCeil;

        commands.addAll(this.getValue(num1, num1MemoryPosition));// A
        commands.addAll(this.getValue(num2, num2MemoryPosition));// B
        // prepare result ceil
        commands.add(appendLine("LOAD 1"));
        commands.add(appendLine("DEC"));
        commands.add(appendLine("STORE ".concat(String.valueOf(resultMemoryPosition)).concat("  #store result")));
        commands.add(appendLine("STORE ".concat(String.valueOf(tempKMemoryPosition)).concat("  #store temp k"))); // result memory postion
        commands.add(appendLine("LOAD ".concat(String.valueOf(num2MemoryPosition)).concat("  # load "+num2)));
        commands.add(appendLine("SUB ".concat(String.valueOf(num1MemoryPosition))));
        int jposJump = commands.size()-1+lineOffSet+11;
        commands.add(appendLine("JPOS ".concat(String.valueOf(jposJump))));
        commands.add(appendLine("LOAD ".concat(String.valueOf(num2MemoryPosition))));
        int beginWhileJump = commands.size()-1+lineOffSet-3;
        commands.add(appendLine("JNEG ".concat(String.valueOf(jposJump))));
        commands.add(appendLine("JZERO ".concat(String.valueOf(jposJump))));
        commands.add(appendLine("SHIFT 1"));
        commands.add(appendLine("STORE ".concat(String.valueOf(num2MemoryPosition))));
        commands.add(appendLine("LOAD ".concat(String.valueOf(tempKMemoryPosition))));
        commands.add(appendLine("INC"));
        commands.add(appendLine("STORE ".concat(String.valueOf(tempKMemoryPosition))));
        commands.add(appendLine("JUMP ".concat(String.valueOf(beginWhileJump))));
        commands.add(appendLine("LOAD ".concat(String.valueOf(tempKMemoryPosition)).concat(" # load k")));
        int endWhileJump = commands.size()-1+lineOffSet+1+22;
        commands.add(appendLine("JNEG ".concat(String.valueOf(endWhileJump))));
        commands.add(appendLine("JZERO ".concat(String.valueOf(endWhileJump))));
        commands.add(appendLine("DEC "));
        commands.add(appendLine("STORE ".concat(String.valueOf(tempKMemoryPosition))));
        commands.add(appendLine("LOAD ".concat(String.valueOf(num2MemoryPosition)).concat(" # load "+num2)));
        commands.add(appendLine("SHIFT 2"));
        commands.add(appendLine("STORE ".concat(String.valueOf(num2MemoryPosition))));
        commands.add(appendLine("LOAD ".concat(String.valueOf(num2MemoryPosition))));
        commands.add(appendLine("SUB ".concat(String.valueOf(num1MemoryPosition)).concat(" # if B <= A")));
        int elseJump = commands.size() -1+lineOffSet+10;
        commands.add(appendLine("JPOS ".concat(String.valueOf(elseJump)).concat("  # stąd ")));
        commands.add(appendLine("LOAD ".concat(String.valueOf(num1MemoryPosition))));
        commands.add(appendLine("SUB ".concat(String.valueOf(num2MemoryPosition))));
        commands.add(appendLine("STORE ".concat(String.valueOf(num1MemoryPosition))));
        commands.add(appendLine("LOAD ".concat(String.valueOf(resultMemoryPosition))));
        commands.add(appendLine("SHIFT 1"));
        commands.add(appendLine("INC"));
        commands.add(appendLine("STORE ".concat(String.valueOf(resultMemoryPosition))));
        commands.add(appendLine("JUMP ".concat(String.valueOf(jposJump))));
        commands.add(appendLine("LOAD ".concat(String.valueOf(resultMemoryPosition)).concat("  # result << tutaj")));
        commands.add(appendLine("SHIFT 1"));
        commands.add(appendLine("STORE ".concat(String.valueOf(resultMemoryPosition))));
        commands.add(appendLine("JUMP ".concat(String.valueOf(jposJump))));
        commands.add(appendLine("LOAD ".concat(String.valueOf(resultMemoryPosition)).concat(" # spr result")));
        commands.add(appendLine("PUT "));

        return commands;
    }
    /**
     * Komórki mi potrzebne które są w uzyciu
     */
    public List<String> multiply(BigInteger num, BigInteger num2, int lineOffset) {

        List<String> innerCommands = new ArrayList<>();
        int num1MemoryPosition = ++this.lastFreeCeil;
        int num2MemoryPosition = ++this.lastFreeCeil;
        int resultInMemory = ++this.lastFreeCeil;

        if (num.compareTo(num2) < 0) {// num powinien być zawsze większy niż num2
            BigInteger tmp = num; // shift
            num = num2;
            num2 = tmp;
        }
        innerCommands.addAll(this.getValue(num, num1MemoryPosition));
        innerCommands.addAll(this.getValue(num2, num2MemoryPosition)); // smaller number is always in num2memor

        innerCommands.add(appendLine("LOAD 1")); // get num = 1
        innerCommands.add(appendLine("DEC")); // set 0
        innerCommands.add(appendLine("STORE ".concat(String.valueOf(resultInMemory).concat(" #save result"))));
        if (num2.signum() < 0) {
            this.changeNumberSign(innerCommands,num2MemoryPosition);
        }
        if (num.signum() < 0) {
            this.changeNumberSign(innerCommands,num1MemoryPosition);
        }
        innerCommands.add(appendLine("LOAD ".concat(String.valueOf(num2MemoryPosition))));
        int jumpPosition = innerCommands.size() - 1 + lineOffset;
        innerCommands.add(appendLine("SHIFT 2"));
        innerCommands.add(appendLine("SHIFT 1"));
        innerCommands.add(appendLine("SUB ".concat(String.valueOf(num2MemoryPosition))));
        int jzeroJumpPosition = innerCommands.size() + lineOffset - 1 + 5; // get current post and jump 4 post in
        innerCommands.add(appendLine("JZERO ".concat(String.valueOf(jzeroJumpPosition))));
        // Ther should be JZERO XX but we have to know which line so remember this postion
        innerCommands.add(appendLine("LOAD ".concat(String.valueOf(resultInMemory))));
        innerCommands.add(appendLine("ADD ".concat(String.valueOf(num1MemoryPosition))));
        innerCommands.add(appendLine("STORE ".concat(String.valueOf(resultInMemory))));
        innerCommands.add(appendLine("LOAD ".concat(String.valueOf(num1MemoryPosition))));
        innerCommands.add(appendLine("SHIFT 1"));
        innerCommands.add(appendLine("STORE ".concat(String.valueOf(num1MemoryPosition)))); // # ZAPISZ B
        innerCommands.add(appendLine("LOAD ".concat(String.valueOf(num2MemoryPosition))));
        innerCommands.add(appendLine("SHIFT 2"));
        innerCommands.add(appendLine("STORE ".concat(String.valueOf(num2MemoryPosition))));//# ZAPISZ NOWE A
        innerCommands.add(appendLine("JZERO ".concat(String.valueOf((innerCommands.size() - 1) + 3 + lineOffset))));
        innerCommands.add(appendLine("JUMP ".concat(String.valueOf(jumpPosition))));///
        if (!(num2.signum() == num.signum())){
            changeNumberSign(innerCommands,resultInMemory);
        }
        innerCommands.add(appendLine("LOAD ".concat(String.valueOf(resultInMemory))));
        innerCommands.add(appendLine("PUT "));
        return innerCommands;
    }

    private void changeNumberSign(List<String> commands, int ceilPost){
        commands.add(appendLine("LOAD 1")); // get num = 1
        commands.add(appendLine("DEC")); // SET 0
        commands.add(appendLine("SUB ".concat(String.valueOf(ceilPost))));
        commands.add(appendLine("STORE ".concat(String.valueOf(ceilPost))));
    }
}
