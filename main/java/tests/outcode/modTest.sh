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
INC
SHIFT 1
INC
SHIFT 1
INC
SHIFT 1
INC
STORE 4  # there is value : 111  A
LOAD 1
SHIFT 1
LOAD 1
DEC
STORE 3  # there is value : 0 B
STORE 5  # there is value : 8 tmp B
LOAD 4  # load 8 B # START WHILE B< A
ADD 5   # B+B > A ?
SUB 3
JPOS 33  # jeśli pozytywne to B > A <--------------
LOAD 4  # else
ADD 5
STORE 4
JUMP 25 # SKOCZ NA POCZĄTEK WHILE  <--------------
LOAD 5 # sprawdź czy pierwotne b > a
SUB 3
JNEG 39 # <--------------
LOAD 1
DEC
STORE 4
LOAD 3 # WEZ A
SUB 4 # ODEJMIJ B
STORE 6
PUT
HALT
