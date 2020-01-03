package compiler;

public class SimpleType implements TypeMarkInterface {

    private long value;

    public SimpleType(long value) {
        this.value = value;
    }

    public long getValue() {
        return value;
    }

    public void setValue(long value) {
        this.value = value;
    }
}
