/* A Bison parser, made by GNU Bison 3.3.2.  */

/* Skeleton implementation for Bison LALR(1) parsers in Java

   Copyright (C) 2007-2015, 2018 Free Software Foundation, Inc.

   This program is free software: you can redistribute it and/or modify
   it under the terms of the GNU General Public License as published by
   the Free Software Foundation, either version 3 of the License, or
   (at your option) any later version.

   This program is distributed in the hope that it will be useful,
   but WITHOUT ANY WARRANTY; without even the implied warranty of
   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
   GNU General Public License for more details.

   You should have received a copy of the GNU General Public License
   along with this program.  If not, see <http://www.gnu.org/licenses/>.  */

/* As a special exception, you may create a larger work that contains
   part or all of the Bison parser skeleton and distribute that work
   under terms of your choice, so long as that work isn't itself a
   parser generator using the skeleton or a modified version thereof
   as a parser skeleton.  Alternatively, if you modify or redistribute
   the parser skeleton itself, you may (at your option) remove this
   special exception, which will cause the skeleton and the resulting
   Bison output files to be licensed under the GNU General Public
   License without this special exception.

   This special exception was added by the Free Software Foundation in
   version 2.2 of Bison.  */

package compiler;



/* "%code imports" blocks.  */
/* "compiler.y":10  */ /* lalr1.java:92  */

import compiler.assembly.AssemblerCodeGenerator;
import compiler.holder.TokenInfo;
import compiler.holder.types.SimpleType;
import compiler.holder.types.VariableType;
import compiler.validation.Validation;
import compiler.utility.ColorMessage;
import static compiler.utility.ColorMessage.makeColor;
import java.math.BigInteger;
import java.util.Scanner;

/* "CompilerBison.java":51  */ /* lalr1.java:92  */

/**
 * A Bison parser, automatically generated from <tt>compiler.y</tt>.
 *
 * @author LALR (1) parser skeleton written by Paolo Bonzini.
 */
@SuppressWarnings("all") public class CompilerBison
{
    /** Version number for the Bison executable that generated this parser.  */
  public static final String bisonVersion = "3.3.2";

  /** Name of the skeleton that generated this parser.  */
  public static final String bisonSkeleton = "lalr1.java";


  /**
   * True if verbose error messages are enabled.
   */
  private boolean yyErrorVerbose = true;

  /**
   * Return whether verbose error messages are enabled.
   */
  public final boolean getErrorVerbose() { return yyErrorVerbose; }

  /**
   * Set the verbosity of error messages.
   * @param verbose True to request verbose error messages.
   */
  public final void setErrorVerbose(boolean verbose)
  { yyErrorVerbose = verbose; }




  

  /**
   * Communication interface between the scanner and the Bison-generated
   * parser <tt>CompilerBison</tt>.
   */
  public interface Lexer {
    /** Token returned by the scanner to signal the end of its input.  */
    public static final int EOF = 0;

/* Tokens.  */
    /** Token number,to be returned by the scanner.  */
    static final int NUM = 258;
    /** Token number,to be returned by the scanner.  */
    static final int PIDENTIFIER = 259;
    /** Token number,to be returned by the scanner.  */
    static final int COMMA = 260;
    /** Token number,to be returned by the scanner.  */
    static final int L_BRACKET = 261;
    /** Token number,to be returned by the scanner.  */
    static final int R_BRACKET = 262;
    /** Token number,to be returned by the scanner.  */
    static final int SEMICOLON = 263;
    /** Token number,to be returned by the scanner.  */
    static final int ERROR = 264;
    /** Token number,to be returned by the scanner.  */
    static final int DECLARE = 265;
    /** Token number,to be returned by the scanner.  */
    static final int BEGIN = 266;
    /** Token number,to be returned by the scanner.  */
    static final int END = 267;
    /** Token number,to be returned by the scanner.  */
    static final int IF = 268;
    /** Token number,to be returned by the scanner.  */
    static final int WHILE = 269;
    /** Token number,to be returned by the scanner.  */
    static final int FOR = 270;
    /** Token number,to be returned by the scanner.  */
    static final int THEN = 271;
    /** Token number,to be returned by the scanner.  */
    static final int ELSE = 272;
    /** Token number,to be returned by the scanner.  */
    static final int ENDIF = 273;
    /** Token number,to be returned by the scanner.  */
    static final int FROM = 274;
    /** Token number,to be returned by the scanner.  */
    static final int TO = 275;
    /** Token number,to be returned by the scanner.  */
    static final int DOWNTO = 276;
    /** Token number,to be returned by the scanner.  */
    static final int ENDFOR = 277;
    /** Token number,to be returned by the scanner.  */
    static final int ENDWHILE = 278;
    /** Token number,to be returned by the scanner.  */
    static final int ENDDO = 279;
    /** Token number,to be returned by the scanner.  */
    static final int COLON = 280;
    /** Token number,to be returned by the scanner.  */
    static final int DO = 281;
    /** Token number,to be returned by the scanner.  */
    static final int READ = 282;
    /** Token number,to be returned by the scanner.  */
    static final int WRITE = 283;
    /** Token number,to be returned by the scanner.  */
    static final int LE = 284;
    /** Token number,to be returned by the scanner.  */
    static final int GE = 285;
    /** Token number,to be returned by the scanner.  */
    static final int LEQ = 286;
    /** Token number,to be returned by the scanner.  */
    static final int GEQ = 287;
    /** Token number,to be returned by the scanner.  */
    static final int EQ = 288;
    /** Token number,to be returned by the scanner.  */
    static final int NEQ = 289;
    /** Token number,to be returned by the scanner.  */
    static final int ASSIGN = 290;
    /** Token number,to be returned by the scanner.  */
    static final int PLUS = 291;
    /** Token number,to be returned by the scanner.  */
    static final int MINUS = 292;
    /** Token number,to be returned by the scanner.  */
    static final int TIMES = 293;
    /** Token number,to be returned by the scanner.  */
    static final int DIV = 294;
    /** Token number,to be returned by the scanner.  */
    static final int MOD = 295;


    

    /**
     * Method to retrieve the semantic value of the last scanned token.
     * @return the semantic value of the last scanned token.
     */
    Object getLVal ();

    /**
     * Entry point for the scanner.  Returns the token identifier corresponding
     * to the next token and prepares to return the semantic value
     * of the token.
     * @return the token identifier corresponding to the next token.
     */
    int yylex () throws java.io.IOException;

    /**
     * Entry point for error reporting.  Emits an error
     * in a user-defined way.
     *
     * 
     * @param msg The string for the error message.
     */
     void yyerror (String msg);
  }

  /**
   * The object doing lexical analysis for us.
   */
  private Lexer yylexer;
  
  



  /**
   * Instantiates the Bison-generated parser.
   * @param yylexer The scanner that will supply tokens to the parser.
   */
  public CompilerBison (Lexer yylexer) 
  {
    
    this.yylexer = yylexer;
    
  }

  private java.io.PrintStream yyDebugStream = System.err;

