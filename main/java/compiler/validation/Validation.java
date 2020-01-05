package compiler.validation;

import compiler.CompilerBison;
import compiler.CompilerFlex;
import compiler.holder.TokenInfo;
import compiler.holder.TypeHolder;
import compiler.holder.types.ArrayType;
import compiler.holder.types.VariableType;

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

    public <V>void validateNewPids(TokenInfo<V> tokenInfo, VariableType type){
        if (pidIdOnInfo.containsKey(tokenInfo.getSemanticValue().toString())) {
            showErrMsg("second declaration " + tokenInfo.getSemanticValue(),tokenInfo.getLinePos());
        } else {
            tokenInfo.setVariableType(type);
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
        validateNewPids(tokenInfo, VariableType.ARRAY);
    }

    public <V> void validationOfVariableValues(TokenInfo<V> tokenInfo){
        if(!pidIdOnInfo.containsKey(tokenInfo.getSemanticValue())){
            showErrMsg("use of uninitialized variable "+tokenInfo.getSemanticValue(),tokenInfo.getLinePos());
        }
    }

    public <V> void validateToWriteToken(TokenInfo<V> tokenInfo){

        System.out.println("\u001b[48;5;28mvalidateToWriteToken : "+tokenInfo+"\u001b[0m");
        if (tokenInfo.getTokenId() != CompilerBison.Lexer.NUM){
            try {
                // Get from map all token info data - if not present we have try to access to undefined var
                TypeHolder type = this.pidIdOnInfo.get(tokenInfo.getSemanticValue()).getType();
                if (type instanceof ArrayType){
                    System.out.println(" Array :"+ type.toString());
                }else { // null value possible
                    System.out.println("Simple type"+ type.toString());
                }
            }catch (NullPointerException ex){
                showErrMsg("use of uninitialized variable "+tokenInfo.getSemanticValue(),tokenInfo.getLinePos());
            }
        }
    }

    public <V> void getArrValueFromToken(TokenInfo<V> tokenInfo, BigInteger indx){
        try {
            TypeHolder type = this.pidIdOnInfo.get(tokenInfo.getSemanticValue()).getType();
            tokenInfo.setValue(((ArrayType)type).getValAtIndex(indx));
        }catch (NullPointerException ex){
            showErrMsg("use of uninitialized variable "+tokenInfo.getSemanticValue(),tokenInfo.getLinePos());
        }
    }

    public <V> void getArrValueFromToken(TokenInfo<V> firstToken,TokenInfo<V> secondToken){
        try {
            BigInteger index = this.pidIdOnInfo.get(secondToken.getSemanticValue()).getValue();
            if (index == null) showErrMsg("use of uninitialized variable "+secondToken.getSemanticValue(),secondToken.getLinePos());
            this.getArrValueFromToken(firstToken,index);
        }catch (NullPointerException ex){
            showErrMsg("use of uninitialized variable "+secondToken.getSemanticValue(),secondToken.getLinePos());
        }
    }

    public <V> void getValueFromToken(TokenInfo<V> tokenInfo){
        try {
            tokenInfo.setValue(this.pidIdOnInfo.get(tokenInfo.getSemanticValue()).getValue());
        }catch (NullPointerException ex){
            showErrMsg("use of uninitialized variable "+tokenInfo.getSemanticValue(),tokenInfo.getLinePos());
        }
    }
    public static boolean isGreaterThanMaxInt(BigInteger bigInt){
      return (bigInt.compareTo(BigInteger.valueOf(Integer.MAX_VALUE)) > 0);
    }

    /** Arithmetic operations */

    public static  <V> void  add(TokenInfo<V> first, TokenInfo<V> second){
        first.setValue(first.getValue().add(second.getValue()));
    };

    public  <V> TokenInfo<V> assign(TokenInfo<V> token,TokenInfo<V> valueHolder ){
        TokenInfo<V> tokenInfo = null;
        try {
            tokenInfo = this.pidIdOnInfo.get(token.getSemanticValue());
            tokenInfo.setValue(valueHolder.getValue());
        }catch (NullPointerException ex){
            showErrMsg("use of uninitialized variable "+token.getSemanticValue(),token.getLinePos());
        }
        return tokenInfo;
    }
}
