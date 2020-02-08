public class JumpHolder {

    private long jumpIfFalse;
    private long jumpIfTrue;
    private long jumpToEnd;
    private long jumpToStart;

    public JumpHolder(){}

    public long getJumpIfFalse() {
        return jumpIfFalse;
    }

    public void setJumpIfFalse(long jumpIfFalse) {
        this.jumpIfFalse = jumpIfFalse;
    }

    public long getJumpIfTrue() {
        return jumpIfTrue;
    }

    public void setJumpIfTrue(long jumpIfTrue) {
        this.jumpIfTrue = jumpIfTrue;
    }

    public long getJumpToEnd() {
        return jumpToEnd;
    }

    public void setJumpToEnd(long jumpToEnd) {
        this.jumpToEnd = jumpToEnd;
    }

    public long getJumpToStart() {
        return jumpToStart;
    }

    public void setJumpToStart(long jumpToStart) {
        this.jumpToStart = jumpToStart;
    }
}