  /**
   * Return the <tt>PrintStream</tt> on which the debugging output is
   * printed.
   */
  public final java.io.PrintStream getDebugStream () { return yyDebugStream; }

  /**
   * Set the <tt>PrintStream</tt> on which the debug output is printed.
   * @param s The stream that is used for debugging output.
   */
  public final void setDebugStream(java.io.PrintStream s) { yyDebugStream = s; }

  private int yydebug = 0;

  /**
   * Answer the verbosity of the debugging output; 0 means that all kinds of
   * output from the parser are suppressed.
   */
  public final int getDebugLevel() { return yydebug; }

  /**
   * Set the verbosity of the debugging output; 0 means that all kinds of
   * output from the parser are suppressed.
   * @param level The verbosity level for debugging output.
   */
  public final void setDebugLevel(int level) { yydebug = level; }

  /**
   * Print an error message via the lexer.
   *
   * @param msg The error message.
   */
  public final void yyerror (String msg)
  {
    yylexer.yyerror (msg);
  }


  protected final void yycdebug (String s) {
    if (yydebug > 0)
      yyDebugStream.println (s);
  }

  private final class YYStack {
    private int[] stateStack = new int[16];
    
    private Object[] valueStack = new Object[16];

    public int size = 16;
    public int height = -1;

    public final void push (int state, Object value                            ) {
      height++;
      if (size == height)
        {
          int[] newStateStack = new int[size * 2];
          System.arraycopy (stateStack, 0, newStateStack, 0, height);
          stateStack = newStateStack;
          

          Object[] newValueStack = new Object[size * 2];
          System.arraycopy (valueStack, 0, newValueStack, 0, height);
          valueStack = newValueStack;

          size *= 2;
        }

      stateStack[height] = state;
      
      valueStack[height] = value;
    }

    public final void pop () {
      pop (1);
    }

    public final void pop (int num) {
      // Avoid memory leaks... garbage collection is a white lie!
      if (num > 0) {
        java.util.Arrays.fill (valueStack, height - num + 1, height + 1, null);
        
      }
      height -= num;
    }

    public final int stateAt (int i) {
      return stateStack[height - i];
    }

    public final Object valueAt (int i) {
      return valueStack[height - i];
    }

    // Print the state stack on the debug stream.
    public void print (java.io.PrintStream out) {
      out.print ("Stack now");

      for (int i = 0; i <= height; i++)
        {
          out.print (' ');
          out.print (stateStack[i]);
        }
      out.println ();
    }
  }

  /**
   * Returned by a Bison action in order to stop the parsing process and
   * return success (<tt>true</tt>).
   */
  public static final int YYACCEPT = 0;

  /**
   * Returned by a Bison action in order to stop the parsing process and
   * return failure (<tt>false</tt>).
   */
  public static final int YYABORT = 1;



  /**
   * Returned by a Bison action in order to start error recovery without
   * printing an error message.
   */
  public static final int YYERROR = 2;

  /**
   * Internal return codes that are not supported for user semantic
   * actions.
   */
  private static final int YYERRLAB = 3;
  private static final int YYNEWSTATE = 4;
  private static final int YYDEFAULT = 5;
  private static final int YYREDUCE = 6;
  private static final int YYERRLAB1 = 7;
  private static final int YYRETURN = 8;


  private int yyerrstatus_ = 0;


  /**
   * Return whether error recovery is being done.  In this state, the parser
   * reads token until it reaches a known state, and then restarts normal
   * operation.
   */
  public final boolean recovering ()
  {
    return yyerrstatus_ == 0;
  }

  /** Compute post-reduction state.
   * @param yystate   the current state
   * @param yysym     the nonterminal to push on the stack
   */
  private int yy_lr_goto_state_ (int yystate, int yysym)
  {
    int yyr = yypgoto_[yysym - yyntokens_] + yystate;
    if (0 <= yyr && yyr <= yylast_ && yycheck_[yyr] == yystate)
      return yytable_[yyr];
    else
      return yydefgoto_[yysym - yyntokens_];
  }

