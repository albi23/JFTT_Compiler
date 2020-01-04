package compiler.validation;

import compiler.CompilerFlex;
import compiler.holder.TokenInfo;
import compiler.holder.types.ArrayType;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

public class Validation {

    private Map<String, TokenInfo> pidIdOnInfo = new HashMap<>(100);
    private CompilerFlex scanner;

    public Validation(CompilerFlex scanner) {
        this.scanner = scanner;
    }

    public void showErrMsg(String msg, int line) {
        scanner.yyerror("Error in line " + line + ": " + msg);
        finishChecking();
    }

    private void finishChecking() {
        System.exit(1);
    }


    /**
     * Validation function using identifiers
     */

    public <V>void validateNewPids(TokenInfo<V> tokenInfo){
        if (pidIdOnInfo.containsKey(tokenInfo.getSemanticValue().toString())) {
            showErrMsg("second declaration " + tokenInfo.getSemanticValue(),tokenInfo.getLinePos());
        } else {
            pidIdOnInfo.put(tokenInfo.getSemanticValue().toString(),tokenInfo);
        }
    }

    /**
     * Validaion array declarations
     */
    public <V>void validateArrayDeclarations(BigInteger beginArray, BigInteger endArray, TokenInfo<V> tokenInfo){
        if( beginArray.compareTo(endArray) > 0){
            showErrMsg("invalid array "+tokenInfo.getSemanticValue()+" declaration",tokenInfo.getLinePos());
        }
        tokenInfo.setType(new ArrayType(beginArray,endArray));
        validateNewPids(tokenInfo);
    }

    public <V> void validationOfVariableValues(TokenInfo<V> tokenInfo){
        if(!pidIdOnInfo.containsKey(tokenInfo.getSemanticValue())){
            showErrMsg("use of uninitialized variable "+tokenInfo.getSemanticValue(),tokenInfo.getLinePos());
        }
    }

}
