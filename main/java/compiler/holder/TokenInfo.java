package compiler.holder;

import compiler.holder.types.VariableType;

import java.math.BigInteger;

public class TokenInfo<V> {

    private V semanticValue;
    private TypeHolder type;
    private VariableType variableType;
    private BigInteger value;
    private int linePos;
    private int beforeTokenId;

    public TokenInfo(V semanticValue, int linePos) {
        this.semanticValue = semanticValue;
        this.linePos = linePos;
        this.beforeTokenId = -1;
    }

    public TokenInfo(V semanticValue, int linePos, int beforeTokenId) {
        this.semanticValue = semanticValue;
        this.linePos = linePos;
        this.beforeTokenId = beforeTokenId;
    }
    public V getSemanticValue() {
        return semanticValue;
    }
    public int getLinePos() {
        return linePos;
    }

    public int getBeforeTokenId() {
        return beforeTokenId;
    }

    public void setType(TypeHolder type) {
        this.type = type;
    }

    public TypeHolder getType() {
        return type;
    }

    public VariableType getVariableType() {
        return variableType;
    }

    public BigInteger getValue() {
        return value;
    }

    public void setValue(BigInteger value) {
        this.value = value;
    }

    public void setVariableType(VariableType variableType) {
        this.variableType = variableType;
    }

    @Override
    public String toString() {
        return "TokenInfo{" +
                "semanticValue=" + semanticValue +
                ", type=" + type +
                ", variableType=" + variableType +
                ", value=" + value +
                ", linePos=" + linePos +
                ", tokenId=" + beforeTokenId +
                '}';
    }
}