  private int yyaction (int yyn, YYStack yystack, int yylen) 
  {
    Object yyval;
    

    /* If YYLEN is nonzero, implement the default value of the action:
       '$$ = $1'.  Otherwise, use the top of the stack.

       Otherwise, the following line sets YYVAL to garbage.
       This behavior is undocumented and Bison
       users should not rely upon it.  */
    if (yylen > 0)
      yyval = yystack.valueAt (yylen - 1);
    else
      yyval = yystack.valueAt (0);

    yy_reduce_print (yyn, yystack);

    switch (yyn)
      {
          case 2:
  if (yyn == 2)
    /* "compiler.y":74  */ /* lalr1.java:480  */
    {
                System.out.println("Deklaracje :... ");

                };
  break;
    

  case 3:
  if (yyn == 3)
    /* "compiler.y":78  */ /* lalr1.java:480  */
    {
                makeColor(ColorMessage.WHITE,"[1] : DECLARE declarations BEGIN commands END");
                makeColor(ColorMessage.WHITE,"[1] : declarations : "+(TokenInfo)yystack.valueAt (4)+" commands : "+(TokenInfo)yystack.valueAt (2));
               };
  break;
    

  case 4:
  if (yyn == 4)
    /* "compiler.y":82  */ /* lalr1.java:480  */
    {
                makeColor(ColorMessage.WHITE,"[2] :  BEGIN commands END");
                makeColor(ColorMessage.WHITE,"[2] : "+(TokenInfo)yystack.valueAt (1));
              };
  break;
    

  case 5:
  if (yyn == 5)
    /* "compiler.y":87  */ /* lalr1.java:480  */
    {
                makeColor(ColorMessage.WHITE,"[3] : declarations COMMA PIDENTIFIER");
                makeColor(ColorMessage.WHITE,"[3] : declarations = "+(TokenInfo)yystack.valueAt (2)+" PIDENTIFIER : "+(TokenInfo)yystack.valueAt (0));
                this.validation.validateNewPids((TokenInfo)yystack.valueAt (0), VariableType.VAR);
              };
  break;
    

  case 6:
  if (yyn == 6)
    /* "compiler.y":93  */ /* lalr1.java:480  */
    {
                BigInteger beginArray = (BigInteger)((TokenInfo)yystack.valueAt (3)).getSemanticValue();
                BigInteger endArray = (BigInteger)(((TokenInfo)yystack.valueAt (1)).getSemanticValue());
                this.validation.validateArrayDeclarations(beginArray,endArray,(TokenInfo) yystack.valueAt (5));
                 makeColor(ColorMessage.WHITE,"[4] : declarations COMMA PIDENTIFIER L_BRACKET NUM COLON NUM R_BRACKET");
                 makeColor(ColorMessage.WHITE,"[4] : declarations : "+(TokenInfo)yystack.valueAt (7)+" PIDENTIFIER : "+(TokenInfo)yystack.valueAt (5)+" NUM : "+(TokenInfo)yystack.valueAt (3)+" NUM "+(TokenInfo)yystack.valueAt (1));

              };
  break;
    

  case 7:
  if (yyn == 7)
    /* "compiler.y":101  */ /* lalr1.java:480  */
    {
               this.validation.validateNewPids((TokenInfo)yystack.valueAt (0), VariableType.VAR);
               makeColor(ColorMessage.GREEN,"[5] : PIDENTIFIER");
               makeColor(ColorMessage.GREEN,"[5] : PIDENTIFIER "+(TokenInfo)yystack.valueAt (0));
              };
  break;
    

  case 8:
  if (yyn == 8)
    /* "compiler.y":107  */ /* lalr1.java:480  */
    {
                /** Array declarations control */
                BigInteger beginArray = (BigInteger)((TokenInfo)yystack.valueAt (3)).getSemanticValue();
                BigInteger endArray = (BigInteger)(((TokenInfo)yystack.valueAt (1)).getSemanticValue());
                this.validation.validateArrayDeclarations(beginArray,endArray,(TokenInfo) yystack.valueAt (5));
                makeColor(ColorMessage.GREEN,"[6] : PIDENTIFIER L_BRACKET NUM COLON NUM R_BRACKET ");
                makeColor(ColorMessage.GREEN,"[6] : PIDENTIFIER "+(TokenInfo)yystack.valueAt (5)+" NUM : "+(TokenInfo) yystack.valueAt (3)+" NUM  : "+(TokenInfo) yystack.valueAt (1));
              };
  break;
    

  case 9:
  if (yyn == 9)
    /* "compiler.y":116  */ /* lalr1.java:480  */
    {
                    //System.out.println("Mamy komendy : "+(TokenInfo)$1+"\n oraz : "+(TokenInfo)$2);
                    makeColor(ColorMessage.YELLOW,"[7] : commands command ");
                    makeColor(ColorMessage.YELLOW,"[7] : commands : "+(TokenInfo)yystack.valueAt (1)+"  command "+(TokenInfo)yystack.valueAt (0));
                };
  break;
    

  case 10:
  if (yyn == 10)
    /* "compiler.y":121  */ /* lalr1.java:480  */
    {
                     //System.out.println("Mamy komendę : "+(TokenInfo)$1);
                     makeColor(ColorMessage.YELLOW,"[8] : command ");
                     makeColor(ColorMessage.YELLOW,"[8] : command : "+(TokenInfo)yystack.valueAt (0));
               };
  break;
    

  case 11:
  if (yyn == 11)
    /* "compiler.y":127  */ /* lalr1.java:480  */
    {
                 TokenInfo tokenInfo =(TokenInfo) yystack.valueAt (3);
                 //tokenInfo = this.validation.assign((TokenInfo) $1,(TokenInfo) $3);
                 //makeColor(ColorMessage.BLUE,"identifier : "+$1+"  expression : "+$3);
                 //makeColor(ColorMessage.BLUE,"after change : "+tokenInfo);
                 makeColor(ColorMessage.BLUE,"[9] : identifier ASSIGN expression SEMICOLON ");
                 makeColor(ColorMessage.BLUE,"[9] : identifier : "+(TokenInfo) yystack.valueAt (3)+" expression : "+(TokenInfo) yystack.valueAt (1));
                };
  break;
    

  case 12:
  if (yyn == 12)
    /* "compiler.y":135  */ /* lalr1.java:480  */
    {
                  makeColor(ColorMessage.BLUE,"[10] : IF condition THEN commands ELSE commands ENDIF");
                  makeColor(ColorMessage.BLUE,"[10] : condition: "+(TokenInfo) yystack.valueAt (5)+" commands: "+(TokenInfo) yystack.valueAt (3)+" commands2 "+(TokenInfo) yystack.valueAt (1));
              };
  break;
    

  case 13:
  if (yyn == 13)
    /* "compiler.y":139  */ /* lalr1.java:480  */
    {

                makeColor(ColorMessage.BLUE,"[11] : IF condition THEN commands ENDIF");
                makeColor(ColorMessage.BLUE,"[11] : condition: "+(TokenInfo) yystack.valueAt (3)+" commands: "+(TokenInfo)yystack.valueAt (1));
              };
  break;
    

  case 14:
  if (yyn == 14)
    /* "compiler.y":144  */ /* lalr1.java:480  */
    {
                makeColor(ColorMessage.BLUE,"[12] : WHILE condition DO commands ENDWHILE");
                makeColor(ColorMessage.BLUE,"[12] :  condition: "+(TokenInfo) yystack.valueAt (3)+" commands "+(TokenInfo) yystack.valueAt (1));
              };
  break;
    

  case 15:
  if (yyn == 15)
    /* "compiler.y":148  */ /* lalr1.java:480  */
    {
                 makeColor(ColorMessage.BLUE,"[13] : DO commands WHILE condition ENDDO");
                 makeColor(ColorMessage.BLUE,"[13] : commands: "+(TokenInfo) yystack.valueAt (3)+" condition: "+(TokenInfo) yystack.valueAt (4));
              };
  break;
    

  case 16:
  if (yyn == 16)
    /* "compiler.y":152  */ /* lalr1.java:480  */
    {

               this.validation.loopIteratorPid((TokenInfo)yystack.valueAt (2),(TokenInfo)yystack.valueAt (0),VariableType.ITERATOR);
              System.out.println("FOR "+(TokenInfo)yystack.valueAt (2)+" FROM VALUE "+(TokenInfo)yystack.valueAt (0));
              };
  break;
    

  case 17:
  if (yyn == 17)
    /* "compiler.y":157  */ /* lalr1.java:480  */
    {
                System.out.println(" TO VALUE  "+(TokenInfo)yystack.valueAt (5)+" DO COMMANDS : ");
              };
  break;
    

  case 18:
  if (yyn == 18)
    /* "compiler.y":160  */ /* lalr1.java:480  */
    {
                System.out.println("KONIEC FORA !!");
               makeColor(ColorMessage.BLUE,"[14] : FOR PIDENTIFIER FROM value TO value DO commands ENDFOR");
               makeColor(ColorMessage.BLUE,"[14] : PIDENTIFIER : "+(TokenInfo) yystack.valueAt (9)+" value : "+(TokenInfo) yystack.valueAt (7)+" value2 : "+(TokenInfo) yystack.valueAt (5)+" commands "+(TokenInfo) yystack.valueAt (3));
               makeColor(ColorMessage.BLUE,"[14] : ENDFOR : "+(TokenInfo) yystack.valueAt (8));
               this.validation.removeLoopIteratorDeclarations((TokenInfo) yystack.valueAt (9));
              };
  break;
    

  case 19:
  if (yyn == 19)
    /* "compiler.y":168  */ /* lalr1.java:480  */
    {

                this.validation.loopIteratorPid((TokenInfo)yystack.valueAt (2),(TokenInfo)yystack.valueAt (0),VariableType.ITERATOR);
                System.out.println("FOR "+(TokenInfo)yystack.valueAt (2)+" FROM VALUE "+(TokenInfo)yystack.valueAt (0));
              };
  break;
    

  case 20:
  if (yyn == 20)
    /* "compiler.y":173  */ /* lalr1.java:480  */
    {
               System.out.println(" DOWNTO VALUE"+(TokenInfo)yystack.valueAt (5));
               };
  break;
    

  case 21:
  if (yyn == 21)
    /* "compiler.y":176  */ /* lalr1.java:480  */
    {
                System.out.println("KONIEC FORA !!");
                makeColor(ColorMessage.BLUE,"[15] : FOR PIDENTIFIER FROM value DOWNTO value DO commands ENDFOR");
                makeColor(ColorMessage.BLUE,"[15] : PIDENTIFIER : "+(TokenInfo) yystack.valueAt (9)+" value : "+(TokenInfo) yystack.valueAt (7)+" value2 : "+(TokenInfo) yystack.valueAt (5)+" commands "+(TokenInfo) yystack.valueAt (3));
                this.validation.removeLoopIteratorDeclarations((TokenInfo) yystack.valueAt (9));
                };
  break;
    

  case 22:
  if (yyn == 22)
    /* "compiler.y":182  */ /* lalr1.java:480  */
    {
                makeColor(ColorMessage.BLUE,"[16] : READ identifier SEMICOLON ");
                makeColor(ColorMessage.BLUE,"[16] :  identifier: "+(TokenInfo) yystack.valueAt (1));
                this.validation.readFromInput(numbersInput,(TokenInfo)yystack.valueAt (1));
              };
  break;
    

  case 23:
  if (yyn == 23)
    /* "compiler.y":187  */ /* lalr1.java:480  */
    {
                makeColor(ColorMessage.BLUE,"[17] : WRITE value SEMICOLON ");
                makeColor(ColorMessage.BLUE,"[17] :  value  : "+(TokenInfo) yystack.valueAt (1));
                this.validation.validateToWriteToken((TokenInfo)yystack.valueAt (1));
              };
  break;
    

  case 24:
  if (yyn == 24)
    /* "compiler.y":193  */ /* lalr1.java:480  */
    {
                makeColor(ColorMessage.PINK,"[18] :  value  ");
                makeColor(ColorMessage.PINK,"[18] :  value : "+(TokenInfo)yystack.valueAt (0));

              };
  break;
    

  case 25:
  if (yyn == 25)
    /* "compiler.y":198  */ /* lalr1.java:480  */
    {
                makeColor(ColorMessage.PINK,"[19] :  value  PLUS value");
                makeColor(ColorMessage.PINK,"[19] :  value1:  "+(TokenInfo)yystack.valueAt (2)+" value2 : "+(TokenInfo)yystack.valueAt (0));
                //this.validation.add((TokenInfo)$$,(TokenInfo)$3);
                //makeColor(ColorMessage.GREEN,"(value PLUS value) $$ =  "+$$+" $1 = "+ $1+" $2 =  "+$3);
              };
  break;
    

  case 26:
  if (yyn == 26)
    /* "compiler.y":205  */ /* lalr1.java:480  */
    {
              makeColor(ColorMessage.PINK,"[20] :  value  MINUS value");
              makeColor(ColorMessage.PINK,"[20] :  value1:  "+(TokenInfo)yystack.valueAt (2)+" value2 : "+(TokenInfo)yystack.valueAt (0));

              };
  break;
    

  case 27:
  if (yyn == 27)
    /* "compiler.y":210  */ /* lalr1.java:480  */
    {
              makeColor(ColorMessage.PINK,"[21] :  value  TIMES value");
              makeColor(ColorMessage.PINK,"[21] :  value1:  "+(TokenInfo)yystack.valueAt (2)+" value2 : "+(TokenInfo)yystack.valueAt (0));
              };
  break;
    

  case 28:
  if (yyn == 28)
    /* "compiler.y":214  */ /* lalr1.java:480  */
    {
              makeColor(ColorMessage.PINK,"[22] :  value  DIV value");
              makeColor(ColorMessage.PINK,"[22] :  value1:  "+(TokenInfo)yystack.valueAt (2)+" value2 : "+(TokenInfo)yystack.valueAt (0));
              };
  break;
    

  case 29:
  if (yyn == 29)
    /* "compiler.y":218  */ /* lalr1.java:480  */
    {
              makeColor(ColorMessage.PINK,"[23] :  value  MOD value");
              makeColor(ColorMessage.PINK,"[23] :  value1:  "+(TokenInfo)yystack.valueAt (2)+" value2 : "+(TokenInfo)yystack.valueAt (0));
              };
  break;
    

  case 30:
  if (yyn == 30)
    /* "compiler.y":223  */ /* lalr1.java:480  */
    {
                makeColor(ColorMessage.RED,"[24] :  value  EQ value");
                makeColor(ColorMessage.RED,"[24] :  value1:  "+(TokenInfo)yystack.valueAt (2)+" value2 : "+(TokenInfo)yystack.valueAt (0));
                };
  break;
    

  case 31:
  if (yyn == 31)
    /* "compiler.y":227  */ /* lalr1.java:480  */
    {
                makeColor(ColorMessage.RED,"[25] :  value  NEQ value");
                makeColor(ColorMessage.RED,"[25] :  value1:  "+(TokenInfo)yystack.valueAt (2)+" value2 : "+(TokenInfo)yystack.valueAt (0));

              };
  break;
    

  case 32:
  if (yyn == 32)
    /* "compiler.y":232  */ /* lalr1.java:480  */
    {
                makeColor(ColorMessage.RED,"[26] :  value  LE value");
                makeColor(ColorMessage.RED,"[26] :  value1:  "+(TokenInfo)yystack.valueAt (2)+" value2 : "+(TokenInfo)yystack.valueAt (0));

              };
  break;
    

  case 33:
  if (yyn == 33)
    /* "compiler.y":237  */ /* lalr1.java:480  */
    {
                makeColor(ColorMessage.RED,"[27] :  value  GE value");
                makeColor(ColorMessage.RED,"[27] :  value1:  "+(TokenInfo)yystack.valueAt (2)+" value2 : "+(TokenInfo)yystack.valueAt (0));

              };
  break;
    

  case 34:
  if (yyn == 34)
    /* "compiler.y":242  */ /* lalr1.java:480  */
    {
                makeColor(ColorMessage.RED,"[28] :  value  LEQ value");
                makeColor(ColorMessage.RED,"[28] :  value1:  "+(TokenInfo)yystack.valueAt (2)+" value2 : "+(TokenInfo)yystack.valueAt (0));

              };
  break;
    

  case 35:
  if (yyn == 35)
    /* "compiler.y":247  */ /* lalr1.java:480  */
    {
                makeColor(ColorMessage.RED,"[29] :  value  GEQ value");
                makeColor(ColorMessage.RED,"[29] :  value1:  "+(TokenInfo)yystack.valueAt (2)+" value2 : "+(TokenInfo)yystack.valueAt (0));

              };
  break;
    

  case 36:
  if (yyn == 36)
    /* "compiler.y":253  */ /* lalr1.java:480  */
    {
                     yyval = this.validation.getConst((TokenInfo)yystack.valueAt (0));
                     makeColor(ColorMessage.RED,"[30] :  value.NUM ");
                     makeColor(ColorMessage.RED,"[30] :  NUM "+(TokenInfo)yystack.valueAt (0));
               };
  break;
    

  case 37:
  if (yyn == 37)
    /* "compiler.y":258  */ /* lalr1.java:480  */
    {
                yyval = (TokenInfo)yystack.valueAt (0); // returned under...
                makeColor(ColorMessage.RED,"[31] : value.identifier ");
                makeColor(ColorMessage.RED,"[31] : value.identifier "+(TokenInfo)yystack.valueAt (0));
              };
  break;
    

  case 38:
  if (yyn == 38)
    /* "compiler.y":264  */ /* lalr1.java:480  */
    {

                yyval = this.validation.getVar((TokenInfo)yystack.valueAt (0));
                makeColor(ColorMessage.GREEN,"[32] : identifier -> PIDENTIFIER "+yyval);
                };
  break;
    

  case 39:
  if (yyn == 39)
    /* "compiler.y":270  */ /* lalr1.java:480  */
    {
                yyval = this.validation.getArrValueFromToken((TokenInfo)yystack.valueAt (3),(TokenInfo)yystack.valueAt (1) );
                //makeColor(ColorMessage.RED,"value.identifier.PIDENTIFIER tab(pid) "+$$);
                makeColor(ColorMessage.GREEN,"[33] PIDENTIFIER L_BRACKET PIDENTIFIER R_BRACKET ");
                makeColor(ColorMessage.GREEN,"[33] PIDENTIFIER "+(TokenInfo)yystack.valueAt (3)+" PIDENTIFIER "+(TokenInfo)yystack.valueAt (1));
              };
  break;
    

  case 40:
  if (yyn == 40)
    /* "compiler.y":276  */ /* lalr1.java:480  */
    {
                makeColor(ColorMessage.GREEN,"[34] PIDENTIFIER L_BRACKET NUM R_BRACKET ");
                makeColor(ColorMessage.GREEN,"[34] PIDENTIFIER "+(TokenInfo)yystack.valueAt (3)+" PIDENTIFIER "+(TokenInfo)yystack.valueAt (1));
                yyval = this.validation.getArrValueFromToken((TokenInfo)yystack.valueAt (3),(BigInteger)(((TokenInfo)yystack.valueAt (1)).getSemanticValue()) );
              };
  break;
    


/* "CompilerBison.java":838  */ /* lalr1.java:480  */
        default: break;
      }

    yy_symbol_print ("-> $$ =", yyr1_[yyn], yyval);

    yystack.pop (yylen);
    yylen = 0;

    /* Shift the result of the reduction.  */
    int yystate = yy_lr_goto_state_ (yystack.stateAt (0), yyr1_[yyn]);
    yystack.push (yystate, yyval);
    return YYNEWSTATE;
  }


