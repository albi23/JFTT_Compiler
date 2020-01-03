package compiler;

public class ArrayType implements TypeMarkInterface {

    /** Pytanie czy może dać tak duże zakresy że stworzmy tablicę większa niż int*/
    private long beginTab;
    private long endTab;
    private long tabSize;
    private long[] tab;

    public ArrayType(long beginTab, long endTab) {
        this.beginTab = beginTab;
        this.endTab = endTab;
        long tmp = endTab-beginTab+1;
        this.tabSize = ( tmp > 2_147_483_647L) ? 2_147_483_647 :(int)tmp;
        tab = new long[(int) tabSize];
    }

    public long getBeginTab() {
        return beginTab;
    }

    public long getEndTab() {
        return endTab;
    }

    public boolean setValAtIndex(long indx){
        if (beginTab <= indx && indx <= endTab){

        }
        return true;
    }
}
