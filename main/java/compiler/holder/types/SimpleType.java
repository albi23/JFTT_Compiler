package compiler.holder.types;

import compiler.holder.TypeHolder;

import java.math.BigInteger;

public class SimpleType implements TypeHolder {

    private BigInteger value;

    public SimpleType(BigInteger value) {
        this.value = value;
    }

    public BigInteger getValue() {
        return value;
    }

    public void setValue(BigInteger value) {
        this.value = value;
    }
}
