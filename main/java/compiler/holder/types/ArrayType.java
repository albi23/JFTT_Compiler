package compiler.holder.types;

import compiler.holder.TypeHolder;

import java.math.BigInteger;
import java.util.Arrays;

public class ArrayType implements TypeHolder {

    private static final int maxArrSize = 2_147_483_647;
    private int beginTab;
    private int endTab;
    private int shift;
    private BigInteger[] tab;

    public ArrayType(BigInteger beginTab, BigInteger endTab) throws IllegalArgumentException{
        long tmp1 = endTab.longValueExact();
        long tmp2 = beginTab.longValueExact();
        long tabSize = tmp1 - tmp2 + 1;
        if (tabSize > maxArrSize) throw new IllegalArgumentException("The maximum size of an array is "+maxArrSize);
        this.beginTab = (int) tmp2;
        this.endTab = (int)tmp1;
        this.tab = new BigInteger[(int) tabSize];
        this.shift = -this.beginTab;
    }


    public void setValAtIndex(int indx, BigInteger value) throws ArrayIndexOutOfBoundsException {
        if (beginTab > indx || indx > endTab) {
            throw new ArrayIndexOutOfBoundsException("Attempting to access a non-existing index: "+indx);
        }
        this.tab[shift + indx] = value;
    }

    public BigInteger getValAtIndex(int indx) throws ArrayIndexOutOfBoundsException {
        if (beginTab > indx || indx > endTab) {
            throw new ArrayIndexOutOfBoundsException("Attempting to access a non-existing index: "+indx);
        }
        return this.tab[shift + indx];
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
