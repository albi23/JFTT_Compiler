package compiler.holder;

import compiler.holder.types.VariableType;

public class TokenInfo<V> {

    private V semanticValue;
    private int linePos;
    private VariableType type;
    private int tokenId;

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

    public void setType(VariableType type) {
        this.type = type;
    }

    public int getTokenId() {
        return tokenId;
    }

    public void setTokenId(int tokenId) {
        this.tokenId = tokenId;
    }

    @Override
    public String toString() {
        return "TokenInfo{" +
                "semanticValue='" + semanticValue + '\'' +
                ", linePos=" + linePos +
                '}';
    }
}