  /* Return YYSTR after stripping away unnecessary quotes and
     backslashes, so that it's suitable for yyerror.  The heuristic is
     that double-quoting is unnecessary unless the string contains an
     apostrophe, a comma, or backslash (other than backslash-backslash).
     YYSTR is taken from yytname.  */
  private final String yytnamerr_ (String yystr)
  {
    if (yystr.charAt (0) == '"')
      {
        StringBuffer yyr = new StringBuffer ();
        strip_quotes: for (int i = 1; i < yystr.length (); i++)
          switch (yystr.charAt (i))
            {
            case '\'':
            case ',':
              break strip_quotes;

            case '\\':
              if (yystr.charAt(++i) != '\\')
                break strip_quotes;
              /* Fall through.  */
            default:
              yyr.append (yystr.charAt (i));
              break;

            case '"':
              return yyr.toString ();
            }
      }
    else if (yystr.equals ("$end"))
      return "end of input";

    return yystr;
  }


  /*--------------------------------.
  | Print this symbol on YYOUTPUT.  |
  `--------------------------------*/

  private void yy_symbol_print (String s, int yytype,
                                 Object yyvaluep                                 )
  {
    if (yydebug > 0)
    yycdebug (s + (yytype < yyntokens_ ? " token " : " nterm ")
              + yytname_[yytype] + " ("
              + (yyvaluep == null ? "(null)" : yyvaluep.toString ()) + ")");
  }


