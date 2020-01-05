package compiler.holder.types;

import compiler.holder.TypeHolder;
import compiler.validation.Validation;

import java.math.BigInteger;
import java.util.Arrays;

public class ArrayType implements TypeHolder {

    private BigInteger beginTab;
    private BigInteger endTab;
    private BigInteger[] tab;
    private BigInteger shift;

    public ArrayType(BigInteger beginTab, BigInteger endTab) throws IllegalArgumentException{
        BigInteger arrSize = endTab.subtract(beginTab).add(new BigInteger("1"));
        if (Validation.isGreaterThanMaxInt(arrSize)) throw new IllegalArgumentException("The maximum size of an array is "+Integer.MAX_VALUE);
        int tabSize = arrSize.intValue();
        this.beginTab = beginTab;
        this.endTab = endTab;
        this.tab = new BigInteger[tabSize];
        this.shift = this.beginTab.negate();
    }


    public void setValAtIndex(BigInteger indx, BigInteger value) throws ArrayIndexOutOfBoundsException {
        if (beginTab.compareTo(indx) > 0|| indx.compareTo(endTab) > 0) {
            throw new ArrayIndexOutOfBoundsException("Attempting to access a non-existing index: "+indx);
        }
        int shiftedIndex = shift.add(indx).intValue();
        this.tab[shiftedIndex] = value;
    }

    public BigInteger getValAtIndex(BigInteger indx) throws ArrayIndexOutOfBoundsException {
        if (beginTab.compareTo(indx) > 0|| indx.compareTo(endTab) > 0) {
            throw new ArrayIndexOutOfBoundsException("Attempting to access a non-existing index: "+indx);
        }
        int shiftedIndex = shift.add(indx).intValue();
        return this.tab[shiftedIndex];
    }

    @Override
    public String toString() {
        return "ArrayType{" +
                "beginTab=" + beginTab +
                ", endTab=" + endTab +
                ", shift=" + shift +
                ", tab=" + Arrays.toString(tab) +
                '}';
    }
}
