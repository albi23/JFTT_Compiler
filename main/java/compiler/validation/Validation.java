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

public class Validation {

    public Map<String, TokenInfo> pidIdOnInfo = new HashMap<>(100);
    private CompilerFlex scanner;
    private AssemblerCodeGenerator asm;

    public Validation(CompilerFlex scanner, AssemblerCodeGenerator asm) {
        this.scanner = scanner;
    }

    public void showErrMsg(String msg, int line) {
        scanner.yyerror("Error in line " + line + ": " + msg);
        finishChecking();
    }

    private void finishChecking() {
        System.exit(1);
    }

    public void printDeclarations(){
        pidIdOnInfo.forEach((k,v)->{
            System.out.println("key : "+k+"  value : "+v);
        });
    }

    /**
     * Validation function using identifiers
     */

    public <V>void validateNewPids(TokenInfo<V> tokenInfo, VariableType type){
        if (pidIdOnInfo.containsKey(tokenInfo.getSemanticValue().toString())) {
            showErrMsg("second declaration " + tokenInfo.getSemanticValue(),tokenInfo.getLinePos());
        } else {
            tokenInfo.setVariableType(type);
            tokenInfo.setMemoryAddr(asm.getLastFreeCeil());
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
        if (!(tokenInfo.getSemanticValue() instanceof BigInteger)){ // only num token has semantic of BigInt
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

    public <V> void getArrValueFromToken(TokenInfo<V> firstToken,TokenInfo<V> indexHolder){
        try {
            TokenInfo<V> tempIndexHolder = this.pidIdOnInfo.get(indexHolder.getSemanticValue());
            if (tempIndexHolder.getValue() == null && tempIndexHolder.getType() instanceof SimpleType) return;
            if (tempIndexHolder.getValue() == null ) showErrMsg("use of uninitialized variable "+indexHolder.getSemanticValue(),indexHolder.getLinePos());
            this.getArrValueFromToken(firstToken,tempIndexHolder.getValue() );
        }catch (NullPointerException ex){
            showErrMsg("use of uninitialized variable "+indexHolder.getSemanticValue(),indexHolder.getLinePos());
        }
    }

    public <V> void getValueFromToken(TokenInfo<V> tokenInfo){
//        if (tokenInfo.getType() instanceof SimpleType){
//            return;
//        }
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

    public   <V> void  add(TokenInfo<V> first, TokenInfo<V> second){
        TokenInfo<V> firstToken = pidIdOnInfo.get(first.getSemanticValue());
        TokenInfo<V> secondToken = pidIdOnInfo.get(second.getSemanticValue());
        if (firstToken.getType() instanceof SimpleType && secondToken.getType() instanceof SimpleType){
            ((SimpleType) firstToken.getType()).setCurrentValue(((SimpleType) secondToken.getType()).getCurrentValue().add(((SimpleType) secondToken.getType()).getCurrentValue()));
        }else if (firstToken.getType() instanceof SimpleType){
            ((SimpleType) firstToken.getType()).setCurrentValue(secondToken.getValue().add(((SimpleType) firstToken.getType()).getCurrentValue()));
        }else if (secondToken.getType() instanceof SimpleType){
            first.setValue(((SimpleType) secondToken.getType()).getCurrentValue().add(firstToken.getValue()));
        }else{
            firstToken.setValue(secondToken.getValue().add(secondToken.getValue()));
        }
    };

    public  <V> TokenInfo<V> assign(TokenInfo<V> token,TokenInfo<V> valueHolder ){
        TokenInfo<V> tokenInfo = null;
        try {

            tokenInfo = this.pidIdOnInfo.get(token.getSemanticValue());
            TokenInfo<V> tokenInfo1 = this.pidIdOnInfo.get(valueHolder.getSemanticValue());
            if (tokenInfo.getType() instanceof  SimpleType &&tokenInfo1.getType() instanceof SimpleType){
                ((SimpleType) tokenInfo.getType()).setCurrentValue(((SimpleType) tokenInfo1.getType()).getCurrentValue());
            }else if(tokenInfo.getType() instanceof SimpleType) {
                ((SimpleType) tokenInfo.getType()).setCurrentValue(valueHolder.getValue());
            }else if (tokenInfo1 != null && tokenInfo1.getType() instanceof SimpleType){
                tokenInfo.setValue(((SimpleType) tokenInfo1.getType()).getCurrentValue());
            }else {
                tokenInfo.setValue(valueHolder.getValue());
            }

        }catch (NullPointerException ex){
            showErrMsg("use of uninitialized variable "+token.getSemanticValue(),token.getLinePos());
        }
        return tokenInfo;
    }
}
