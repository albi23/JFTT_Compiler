public enum Commands {
    GET,
    PUT,

    LOAD,
    STORE,
    LOADI,
    STOREI,

    ADD,
    SUB,
    SHIFT,
    INC,
    DEC,

    JUMP,
    JPOS,
    JZERO,
    JNEG,

    HALT;

    @Override
    public String toString() {
        return super.toString().toUpperCase();
    }
}