  /**
   * Parse input from the scanner that was specified at object construction
   * time.  Return whether the end of the input was reached successfully.
   *
   * @return <tt>true</tt> if the parsing succeeds.  Note that this does not
   *          imply that there were no syntax errors.
   */
   public boolean parse () throws java.io.IOException

  {
    


    /* Lookahead and lookahead in internal form.  */
    int yychar = yyempty_;
    int yytoken = 0;

    /* State.  */
    int yyn = 0;
    int yylen = 0;
    int yystate = 0;
    YYStack yystack = new YYStack ();
    int label = YYNEWSTATE;

    /* Error handling.  */
    int yynerrs_ = 0;
    

    /* Semantic value of the lookahead.  */
    Object yylval = null;

    yycdebug ("Starting parse\n");
    yyerrstatus_ = 0;

    /* Initialize the stack.  */
    yystack.push (yystate, yylval );



    for (;;)
      switch (label)
      {
        /* New state.  Unlike in the C/C++ skeletons, the state is already
           pushed when we come here.  */
      case YYNEWSTATE:
        yycdebug ("Entering state " + yystate + "\n");
        if (yydebug > 0)
          yystack.print (yyDebugStream);

        /* Accept?  */
        if (yystate == yyfinal_)
          return true;

        /* Take a decision.  First try without lookahead.  */
        yyn = yypact_[yystate];
        if (yy_pact_value_is_default_ (yyn))
          {
            label = YYDEFAULT;
            break;
          }

        /* Read a lookahead token.  */
        if (yychar == yyempty_)
          {


            yycdebug ("Reading a token: ");
            yychar = yylexer.yylex ();
            yylval = yylexer.getLVal ();

          }

        /* Convert token to internal form.  */
        if (yychar <= Lexer.EOF)
          {
            yychar = yytoken = Lexer.EOF;
            yycdebug ("Now at end of input.\n");
          }
        else
          {
            yytoken = yytranslate_ (yychar);
            yy_symbol_print ("Next token is", yytoken,
                             yylval);
          }

        /* If the proper action on seeing token YYTOKEN is to reduce or to
           detect an error, take that action.  */
        yyn += yytoken;
        if (yyn < 0 || yylast_ < yyn || yycheck_[yyn] != yytoken)
          label = YYDEFAULT;

        /* <= 0 means reduce or error.  */
        else if ((yyn = yytable_[yyn]) <= 0)
          {
            if (yy_table_value_is_error_ (yyn))
              label = YYERRLAB;
            else
              {
                yyn = -yyn;
                label = YYREDUCE;
              }
          }

        else
          {
            /* Shift the lookahead token.  */
            yy_symbol_print ("Shifting", yytoken,
                             yylval);

            /* Discard the token being shifted.  */
            yychar = yyempty_;

            /* Count tokens shifted since error; after three, turn off error
               status.  */
            if (yyerrstatus_ > 0)
              --yyerrstatus_;

            yystate = yyn;
            yystack.push (yystate, yylval);
            label = YYNEWSTATE;
          }
        break;

      /*-----------------------------------------------------------.
      | yydefault -- do the default action for the current state.  |
      `-----------------------------------------------------------*/
      case YYDEFAULT:
        yyn = yydefact_[yystate];
        if (yyn == 0)
          label = YYERRLAB;
        else
          label = YYREDUCE;
        break;

      /*-----------------------------.
      | yyreduce -- Do a reduction.  |
      `-----------------------------*/
      case YYREDUCE:
        yylen = yyr2_[yyn];
        label = yyaction (yyn, yystack, yylen);
        yystate = yystack.stateAt (0);
        break;

      /*------------------------------------.
      | yyerrlab -- here on detecting error |
      `------------------------------------*/
      case YYERRLAB:
        /* If not already recovering from an error, report this error.  */
        if (yyerrstatus_ == 0)
          {
            ++yynerrs_;
            if (yychar == yyempty_)
              yytoken = yyempty_;
            yyerror (yysyntax_error (yystate, yytoken));
          }

        
        if (yyerrstatus_ == 3)
          {
        /* If just tried and failed to reuse lookahead token after an
         error, discard it.  */

        if (yychar <= Lexer.EOF)
          {
          /* Return failure if at end of input.  */
          if (yychar == Lexer.EOF)
            return false;
          }
        else
            yychar = yyempty_;
          }

        /* Else will try to reuse lookahead token after shifting the error
           token.  */
        label = YYERRLAB1;
        break;

      /*-------------------------------------------------.
      | errorlab -- error raised explicitly by YYERROR.  |
      `-------------------------------------------------*/
      case YYERROR:

        
        /* Do not reclaim the symbols of the rule which action triggered
           this YYERROR.  */
        yystack.pop (yylen);
        yylen = 0;
        yystate = yystack.stateAt (0);
        label = YYERRLAB1;
        break;

      /*-------------------------------------------------------------.
      | yyerrlab1 -- common code for both syntax error and YYERROR.  |
      `-------------------------------------------------------------*/
      case YYERRLAB1:
        yyerrstatus_ = 3;       /* Each real token shifted decrements this.  */

        for (;;)
          {
            yyn = yypact_[yystate];
            if (!yy_pact_value_is_default_ (yyn))
              {
                yyn += yyterror_;
                if (0 <= yyn && yyn <= yylast_ && yycheck_[yyn] == yyterror_)
                  {
                    yyn = yytable_[yyn];
                    if (0 < yyn)
                      break;
                  }
              }

            /* Pop the current state because it cannot handle the
             * error token.  */
            if (yystack.height == 0)
              return false;

            
            yystack.pop ();
            yystate = yystack.stateAt (0);
            if (yydebug > 0)
              yystack.print (yyDebugStream);
          }

        if (label == YYABORT)
            /* Leave the switch.  */
            break;



        /* Shift the error token.  */
        yy_symbol_print ("Shifting", yystos_[yyn],
                         yylval);

        yystate = yyn;
        yystack.push (yyn, yylval);
        label = YYNEWSTATE;
        break;

        /* Accept.  */
      case YYACCEPT:
        return true;

        /* Abort.  */
      case YYABORT:
        return false;
      }
}




