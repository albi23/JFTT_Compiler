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
import compiler.validation.Validation;

import java.math.BigInteger;
}

%code{

  /**
   * ===================================================================================
   */
   private static CompilerFlex scanner = null;
   private static  Validation validation;

   public static void main(String argv[]) {
           if (argv.length == 0) {
               System.out.println("Usage : java CompilerBison [ --encoding <name> ] <inputfile(s)>");
           }
           else {
               int firstFilePos = 0;
               String encodingName = "UTF-8";
               if (argv[0].equals("--encoding")) {
                   firstFilePos = 2;
                   encodingName = argv[1];
                   try {
                       java.nio.charset.Charset.forName(encodingName); // Side-effect: is encodingName valid?
                   } catch (Exception e) {
                       System.out.println("Invalid encoding '" + encodingName + "'");
                       return;
                   }
               }
               for (int i = firstFilePos; i < argv.length; i++) {

                   try {
                       java.io.FileInputStream stream = new java.io.FileInputStream(argv[i]);
                       java.io.Reader reader = new java.io.InputStreamReader(stream, encodingName);
                       scanner = new CompilerFlex(reader);
                       validation = new Validation(scanner);
                       CompilerBison compilerBison = new CompilerBison(scanner);
                       compilerBison.parse();
                   }
                   catch (java.io.FileNotFoundException e) {
                       System.out.println("File not found : \""+argv[i]+"\"");
                   }
                   catch (java.io.IOException e) {
                       System.out.println("IO error scanning file \""+argv[i]+"\"");
                       System.out.println(e);
                   }
                   catch (Exception e) {
                       System.out.println("Unexpected exception:");
                       e.printStackTrace();
                   }
               }
           }
       }
   }


%token COMMA L_BRACKET R_BRACKET SEMICOLON
%token ERROR
%token DECLARE BEGIN END
%token IF WHILE DO
%token FOR
%token THEN ELSE ENDIF FROM TO DOWNTO ENDFOR ENDWHILE ENDDO COLON
%token READ WRITE
%token LE GE LEQ GEQ EQ NEQ
%token ASSIGN
%token PIDENTIFIER
%token NUM

%left PLUS MINUS
%left TIMES DIV MOD
%%

program:        DECLARE declarations BEGIN commands END {}
              | BEGIN commands END {}
;
declarations:  declarations COMMA PIDENTIFIER {
                this.validation.validateNewPids((TokenInfo)$3);
              }

              | declarations COMMA PIDENTIFIER L_BRACKET NUM COLON NUM R_BRACKET {
                BigInteger beginArray = (BigInteger)((TokenInfo)$5).getSemanticValue();
                BigInteger endArray = (BigInteger)(((TokenInfo)$7).getSemanticValue());
                this.validation.validateArrayDeclarations(beginArray,endArray,(TokenInfo) $3);

              }
              | PIDENTIFIER {
                this.validation.validateNewPids((TokenInfo)$1);
              }

              | PIDENTIFIER L_BRACKET NUM COLON NUM R_BRACKET {

                /** Array declarations control */
                BigInteger beginArray = (BigInteger)((TokenInfo)$3).getSemanticValue();
                BigInteger endArray = (BigInteger)(((TokenInfo)$5).getSemanticValue());
                this.validation.validateArrayDeclarations(beginArray,endArray,(TokenInfo) $1);
              }
;
commands:       commands command {}
              | command {}
;
command:        identifier ASSIGN expression SEMICOLON { /*System.out.println(" przypisanie wartości w linijce : "+scanner.getYyline());*/}
              | IF condition THEN commands ELSE commands ENDIF {}
              | IF condition THEN commands ENDIF {}
              | WHILE condition DO commands ENDWHILE {}
              | DO commands WHILE condition ENDDO {}
              | FOR PIDENTIFIER FROM value TO value DO commands ENDFOR {}
              | FOR PIDENTIFIER FROM value DOWNTO value DO commands ENDFOR {}
              | READ identifier SEMICOLON {
                validation.validationOfVariableValues((TokenInfo)$1);
              }
              | WRITE value SEMICOLON {
                System.out.println("Very important info00o !!!"+$2);
              }
;
expression:     value {/** value */}
              | value PLUS value { /** value PLUS value */}
              | value MINUS value {/** value MINUS value */}
              | value TIMES value {/** value TIMES value */}
              | value DIV value {/** value DIV value */}
              | value MOD value {/** value MOD value */}
;
condition:      value EQ value {/** value EQ value */}
              | value NEQ value {/** value NEQ value */}
              | value LE value {/** value LE value */}
              | value GE value {/** value GE value */}
              | value LEQ value {/** value LEQ value */}
              | value GEQ value {/** value GEQ value */}
;
value:          NUM { System.out.println("Mam numerek :D ");}
              | identifier {/** value */}
;
identifier:     PIDENTIFIER { }
              | PIDENTIFIER L_BRACKET PIDENTIFIER R_BRACKET {}
              | PIDENTIFIER L_BRACKET NUM R_BRACKET     {/*System.out.println("\u001b[48;5;160m Inicjalizacja tablicy \u001b[0m");*/}
%%
