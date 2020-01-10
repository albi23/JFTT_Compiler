SUB 0
DEC
STORE 2
INC
INC
STORE 1
LOAD 1
SHIFT 1
INC
SHIFT 1
SHIFT 1
SHIFT 1
INC
STORE 4
LOAD 1
DEC
SUB 4  # there is reversion of number
STORE 4  # there is value : -25
LOAD 1
DEC
STORE 5  # there is value : 0
LOAD 1
DEC
STORE 3 #save result
LOAD 1 # start reversion
DEC
SUB 4
STORE 4 # end reversion
LOAD 5 # B
STORE 6 # B'
JZERO  47
LOAD 5
ADD 6
SUB 4
JPOS  39
LOAD 5
ADD 6
STORE 5
JUMP  31 # to begin while
LOAD  6 # is clone of -> b' > a
SUB  4
JNEG  45
LOAD 1
DEC
STORE 5
LOAD 4
SUB 5
STORE 3
LOAD 1 # start reversion
DEC
SUB 4
STORE 4 # end reversion
PUT
HALT
