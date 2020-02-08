public class TokenHolder {

    private long numericValue;
    private String semanticValue;
    public JumpHolder jumpHolder;

    public TokenHolder(){};

    public long getNumericValue() {
        return numericValue;
    }

    public void setNumericValue(long numericValue) {
        this.numericValue = numericValue;
    }

    public String getSemanticValue() {
        return semanticValue;
    }

    public void setSemanticValue(String semanticValue) {
        this.semanticValue = semanticValue;
    }

    public JumpHolder getJumpHolder() {
        return jumpHolder;
    }

    public void setJumpHolder(JumpHolder jumpHolder) {
        this.jumpHolder = jumpHolder;
    }
}
