SUB 0     #p0=0
INC       #p0=1
STORE 1   #p1=1
INC       #p0=2
STORE 2   #p2=2
SHIFT 1   #p0=4
INC       #p0=5
STORE 6   #p6=5 kontrolka ile mamy zmiennych
SHIFT 1   #p=10
INC
INC       #p0=12 od 12 do 2 to 11 komórek
DEC       #p0=11 więc
STORE 5   #p5=11
JZERO 25  #p0=0 ? jump 21 else k++
ADD 6     #p0 = 16
PUT
STORE 2   #p2 =16
LOAD 1    #p0= 1
STOREI 2  #p(16) = 1
LOAD 5    #p0=11
DEC       #p0=10
STORE 5   #p5=10
JUMP 15
HALT
