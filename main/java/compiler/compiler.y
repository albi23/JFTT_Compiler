%language "Java"
%define api.prefix {CompilerBison}
%define api.parser.class {CompilerBison}
%define api.parser.public
%define package {compiler}
%define parse.error verbose
%define api.parser.annotations {@SuppressWarnings("all")}
%debug

%code imports {
import compiler.holder.TokenInfo;
import compiler.holder.types.SimpleType;
import compiler.holder.types.VariableType;
import compiler.validation.Validation;
import compiler.utility.ColorMessage;
import static compiler.utility.ColorMessage.makeColor;
import java.math.BigInteger;
}

%code{

  public void color(String msg){
    System.out.println("\u001b[32m"+msg+"\u001B[0m");
  }
   private static CompilerFlex scanner = null;
   private static  Validation validation;

   public static void main(String argv[]) {
       if (argv.length != 2 ) {
           System.out.println("Usage : java CompilerBison <inputfile>  <outputfile>");
       } else {
           String encodingName = "UTF-8";
           try {
               java.io.FileInputStream stream = new java.io.FileInputStream(argv[0]);
               java.io.Reader reader = new java.io.InputStreamReader(stream, encodingName);
               scanner = new CompilerFlex(reader);
               validation = new Validation(scanner, new AssemblerCodeGenerator(argv[1]));
               CompilerBison compilerBison = new CompilerBison(scanner);
               compilerBison.parse();
           } catch (java.io.FileNotFoundException e) {
               System.out.println("File not found : \"" + argv[0] + "\" or \" +" +argv[1] + "\"");
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

%token NUM
%token PIDENTIFIER
%token COMMA L_BRACKET R_BRACKET SEMICOLON
%token ERROR
%token DECLARE BEGIN END
%token IF WHILE FOR
%token THEN ELSE ENDIF FROM TO DOWNTO ENDFOR ENDWHILE ENDDO COLON DO
%token READ WRITE
%token LE GE LEQ GEQ EQ NEQ
%token ASSIGN


%left PLUS MINUS
%left TIMES DIV MOD
%%

program:        DECLARE declarations{
                System.out.println("Deklaracje :... ");

                }
                BEGIN commands END {
                makeColor(ColorMessage.WHITE,"[1] : DECLARE declarations BEGIN commands END");
                makeColor(ColorMessage.WHITE,"[1] : declarations : "+(TokenInfo)$2+" commands : "+(TokenInfo)$4);
               }
              | BEGIN commands END {
                makeColor(ColorMessage.WHITE,"[2] :  BEGIN commands END");
                makeColor(ColorMessage.WHITE,"[2] : "+(TokenInfo)$2);
              }
;
declarations:  declarations COMMA PIDENTIFIER {
                makeColor(ColorMessage.WHITE,"[3] : declarations COMMA PIDENTIFIER");
                makeColor(ColorMessage.WHITE,"[3] : declarations = "+(TokenInfo)$1+" PIDENTIFIER : "+(TokenInfo)$3);
                this.validation.validateNewPids((TokenInfo)$3, VariableType.VAR);
              }

              | declarations COMMA PIDENTIFIER L_BRACKET NUM COLON NUM R_BRACKET {
                //BigInteger beginArray = (BigInteger)((TokenInfo)$5).getSemanticValue();
                //BigInteger endArray = (BigInteger)(((TokenInfo)$7).getSemanticValue());
                //this.validation.validateArrayDeclarations(beginArray,endArray,(TokenInfo) $3);
                 makeColor(ColorMessage.WHITE,"[4] : declarations COMMA PIDENTIFIER L_BRACKET NUM COLON NUM R_BRACKET");
                 makeColor(ColorMessage.WHITE,"[4] : declarations : "+(TokenInfo)$1+" PIDENTIFIER : "+(TokenInfo)$3+" NUM : "+(TokenInfo)$5+" NUM "+(TokenInfo)$7);

              }
              | PIDENTIFIER {
               this.validation.validateNewPids((TokenInfo)$1, VariableType.VAR);
               makeColor(ColorMessage.GREEN,"[5] : PIDENTIFIER");
               makeColor(ColorMessage.GREEN,"[5] : PIDENTIFIER "+(TokenInfo)$1);
              }

              | PIDENTIFIER L_BRACKET NUM COLON NUM R_BRACKET {
                /** Array declarations control */
                BigInteger beginArray = (BigInteger)((TokenInfo)$3).getSemanticValue();
                BigInteger endArray = (BigInteger)(((TokenInfo)$5).getSemanticValue());
                this.validation.validateArrayDeclarations(beginArray,endArray,(TokenInfo) $1);
                makeColor(ColorMessage.GREEN,"[6] : PIDENTIFIER L_BRACKET NUM COLON NUM R_BRACKET ");
                makeColor(ColorMessage.GREEN,"[6] : PIDENTIFIER "+(TokenInfo)$1+" NUM : "+(TokenInfo) $3+" NUM  : "+(TokenInfo) $5);
              }
;
commands:       commands command {
                    //System.out.println("Mamy komendy : "+(TokenInfo)$1+"\n oraz : "+(TokenInfo)$2);
                    makeColor(ColorMessage.YELLOW,"[7] : commands command ");
                    makeColor(ColorMessage.YELLOW,"[7] : commands : "+(TokenInfo)$1+"  command "+(TokenInfo)$2);
                }
              | command {
                     //System.out.println("Mamy komendę : "+(TokenInfo)$1);
                     makeColor(ColorMessage.YELLOW,"[8] : command ");
                     makeColor(ColorMessage.YELLOW,"[8] : command : "+(TokenInfo)$1);
               }
;
command:        identifier ASSIGN expression SEMICOLON {
                 TokenInfo tokenInfo =(TokenInfo) $1;
                 //tokenInfo = this.validation.assign((TokenInfo) $1,(TokenInfo) $3);
                 //makeColor(ColorMessage.BLUE,"identifier : "+$1+"  expression : "+$3);
                 //makeColor(ColorMessage.BLUE,"after change : "+tokenInfo);
                 makeColor(ColorMessage.BLUE,"[9] : identifier ASSIGN expression SEMICOLON ");
                 makeColor(ColorMessage.BLUE,"[9] : identifier : "+(TokenInfo) $1+" expression : "+(TokenInfo) $3);
                }
              | IF condition THEN commands ELSE commands ENDIF {
                  makeColor(ColorMessage.BLUE,"[10] : IF condition THEN commands ELSE commands ENDIF");
                  makeColor(ColorMessage.BLUE,"[10] : condition: "+(TokenInfo) $2+" commands: "+(TokenInfo) $4+" commands2 "+(TokenInfo) $6);
              }
              | IF condition THEN commands ENDIF {

                makeColor(ColorMessage.BLUE,"[11] : IF condition THEN commands ENDIF");
                makeColor(ColorMessage.BLUE,"[11] : condition: "+(TokenInfo) $2+" commands: "+(TokenInfo)$4);
              }
              | WHILE condition DO commands ENDWHILE {
                makeColor(ColorMessage.BLUE,"[12] : WHILE condition DO commands ENDWHILE");
                makeColor(ColorMessage.BLUE,"[12] :  condition: "+(TokenInfo) $2+" commands "+(TokenInfo) $4);
              }
              | DO commands WHILE condition ENDDO {
                 makeColor(ColorMessage.BLUE,"[13] : DO commands WHILE condition ENDDO");
                 makeColor(ColorMessage.BLUE,"[13] : commands: "+(TokenInfo) $2+" condition: "+(TokenInfo) $1);
              }
              | FOR PIDENTIFIER FROM value { System.out.println("FOR FROM VALUE "+(TokenInfo)$4); }
              TO value { {System.out.println(" TO VALUE  "+(TokenInfo)$2+" DO COMMANDS : ");} }
              DO commands ENDFOR {
                System.out.println("KONIEC FORA !!");
               makeColor(ColorMessage.BLUE,"[14] : FOR PIDENTIFIER FROM value TO value DO commands ENDFOR");
               makeColor(ColorMessage.BLUE,"[14] : PIDENTIFIER : "+(TokenInfo) $2+" value : "+(TokenInfo) $4+" value2 : "+(TokenInfo) $6+" commands "+(TokenInfo) $8);
               makeColor(ColorMessage.BLUE,"[14] : ENDFOR : "+(TokenInfo) $3);
                //System.out.println("Iterujemy za pomocą  :"+$2);
                //System.out.println("FROM                 :"+$4);
                //System.out.println("TO                   : "+$6);
              }

              | FOR PIDENTIFIER FROM value { System.out.println("FOR FROM VALUE "+(TokenInfo)$4); }
               DOWNTO value {System.out.println(" DOWNTO VALUE"+(TokenInfo)$2);}
               DO commands ENDFOR {

                makeColor(ColorMessage.BLUE,"[15] : FOR PIDENTIFIER FROM value DOWNTO value DO commands ENDFOR");
                makeColor(ColorMessage.BLUE,"[15] : PIDENTIFIER : "+(TokenInfo) $2+" value : "+(TokenInfo) $4+" value2 : "+(TokenInfo) $6+" commands "+(TokenInfo) $8);
                //System.out.println("Iterujemy za pomocą  :"+$2);
                //System.out.println("FROM                 :"+$4);
                //System.out.println("TO                   : "+$6);
                }
              | READ identifier SEMICOLON {
                makeColor(ColorMessage.BLUE,"[16] : READ identifier SEMICOLON ");
                makeColor(ColorMessage.BLUE,"[16] :  identifier: "+(TokenInfo) $1);
                //validation.validationOfVariableValues((TokenInfo)$1);
              }
              | WRITE value SEMICOLON {
                makeColor(ColorMessage.BLUE,"[17] : WRITE value SEMICOLON ");
                makeColor(ColorMessage.BLUE,"[17] :  value  : "+(TokenInfo) $2);
                //this.validation.validateToWriteToken((TokenInfo)$2);
              }
;
expression:   value {
                //System.out.println("value : "+(TokenInfo)$1);
                makeColor(ColorMessage.PINK,"[18] :  value  ");
                makeColor(ColorMessage.PINK,"[18] :  value : "+(TokenInfo)$1);

              }
              | value PLUS value {
                makeColor(ColorMessage.PINK,"[19] :  value  PLUS value");
                makeColor(ColorMessage.PINK,"[19] :  value1:  "+(TokenInfo)$1+" value2 : "+(TokenInfo)$3);
                //this.validation.add((TokenInfo)$$,(TokenInfo)$3);
                //makeColor(ColorMessage.GREEN,"(value PLUS value) $$ =  "+$$+" $1 = "+ $1+" $2 =  "+$3);
              }

              | value MINUS value {
              /** value MINUS value */
              makeColor(ColorMessage.PINK,"[20] :  value  MINUS value");
              makeColor(ColorMessage.PINK,"[20] :  value1:  "+(TokenInfo)$1+" value2 : "+(TokenInfo)$3);

              }
              | value TIMES value {
              /** value TIMES value */
              makeColor(ColorMessage.PINK,"[21] :  value  TIMES value");
              makeColor(ColorMessage.PINK,"[21] :  value1:  "+(TokenInfo)$1+" value2 : "+(TokenInfo)$3);
              }
              | value DIV value {/** value DIV value */
              makeColor(ColorMessage.PINK,"[22] :  value  DIV value");
              makeColor(ColorMessage.PINK,"[22] :  value1:  "+(TokenInfo)$1+" value2 : "+(TokenInfo)$3);
              }
              | value MOD value {/** value MOD value */
              makeColor(ColorMessage.PINK,"[23] :  value  MOD value");
              makeColor(ColorMessage.PINK,"[23] :  value1:  "+(TokenInfo)$1+" value2 : "+(TokenInfo)$3);
              }
;
condition:      value EQ value {/** value EQ value */

                makeColor(ColorMessage.RED,"[24] :  value  EQ value");
                makeColor(ColorMessage.RED,"[24] :  value1:  "+(TokenInfo)$1+" value2 : "+(TokenInfo)$3);
                }
              | value NEQ value {/** value NEQ value */
                makeColor(ColorMessage.RED,"[25] :  value  NEQ value");
                makeColor(ColorMessage.RED,"[25] :  value1:  "+(TokenInfo)$1+" value2 : "+(TokenInfo)$3);

              }
              | value LE value {/** value LE value */
                makeColor(ColorMessage.RED,"[26] :  value  LE value");
                makeColor(ColorMessage.RED,"[26] :  value1:  "+(TokenInfo)$1+" value2 : "+(TokenInfo)$3);

              }
              | value GE value {/** value GE value */
                makeColor(ColorMessage.RED,"[27] :  value  GE value");
                makeColor(ColorMessage.RED,"[27] :  value1:  "+(TokenInfo)$1+" value2 : "+(TokenInfo)$3);

              }
              | value LEQ value {/** value LEQ value */
                makeColor(ColorMessage.RED,"[28] :  value  LEQ value");
                makeColor(ColorMessage.RED,"[28] :  value1:  "+(TokenInfo)$1+" value2 : "+(TokenInfo)$3);

              }
              | value GEQ value {/** value GEQ value */
                makeColor(ColorMessage.RED,"[29] :  value  GEQ value");
                makeColor(ColorMessage.RED,"[29] :  value1:  "+(TokenInfo)$1+" value2 : "+(TokenInfo)$3);

              }
;
value:          NUM {
                     //((TokenInfo)$1).setValue((BigInteger)((TokenInfo)$1).getSemanticValue());
                     //makeColor(ColorMessage.PINK,"NUM "+$$);
                      makeColor(ColorMessage.RED,"[30] :  value.NUM ");
                      makeColor(ColorMessage.RED,"[30] :  NUM "+(TokenInfo)$1);
               }
              | identifier {
                //makeColor(ColorMessage.PINK,"value.identifier "+$$);
                makeColor(ColorMessage.RED,"[31] : value.identifier ");
                makeColor(ColorMessage.RED,"[31] : value.identifier "+(TokenInfo)$1);
              }
;
identifier:     PIDENTIFIER {

                /* if (yystack.height >= 5){
                   if (((TokenInfo)yystack.valueAt (0)).getBeforeTokenId() == Lexer.FROM){
                        TokenInfo forIterator = (TokenInfo) yystack.valueAt(2);
                        forIterator.setType(new SimpleType(new BigInteger("-1")));
                        this.validation.pidIdOnInfo.put((String) forIterator.getSemanticValue(),forIterator);
                   }
                 }*/
                //this.validation.getValueFromToken((TokenInfo)$1);
                makeColor(ColorMessage.GREEN,"[32] : identifier -> PIDENTIFIER "+$$);
                }

              | PIDENTIFIER L_BRACKET PIDENTIFIER R_BRACKET {
                //this.validation.getArrValueFromToken((TokenInfo)$1,(TokenInfo)$3 );
                //makeColor(ColorMessage.RED,"value.identifier.PIDENTIFIER tab(pid) "+$$);
                makeColor(ColorMessage.GREEN,"[33] PIDENTIFIER L_BRACKET PIDENTIFIER R_BRACKET ");
                makeColor(ColorMessage.GREEN,"[33] PIDENTIFIER "+(TokenInfo)$1+" PIDENTIFIER "+(TokenInfo)$3);
              }
              | PIDENTIFIER L_BRACKET NUM R_BRACKET{
                makeColor(ColorMessage.GREEN,"[34] PIDENTIFIER L_BRACKET NUM R_BRACKET ");
                makeColor(ColorMessage.GREEN,"[34] PIDENTIFIER "+(TokenInfo)$1+" PIDENTIFIER "+(TokenInfo)$3);

                //this.validation.getArrValueFromToken((TokenInfo)$1,(BigInteger)(((TokenInfo)$3).getSemanticValue()) );
              }
%%