  // Generate an error message.
  private String yysyntax_error (int yystate, int tok)
  {
    if (yyErrorVerbose)
      {
        /* There are many possibilities here to consider:
           - If this state is a consistent state with a default action,
             then the only way this function was invoked is if the
             default action is an error action.  In that case, don't
             check for expected tokens because there are none.
           - The only way there can be no lookahead present (in tok) is
             if this state is a consistent state with a default action.
             Thus, detecting the absence of a lookahead is sufficient to
             determine that there is no unexpected or expected token to
             report.  In that case, just report a simple "syntax error".
           - Don't assume there isn't a lookahead just because this
             state is a consistent state with a default action.  There
             might have been a previous inconsistent state, consistent
             state with a non-default action, or user semantic action
             that manipulated yychar.  (However, yychar is currently out
             of scope during semantic actions.)
           - Of course, the expected token list depends on states to
             have correct lookahead information, and it depends on the
             parser not to perform extra reductions after fetching a
             lookahead from the scanner and before detecting a syntax
             error.  Thus, state merging (from LALR or IELR) and default
             reductions corrupt the expected token list.  However, the
             list is correct for canonical LR with one exception: it
             will still contain any token that will not be accepted due
             to an error action in a later state.
        */
        if (tok != yyempty_)
          {
            /* FIXME: This method of building the message is not compatible
               with internationalization.  */
            StringBuffer res =
              new StringBuffer ("syntax error, unexpected ");
            res.append (yytnamerr_ (yytname_[tok]));
            int yyn = yypact_[yystate];
            if (!yy_pact_value_is_default_ (yyn))
              {
                /* Start YYX at -YYN if negative to avoid negative
                   indexes in YYCHECK.  In other words, skip the first
                   -YYN actions for this state because they are default
                   actions.  */
                int yyxbegin = yyn < 0 ? -yyn : 0;
                /* Stay within bounds of both yycheck and yytname.  */
                int yychecklim = yylast_ - yyn + 1;
                int yyxend = yychecklim < yyntokens_ ? yychecklim : yyntokens_;
                int count = 0;
                for (int x = yyxbegin; x < yyxend; ++x)
                  if (yycheck_[x + yyn] == x && x != yyterror_
                      && !yy_table_value_is_error_ (yytable_[x + yyn]))
                    ++count;
                if (count < 5)
                  {
                    count = 0;
                    for (int x = yyxbegin; x < yyxend; ++x)
                      if (yycheck_[x + yyn] == x && x != yyterror_
                          && !yy_table_value_is_error_ (yytable_[x + yyn]))
                        {
                          res.append (count++ == 0 ? ", expecting " : " or ");
                          res.append (yytnamerr_ (yytname_[x]));
                        }
                  }
              }
            return res.toString ();
          }
      }

    return "syntax error";
  }

  /**
   * Whether the given <code>yypact_</code> value indicates a defaulted state.
   * @param yyvalue   the value to check
   */
  private static boolean yy_pact_value_is_default_ (int yyvalue)
  {
    return yyvalue == yypact_ninf_;
  }

  /**
   * Whether the given <code>yytable_</code>
   * value indicates a syntax error.
   * @param yyvalue the value to check
   */
  private static boolean yy_table_value_is_error_ (int yyvalue)
  {
    return yyvalue == yytable_ninf_;
  }

