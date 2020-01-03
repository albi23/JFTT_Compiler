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
/* "compiler.y":9  */ /* lalr1.java:92  */

  import java.util.ArrayList;
  import java.util.List;
  import compiler.CompilerFlex.TokenInfo;


/* "CompilerBison.java":46  */ /* lalr1.java:92  */

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
   * Communication interface between the scanner and the Bison-generated
   * parser <tt>CompilerBison</tt>.
   */
  public interface Lexer {
    /** Token returned by the scanner to signal the end of its input.  */
    public static final int EOF = 0;

/* Tokens.  */
    /** Token number,to be returned by the scanner.  */
    static final int COMMA = 258;
    /** Token number,to be returned by the scanner.  */
    static final int L_BRACKET = 259;
    /** Token number,to be returned by the scanner.  */
    static final int R_BRACKET = 260;
    /** Token number,to be returned by the scanner.  */
    static final int SEMICOLON = 261;
    /** Token number,to be returned by the scanner.  */
    static final int ERROR = 262;
    /** Token number,to be returned by the scanner.  */
    static final int DECLARE = 263;
    /** Token number,to be returned by the scanner.  */
    static final int BEGIN = 264;
    /** Token number,to be returned by the scanner.  */
    static final int END = 265;
    /** Token number,to be returned by the scanner.  */
    static final int IF = 266;
    /** Token number,to be returned by the scanner.  */
    static final int WHILE = 267;
    /** Token number,to be returned by the scanner.  */
    static final int DO = 268;
    /** Token number,to be returned by the scanner.  */
    static final int FOR = 269;
    /** Token number,to be returned by the scanner.  */
    static final int THEN = 270;
    /** Token number,to be returned by the scanner.  */
    static final int ELSE = 271;
    /** Token number,to be returned by the scanner.  */
    static final int ENDIF = 272;
    /** Token number,to be returned by the scanner.  */
    static final int FROM = 273;
    /** Token number,to be returned by the scanner.  */
    static final int TO = 274;
    /** Token number,to be returned by the scanner.  */
    static final int DOWNTO = 275;
    /** Token number,to be returned by the scanner.  */
    static final int ENDFOR = 276;
    /** Token number,to be returned by the scanner.  */
    static final int ENDWHILE = 277;
    /** Token number,to be returned by the scanner.  */
    static final int ENDDO = 278;
    /** Token number,to be returned by the scanner.  */
    static final int COLON = 279;
    /** Token number,to be returned by the scanner.  */
    static final int READ = 280;
    /** Token number,to be returned by the scanner.  */
    static final int WRITE = 281;
    /** Token number,to be returned by the scanner.  */
    static final int LE = 282;
    /** Token number,to be returned by the scanner.  */
    static final int GE = 283;
    /** Token number,to be returned by the scanner.  */
    static final int LEQ = 284;
    /** Token number,to be returned by the scanner.  */
    static final int GEQ = 285;
    /** Token number,to be returned by the scanner.  */
    static final int EQ = 286;
    /** Token number,to be returned by the scanner.  */
    static final int NEQ = 287;
    /** Token number,to be returned by the scanner.  */
    static final int ASSIGN = 288;
    /** Token number,to be returned by the scanner.  */
    static final int PIDENTIFIER = 289;
    /** Token number,to be returned by the scanner.  */
    static final int NUM = 290;
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
    /* "compiler.y":98  */ /* lalr1.java:480  */
    {};
  break;
    

  case 3:
  if (yyn == 3)
    /* "compiler.y":99  */ /* lalr1.java:480  */
    {};
  break;
    

  case 4:
  if (yyn == 4)
    /* "compiler.y":101  */ /* lalr1.java:480  */
    {
                validateNewPids((TokenInfo)yystack.valueAt (0));
              };
  break;
    

  case 5:
  if (yyn == 5)
    /* "compiler.y":105  */ /* lalr1.java:480  */
    {
              /** Array declarations control */
                if((Integer)((TokenInfo)yystack.valueAt (3)).getSemanticValue() > (Integer)(((TokenInfo)yystack.valueAt (1)).getSemanticValue())){
                    showErrMsg("invalid array "+((TokenInfo)yystack.valueAt (5)).getSemanticValue()+" declaration",((TokenInfo)yystack.valueAt (5)).getLinePos());
                }
                validateNewPids((TokenInfo)yystack.valueAt (5));

              };
  break;
    

  case 6:
  if (yyn == 6)
    /* "compiler.y":113  */ /* lalr1.java:480  */
    {
                validateNewPids((TokenInfo)yystack.valueAt (0));
              };
  break;
    

  case 7:
  if (yyn == 7)
    /* "compiler.y":117  */ /* lalr1.java:480  */
    {

                /*System.out.println("Inicjalizacja tablicy w  w linijce "+scanner.getYyline() );*/
                validateNewPids((TokenInfo)yystack.valueAt (5));
              };
  break;
    

  case 8:
  if (yyn == 8)
    /* "compiler.y":123  */ /* lalr1.java:480  */
    {};
  break;
    

  case 9:
  if (yyn == 9)
    /* "compiler.y":124  */ /* lalr1.java:480  */
    {};
  break;
    

  case 10:
  if (yyn == 10)
    /* "compiler.y":126  */ /* lalr1.java:480  */
    { /*System.out.println(" przypisanie wartości w linijce : "+scanner.getYyline());*/};
  break;
    

  case 11:
  if (yyn == 11)
    /* "compiler.y":127  */ /* lalr1.java:480  */
    {};
  break;
    

  case 12:
  if (yyn == 12)
    /* "compiler.y":128  */ /* lalr1.java:480  */
    {};
  break;
    

  case 13:
  if (yyn == 13)
    /* "compiler.y":129  */ /* lalr1.java:480  */
    {};
  break;
    

  case 14:
  if (yyn == 14)
    /* "compiler.y":130  */ /* lalr1.java:480  */
    {};
  break;
    

  case 15:
  if (yyn == 15)
    /* "compiler.y":131  */ /* lalr1.java:480  */
    {};
  break;
    

  case 16:
  if (yyn == 16)
    /* "compiler.y":132  */ /* lalr1.java:480  */
    {};
  break;
    

  case 17:
  if (yyn == 17)
    /* "compiler.y":133  */ /* lalr1.java:480  */
    {
                if(!pidIds.contains(((TokenInfo)yystack.valueAt (2)).getSemanticValue())){
                  showErrMsg("use of uninitialized variable "+((TokenInfo)yystack.valueAt (1)).getSemanticValue(),((TokenInfo)yystack.valueAt (1)).getLinePos());
                }
              };
  break;
    

  case 18:
  if (yyn == 18)
    /* "compiler.y":138  */ /* lalr1.java:480  */
    {};
  break;
    

  case 19:
  if (yyn == 19)
    /* "compiler.y":140  */ /* lalr1.java:480  */
    {};
  break;
    

  case 20:
  if (yyn == 20)
    /* "compiler.y":141  */ /* lalr1.java:480  */
    {};
  break;
    

  case 21:
  if (yyn == 21)
    /* "compiler.y":142  */ /* lalr1.java:480  */
    {};
  break;
    

  case 22:
  if (yyn == 22)
    /* "compiler.y":143  */ /* lalr1.java:480  */
    {};
  break;
    

  case 23:
  if (yyn == 23)
    /* "compiler.y":144  */ /* lalr1.java:480  */
    {};
  break;
    

  case 24:
  if (yyn == 24)
    /* "compiler.y":145  */ /* lalr1.java:480  */
    {};
  break;
    

  case 25:
  if (yyn == 25)
    /* "compiler.y":147  */ /* lalr1.java:480  */
    {};
  break;
    

  case 26:
  if (yyn == 26)
    /* "compiler.y":148  */ /* lalr1.java:480  */
    {};
  break;
    

  case 27:
  if (yyn == 27)
    /* "compiler.y":149  */ /* lalr1.java:480  */
    {};
  break;
    

  case 28:
  if (yyn == 28)
    /* "compiler.y":150  */ /* lalr1.java:480  */
    {};
  break;
    

  case 29:
  if (yyn == 29)
    /* "compiler.y":151  */ /* lalr1.java:480  */
    {};
  break;
    

  case 30:
  if (yyn == 30)
    /* "compiler.y":152  */ /* lalr1.java:480  */
    {};
  break;
    

  case 31:
  if (yyn == 31)
    /* "compiler.y":154  */ /* lalr1.java:480  */
    { };
  break;
    

  case 32:
  if (yyn == 32)
    /* "compiler.y":155  */ /* lalr1.java:480  */
    {};
  break;
    

  case 33:
  if (yyn == 33)
    /* "compiler.y":157  */ /* lalr1.java:480  */
    { /*System.out.println("\u001b[48;5;160m Inicjalizacja zmiennej"+$1+"\u001b[0m");*/};
  break;
    

  case 34:
  if (yyn == 34)
    /* "compiler.y":158  */ /* lalr1.java:480  */
    {/*System.out.println("\u001b[48;5;160m Pobieranie wartości z tablicy \u001b[0m");*/};
  break;
    

  case 35:
  if (yyn == 35)
    /* "compiler.y":159  */ /* lalr1.java:480  */
    {/*System.out.println("\u001b[48;5;160m Inicjalizacja tablicy \u001b[0m");*/};
  break;
    


/* "CompilerBison.java":644  */ /* lalr1.java:480  */
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

  private static final short yypact_ninf_ = -34;
  private static final byte yytable_ninf_ = -1;

  /* YYPACT[STATE-NUM] -- Index in YYTABLE of the portion describing
   STATE-NUM.  */
  private static final short yypact_[] = yypact_init();
  private static final short[] yypact_init()
  {
    return new short[]
    {
      16,   -33,   173,     4,     3,     7,    -7,    -7,   173,   -25,
     -22,    -7,    25,    32,   -34,     2,   -34,    12,    14,   173,
     -34,    35,   174,   -34,    39,   183,    33,    48,    49,    -3,
     -34,   -34,    -7,    37,    55,    89,   173,    -7,    -7,    -7,
      -7,    -7,    -7,   173,    -7,    -7,   -34,   -34,    57,    59,
      61,   -18,    30,    36,   -34,   108,   -34,   -34,   -34,   -34,
     -34,   -34,   115,    -8,    19,   -34,   -34,   -34,    -7,    -7,
      -7,    -7,    -7,    63,    50,   173,   -34,   -34,   -34,    -7,
      -7,   -34,   -34,   -34,   -34,   -34,   -34,    46,   133,    69,
      71,    67,   -34,   173,   173,   -34,   140,   157,   -34,   -34
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
       0,     0,     0,     0,     6,     0,     0,     0,     0,     0,
       0,     0,    33,     0,     9,     0,     1,     0,     0,     0,
      31,     0,     0,    32,     0,     0,     0,     0,     0,     0,
       3,     8,     0,     0,     4,     0,     0,     0,     0,     0,
       0,     0,     0,     0,     0,     0,    17,    18,     0,     0,
       0,    19,     0,     0,     2,     0,    27,    28,    29,    30,
      25,    26,     0,     0,     0,    34,    35,    10,     0,     0,
       0,     0,     0,     0,     0,     0,    12,    13,    14,     0,
       0,    20,    21,    22,    23,    24,     7,     0,     0,     0,
       0,     0,    11,     0,     0,     5,     0,     0,    15,    16
    };
  }

/* YYPGOTO[NTERM-NUM].  */
  private static final byte yypgoto_[] = yypgoto_init();
  private static final byte[] yypgoto_init()
  {
    return new byte[]
    {
     -34,   -34,   -34,    -6,     1,   -34,    -4,    38,    -2
    };
  }

/* YYDEFGOTO[NTERM-NUM].  */
  private static final byte yydefgoto_[] = yydefgoto_init();
  private static final byte[] yydefgoto_init()
  {
    return new byte[]
    {
      -1,     3,     5,    13,    14,    50,    21,    22,    23
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
      15,     4,    25,    24,    16,    43,    15,    17,    27,    26,
      18,    15,    12,    35,    31,    78,    19,    15,    68,    69,
      70,    71,    72,    15,     1,     2,    31,    12,    20,    29,
      55,    48,    49,    15,    15,    32,    31,    62,    79,    80,
      63,    15,    30,     6,     7,     8,     9,    33,    34,    28,
      36,    45,    43,    15,    46,    47,    31,    10,    11,    53,
      15,    52,    65,    31,    66,    73,    12,    67,    86,    88,
      51,    74,    95,    15,    87,    56,    57,    58,    59,    60,
      61,    91,    93,    64,    94,     0,    15,    96,    97,    31,
       0,    15,    15,     0,    15,    15,     0,    31,    31,    54,
       6,     7,     8,     9,     0,     0,    81,    82,    83,    84,
      85,     0,     0,     0,    10,    11,     0,    89,    90,     6,
       7,     8,     9,    12,    75,    76,     6,     7,     8,     9,
       0,     0,     0,    10,    11,     0,     0,    77,     0,     0,
      10,    11,    12,     0,     6,     7,     8,     9,     0,    12,
      92,     6,     7,     8,     9,     0,     0,     0,    10,    11,
       0,    98,     0,     0,     0,    10,    11,    12,     6,     7,
       8,     9,     0,     0,    12,     0,     0,     0,    99,     0,
       0,     0,    10,    11,     6,     7,     8,     9,     0,     0,
       0,    12,     0,     0,     6,    44,     8,     9,    10,    11,
       0,    37,    38,    39,    40,    41,    42,    12,    10,    11,
       0,     0,     0,     0,     0,     0,     0,    12
    };
  }

private static final byte yycheck_[] = yycheck_init();
  private static final byte[] yycheck_init()
  {
    return new byte[]
    {
       2,    34,     8,     7,     0,    13,     8,     4,    10,    34,
       3,    13,    34,    19,    13,    23,     9,    19,    36,    37,
      38,    39,    40,    25,     8,     9,    25,    34,    35,     4,
      36,    34,    35,    35,    36,    33,    35,    43,    19,    20,
      44,    43,    10,    11,    12,    13,    14,    35,    34,    11,
      15,    18,    13,    55,     6,     6,    55,    25,    26,     4,
      62,    24,     5,    62,     5,    35,    34,     6,     5,    75,
      32,    35,     5,    75,    24,    37,    38,    39,    40,    41,
      42,    35,    13,    45,    13,    -1,    88,    93,    94,    88,
      -1,    93,    94,    -1,    96,    97,    -1,    96,    97,    10,
      11,    12,    13,    14,    -1,    -1,    68,    69,    70,    71,
      72,    -1,    -1,    -1,    25,    26,    -1,    79,    80,    11,
      12,    13,    14,    34,    16,    17,    11,    12,    13,    14,
      -1,    -1,    -1,    25,    26,    -1,    -1,    22,    -1,    -1,
      25,    26,    34,    -1,    11,    12,    13,    14,    -1,    34,
      17,    11,    12,    13,    14,    -1,    -1,    -1,    25,    26,
      -1,    21,    -1,    -1,    -1,    25,    26,    34,    11,    12,
      13,    14,    -1,    -1,    34,    -1,    -1,    -1,    21,    -1,
      -1,    -1,    25,    26,    11,    12,    13,    14,    -1,    -1,
      -1,    34,    -1,    -1,    11,    12,    13,    14,    25,    26,
      -1,    27,    28,    29,    30,    31,    32,    34,    25,    26,
      -1,    -1,    -1,    -1,    -1,    -1,    -1,    34
    };
  }

/* YYSTOS[STATE-NUM] -- The (internal number of the) accessing
   symbol of state STATE-NUM.  */
  private static final byte yystos_[] = yystos_init();
  private static final byte[] yystos_init()
  {
    return new byte[]
    {
       0,     8,     9,    42,    34,    43,    11,    12,    13,    14,
      25,    26,    34,    44,    45,    49,     0,     4,     3,     9,
      35,    47,    48,    49,    47,    44,    34,    49,    48,     4,
      10,    45,    33,    35,    34,    44,    15,    27,    28,    29,
      30,    31,    32,    13,    12,    18,     6,     6,    34,    35,
      46,    48,    24,     4,    10,    44,    48,    48,    48,    48,
      48,    48,    44,    47,    48,     5,     5,     6,    36,    37,
      38,    39,    40,    35,    35,    16,    17,    22,    23,    19,
      20,    48,    48,    48,    48,    48,     5,    24,    44,    48,
      48,    35,    17,    13,    13,     5,    44,    44,    21,    21
    };
  }

/* YYR1[YYN] -- Symbol number of symbol that rule YYN derives.  */
  private static final byte yyr1_[] = yyr1_init();
  private static final byte[] yyr1_init()
  {
    return new byte[]
    {
       0,    41,    42,    42,    43,    43,    43,    43,    44,    44,
      45,    45,    45,    45,    45,    45,    45,    45,    45,    46,
      46,    46,    46,    46,    46,    47,    47,    47,    47,    47,
      47,    48,    48,    49,    49,    49
    };
  }

/* YYR2[YYN] -- Number of symbols on the right hand side of rule YYN.  */
  private static final byte yyr2_[] = yyr2_init();
  private static final byte[] yyr2_init()
  {
    return new byte[]
    {
       0,     2,     5,     3,     3,     8,     1,     6,     2,     1,
       4,     7,     5,     5,     5,     9,     9,     3,     3,     1,
       3,     3,     3,     3,     3,     3,     3,     3,     3,     3,
       3,     1,     1,     1,     4,     4
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
  "$end", "error", "$undefined", "COMMA", "L_BRACKET", "R_BRACKET",
  "SEMICOLON", "ERROR", "DECLARE", "BEGIN", "END", "IF", "WHILE", "DO",
  "FOR", "THEN", "ELSE", "ENDIF", "FROM", "TO", "DOWNTO", "ENDFOR",
  "ENDWHILE", "ENDDO", "COLON", "READ", "WRITE", "LE", "GE", "LEQ", "GEQ",
  "EQ", "NEQ", "ASSIGN", "PIDENTIFIER", "NUM", "PLUS", "MINUS", "TIMES",
  "DIV", "MOD", "$accept", "program", "declarations", "commands",
  "command", "expression", "condition", "value", "identifier", null
    };
  }

  /* YYRLINE[YYN] -- Source line where rule number YYN was defined.  */
  private static final short yyrline_[] = yyrline_init();
  private static final short[] yyrline_init()
  {
    return new short[]
    {
       0,    98,    98,    99,   101,   105,   113,   117,   123,   124,
     126,   127,   128,   129,   130,   131,   132,   133,   138,   140,
     141,   142,   143,   144,   145,   147,   148,   149,   150,   151,
     152,   154,   155,   157,   158,   159
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

  private static final int yylast_ = 217;
  private static final int yynnts_ = 9;
  private static final int yyempty_ = -2;
  private static final int yyfinal_ = 16;
  private static final int yyterror_ = 1;
  private static final int yyerrcode_ = 256;
  private static final int yyntokens_ = 41;

  private static final int yyuser_token_number_max_ = 295;
  private static final int yyundef_token_ = 2;

/* User implementation code.  */
/* Unqualified %code blocks.  */
/* "compiler.y":16  */ /* lalr1.java:1056  */


   private static CompilerFlex scanner = null;
   private List<String> pidIds = new ArrayList<>(100);


   private void showErrMsg(String msg, int line) {
       scanner.yyerror("Error in line "+line+": "+msg);
       finishChecking();
   }

   private void finishChecking(){
     System.exit(1);
   }

   private void validateNewPids(TokenInfo tokenInfo) {
     if (pidIds.contains(tokenInfo.getSemanticValue().toString())) {
       showErrMsg("second declaration " + tokenInfo.getSemanticValue(),tokenInfo.getLinePos());
     } else {
       pidIds.add(tokenInfo.getSemanticValue().toString());
     }
   }

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
   

/* "CompilerBison.java":1316  */ /* lalr1.java:1056  */

}

/* "compiler.y":160  */ /* lalr1.java:1060  */

