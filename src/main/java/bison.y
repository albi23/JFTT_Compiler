%language "Java"
%define api.prefix {Compiler}
%define api.parser.class {Compiler}
%define api.parser.public
%define parse.error verbose
%define api.parser.annotations {@SuppressWarnings("all")}
%define api.parser.extends {Constants}
%debug

%code imports {
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

}

%code{
	   private static Flex scanner = null;
	   private static AssemblerCodeGenerator asm = null;


    public static void main(String argv[]) {
        if (argv.length != 2) {
            System.out.println("Usage : java Compiler <inputfile>  <outputfile>");
        } else {
            String encodingName = "UTF-8";
            try {
                java.io.FileInputStream stream = new java.io.FileInputStream(argv[0]);
                java.io.Reader reader = new java.io.InputStreamReader(stream, encodingName);
                scanner = new Flex(reader);
                Compiler compilerBison = new Compiler(scanner);
                asm = new AssemblerCodeGenerator(scanner);
                asm.setup();
                compilerBison.parse();
                asm.generateCode(Commands.HALT);

                for (int i = 0; i < asm.getCodeDataOffset(); i++) {
                    if (asm.getInnerMemory()[i].isInit() == false && asm.getInnerMemory()[i].getUsed() != -1) {
                        System.out.println("Error: variable uninitialized: Line: " + asm.getInnerMemory()[i].getUsed());
                        System.exit(0);
                    }
                }

                try (FileWriter fileWriter = new FileWriter(System.getProperty("user.dir")+"/"+ argv[1])) {
                    PrintWriter printWriter = new PrintWriter(fileWriter);

                    for (int i = 0; i < asm.getGeneratedCodeOffset(); i++) {
                        if (asm.getGeneratedCode()[i].getCodeLine() != -1) {
                            printWriter.println(asm.getGeneratedCode()[i].getOperator() + " " + asm.getGeneratedCode()[i].getCodeLine());
                        } else {
                            printWriter.println(asm.getGeneratedCode()[i].getOperator());
                        }
                    }
                    printWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            } catch (java.io.FileNotFoundException e) {
                System.out.println("File not found : \"" + argv[0] + "\" or \" +" + argv[1] + "\"");
            } catch (java.io.IOException e) {
                System.out.println("IO error scanning file \"" + argv[0] + "\"");
                System.out.println(e);
            } catch (Exception e) {
                System.out.println("Unexpected exception:");
                e.printStackTrace();
            }

        }
    }
}

%token<TokenHolder> NUM
%token <TokenHolder>PIDENTIFIER
%token COMMA L_BRACKET R_BRACKET SEMICOLON
%token ERROR
%token DECLARE BEGIN END
%token IF WHILE FOR
%token THEN ELSE ENDIF FROM TO DOWNTO ENDFOR ENDWHILE ENDDO COLON DO
%token READ WRITE
%token LE GE LEQ GEQ EQ NEQ
%token ASSIGN

%type  <Long> identifier
%type  <JumpHolder> condition
%type  <TokenHolder> WHILE DO FOR

%left PLUS MINUS
%left TIMES DIV MOD
%%

program:        DECLARE declarations{}
                BEGIN commands END { }
              | BEGIN commands END {}
;
declarations:  declarations COMMA PIDENTIFIER {asm.createNewVariable($3.getSemanticValue());}

              | declarations COMMA PIDENTIFIER L_BRACKET NUM COLON NUM R_BRACKET  {asm.createNewVariable($3.getSemanticValue(),$5.getNumericValue(),$7.getNumericValue());}
              | PIDENTIFIER {asm.createNewVariable($1.getSemanticValue());}

              | PIDENTIFIER L_BRACKET NUM COLON NUM R_BRACKET {asm.createNewVariable($1.getSemanticValue(),$3.getNumericValue(),$5.getNumericValue());}
;
commands:       commands command {
                }
              | command {
 }
;
command:        identifier ASSIGN expression SEMICOLON 	{asm.assignNum($1);}

              | IF condition THEN commands{

              {$2.setJumpToEnd(asm.generateCode(Commands.JUMP,-1));
              }

              } ELSE {asm.getGeneratedCode()[(int)$2.getJumpIfFalse()].setCodeLine(asm.getGeneratedCodeOffset());}

              commands ENDIF {
              asm.getGeneratedCode()[(int)$2.getJumpToEnd()].setCodeLine(asm.getGeneratedCodeOffset());

              }
              | IF condition THEN commands ENDIF {
              asm.getGeneratedCode()[(int)$2.getJumpIfFalse()].setCodeLine(asm.getGeneratedCodeOffset());

               }
              | WHILE {
              JumpHolder j = $1.jumpHolder;
              j.setJumpToStart(asm.getGeneratedCodeOffset());;

              }
              condition DO commands ENDWHILE {
               asm.generateCode(Commands.JUMP,$1.jumpHolder.getJumpToStart());
               asm.getGeneratedCode()[(int)$3.getJumpIfFalse()].setCodeLine(asm.getGeneratedCodeOffset());
              }
              | DO
              				{ JumpHolder j = $1.jumpHolder;
              				j.setJumpToStart(asm.getGeneratedCodeOffset());}
              commands WHILE condition ENDDO {

              	asm.generateCode(Commands.JUMP,$1.jumpHolder.getJumpToStart());
              					asm.getGeneratedCode()[(int)$5.getJumpIfFalse()].setCodeLine(asm.getGeneratedCodeOffset());
              }
              | FOR PIDENTIFIER FROM value {
              				asm.beginFromFor($1.jumpHolder,$2.getSemanticValue());
              }
              TO value DO{

					JumpHolder jumper = $1.jumpHolder;
					jumper.setJumpToStart(asm.generateCode(Commands.LOADI,TOP_STACK));
					asm.generateCode(Commands.SUB,asm.tryToGetvar($2.getSemanticValue()));
					$1.jumpHolder.setJumpIfFalse(asm.generateCode(Commands.JNEG,-1));
              }
              commands ENDFOR {
              					asm.endToFor($1,$2.getSemanticValue());
              }
              | FOR PIDENTIFIER FROM value {
              			asm.beginDownToFor($1.jumpHolder,$2.getSemanticValue());
              }
               DOWNTO value DO {
                                asm.downToValidation($1,$2.getSemanticValue());
               }
                commands ENDFOR {
               					asm.endForCommands($2.getSemanticValue(),$1 );
               }
              | READ identifier SEMICOLON {
              asm.read($2);
              }
              | WRITE value SEMICOLON {
              asm.write();
              }
;
expression:   value {}
              | value PLUS value {asm.plus();}
              | value MINUS value {asm.minus();}
              | value TIMES value {asm.times();}
              | value DIV value {asm.div();}
              | value MOD value {asm.modulo();}
;
condition:      value EQ value {JumpHolder  j = new JumpHolder();
                asm.eq(j);
                $$ =j;
}
              | value NEQ value {JumpHolder  jump = new JumpHolder();asm.neq(jump);$$ = jump;}
              | value LE value { JumpHolder  jump = new JumpHolder();asm.le(jump);$$ = jump;}
              | value GE value {JumpHolder  jump = new JumpHolder();asm.ge(jump);$$ = jump;}
              | value LEQ value {JumpHolder  jump = new JumpHolder();asm.leq(jump);$$ = jump;}
              | value GEQ value {JumpHolder  jump = new JumpHolder();asm.geq(jump);$$ = jump;}
;
value:          NUM {asm.addNewNumber($1.getNumericValue());}
              | identifier {
              asm.valueFromPidValidation($1,scanner.getYyline());
              }
;
identifier:     PIDENTIFIER {

                $$ = asm.tryToGetvar($1.getSemanticValue());
				asm.identifierValidation($1.getSemanticValue());
 }
              | PIDENTIFIER L_BRACKET PIDENTIFIER R_BRACKET {

                asm.tabValidation($1.getSemanticValue(),$3.getSemanticValue());
				$$=-1L;
               }
              | PIDENTIFIER L_BRACKET NUM R_BRACKET{
              $$ = asm.tryToGetvar(((TokenHolder)$1).getSemanticValue(),$3.getNumericValue());
              }
%%
