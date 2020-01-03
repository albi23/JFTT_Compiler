package compiler.holder;

import compiler.holder.types.VariableType;

public class TokenInfo<V> {

    private V semanticValue;
    private int linePos;
    private VariableType type;

    public TokenInfo(V semanticValue, int linePos) {
        this.semanticValue = semanticValue;
        this.linePos = linePos;
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

    @Override
    public String toString() {
        return "TokenInfo{" +
                "semanticValue='" + semanticValue + '\'' +
                ", linePos=" + linePos +
                '}';
    }
}
