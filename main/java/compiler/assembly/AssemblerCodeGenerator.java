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
    private static int lastFreeCeil = 11;
    private static final int ONE_VALUE = ++lastFreeCeil;
    private static final int MINUS_ONE_VALUE = ++lastFreeCeil;

    public AssemblerCodeGenerator(String outFileName) {
        this.outFileName = outFileName;
//        this.beginProgram();
//        this.outFileName = "myModTest.sh";
    }

    public  int getLastFreeCeil(){
        return lastFreeCeil;
    }

    private String appendLine(String toAppend) {
        return toAppend.concat("\n");
    }

    public void beginProgram() { //testing
        code.add(appendLine("SUB 0"));
        code.add(appendLine("DEC"));
        code.add(appendLine("STORE ".concat(String.valueOf(MINUS_ONE_VALUE))));
        code.add(appendLine("INC"));
        code.add(appendLine("INC"));
        code.add(appendLine("STORE ".concat(String.valueOf(ONE_VALUE))));
        code.addAll(mod(new BigInteger("-17"), new BigInteger("4"), code.size()));
        this.showLastStatement(code,false,0);
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
            innerCode.add(appendLine("LOAD ".concat(String.valueOf(ONE_VALUE))));
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
        innerCode.add(appendLine("LOAD ".concat(String.valueOf(ONE_VALUE))));
        while (numberDeclarations > 1) {
            if (numberDeclarations % 2 == 1) {
                commands.push("INC");
            }
            commands.push("SHIFT ".concat(String.valueOf(ONE_VALUE)));
            numberDeclarations = numberDeclarations / 2;
        }

        while (commands.size() > 0) innerCode.add(appendLine(commands.pop()));
        if (signChange){
            int ceil = (storeCeil > 0) ? storeCeil : lastFreeCeil+1;
            innerCode.add(appendLine("STORE ".concat(String.valueOf(ceil))));
            innerCode.add(appendLine("LOAD ".concat(String.valueOf(ONE_VALUE))));
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

        int num1MemoryPosition = ++lastFreeCeil;
        int num2MemoryPosition = ++lastFreeCeil;
        int resultMemoryPosition = ++lastFreeCeil;
        int tempKMemoryPosition = ++lastFreeCeil;

        commands.addAll(this.getValue(num1, num1MemoryPosition));// A
        commands.addAll(this.getValue(num2, num2MemoryPosition));// B
        // prepare result ceil
        commands.add(appendLine("LOAD ".concat(String.valueOf(ONE_VALUE))));
        commands.add(appendLine("DEC"));
        commands.add(appendLine("STORE ".concat(String.valueOf(resultMemoryPosition)).concat("  #store result")));
        commands.add(appendLine("STORE ".concat(String.valueOf(tempKMemoryPosition)).concat("  #store temp k"))); // result memory postion

        this.changeIfNegativeNumber(commands, num1, num1MemoryPosition);
        this.changeIfNegativeNumber(commands, num2, num2MemoryPosition);

        commands.add(appendLine("LOAD ".concat(String.valueOf(num2MemoryPosition)).concat("  # load " + num2)));
        commands.add(appendLine("SUB ".concat(String.valueOf(num1MemoryPosition))));
        int jposJump = commands.size() - 1 + lineOffSet + 11;
        commands.add(appendLine("JPOS ".concat(String.valueOf(jposJump))));
        commands.add(appendLine("LOAD ".concat(String.valueOf(num2MemoryPosition))));
        int beginWhileJump = commands.size() - 1 + lineOffSet - 3;
        commands.add(appendLine("JNEG ".concat(String.valueOf(jposJump))));
        commands.add(appendLine("JZERO ".concat(String.valueOf(jposJump))));
        commands.add(appendLine("SHIFT ".concat(String.valueOf(ONE_VALUE))));
        commands.add(appendLine("STORE ".concat(String.valueOf(num2MemoryPosition))));
        commands.add(appendLine("LOAD ".concat(String.valueOf(tempKMemoryPosition))));
        commands.add(appendLine("INC"));
        commands.add(appendLine("STORE ".concat(String.valueOf(tempKMemoryPosition))));
        commands.add(appendLine("JUMP ".concat(String.valueOf(beginWhileJump))));
        commands.add(appendLine("LOAD ".concat(String.valueOf(tempKMemoryPosition)).concat(" # load k")));
        int endWhileJump = commands.size() - 1 + lineOffSet + 1 + 22;
        commands.add(appendLine("JNEG ".concat(String.valueOf(endWhileJump))));
        commands.add(appendLine("JZERO ".concat(String.valueOf(endWhileJump))));
        commands.add(appendLine("DEC "));
        commands.add(appendLine("STORE ".concat(String.valueOf(tempKMemoryPosition))));
        commands.add(appendLine("LOAD ".concat(String.valueOf(num2MemoryPosition)).concat(" # load " + num2)));
        commands.add(appendLine("SHIFT ".concat(String.valueOf(MINUS_ONE_VALUE))));
        commands.add(appendLine("STORE ".concat(String.valueOf(num2MemoryPosition))));
        commands.add(appendLine("LOAD ".concat(String.valueOf(num2MemoryPosition))));
        commands.add(appendLine("SUB ".concat(String.valueOf(num1MemoryPosition)).concat(" # if B <= A")));
        int elseJump = commands.size() - 1 + lineOffSet + 10;
        commands.add(appendLine("JPOS ".concat(String.valueOf(elseJump)).concat("  # stąd ")));
        commands.add(appendLine("LOAD ".concat(String.valueOf(num1MemoryPosition))));
        commands.add(appendLine("SUB ".concat(String.valueOf(num2MemoryPosition))));
        commands.add(appendLine("STORE ".concat(String.valueOf(num1MemoryPosition))));
        commands.add(appendLine("LOAD ".concat(String.valueOf(resultMemoryPosition))));
        commands.add(appendLine("SHIFT ".concat(String.valueOf(ONE_VALUE))));
        commands.add(appendLine("INC"));
        commands.add(appendLine("STORE ".concat(String.valueOf(resultMemoryPosition))));
        commands.add(appendLine("JUMP ".concat(String.valueOf(jposJump))));
        commands.add(appendLine("LOAD ".concat(String.valueOf(resultMemoryPosition)).concat("  # result << tutaj")));
        commands.add(appendLine("SHIFT ".concat(String.valueOf(ONE_VALUE))));
        commands.add(appendLine("STORE ".concat(String.valueOf(resultMemoryPosition))));
        commands.add(appendLine("JUMP ".concat(String.valueOf(jposJump))));

        this.changeIfNegativeNumber(commands, num1, num1MemoryPosition);
        this.changeIfNegativeNumber(commands, num2, num2MemoryPosition);
        if (!(num1.signum() == num2.signum() || num2.abs().compareTo(num1.abs()) > 0)) { // tutaj można wywali dla 2 wersji (|| num2.abs().compareTo(num1.abs()) > 0)
            this.changeNumberSign(commands, resultMemoryPosition);
        }
        commands.add(appendLine("LOAD ".concat(String.valueOf(resultMemoryPosition)).concat(" # spr result")));
        commands.add(appendLine("PUT "));

        --lastFreeCeil; // free k temp ceil
        return commands;
    }
    /**
     * Komórki mi potrzebne które są w uzyciu
     */
    public List<String> multiply(BigInteger num, BigInteger num2, int lineOffset) {

        List<String> innerCommands = new ArrayList<>();
        int num1MemoryPosition = ++lastFreeCeil;
        int num2MemoryPosition = ++lastFreeCeil;
        int resultInMemory = ++lastFreeCeil;

        if (num.compareTo(num2) < 0) {// num powinien być zawsze większy niż num2
            BigInteger tmp = num; // shift
            num = num2;
            num2 = tmp;
        }
        innerCommands.addAll(this.getValue(num, num1MemoryPosition));
        innerCommands.addAll(this.getValue(num2, num2MemoryPosition)); // smaller number is always in num2memor

        innerCommands.add(appendLine("LOAD ".concat(String.valueOf(ONE_VALUE)))); // get num = 1
        innerCommands.add(appendLine("DEC")); // set 0
        innerCommands.add(appendLine("STORE ".concat(String.valueOf(resultInMemory).concat(" #save result"))));
        this.changeIfNegativeNumber(innerCommands,num2,num2MemoryPosition);
        this.changeIfNegativeNumber(innerCommands,num,num1MemoryPosition);

        innerCommands.add(appendLine("LOAD ".concat(String.valueOf(num2MemoryPosition))));
        int jumpPosition = innerCommands.size() - 1 + lineOffset;
        innerCommands.add(appendLine("SHIFT ".concat(String.valueOf(MINUS_ONE_VALUE))));
        innerCommands.add(appendLine("SHIFT ".concat(String.valueOf(ONE_VALUE))));
        innerCommands.add(appendLine("SUB ".concat(String.valueOf(num2MemoryPosition))));
        int jzeroJumpPosition = innerCommands.size() + lineOffset - 1 + 5; // get current post and jump 4 post in
        innerCommands.add(appendLine("JZERO ".concat(String.valueOf(jzeroJumpPosition))));
        // Ther should be JZERO XX but we have to know which line so remember this postion
        innerCommands.add(appendLine("LOAD ".concat(String.valueOf(resultInMemory))));
        innerCommands.add(appendLine("ADD ".concat(String.valueOf(num1MemoryPosition))));
        innerCommands.add(appendLine("STORE ".concat(String.valueOf(resultInMemory))));
        innerCommands.add(appendLine("LOAD ".concat(String.valueOf(num1MemoryPosition))));
        innerCommands.add(appendLine("SHIFT ".concat(String.valueOf(ONE_VALUE))));
        innerCommands.add(appendLine("STORE ".concat(String.valueOf(num1MemoryPosition)))); // # ZAPISZ B
        innerCommands.add(appendLine("LOAD ".concat(String.valueOf(num2MemoryPosition))));
        innerCommands.add(appendLine("SHIFT ".concat(String.valueOf(MINUS_ONE_VALUE))));
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

    public List<String> mod(BigInteger num, BigInteger num2, int lineOffset){ // a mod b

        List<String> modCommands = new ArrayList<>();

        int resultInMemory = ++lastFreeCeil;
        int num1MemoryPosition = ++lastFreeCeil;
        int num2MemoryPosition = ++lastFreeCeil;
        int num2CopyMemoryPosition = ++lastFreeCeil;

        modCommands.addAll(this.getValue(num, num1MemoryPosition));
        modCommands.addAll(this.getValue(num2, num2MemoryPosition)); // smaller number is always in num2memor

        modCommands.add(appendLine("LOAD ".concat(String.valueOf(ONE_VALUE)))); // get num = 1
        modCommands.add(appendLine("DEC")); // set 0
        modCommands.add(appendLine("STORE ".concat(String.valueOf(resultInMemory).concat(" #save result"))));

        if (num.signum() > 0 && num2.signum() < 0) {
            return modPositiveOnNegative(modCommands, resultInMemory, num1MemoryPosition, num2CopyMemoryPosition, num2MemoryPosition, lineOffset,"JNEG");
        }
        if (num.signum() < 0 && num2.signum() > 0) {
            return modPositiveOnNegative(modCommands, resultInMemory, num1MemoryPosition, num2CopyMemoryPosition, num2MemoryPosition, lineOffset,"JPOS");
        }
        this.changeIfNegativeNumber(modCommands,num,num1MemoryPosition);
        this.changeIfNegativeNumber(modCommands,num2,num2MemoryPosition);

        modCommands.add(appendLine("LOAD ".concat(String.valueOf(num2MemoryPosition).concat(" # B"))));
        modCommands.add(appendLine("STORE ".concat(String.valueOf(num2CopyMemoryPosition).concat(" # B'"))));
        int modBy0Jump = modCommands.size()-1+lineOffset+1+17;
        modCommands.add(appendLine("JZERO  ".concat(String.valueOf(modBy0Jump))));
        int beginWhile = modCommands.size()-1+lineOffset+1;
        modCommands.add(appendLine("LOAD ".concat(String.valueOf(num2MemoryPosition))));
        modCommands.add(appendLine("ADD ".concat(String.valueOf(num2CopyMemoryPosition))));
        modCommands.add(appendLine("SUB ".concat(String.valueOf(num1MemoryPosition))));
        int endWhileJump = modCommands.size()-1+lineOffset+1+5;
        modCommands.add(appendLine("JPOS  ".concat(String.valueOf(endWhileJump))));
        modCommands.add(appendLine("LOAD ".concat(String.valueOf(num2MemoryPosition))));
        modCommands.add(appendLine("ADD ".concat(String.valueOf(num2CopyMemoryPosition))));
        modCommands.add(appendLine("STORE ".concat(String.valueOf(num2MemoryPosition))));
        modCommands.add(appendLine("JUMP  ".concat(String.valueOf(beginWhile).concat(" # to begin while"))));
        modCommands.add(appendLine("LOAD  ".concat(String.valueOf(num2CopyMemoryPosition).concat(" # is clone of -> b' > a"))));
        modCommands.add(appendLine("SUB  ".concat(String.valueOf(num1MemoryPosition))));
        int endIfJump = modCommands.size()-1+lineOffset+1+4;
        modCommands.add(appendLine("JNEG  ".concat(String.valueOf(endIfJump))));
        modCommands.add(appendLine("LOAD ".concat(String.valueOf(ONE_VALUE))));
        modCommands.add(appendLine("DEC"));
        modCommands.add(appendLine("STORE ".concat(String.valueOf(num2MemoryPosition))));
        modCommands.add(appendLine("LOAD ".concat(String.valueOf(num1MemoryPosition))));
        modCommands.add(appendLine("SUB ".concat(String.valueOf(num2MemoryPosition))));
        modCommands.add(appendLine("STORE ".concat(String.valueOf(resultInMemory))));

        this.changeIfNegativeNumber(modCommands,num,num1MemoryPosition);
        this.changeIfNegativeNumber(modCommands,num2,num2MemoryPosition);
        this.changeIfNegativeNumber(modCommands,num2,resultInMemory); // result must have the same sign what divider
        --lastFreeCeil;
//        modCommands.add(appendLine("LOAD ".concat(String.valueOf(resultInMemory)).concat(" # spr result")));
//        modCommands.add(appendLine("PUT"));

        return modCommands;
    }

    private List<String> modPositiveOnNegative(List<String> code, int resultInMemory,int num1Memory, int num1Copy,int num2Memory, int lineOffset, String jumCmd){

        code.add(appendLine("LOAD ".concat(String.valueOf(num1Memory))));
        code.add(appendLine("STORE ".concat(String.valueOf(num1Copy))));
        code.add(appendLine("LOAD ".concat(String.valueOf(num2Memory))));
        int endWhileJump = code.size() -1+1+lineOffset+7;
        code.add(appendLine("JZERO ".concat(String.valueOf(endWhileJump))));
        code.add(appendLine("LOAD ".concat(String.valueOf(num1Copy))));
        code.add(appendLine(jumCmd.concat(String.valueOf(endWhileJump))));
        code.add(appendLine("LOAD ".concat(String.valueOf(num1Copy))));
        code.add(appendLine("ADD ".concat(String.valueOf(num2Memory))));
        code.add(appendLine("STORE ".concat(String.valueOf(num1Copy))));
        int beginWhileJump = endWhileJump -7;
        code.add(appendLine("JUMP ".concat(String.valueOf(beginWhileJump))));
        code.add(appendLine("STORE ".concat(String.valueOf(resultInMemory))));

        return code;
    }

    private void showLastStatement(List<String> code, boolean loadCeilFromMemory, int loadCeilMemory){
        if (loadCeilFromMemory){
            code.add(appendLine("LOAD ".concat(String.valueOf(loadCeilMemory))));
        }
        code.add(appendLine("PUT"));
        code.add(appendLine("HALT"));
    }

    private void changeIfNegativeNumber(List<String> commands,BigInteger numToCheck, int ceilPost){
        if (numToCheck.signum() < 0) {
            this.changeNumberSign(commands, ceilPost);
        }
    }

    private void changeNumberSign(List<String> commands, int ceilPost){
        commands.add(appendLine("LOAD ".concat(String.valueOf(ONE_VALUE)).concat(" # start reversion"))); // get num = 1
        commands.add(appendLine("DEC")); // SET 0
        commands.add(appendLine("SUB ".concat(String.valueOf(ceilPost))));
        commands.add(appendLine("STORE ".concat(String.valueOf(ceilPost)).concat(" # end reversion")));
    }
}
