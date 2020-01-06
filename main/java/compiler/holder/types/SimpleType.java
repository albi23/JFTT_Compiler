package compiler.holder.types;

import compiler.holder.TypeHolder;

import java.math.BigInteger;

public class SimpleType implements TypeHolder {

    private BigInteger beginValue;
    private BigInteger endValue;
    private BigInteger currentValue;

    public SimpleType(BigInteger beginValue) {
        this.beginValue = beginValue;
    }

    public BigInteger getBeginValue() {
        return beginValue;
    }

    public void setBeginValue(BigInteger beginValue) {
        this.beginValue = beginValue;
    }

    public BigInteger getCurrentValue() {
        return currentValue;
    }

    public void setCurrentValue(BigInteger currentValue) {
        this.currentValue = currentValue;
    }

    public BigInteger getEndValue() {
        return endValue;
    }

    public void setEndValue(BigInteger endValue) {
        this.endValue = endValue;
    }
}
