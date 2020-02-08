public class CommandHolder {
    private String operator;
    private long codeLine;
    public String comment;

    public CommandHolder(){};


    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public long getCodeLine() {
        return codeLine;
    }

    public void setCodeLine(long codeLine) {
        this.codeLine = codeLine;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