  private static final short yypact_ninf_ = -20;
  private static final byte yytable_ninf_ = -20;

  /* YYPACT[STATE-NUM] -- Index in YYTABLE of the portion describing
   STATE-NUM.  */
  private static final short yypact_[] = yypact_init();
  private static final short[] yypact_init()
  {
    return new short[]
    {
       8,    -3,   177,     4,    22,     5,    23,    18,    18,    28,
     177,    30,    18,   117,   -20,    -9,   -20,    32,    34,    29,
      20,   -20,    15,   -17,   -20,    13,    25,   193,    33,    38,
     -20,   -20,    18,    26,    39,   177,    41,    46,   177,    18,
      18,    18,    18,    18,    18,   177,    18,    18,   -20,   -20,
      48,    27,    51,    55,   122,   -20,   -20,    97,   -20,   -20,
     -20,   -20,   -20,   -20,   138,    40,   -19,   -20,    18,    18,
      18,    18,    18,    52,    35,   -20,   177,   -20,   -20,    50,
      54,   -20,   -20,   -20,   -20,   -20,   -20,   -20,    68,   142,
      18,    18,    65,   -20,   -20,   -20,   -20,    47,    58,   177,
     177,   158,   174,   -20,   -20
    };
  }

/* YYDEFACT[STATE-NUM] -- Default reduction number in state STATE-NUM.
   Performed when YYTABLE does not specify something else to do.  Zero
   means the default is an error.  */
  private static final byte yydefact_[] = yydefact_init();
  private static final byte[] yydefact_init()
  {
    return new byte[]
    {
       0,     0,     0,     0,     7,     2,    38,     0,     0,     0,
       0,     0,     0,     0,    10,     0,     1,     0,     0,     0,
       0,    36,     0,     0,    37,     0,     0,     0,     0,     0,
       4,     9,     0,     0,     5,     0,     0,     0,     0,     0,
       0,     0,     0,     0,     0,     0,     0,     0,    22,    23,
       0,    24,     0,     0,     0,    40,    39,     0,    32,    33,
      34,    35,    30,    31,     0,    16,     0,    11,     0,     0,
       0,     0,     0,     0,     0,     3,     0,    13,    14,     0,
       0,    15,    25,    26,    27,    28,    29,     8,     0,     0,
       0,     0,     0,    12,    17,    20,     6,     0,     0,     0,
       0,     0,     0,    18,    21
    };
  }

/* YYPGOTO[NTERM-NUM].  */
  private static final byte yypgoto_[] = yypgoto_init();
  private static final byte[] yypgoto_init()
  {
    return new byte[]
    {
     -20,   -20,   -20,   -20,    -8,    -7,   -20,   -20,   -20,   -20,
     -20,    -5,    37,    -2
    };
  }

/* YYDEFGOTO[NTERM-NUM].  */
  private static final byte yydefgoto_[] = yydefgoto_init();
  private static final byte[] yydefgoto_init()
  {
    return new byte[]
    {
      -1,     3,    19,     5,    13,    14,    79,    97,    80,    98,
      50,    22,    23,    24
    };
  }

/* YYTABLE[YYPACT[STATE-NUM]] -- What to do in state STATE-NUM.  If
   positive, shift that token.  If negative, reduce the rule whose
   number is the opposite.  If YYTABLE_NINF, syntax error.  */
  private static final byte yytable_[] = yytable_init();
  private static final byte[] yytable_init()
  {
    return new byte[]
    {
      15,     4,    27,    25,    16,    81,    31,    45,    15,    28,
      18,    15,    39,    40,    41,    42,    43,    44,     1,     2,
      31,    21,     6,    36,    37,    15,    32,    54,    17,    20,
      57,    38,    26,    15,     6,    33,    15,    64,    34,    45,
      35,    48,    66,    15,    46,    53,    49,    31,    55,    29,
      31,    52,    15,    56,    73,    15,    67,    31,    74,    87,
      88,   -19,    15,    68,    69,    70,    71,    72,    89,    51,
      90,    92,    96,    99,    15,    91,    58,    59,    60,    61,
      62,    63,    31,    65,   100,     0,     0,    15,     0,     0,
       0,   101,   102,     0,    31,    31,     0,    15,    15,    15,
      15,     6,     0,     0,     0,    82,    83,    84,    85,    86,
       7,     8,     9,     0,    76,    77,     0,     0,     0,     0,
       0,     6,     0,    10,    11,    12,     6,    94,    95,    30,
       7,     8,     9,     0,    75,     7,     8,     9,     0,     0,
       0,     0,     6,    10,    11,    12,     6,     0,    10,    11,
      12,     7,     8,     9,     0,     7,     8,     9,     0,     0,
      93,    78,     6,     0,    10,    11,    12,     0,    10,    11,
      12,     7,     8,     9,     0,     0,     0,     0,     6,     0,
     103,     6,     0,     0,    10,    11,    12,     7,     8,     9,
       7,     8,     9,     0,     0,     0,   104,     6,     0,     0,
      10,    11,    12,    10,    11,    12,     7,    47,     9,     0,
       0,     0,     0,     0,     0,     0,     0,     0,     0,    10,
      11,    12
    };
  }

private static final byte yycheck_[] = yycheck_init();
  private static final byte[] yycheck_init()
  {
    return new byte[]
    {
       2,     4,    10,     8,     0,    24,    13,    26,    10,    11,
       5,    13,    29,    30,    31,    32,    33,    34,    10,    11,
      27,     3,     4,     3,     4,    27,    35,    35,     6,     6,
      38,    16,     4,    35,     4,     3,    38,    45,     4,    26,
      11,     8,    47,    45,    19,     6,     8,    54,     7,    12,
      57,    25,    54,     7,     3,    57,     8,    64,     3,     7,
      25,    21,    64,    36,    37,    38,    39,    40,    76,    32,
      20,     3,     7,    26,    76,    21,    39,    40,    41,    42,
      43,    44,    89,    46,    26,    -1,    -1,    89,    -1,    -1,
      -1,    99,   100,    -1,   101,   102,    -1,    99,   100,   101,
     102,     4,    -1,    -1,    -1,    68,    69,    70,    71,    72,
      13,    14,    15,    -1,    17,    18,    -1,    -1,    -1,    -1,
      -1,     4,    -1,    26,    27,    28,     4,    90,    91,    12,
      13,    14,    15,    -1,    12,    13,    14,    15,    -1,    -1,
      -1,    -1,     4,    26,    27,    28,     4,    -1,    26,    27,
      28,    13,    14,    15,    -1,    13,    14,    15,    -1,    -1,
      18,    23,     4,    -1,    26,    27,    28,    -1,    26,    27,
      28,    13,    14,    15,    -1,    -1,    -1,    -1,     4,    -1,
      22,     4,    -1,    -1,    26,    27,    28,    13,    14,    15,
      13,    14,    15,    -1,    -1,    -1,    22,     4,    -1,    -1,
      26,    27,    28,    26,    27,    28,    13,    14,    15,    -1,
      -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    26,
      27,    28
    };
  }

/* YYSTOS[STATE-NUM] -- The (internal number of the) accessing
   symbol of state STATE-NUM.  */
  private static final byte yystos_[] = yystos_init();
  private static final byte[] yystos_init()
  {
    return new byte[]
    {
       0,    10,    11,    42,     4,    44,     4,    13,    14,    15,
      26,    27,    28,    45,    46,    54,     0,     6,     5,    43,
       6,     3,    52,    53,    54,    52,     4,    45,    54,    53,
      12,    46,    35,     3,     4,    11,     3,     4,    16,    29,
      30,    31,    32,    33,    34,    26,    19,    14,     8,     8,
      51,    53,    25,     6,    45,     7,     7,    45,    53,    53,
      53,    53,    53,    53,    45,    53,    52,     8,    36,    37,
      38,    39,    40,     3,     3,    12,    17,    18,    23,    47,
      49,    24,    53,    53,    53,    53,    53,     7,    25,    45,
      20,    21,     3,    18,    53,    53,     7,    48,    50,    26,
      26,    45,    45,    22,    22
    };
  }

/* YYR1[YYN] -- Symbol number of symbol that rule YYN derives.  */
  private static final byte yyr1_[] = yyr1_init();
  private static final byte[] yyr1_init()
  {
    return new byte[]
    {
       0,    41,    43,    42,    42,    44,    44,    44,    44,    45,
      45,    46,    46,    46,    46,    46,    47,    48,    46,    49,
      50,    46,    46,    46,    51,    51,    51,    51,    51,    51,
      52,    52,    52,    52,    52,    52,    53,    53,    54,    54,
      54
    };
  }

/* YYR2[YYN] -- Number of symbols on the right hand side of rule YYN.  */
  private static final byte yyr2_[] = yyr2_init();
  private static final byte[] yyr2_init()
  {
    return new byte[]
    {
       0,     2,     0,     6,     3,     3,     8,     1,     6,     2,
       1,     4,     7,     5,     5,     5,     0,     0,    11,     0,
       0,    11,     3,     3,     1,     3,     3,     3,     3,     3,
       3,     3,     3,     3,     3,     3,     1,     1,     1,     4,
       4
    };
  }

