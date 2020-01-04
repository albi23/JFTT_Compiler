package compiler.holder;

import compiler.holder.types.VariableType;

public class TokenInfo<V> {

    private V semanticValue;
    private TypeHolder type;
    private int linePos;
    private int tokenId;
    private VariableType variableType;

    public TokenInfo(V semanticValue, int linePos) {
        this.semanticValue = semanticValue;
        this.linePos = linePos;
        this.tokenId = -1;
    }

    public TokenInfo(V semanticValue, int linePos, int tokenId) {
        this.semanticValue = semanticValue;
        this.linePos = linePos;
        this.tokenId = tokenId;
    }
    public V getSemanticValue() {
        return semanticValue;
    }
    public int getLinePos() {
        return linePos;
    }

    public int getTokenId() {
        return tokenId;
    }

    public void setType(TypeHolder type) {
        this.type = type;
    }

    public VariableType getVariableType() {
        return variableType;
    }

    public void setVariableType(VariableType variableType) {
        this.variableType = variableType;
    }

    @Override
    public String toString() {
        return "TokenInfo{" +
                "semanticValue='" + semanticValue + '\'' +
                ", linePos=" + linePos +
                '}';
    }
}
