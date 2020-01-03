package compiler.holder.types;

import compiler.holder.TypeMarkInterface;

import java.math.BigInteger;
import java.util.Arrays;

public class ArrayType implements TypeMarkInterface {

    private static final int maxArrSize = 2_147_483_647;
    private int beginTab;
    private int endTab;
    private int shift;
    private BigInteger[] tab;

    public ArrayType(int beginTab, int endTab) throws IllegalArgumentException{
        this.beginTab = beginTab;
        this.endTab = endTab;
        long tabSize = (long) endTab - (long) beginTab + 1;
        if (tabSize > maxArrSize) throw new IllegalArgumentException("The maximum size of an array is "+maxArrSize);
        this.tab = new BigInteger[(int) tabSize];
        this.shift = -beginTab;
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
                "tab=" + Arrays.toString(tab) +
                '}';
    }
}