  /* YYTOKEN_NUMBER[YYLEX-NUM] -- Internal symbol number corresponding
      to YYLEX-NUM.  */
  private static final short yytoken_number_[] = yytoken_number_init();
  private static final short[] yytoken_number_init()
  {
    return new short[]
    {
       0,   256,   257,   258,   259,   260,   261,   262,   263,   264,
     265,   266,   267,   268,   269,   270,   271,   272,   273,   274,
     275,   276,   277,   278,   279,   280,   281,   282,   283,   284,
     285,   286,   287,   288,   289,   290,   291,   292,   293,   294,
     295
    };
  }

  /* YYTNAME[SYMBOL-NUM] -- String name of the symbol SYMBOL-NUM.
     First, the terminals, then, starting at \a yyntokens_, nonterminals.  */
  private static final String yytname_[] = yytname_init();
  private static final String[] yytname_init()
  {
    return new String[]
    {
  "$end", "error", "$undefined", "NUM", "PIDENTIFIER", "COMMA",
  "L_BRACKET", "R_BRACKET", "SEMICOLON", "ERROR", "DECLARE", "BEGIN",
  "END", "IF", "WHILE", "FOR", "THEN", "ELSE", "ENDIF", "FROM", "TO",
  "DOWNTO", "ENDFOR", "ENDWHILE", "ENDDO", "COLON", "DO", "READ", "WRITE",
  "LE", "GE", "LEQ", "GEQ", "EQ", "NEQ", "ASSIGN", "PLUS", "MINUS",
  "TIMES", "DIV", "MOD", "$accept", "program", "$@1", "declarations",
  "commands", "command", "$@2", "@3", "$@4", "@5", "expression",
  "condition", "value", "identifier", null
    };
  }

  /* YYRLINE[YYN] -- Source line where rule number YYN was defined.  */
  private static final short yyrline_[] = yyrline_init();
  private static final short[] yyrline_init()
  {
    return new short[]
    {
       0,    74,    74,    74,    82,    87,    93,   101,   107,   116,
     121,   127,   135,   139,   144,   148,   152,   157,   152,   168,
     173,   168,   182,   187,   193,   198,   205,   210,   214,   218,
     223,   227,   232,   237,   242,   247,   253,   258,   264,   270,
     276
    };
  }


  // Report on the debug stream that the rule yyrule is going to be reduced.
  private void yy_reduce_print (int yyrule, YYStack yystack)
  {
    if (yydebug == 0)
      return;

    int yylno = yyrline_[yyrule];
    int yynrhs = yyr2_[yyrule];
    /* Print the symbols being reduced, and their result.  */
    yycdebug ("Reducing stack by rule " + (yyrule - 1)
              + " (line " + yylno + "), ");

    /* The symbols being reduced.  */
    for (int yyi = 0; yyi < yynrhs; yyi++)
      yy_symbol_print ("   $" + (yyi + 1) + " =",
                       yystos_[yystack.stateAt(yynrhs - (yyi + 1))],
                       yystack.valueAt ((yynrhs) - (yyi + 1)));
  }

  /* YYTRANSLATE(TOKEN-NUM) -- Symbol number corresponding to TOKEN-NUM
     as returned by yylex, with out-of-bounds checking.  */
  private static final byte yytranslate_table_[] = yytranslate_table_init();
  private static final byte[] yytranslate_table_init()
  {
    return new byte[]
    {
       0,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     1,     2,     3,     4,
       5,     6,     7,     8,     9,    10,    11,    12,    13,    14,
      15,    16,    17,    18,    19,    20,    21,    22,    23,    24,
      25,    26,    27,    28,    29,    30,    31,    32,    33,    34,
      35,    36,    37,    38,    39,    40
    };
  }

  private static final byte yytranslate_ (int t)
  {
    if (t >= 0 && t <= yyuser_token_number_max_)
      return yytranslate_table_[t];
    else
      return yyundef_token_;
  }

  private static final int yylast_ = 221;
  private static final int yynnts_ = 14;
  private static final int yyempty_ = -2;
  private static final int yyfinal_ = 16;
  private static final int yyterror_ = 1;
  private static final int yyerrcode_ = 256;
  private static final int yyntokens_ = 41;

  private static final int yyuser_token_number_max_ = 295;
  private static final int yyundef_token_ = 2;

/* User implementation code.  */
/* Unqualified %code blocks.  */
/* "compiler.y":22  */ /* lalr1.java:1056  */


  public void color(String msg){
    System.out.println("\u001b[32m"+msg+"\u001B[0m");
  }
   private static CompilerFlex scanner = null;
   private static  Validation validation;
   private Scanner numbersInput = new Scanner(System.in);


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
   

/* "CompilerBison.java":1594  */ /* lalr1.java:1056  */

}

/* "compiler.y":281  */ /* lalr1.java:1060  */

