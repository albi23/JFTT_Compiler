package compiler.holder.types;

import compiler.holder.TokenInfo;
import compiler.holder.TypeHolder;
import compiler.validation.Validation;

import java.math.BigInteger;
import java.util.Arrays;

public class ArrayType implements TypeHolder {

    private BigInteger beginTab;
    private BigInteger endTab;
    private BigInteger shift;
    private  BigInteger arrSize;

    public ArrayType(BigInteger beginTab, BigInteger endTab) throws IllegalArgumentException{
         arrSize = endTab.subtract(beginTab).add(new BigInteger("1"));
        if (Validation.isGreaterThanMaxInt(arrSize)) throw new IllegalArgumentException("The maximum size of an array is "+Integer.MAX_VALUE);
        this.beginTab = beginTab;
        this.endTab = endTab;
        this.shift = this.beginTab.negate();
    }

    public long getArrSize() {
        return arrSize.longValueExact();
    }

    public void setValAtIndex(BigInteger indx, BigInteger value) throws ArrayIndexOutOfBoundsException {
        if (beginTab.compareTo(indx) > 0|| indx.compareTo(endTab) > 0) {
            throw new ArrayIndexOutOfBoundsException("Attempting to access a non-existing index: "+indx);
        }
        int shiftedIndex = shift.add(indx).intValue();
    }

    public boolean isCorrectIndx(BigInteger indx) throws ArrayIndexOutOfBoundsException {
        if (beginTab.compareTo(indx) > 0|| indx.compareTo(endTab) > 0) {
            throw new ArrayIndexOutOfBoundsException("Attempting to access a non-existing index: "+indx);
        }
        return true;
    }

    public BigInteger getBeginTab() {
        return beginTab;
    }

    public void setBeginTab(BigInteger beginTab) {
        this.beginTab = beginTab;
    }

    public BigInteger getEndTab() {
        return endTab;
    }

    public void setEndTab(BigInteger endTab) {
        this.endTab = endTab;
    }

    @Override
    public String toString() {
        return "ArrayType{" +
                "beginTab=" + beginTab +
                ", endTab=" + endTab +
                ", shift=" + shift +
                '}';
    }
}
