package compiler.validation;

import compiler.CompilerBison;
import compiler.CompilerFlex;
import compiler.assembly.AssemblerCodeGenerator;
import compiler.holder.TokenInfo;
import compiler.holder.TypeHolder;
import compiler.holder.types.ArrayType;
import compiler.holder.types.SimpleType;
import compiler.holder.types.VariableType;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Validation {

    public Map<String, TokenInfo> pidIdOnInfo = new HashMap<>(100);
    private CompilerFlex scanner;
    private AssemblerCodeGenerator asm;


    public Validation(CompilerFlex scanner, AssemblerCodeGenerator asm) {
        this.scanner = scanner;
        this.asm = asm;
    }

    public void showErrMsg(String msg, int line) {
        scanner.yyerror("Error in line " + line + ": " + msg);
        finishChecking();
    }

    private void finishChecking() {
        System.exit(1);
    }

    public void printDeclarations() {
        pidIdOnInfo.forEach((k, v) -> {
            System.out.println("key : " + k + "  value : " + v);
        });
    }

    public <V> void readFromInput(Scanner scan, TokenInfo<V> tokenInfo) {
        System.out.print("ENTER THE NUMBER : ");
        String number = scan.nextLine();
        try {
            BigInteger value = new BigInteger(number);
            TokenInfo<V> updatedToken = this.pidIdOnInfo.get(tokenInfo.getSemanticValue().toString());
            if (updatedToken.getVariableType() == VariableType.VAR) {
                updatedToken.setValue(value);
                System.out.println("GENEROWANIE KODU DO PRZYPISANIA ZMIENNEJ...");
            }
//            if (updatedToken.getVariableType() == VariableType.ARRAY) { }
        }catch (NumberFormatException ex){
            scanner.yyerror("Provided number is incorrect");
        }catch (NullPointerException ex){
            showErrMsg("use of uninitialized variable " + tokenInfo.getSemanticValue(), tokenInfo.getLinePos());
        }
    }

    public <V> TokenInfo<V> getConst(TokenInfo<V> token) {

        String tokenName = token.getSemanticValue().toString();
        try {
            // liczba już istnieje
            System.out.println("GENEROWANIE KODU : ZAŁADOWANIE ZMIENNEJ...");
            TokenInfo<V> tokenInfo = this.pidIdOnInfo.get(tokenName);
            tokenInfo.getLinePos(); // if null go to catch
            return tokenInfo;
        } catch (NullPointerException ex) {
            System.out.println("GENEROWANIE KODU ZMIENNEJ...");
            // liczba nie istnieje więc ją tworzymy
            token.setValue(new BigInteger(tokenName)); // jest liczbą zapisaną w stringu, czyli ok
            token.setMemoryAddr(asm.getLastFreeCeil());
            asm.updateLastFreeCeil(1);
            this.pidIdOnInfo.put(tokenName, token);
            return token;
        }
    }

    private <V> boolean  isLoopIterator(TokenInfo<V> tokenInfo){
        return tokenInfo.getBeforeTokenId() == CompilerBison.Lexer.FROM;
    }
    /**
     * Validation function using identifiers
     */

    public <V> void loopIteratorPid(TokenInfo<V> pidInfo, TokenInfo<V> valueInfo, VariableType type) {
        pidInfo.setVariableType(type);
        pidInfo.setValue(valueInfo.getValue());
        pidInfo.setMemoryAddr(asm.getLastFreeCeil());
        asm.updateLastFreeCeil(1);
        pidIdOnInfo.put(pidInfo.getSemanticValue().toString(), pidInfo);
    }

    public <V> void validateNewPids(TokenInfo<V> tokenInfo, VariableType type) {
        if (pidIdOnInfo.containsKey(tokenInfo.getSemanticValue().toString())) {
            showErrMsg("second declaration " + tokenInfo.getSemanticValue(), tokenInfo.getLinePos());
        } else {
            tokenInfo.setVariableType(type);
            tokenInfo.setMemoryAddr(asm.getLastFreeCeil());
            asm.updateLastFreeCeil(1);
            pidIdOnInfo.put(tokenInfo.getSemanticValue().toString(), tokenInfo);
        }
    }

    /**
     * Validaion array declarations
     */
    public <V> void validateArrayDeclarations(BigInteger beginArray, BigInteger endArray, TokenInfo<V> tokenInfo) {
        if (beginArray.compareTo(endArray) > 0) {
            showErrMsg("invalid array " + tokenInfo.getSemanticValue() + " declaration", tokenInfo.getLinePos());
        }
        ArrayType arrayType = new ArrayType(beginArray, endArray);
        tokenInfo.setType(arrayType);
        tokenInfo.setMemoryAddr(asm.getLastFreeCeil());
        asm.updateLastFreeCeil(arrayType.getArrSize());
        validateNewPids(tokenInfo, VariableType.ARRAY);
    }

    public <V> void removeLoopIteratorDeclarations(TokenInfo<V> tokenInfo){
        this.pidIdOnInfo.remove(tokenInfo.getSemanticValue());
    }

    public <V> TokenInfo<V> validateToWriteToken(TokenInfo<V> tokenInfo) {

        TokenInfo<V> token = this.pidIdOnInfo.get(tokenInfo.getSemanticValue());
        if (token.getVariableType() == VariableType.ARRAY || token.getVariableType() == VariableType.ARRAY_EL) return token;
        if (token == null || token.getValue() == null){
            showErrMsg("use of uninitialized variable " + tokenInfo.getSemanticValue(), tokenInfo.getLinePos());
        }
        return token;
    }

    // pidentifier ( num )
    public <V> TokenInfo<V> getArrValueFromToken(TokenInfo<V> tokenInfo, BigInteger indx) {

        TokenInfo<V> token = this.pidIdOnInfo.get(tokenInfo.getSemanticValue());
        if (token == null){
            showErrMsg("use of uninitialized variable " + tokenInfo.getSemanticValue(), tokenInfo.getLinePos());
            return null;
        }
        if (!(token.getVariableType() == VariableType.ARRAY)){
            showErrMsg("Invalid variable usage " + tokenInfo.getSemanticValue(), tokenInfo.getLinePos());
        }
        TokenInfo<V> tokenVal = new TokenInfo<>(tokenInfo.getSemanticValue(), tokenInfo.getLinePos());
        tokenVal.setVariableType(VariableType.ARRAY_EL);
        ArrayType type1 = (ArrayType) token.getType();
        type1.isCorrectIndx(indx);
        tokenVal.setMemoryAddr(token.getMemoryAddr()+indx.longValueExact()-type1.getBeginTab().longValueExact());
        return tokenVal;
    }

    public <V> TokenInfo<V> getArrValueFromToken(TokenInfo<V> firstToken, TokenInfo<V> indexHolder) {

        TokenInfo<V> arrayHolder = this.pidIdOnInfo.get(firstToken.getSemanticValue());
        TokenInfo<V> index = this.pidIdOnInfo.get(indexHolder.getSemanticValue());

        if (arrayHolder == null || index == null){
            showErrMsg("use of uninitialized variable " + indexHolder.getSemanticValue(), indexHolder.getLinePos());
        } else if (index.getType() instanceof ArrayType){
            showErrMsg("Invalid variable usage " + indexHolder.getSemanticValue(), indexHolder.getLinePos());
        }else if (!(arrayHolder.getType() instanceof ArrayType)){
            showErrMsg("Invalid variable usage " + arrayHolder.getSemanticValue(), arrayHolder.getLinePos());
        }// err

        TokenInfo<V> valToken = new TokenInfo<>(arrayHolder.getSemanticValue(),arrayHolder.getLinePos());
        valToken.setVariableType(VariableType.ARRAY_EL);
        ArrayType type1 = (ArrayType) arrayHolder.getType();
        type1.isCorrectIndx(index.getValue());
        valToken.setMemoryAddr(arrayHolder.getMemoryAddr()+index.getValue().longValueExact()-type1.getBeginTab().longValueExact());
        return valToken;
    }

    public <V> TokenInfo<V> getVar(TokenInfo<V> tokenInfo) {
        TokenInfo<V> declaredToken = this.pidIdOnInfo.get(tokenInfo.getSemanticValue());

        // if iterator of loop then skip
        if (declaredToken.getVariableType() == VariableType.ITERATOR) return declaredToken;

        if (declaredToken.getVariableType() == VariableType.ARRAY) // zwracanie całej tablicy jest błędem ?
            showErrMsg("Invalid variable usage " + tokenInfo.getSemanticValue(), tokenInfo.getLinePos());

        if (declaredToken == null){ // can be null ...
            showErrMsg("use of uninitialized variable " + tokenInfo.getSemanticValue(), tokenInfo.getLinePos());
        }
        System.out.println("Generate load value ?");
        return declaredToken;
    }

    public static boolean isGreaterThanMaxInt(BigInteger bigInt) {
        return (bigInt.compareTo(BigInteger.valueOf(Integer.MAX_VALUE)) > 0);
    }

    /**
     * Arithmetic operations
     */

    public <V> void add(TokenInfo<V> first, TokenInfo<V> second) {
        TokenInfo<V> firstToken = pidIdOnInfo.get(first.getSemanticValue());
        TokenInfo<V> secondToken = pidIdOnInfo.get(second.getSemanticValue());
        if (firstToken.getType() instanceof SimpleType && secondToken.getType() instanceof SimpleType) {
            ((SimpleType) firstToken.getType()).setCurrentValue(((SimpleType) secondToken.getType()).getCurrentValue().add(((SimpleType) secondToken.getType()).getCurrentValue()));
        } else if (firstToken.getType() instanceof SimpleType) {
            ((SimpleType) firstToken.getType()).setCurrentValue(secondToken.getValue().add(((SimpleType) firstToken.getType()).getCurrentValue()));
        } else if (secondToken.getType() instanceof SimpleType) {
            first.setValue(((SimpleType) secondToken.getType()).getCurrentValue().add(firstToken.getValue()));
        } else {
            firstToken.setValue(secondToken.getValue().add(secondToken.getValue()));
        }
    }

    ;

    public <V> TokenInfo<V> assign(TokenInfo<V> token, TokenInfo<V> valueHolder) {
        TokenInfo<V> tokenInfo = null;
        try {

            tokenInfo = this.pidIdOnInfo.get(token.getSemanticValue());
            TokenInfo<V> tokenInfo1 = this.pidIdOnInfo.get(valueHolder.getSemanticValue());
            if (tokenInfo.getType() instanceof SimpleType && tokenInfo1.getType() instanceof SimpleType) {
                ((SimpleType) tokenInfo.getType()).setCurrentValue(((SimpleType) tokenInfo1.getType()).getCurrentValue());
            } else if (tokenInfo.getType() instanceof SimpleType) {
                ((SimpleType) tokenInfo.getType()).setCurrentValue(valueHolder.getValue());
            } else if (tokenInfo1 != null && tokenInfo1.getType() instanceof SimpleType) {
                tokenInfo.setValue(((SimpleType) tokenInfo1.getType()).getCurrentValue());
            } else {
                tokenInfo.setValue(valueHolder.getValue());
            }

        } catch (NullPointerException ex) {
            showErrMsg("use of uninitialized variable " + token.getSemanticValue(), token.getLinePos());
        }
        return tokenInfo;
    }
}
