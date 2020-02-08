public class AssemblerCodeGenerator extends Constants {

    private final int memorySize = 999999;
    private CellHolder[] innerMemory = setTab();
    private CommandHolder[] generatedCode = setTab2();
    private Compiler.Lexer lexer;

    private int generatedCodeOffset = 0;
    private long codeDataOffset = 0;
    private long next_address = MEMORY_START;

    public AssemblerCodeGenerator(Compiler.Lexer lexer) {
        this.lexer = lexer;
    }

    private CellHolder[] setTab() {

        CellHolder[] memory = new CellHolder[memorySize];
        for (int i = 0; i < memorySize; i++) memory[i] = new CellHolder();
        return memory;
    }

    private CommandHolder[] setTab2() {

        CommandHolder[] output = new CommandHolder[memorySize];
        for (int i = 0; i < memorySize; i++) output[i] = new CommandHolder();

        return output;
    }


    public CellHolder[] getInnerMemory() {
        return innerMemory;
    }

    public CommandHolder[] getGeneratedCode() {
        return generatedCode;
    }

    public int getGeneratedCodeOffset() {
        return generatedCodeOffset;
    }

    public long getCodeDataOffset() {
        return codeDataOffset;
    }

    public void decrementData_offset() {
        this.codeDataOffset--;
    }

    public long getNext_address() {
        return next_address;
    }

    public long generateCode(Commands command) {
        generatedCode[generatedCodeOffset].setOperator(command.toString());
        generatedCode[generatedCodeOffset].setCodeLine(-1);
        return generatedCodeOffset++;
    }

    public long generateCode(Commands command, long arg) {
        generatedCode[generatedCodeOffset].setOperator(command.toString());
        generatedCode[generatedCodeOffset].setCodeLine(arg);
        return generatedCodeOffset++;
    }

    public void stackPop() {
        generateCode(Commands.LOAD, TOP_STACK);
        generateCode(Commands.LOAD, TOP_STACK);
        generateCode(Commands.DEC);
        generateCode(Commands.STORE, TOP_STACK);
    }

    public void loadToP0(long number) {
        generateCode(Commands.SUB, ARITHMETIC);
        if (number == 0) {
            return;
        }
        generateCode(Commands.INC);
        byte neg = 0;

        if (number < 0) {
            neg = 1;
            number = -number;
        }

        byte[] opers = new byte[9999];
        int oper_number = 0;
        while (number > 1) {
            if (number % 2 == 0) {
                opers[oper_number++] = 1;
                number /= 2;
            } else {
                opers[oper_number++] = 0;
                number--;
            }
        }
        for (int i = oper_number - 1; i >= 0; i--) {
            if (opers[i] == 0) {
                generateCode(Commands.INC);
            } else generateCode(Commands.SHIFT, ONE);
        }
        if (neg > 0) {
            generateCode(Commands.STORE, BASE);
            generateCode(Commands.STORE, BASE);
            generateCode(Commands.SUB, ARITHMETIC);
            generateCode(Commands.SUB, BASE);
        }
    }

    public void addNewNumber(long number) {
        generateCode(Commands.LOAD, TOP_STACK);
        generateCode(Commands.LOAD, TOP_STACK);
        generateCode(Commands.INC);
        generateCode(Commands.STORE, TOP_STACK);
        loadToP0(number);
        generateCode(Commands.STOREI, TOP_STACK);
    }

    public long tryToGetvar(String var_name) {
        for (int i = 0; i < codeDataOffset; i++) {
            if (var_name.equals(innerMemory[i].getName())) return innerMemory[i].getAddress();
        }
        lexer.yyerror("Variable not found");
        return -1;
    }

    public long tryToGetvar(String var_name, long index) {
        CellHolder cellHolder = null;
        boolean x = false;
        for (int i = 0; i < codeDataOffset; i++) {
            if ((var_name.equals(innerMemory[i].getName()))) {
                cellHolder = innerMemory[i];
                x = true;
                break;
            }
        }
        if (!x) {
            lexer.yyerror("variable not found");
            return -1;
        }
        if (!cellHolder.isTab()) {
            lexer.yyerror("variable cant be accessed as array");
        }
        if (cellHolder.getMax() < index || cellHolder.getMin() > index) {
            lexer.yyerror("array index ouf of range");
        }
        return cellHolder.getAddress() + index - cellHolder.getMin();

    }

    public void assignValue(long var_addr) {
        for (int i = 0; i < codeDataOffset; i++) {
            if (var_addr == innerMemory[i].getAddress()) {
                if (innerMemory[i].isIterator()) {
                    lexer.yyerror("attempt to change loop iterator");
                } else {
                    innerMemory[i].setInit(true);
                    break;
                }
            }

        }
        generateCode(Commands.LOADI, TOP_STACK);
        generateCode(Commands.STORE, var_addr);
        stackPop();
    }

    public void setup() {
        generateCode(Commands.SUB, ARITHMETIC);
        generateCode(Commands.INC);
        generateCode(Commands.STORE, ONE);
        loadToP0(LINK_STACK);
        generateCode(Commands.STORE, TOP_STACK);
        generateCode(Commands.SUB, ARITHMETIC);
        generateCode(Commands.DEC);
        generateCode(Commands.STORE, MINUS_ONE);
        generateCode(Commands.STORE, MINUS_ONE);

    }

    public void addNewIdValue(long addres, int yylineno) {

        for (int i = 0; i < codeDataOffset; i++) {
            if (addres == innerMemory[i].getAddress()) {
                innerMemory[i].setUsed(yylineno);
            }
        }
        generateCode(Commands.LOAD, TOP_STACK);
        generateCode(Commands.INC);
        generateCode(Commands.STORE, TOP_STACK);
        generateCode(Commands.LOAD, addres);
        generateCode(Commands.STOREI, TOP_STACK);
    }

    public void neq(JumpHolder jumper) {
        generateCode(Commands.LOADI, TOP_STACK);
        generateCode(Commands.STORE, COUNTER);
        stackPop();
        generateCode(Commands.LOADI, TOP_STACK);
        generateCode(Commands.STORE, BASE);
        stackPop();
        generateCode(Commands.LOAD, COUNTER);
        generateCode(Commands.SUB, BASE);
        int jumFalse = (int) generateCode(Commands.JZERO, -1);
        int jumpTrue = (int) generateCode(Commands.JUMP, 0);
        generatedCode[jumpTrue].setCodeLine(jumpTrue + 1);
        jumper.setJumpIfTrue(jumpTrue + 1);
        jumper.setJumpIfFalse(jumFalse);
    }

    public void le(JumpHolder jumper) {
        generateCode(Commands.LOADI, TOP_STACK);
        generateCode(Commands.STORE, COUNTER);
        stackPop();
        generateCode(Commands.LOADI, TOP_STACK);
        generateCode(Commands.STORE, BASE);
        stackPop();
        generateCode(Commands.LOAD, BASE);
        generateCode(Commands.INC);
        generateCode(Commands.SUB, COUNTER);
        int jumFalse = (int) generateCode(Commands.JPOS, -1);
        int jumpTrue = (int) generateCode(Commands.JUMP, 0);
        generatedCode[jumpTrue].setCodeLine(jumpTrue + 1);
        jumper.setJumpIfTrue(jumpTrue + 1);
        jumper.setJumpIfFalse(jumFalse);
    }

    public void write() {
        generateCode(Commands.LOADI, TOP_STACK);
        generateCode(Commands.PUT);
        stackPop();
    }

    public void read(long value) {
        generateCode(Commands.GET);
        generateCode(Commands.STORE, value);
        for (int i = 0; i < codeDataOffset; i++) {
            if (innerMemory[i].getAddress() == value) {
                innerMemory[i].setInit(true);
                break;
            }
        }
    }

    public void plus() {
        generateCode(Commands.LOADI, TOP_STACK);
        generateCode(Commands.LOADI, TOP_STACK);
        generateCode(Commands.STORE, BASE);
        stackPop();
        generateCode(Commands.LOADI, TOP_STACK);
        generateCode(Commands.ADD, BASE);
        generateCode(Commands.STOREI, TOP_STACK);
    }

    public void minus() {
        generateCode(Commands.LOADI, TOP_STACK);
        generateCode(Commands.STORE, BASE);
        stackPop();
        generateCode(Commands.LOADI, TOP_STACK);
        generateCode(Commands.SUB, BASE);
        generateCode(Commands.STOREI, TOP_STACK);
    }

    public void times() {
        generateCode(Commands.SUB, ARITHMETIC);
        generateCode(Commands.STORE, INDEX_SOURCE);
        generateCode(Commands.STORE, INDEX_TARGET);
        generateCode(Commands.STORE, OUT_IN_NUM);
        generateCode(Commands.LOADI, TOP_STACK);
        generateCode(Commands.STORE, BASE);
        int jmp_pos = (int) generateCode(Commands.JPOS, -1);
        generateCode(Commands.SUB, ARITHMETIC);
        generateCode(Commands.INC);
        generateCode(Commands.STORE, INDEX_TARGET);
        generateCode(Commands.SUB, ARITHMETIC);
        generateCode(Commands.SUB, BASE);
        generatedCode[jmp_pos].setCodeLine(1 + generateCode(Commands.STORE, BASE));
        stackPop();
        generateCode(Commands.LOADI, TOP_STACK);
        generateCode(Commands.STORE, COUNTER);
        int again = (int) generateCode(Commands.LOAD, OUT_IN_NUM);
        generateCode(Commands.INC);
        generateCode(Commands.STORE, OUT_IN_NUM);
        generateCode(Commands.SUB, ARITHMETIC);
        generateCode(Commands.INC);
        generateCode(Commands.SHIFT, OUT_IN_NUM);
        generateCode(Commands.STORE, IN_OUT);
        generateCode(Commands.SUB, BASE);
        generateCode(Commands.JNEG, again);
        long loop = generateCode(Commands.LOAD, BASE);
        generateCode(Commands.SUB, IN_OUT);
        int jumpneg = (int) generateCode(Commands.JNEG, -1);
        generateCode(Commands.LOAD, COUNTER);
        generateCode(Commands.SHIFT, OUT_IN_NUM);
        generateCode(Commands.ADD, INDEX_SOURCE);
        generateCode(Commands.STORE, INDEX_SOURCE);
        generateCode(Commands.LOAD, BASE);
        generateCode(Commands.SUB, IN_OUT);
        generateCode(Commands.STORE, BASE);
        int neg_target = (int) generateCode(Commands.LOAD, IN_OUT);
        generatedCode[jumpneg].setCodeLine(neg_target);
        generateCode(Commands.SHIFT, MINUS_ONE);
        generateCode(Commands.STORE, IN_OUT);
        generateCode(Commands.LOAD, OUT_IN_NUM);
        generateCode(Commands.DEC);
        generateCode(Commands.STORE, OUT_IN_NUM);
        generateCode(Commands.LOAD, BASE);
        int jump_end = (int) generateCode(Commands.JZERO, -1);
        generateCode(Commands.JUMP, loop);
        long end_target = generateCode(Commands.LOAD, TOP_STACK);
        generatedCode[jump_end].setCodeLine(end_target);
        generateCode(Commands.LOAD, INDEX_TARGET);
        int no_minus = (int) generateCode(Commands.JZERO, -1);
        generateCode(Commands.SUB, ARITHMETIC);
        generateCode(Commands.SUB, INDEX_SOURCE);
        int finish = (int) generateCode(Commands.JUMP, -1);
        generatedCode[no_minus].setCodeLine(generateCode(Commands.LOAD, INDEX_SOURCE));
        generatedCode[finish].setCodeLine(generateCode(Commands.STOREI, TOP_STACK));
    }

    public void div() {
        generateCode(Commands.SUB, ARITHMETIC);
        generateCode(Commands.STORE, INDEX_SOURCE);
        generateCode(Commands.STORE, INDEX_TARGET);
        generateCode(Commands.STORE, OUT_IN_NUM);
        generateCode(Commands.LOADI, TOP_STACK);
        generateCode(Commands.STORE, BASE);
        stackPop();
        generateCode(Commands.LOAD, BASE);
        int jmp_zero_2 = (int) generateCode(Commands.JZERO, -1);
        int jmp_non_zero_2 = (int) generateCode(Commands.JUMP, -1);
        generatedCode[jmp_non_zero_2].setCodeLine((int) generateCode(Commands.JPOS, -1));
        int jmp_neg1 = (int) (generatedCode[jmp_non_zero_2].getCodeLine());
        generateCode(Commands.SUB, ARITHMETIC);
        generateCode(Commands.INC);
        generateCode(Commands.STORE, INDEX_TARGET);
        generateCode(Commands.SUB, ARITHMETIC);
        generateCode(Commands.SUB, BASE);
        generateCode(Commands.STORE, BASE);
        generatedCode[jmp_neg1].setCodeLine(generateCode(Commands.LOADI, TOP_STACK));
        generateCode(Commands.STORE, COUNTER);
        int jmp_neg2 = (int) generateCode(Commands.JPOS, -1);
        generateCode(Commands.SUB, ARITHMETIC);
        generateCode(Commands.SUB, COUNTER);
        generateCode(Commands.STORE, COUNTER);
        generateCode(Commands.LOAD, INDEX_TARGET);
        generateCode(Commands.DEC);
        generateCode(Commands.STORE, INDEX_TARGET);
        generatedCode[jmp_neg2].setCodeLine(generateCode(Commands.SUB, ARITHMETIC));
        int m = (int) generateCode(Commands.LOAD, OUT_IN_NUM);
        generateCode(Commands.INC);
        generateCode(Commands.STORE, OUT_IN_NUM);
        generateCode(Commands.SUB, ARITHMETIC);
        generateCode(Commands.ADD, BASE);
        generateCode(Commands.SHIFT, OUT_IN_NUM);
        generateCode(Commands.STORE, IN_OUT);
        generateCode(Commands.SUB, COUNTER);
        generateCode(Commands.JNEG, m);
        generateCode(Commands.LOAD, IN_OUT);
        generateCode(Commands.LOAD, OUT_IN_NUM);
        int again = (int) generateCode(Commands.LOAD, COUNTER);
        generateCode(Commands.SUB, IN_OUT);
        int jmp_lower = (int) generateCode(Commands.JNEG, -1);
        generateCode(Commands.SUB, ARITHMETIC);
        generateCode(Commands.INC);
        generateCode(Commands.SHIFT, OUT_IN_NUM);
        generateCode(Commands.ADD, INDEX_SOURCE);
        generateCode(Commands.STORE, INDEX_SOURCE);
        generateCode(Commands.LOAD, COUNTER);
        generateCode(Commands.SUB, IN_OUT);
        generateCode(Commands.STORE, COUNTER);
        generatedCode[jmp_lower].setCodeLine(generateCode(Commands.LOAD, IN_OUT));
        generateCode(Commands.SHIFT, MINUS_ONE);
        generateCode(Commands.STORE, IN_OUT);
        generateCode(Commands.LOAD, OUT_IN_NUM);
        generateCode(Commands.DEC);
        generateCode(Commands.STORE, OUT_IN_NUM);
        generateCode(Commands.INC);
        int jmp_koniec = (int) generateCode(Commands.JZERO, -1);
        generateCode(Commands.JUMP, again);
        generatedCode[jmp_koniec].setCodeLine(generateCode(Commands.LOAD, INDEX_TARGET));
        int jmp_pos_out = (int) generateCode(Commands.JZERO, -1);
        generateCode(Commands.SUB, ARITHMETIC);
        generateCode(Commands.SUB, INDEX_SOURCE);
        generateCode(Commands.STORE, INDEX_SOURCE);
        generateCode(Commands.LOAD, COUNTER);
        int zero = (int) generateCode(Commands.JZERO, -1);
        generateCode(Commands.LOAD, INDEX_SOURCE);
        generateCode(Commands.DEC);
        generateCode(Commands.STORE, INDEX_SOURCE);
        long codeLine = generateCode(Commands.LOAD, INDEX_SOURCE);
        generatedCode[zero].setCodeLine(codeLine);
        generatedCode[jmp_zero_2].setCodeLine(codeLine);
        generatedCode[jmp_pos_out].setCodeLine(codeLine);
        generateCode(Commands.STOREI, TOP_STACK);
    }

    public void modulo() {
        generateCode(Commands.SUB, ARITHMETIC);
        generateCode(Commands.STORE, INDEX_SOURCE);
        generateCode(Commands.STORE, INDEX_TARGET);
        generateCode(Commands.STORE, OUT_IN_NUM);
        generateCode(Commands.STORE, COUNTER);
        generateCode(Commands.STORE, EVEREX);
        generateCode(Commands.LOADI, TOP_STACK);
        generateCode(Commands.STORE, BASE);
        stackPop();
        generateCode(Commands.LOAD, BASE);
        int jmp_zero_2 = (int) generateCode(Commands.JZERO, -1);
        int jmp_non_zero_2 = (int) generateCode(Commands.JUMP, -1);
        generatedCode[jmp_non_zero_2].setCodeLine(generateCode(Commands.JPOS, -1));
        int jmp_neg1 = (int) (generatedCode[jmp_non_zero_2].getCodeLine());
        generateCode(Commands.SUB, ARITHMETIC);
        generateCode(Commands.INC);
        generateCode(Commands.STORE, INDEX_TARGET);
        generateCode(Commands.SUB, ARITHMETIC);
        generateCode(Commands.SUB, BASE);
        generateCode(Commands.STORE, BASE);
        generatedCode[jmp_neg1].setCodeLine(generateCode(Commands.LOADI, TOP_STACK));
        generateCode(Commands.STORE, COUNTER);
        int jmp_neg2 = (int) generateCode(Commands.JPOS, -1);
        generateCode(Commands.SUB, ARITHMETIC);
        generateCode(Commands.SUB, COUNTER);
        generateCode(Commands.STORE, COUNTER);
        generateCode(Commands.LOAD, INDEX_TARGET);
        generateCode(Commands.DEC);
        generateCode(Commands.STORE, INDEX_TARGET);
        generateCode(Commands.LOAD, EVEREX);
        generateCode(Commands.INC);
        generateCode(Commands.STORE, EVEREX);
        generatedCode[jmp_neg2].setCodeLine(generateCode(Commands.SUB, ARITHMETIC));
        int m = (int) generateCode(Commands.LOAD, OUT_IN_NUM);
        generateCode(Commands.INC);
        generateCode(Commands.STORE, OUT_IN_NUM);
        generateCode(Commands.SUB, ARITHMETIC);
        generateCode(Commands.ADD, BASE);
        generateCode(Commands.SHIFT, OUT_IN_NUM);
        generateCode(Commands.STORE, IN_OUT);
        generateCode(Commands.SUB, COUNTER);
        generateCode(Commands.JNEG, m);
        generateCode(Commands.LOAD, IN_OUT);
        generateCode(Commands.LOAD, OUT_IN_NUM);
        int again = (int) generateCode(Commands.LOAD, COUNTER);
        generateCode(Commands.SUB, IN_OUT);
        int jumpLower = (int) generateCode(Commands.JNEG, -1);
        generateCode(Commands.SUB, ARITHMETIC);
        generateCode(Commands.INC);
        generateCode(Commands.SHIFT, OUT_IN_NUM);
        generateCode(Commands.ADD, INDEX_SOURCE);
        generateCode(Commands.STORE, INDEX_SOURCE);
        generateCode(Commands.LOAD, COUNTER);
        generateCode(Commands.SUB, IN_OUT);
        generateCode(Commands.STORE, COUNTER);
        generatedCode[jumpLower].setCodeLine(generateCode(Commands.LOAD, IN_OUT));
        generateCode(Commands.SHIFT, MINUS_ONE);
        generateCode(Commands.STORE, IN_OUT);
        generateCode(Commands.LOAD, OUT_IN_NUM);
        generateCode(Commands.DEC);
        generateCode(Commands.STORE, OUT_IN_NUM);
        generateCode(Commands.INC);
        int jumpEnd = (int) generateCode(Commands.JZERO, -1);
        generateCode(Commands.JUMP, again);
        generatedCode[jumpEnd].setCodeLine(generateCode(Commands.LOAD, INDEX_TARGET));
        int jmp_pos_out = (int) generateCode(Commands.JZERO, -1);
        generateCode(Commands.SUB, ARITHMETIC);
        generateCode(Commands.ADD, COUNTER);
        int zero = (int) generateCode(Commands.JZERO, -1);
        generateCode(Commands.SUB, BASE);
        generatedCode[zero].setCodeLine(generateCode(Commands.STORE, COUNTER));
        generatedCode[jmp_zero_2].setCodeLine(generatedCodeOffset);
        generatedCode[jmp_pos_out].setCodeLine(generatedCodeOffset);
        generateCode(Commands.LOAD, EVEREX);
        int aminus = (int) generateCode(Commands.JZERO, -1);
        generateCode(Commands.SUB, ARITHMETIC);
        generateCode(Commands.SUB, COUNTER);
        generateCode(Commands.STORE, COUNTER);
        generatedCode[aminus].setCodeLine(generatedCodeOffset);
        generateCode(Commands.LOAD, COUNTER);
        generateCode(Commands.STOREI, TOP_STACK);
    }

    public void eq(JumpHolder jumper) {
        generateCode(Commands.LOADI, TOP_STACK);
        generateCode(Commands.STORE, COUNTER);
        stackPop();
        generateCode(Commands.LOADI, TOP_STACK);
        generateCode(Commands.STORE, BASE);
        stackPop();
        generateCode(Commands.LOAD, COUNTER);
        generateCode(Commands.SUB, BASE);
        int jumpTrue = (int) generateCode(Commands.JZERO, -1);
        int jumFalse = (int) generateCode(Commands.JUMP, 0);
        generatedCode[jumpTrue].setCodeLine(jumFalse + 1);
        jumper.setJumpIfTrue(jumpTrue);
        jumper.setJumpIfFalse(jumFalse);
    }

    public void ge(JumpHolder jumper) {
        generateCode(Commands.LOADI, TOP_STACK);
        generateCode(Commands.STORE, COUNTER);
        stackPop();
        generateCode(Commands.LOADI, TOP_STACK);
        generateCode(Commands.STORE, BASE);
        stackPop();
        generateCode(Commands.LOAD, BASE);
        generateCode(Commands.SUB, COUNTER);
        generateCode(Commands.DEC);
        int jumFalse = (int) generateCode(Commands.JNEG, -1);
        int jumpTrue = (int) generateCode(Commands.JUMP, 0);
        generatedCode[jumpTrue].setCodeLine(jumpTrue + 1);
        jumper.setJumpIfTrue(jumpTrue + 1);
        jumper.setJumpIfFalse(jumFalse);
    }

    public void leq(JumpHolder jumper) {
        generateCode(Commands.LOADI, TOP_STACK);
        generateCode(Commands.STORE, COUNTER);
        stackPop();
        generateCode(Commands.LOADI, TOP_STACK);
        generateCode(Commands.STORE, BASE);
        stackPop();
        generateCode(Commands.LOAD, BASE);
        generateCode(Commands.SUB, COUNTER);
        int jumFalse = (int) generateCode(Commands.JPOS, -1);
        int jumpTrue = (int) generateCode(Commands.JUMP, 0);
        generatedCode[jumpTrue].setCodeLine(jumpTrue + 1);
        jumper.setJumpIfTrue(jumpTrue + 1);
        jumper.setJumpIfFalse(jumFalse);
    }

    public void tabValidation(String pidName1, String pidName3) {

        loadToP0(tryToGetvar(pidName1));
        generateCode(Commands.STORE, IN_OUT);
        int j = 0;
        for (int i = 0; i < getCodeDataOffset(); i++) {
            if (getInnerMemory()[i].getName().equals(pidName1)) {
                j = i;
                break;
            }
        }
        if (!getInnerMemory()[j].isTab()) {
            lexer.yyerror("attempt to access to var like array");
        }
        loadToP0(-getInnerMemory()[j].getMin());
        generateCode(Commands.ADD, tryToGetvar(pidName3));
        generateCode(Commands.ADD, IN_OUT);
        generateCode(Commands.STORE, OUT_IN_NUM);
        generateCode(Commands.LOAD, TOP_STACK);
        generateCode(Commands.INC);
        generateCode(Commands.STORE, TOP_STACK);
        generateCode(Commands.LOAD, OUT_IN_NUM);
        generateCode(Commands.STOREI, TOP_STACK);
    }

    public void valueFromPidValidation(Long num, int line) {
        if (num == -1) {
            generateCode(Commands.LOADI, TOP_STACK);
            generateCode(Commands.LOADI, ARITHMETIC);
            generateCode(Commands.STOREI, TOP_STACK);
        } else {
            addNewIdValue(num, line);
        }
    }

    public void endForCommands(String name2, TokenHolder token1) {
        generateCode(Commands.LOAD, tryToGetvar(name2));
        generateCode(Commands.DEC);
        generateCode(Commands.STORE, tryToGetvar(name2));
        generateCode(Commands.LOAD, TOP_STACK);
        generateCode(Commands.JUMP, token1.jumpHolder.getJumpToStart());
        getGeneratedCode()[(int) token1.jumpHolder.getJumpIfFalse()].setCodeLine(getGeneratedCodeOffset());
        stackPop();
        decrementData_offset();
    }

    public void beginDownToFor(JumpHolder token1Jump, String token2name) {
        JumpHolder j = token1Jump;
        createNewVariable(token2name);
        getInnerMemory()[(int) getCodeDataOffset() - 1].setInit(true);
        getInnerMemory()[(int) getCodeDataOffset() - 1].setIterator(true);
        generateCode(Commands.LOADI, TOP_STACK);
        generateCode(Commands.LOADI, TOP_STACK);
        generateCode(Commands.STORE, getNext_address() - 1);
        stackPop();
    }

    public void downToValidation(TokenHolder token, String token2name) {
        token.jumpHolder.setJumpToStart(3);
        token.jumpHolder.setJumpToStart(generateCode(Commands.LOADI, TOP_STACK));
        generateCode(Commands.STORE, BASE);
        generateCode(Commands.LOAD, tryToGetvar(token2name));
        generateCode(Commands.SUB, BASE);
        token.jumpHolder.setJumpIfFalse(generateCode(Commands.JNEG, -1));
    }

    public void beginFromFor(JumpHolder token1Jump, String token2name) {
        createNewVariable(token2name);
        getInnerMemory()[(int) getCodeDataOffset() - 1].setIterator(true);
        getInnerMemory()[(int) getCodeDataOffset() - 1].setInit(true);
        generateCode(Commands.LOADI, TOP_STACK);
        generateCode(Commands.STORE, getNext_address() - 1);
        stackPop();
    }

    public void endToFor(TokenHolder token1, String token2Name) {
        generateCode(Commands.LOAD, tryToGetvar(token2Name));
        generateCode(Commands.INC);
        generateCode(Commands.STORE, tryToGetvar(token2Name));
        generateCode(Commands.JUMP, token1.jumpHolder.getJumpToStart());
        getGeneratedCode()[(int) token1.jumpHolder.getJumpIfFalse()].setCodeLine(getGeneratedCodeOffset());
        stackPop();
        decrementData_offset();
    }

    public void geq(JumpHolder jumper) {
        generateCode(Commands.LOADI, TOP_STACK);
        generateCode(Commands.STORE, COUNTER);
        stackPop();
        generateCode(Commands.LOADI, TOP_STACK);
        generateCode(Commands.STORE, BASE);
        stackPop();
        generateCode(Commands.LOAD, BASE);
        generateCode(Commands.SUB, COUNTER);
        int jumFalse = (int) generateCode(Commands.JNEG, -1);
        int jumpTrue = (int) generateCode(Commands.JUMP, 0);
        generatedCode[jumpTrue].setCodeLine(jumpTrue + 1);
        jumper.setJumpIfTrue(jumpTrue + 1);
        jumper.setJumpIfFalse(jumFalse);
    }

    boolean isFree(String temps) {
        for (int i = 0; i < codeDataOffset; i++) {
            if (temps.equals(innerMemory[i].getName())) return false;
        }
        return true;
    }

    public long createNewVariable(String temps) {
        if (!isFree(temps)) {
            lexer.yyerror("second var declaration");
        }
        innerMemory[(int) codeDataOffset].setName(temps);
        innerMemory[(int) codeDataOffset].setAddress(next_address);
        innerMemory[(int) codeDataOffset].setIterator(false);
        innerMemory[(int) codeDataOffset].setInit(false);
        innerMemory[(int) codeDataOffset].setUsed(-1);
        innerMemory[(int) codeDataOffset].setTab(false);
        next_address += 1;
        return codeDataOffset++ + MEMORY_START;
    }

    public long createNewVariable(String temps, long min, long max) {
        if (min > max) {
            lexer.yyerror("Bad array size");
        }
        if (!isFree(temps)) {
            lexer.yyerror("second variable declaration");
        }
        innerMemory[(int) codeDataOffset].setName(temps);
        innerMemory[(int) codeDataOffset].setIterator(false);
        innerMemory[(int) codeDataOffset].setMin(min);
        innerMemory[(int) codeDataOffset].setMax(max);
        innerMemory[(int) codeDataOffset].setInit(false);
        innerMemory[(int) codeDataOffset].setTab(true);
        innerMemory[(int) codeDataOffset].setUsed(-1);
        long x = codeDataOffset;
        innerMemory[(int) codeDataOffset].setAddress(next_address);
        next_address += 1 + max - min;
        codeDataOffset += 1;
        return x + MEMORY_START;
    }

    public void assignNum(Long num) {

        if (num == -1) {
            generateCode(Commands.LOADI, TOP_STACK);
            generateCode(Commands.STORE, BASE);
            stackPop();
            generateCode(Commands.LOADI, TOP_STACK);
            generateCode(Commands.STORE, COUNTER);
            generateCode(Commands.LOAD, BASE);
            generateCode(Commands.STOREI, COUNTER);
            stackPop();
        } else
            assignValue(num);
    }

    public void identifierValidation(String pidName) {
        int j = 0;
        for (int i = 0; i < getCodeDataOffset(); i++) {
            if (getInnerMemory()[i].getName().equals(pidName)) {
                j = i;
                break;
            }
        }
        if (getInnerMemory()[j].isTab()) {
            lexer.yyerror("wrong array accessing");
        }
    }
}
