SUB 0
DEC
STORE 13
INC
INC
STORE 12
LOAD 12
SHIFT 12
INC
SHIFT 12
INC
STORE 15  # there is value : 7
LOAD 13
SHIFT 12
SHIFT 12
STORE 16  # there is value : -4
LOAD 12
DEC
STORE 14 #save result
LOAD 15 # A
STORE 17 # A'
LOAD 16 # CHECK B = 0 ?
JZERO  29  # ?? ille :P
LOAD 17
JNEG  29
LOAD 17
ADD 16
STORE 17
JUMP  23 # to begin while
STORE 14
PUT
HALT