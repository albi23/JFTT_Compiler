/* The following code was generated by JFlex 1.7.0 */

package compiler;

import compiler.holder.TokenInfo;


/**
 * This class is a scanner generated by 
 * <a href="http://www.jflex.de/">JFlex</a> 1.7.0
 * from the specification file <tt>compiler.jflex</tt>
 */
class CompilerFlex implements CompilerBison.Lexer {

  /** This character denotes the end of file */
  public static final int YYEOF = -1;

  /** initial size of the lookahead buffer */
  private static final int ZZ_BUFFERSIZE = 16384;

  /** lexical states */
  public static final int YYINITIAL = 0;

  /**
   * ZZ_LEXSTATE[l] is the state in the DFA for the lexical state l
   * ZZ_LEXSTATE[l+1] is the state in the DFA for the lexical state l
   *                  at the beginning of a line
   * l is of the form l = 2*k, k a non negative integer
   */
  private static final int ZZ_LEXSTATE[] = { 
     0, 1
  };

  /** 
   * Translates characters to character classes
   */
  private static final String ZZ_CMAP_PACKED = 
    "\11\0\1\13\1\2\1\11\1\11\1\11\22\0\1\13\2\0\1\12"+
    "\4\0\1\42\1\43\2\0\1\41\1\10\2\0\1\6\11\7\1\45"+
    "\1\44\5\0\1\20\1\22\1\16\1\14\1\15\1\27\1\23\1\31"+
    "\1\24\2\0\1\17\1\34\1\25\1\33\1\35\1\40\1\21\1\26"+
    "\1\30\1\36\1\37\1\32\3\0\1\4\1\1\1\5\1\0\1\3"+
    "\1\0\32\3\12\0\1\11\u1fa2\0\1\11\1\11\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\udfe6\0";

  /** 
   * Translates characters to character classes
   */
  private static final char [] ZZ_CMAP = zzUnpackCMap(ZZ_CMAP_PACKED);

  /** 
   * Translates DFA states to action switch labels.
   */
  private static final int [] ZZ_ACTION = zzUnpackAction();

  private static final String ZZ_ACTION_PACKED_0 =
    "\2\0\2\1\1\2\1\3\1\1\2\4\1\1\1\5"+
    "\1\2\16\1\1\6\1\7\1\10\1\11\1\12\1\1"+
    "\3\0\1\13\2\0\1\14\1\15\3\0\1\16\1\17"+
    "\5\0\1\20\10\0\1\21\2\0\1\22\1\23\3\0"+
    "\1\24\1\25\1\0\1\26\5\0\1\27\1\0\1\2"+
    "\2\0\1\30\5\0\1\31\1\0\1\32\1\0\1\33"+
    "\3\0\1\34\2\0\1\35\1\36\3\0\1\37\1\40"+
    "\1\41\1\42\1\43\1\0\1\44\1\45\1\0\1\46"+
    "\1\47\1\0\1\50";

  private static int [] zzUnpackAction() {
    int [] result = new int[116];
    int offset = 0;
    offset = zzUnpackAction(ZZ_ACTION_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackAction(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  /** 
   * Translates a state to a row index in the transition table
   */
  private static final int [] ZZ_ROWMAP = zzUnpackRowMap();

  private static final String ZZ_ROWMAP_PACKED_0 =
    "\0\0\0\46\0\114\0\162\0\114\0\230\0\276\0\114"+
    "\0\344\0\u010a\0\114\0\u0130\0\u0156\0\u017c\0\u01a2\0\u01c8"+
    "\0\u01ee\0\u0214\0\u023a\0\u0260\0\u0286\0\u02ac\0\u02d2\0\u02f8"+
    "\0\u031e\0\u0344\0\114\0\114\0\114\0\114\0\114\0\u036a"+
    "\0\276\0\u0390\0\u03b6\0\u03dc\0\u0402\0\u0428\0\114\0\u044e"+
    "\0\u0474\0\u049a\0\u04c0\0\u04e6\0\114\0\u050c\0\u0532\0\u0558"+
    "\0\u057e\0\u05a4\0\114\0\u05ca\0\u05f0\0\u0616\0\u063c\0\u0662"+
    "\0\u036a\0\u0688\0\u06ae\0\114\0\u06d4\0\u06fa\0\u0720\0\114"+
    "\0\u0746\0\u076c\0\u0792\0\114\0\114\0\u07b8\0\114\0\u07de"+
    "\0\u0804\0\u082a\0\u0850\0\u0876\0\114\0\u089c\0\u036a\0\u08c2"+
    "\0\u08e8\0\114\0\u090e\0\u0934\0\u095a\0\u0980\0\u09a6\0\114"+
    "\0\u09cc\0\114\0\u09f2\0\114\0\u0a18\0\u0a3e\0\u0a64\0\114"+
    "\0\u0a8a\0\u0ab0\0\114\0\114\0\u0ad6\0\u0afc\0\u0b22\0\114"+
    "\0\114\0\114\0\114\0\114\0\u0b48\0\114\0\114\0\u0b6e"+
    "\0\114\0\114\0\u0b94\0\114";

  private static int [] zzUnpackRowMap() {
    int [] result = new int[116];
    int offset = 0;
    offset = zzUnpackRowMap(ZZ_ROWMAP_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackRowMap(String packed, int offset, int [] result) {
    int i = 0;  /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int high = packed.charAt(i++) << 16;
      result[j++] = high | packed.charAt(i++);
    }
    return j;
  }

  /** 
   * The transition table of the DFA
   */
  private static final int [] ZZ_TRANS = zzUnpackTrans();

  private static final String ZZ_TRANS_PACKED_0 =
    "\1\3\1\4\1\5\1\6\1\7\1\3\1\10\1\11"+
    "\1\12\1\13\1\3\1\14\1\15\1\16\1\3\1\17"+
    "\1\20\1\21\1\22\1\23\1\24\1\25\1\3\1\26"+
    "\1\27\1\3\1\30\1\3\1\31\1\32\3\3\1\33"+
    "\1\34\1\35\1\36\1\37\1\3\1\4\1\5\1\6"+
    "\1\7\1\3\1\10\1\11\1\12\1\13\1\40\1\14"+
    "\1\15\1\16\1\3\1\17\1\20\1\21\1\22\1\23"+
    "\1\24\1\25\1\3\1\26\1\27\1\3\1\30\1\3"+
    "\1\31\1\32\3\3\1\33\1\34\1\35\1\36\1\37"+
    "\50\0\1\5\46\0\1\6\42\0\4\41\1\0\1\5"+
    "\40\41\6\0\2\11\45\0\1\11\51\0\1\14\47\0"+
    "\1\42\6\0\1\43\6\0\1\44\31\0\1\45\5\0"+
    "\1\46\12\0\1\47\22\0\1\50\56\0\1\51\34\0"+
    "\1\52\45\0\1\53\45\0\1\54\57\0\1\55\33\0"+
    "\1\56\51\0\1\57\11\0\1\60\36\0\1\61\4\0"+
    "\1\62\1\0\1\63\33\0\1\64\7\0\1\65\40\0"+
    "\1\66\6\0\1\67\31\0\1\70\26\0\1\71\1\72"+
    "\1\5\6\71\1\0\34\71\16\0\1\73\66\0\1\74"+
    "\40\0\1\75\41\0\1\76\33\0\1\77\71\0\1\100"+
    "\33\0\1\101\37\0\1\102\50\0\1\103\62\0\1\104"+
    "\45\0\1\105\40\0\1\106\33\0\1\107\60\0\1\110"+
    "\26\0\1\111\54\0\1\112\45\0\1\113\46\0\1\114"+
    "\34\0\1\115\67\0\1\116\7\0\1\71\1\72\1\117"+
    "\6\71\1\0\34\71\17\0\1\120\53\0\1\121\35\0"+
    "\1\122\44\0\1\123\7\0\1\124\2\0\1\125\2\0"+
    "\1\126\37\0\1\127\35\0\1\130\55\0\1\131\55\0"+
    "\1\132\26\0\1\133\55\0\1\134\50\0\1\135\34\0"+
    "\1\136\64\0\1\137\35\0\1\140\37\0\1\141\55\0"+
    "\1\142\50\0\1\143\41\0\1\144\51\0\1\145\43\0"+
    "\1\146\37\0\1\147\47\0\1\150\46\0\1\151\34\0"+
    "\1\152\45\0\1\153\56\0\1\154\40\0\1\155\57\0"+
    "\1\156\33\0\1\157\50\0\1\160\46\0\1\161\35\0"+
    "\1\162\47\0\1\163\43\0\1\164\30\0";

  private static int [] zzUnpackTrans() {
    int [] result = new int[3002];
    int offset = 0;
    offset = zzUnpackTrans(ZZ_TRANS_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackTrans(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      value--;
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  /* error codes */
  private static final int ZZ_UNKNOWN_ERROR = 0;
  private static final int ZZ_NO_MATCH = 1;
  private static final int ZZ_PUSHBACK_2BIG = 2;

  /* error messages for the codes above */
  private static final String ZZ_ERROR_MSG[] = {
    "Unknown internal scanner error",
    "Error: could not match input",
    "Error: pushback value was too large"
  };

  /**
   * ZZ_ATTRIBUTE[aState] contains the attributes of state <code>aState</code>
   */
  private static final int [] ZZ_ATTRIBUTE = zzUnpackAttribute();

  private static final String ZZ_ATTRIBUTE_PACKED_0 =
    "\2\0\1\11\1\1\1\11\2\1\1\11\2\1\1\11"+
    "\17\1\5\11\1\1\3\0\1\1\2\0\1\11\1\1"+
    "\3\0\1\1\1\11\5\0\1\11\10\0\1\11\2\0"+
    "\1\1\1\11\3\0\2\11\1\0\1\11\5\0\1\11"+
    "\1\0\1\1\2\0\1\11\5\0\1\11\1\0\1\11"+
    "\1\0\1\11\3\0\1\11\2\0\2\11\3\0\5\11"+
    "\1\0\2\11\1\0\2\11\1\0\1\11";

  private static int [] zzUnpackAttribute() {
    int [] result = new int[116];
    int offset = 0;
    offset = zzUnpackAttribute(ZZ_ATTRIBUTE_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackAttribute(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }

  /** the input device */
  private java.io.Reader zzReader;

  /** the current state of the DFA */
  private int zzState;

  /** the current lexical state */
  private int zzLexicalState = YYINITIAL;

  /** this buffer contains the current text to be matched and is
      the source of the yytext() string */
  private char zzBuffer[] = new char[ZZ_BUFFERSIZE];

  /** the textposition at the last accepting state */
  private int zzMarkedPos;

  /** the current text position in the buffer */
  private int zzCurrentPos;

  /** startRead marks the beginning of the yytext() string in the buffer */
  private int zzStartRead;

  /** endRead marks the last character in the buffer, that has been read
      from input */
  private int zzEndRead;

  /** number of newlines encountered up to the start of the matched text */
  private int yyline;

  /** the number of characters up to the start of the matched text */
  private int yychar;

  /**
   * the number of characters from the last newline up to the start of the 
   * matched text
   */
  private int yycolumn;

  /** 
   * zzAtBOL == true iff the scanner is currently at the beginning of a line
   */
  private boolean zzAtBOL = true;

  /** zzAtEOF == true iff the scanner is at the EOF */
  private boolean zzAtEOF;

  /** denotes if the user-EOF-code has already been executed */
  private boolean zzEOFDone;
  
  /** 
   * The number of occupied positions in zzBuffer beyond zzEndRead.
   * When a lead/high surrogate has been read from the input stream
   * into the final zzBuffer position, this will have a value of 1;
   * otherwise, it will have a value of 0.
   */
  private int zzFinalHighSurrogate = 0;

  /* user code: */
  @Override
  public void yyerror(String msg) {
    System.err.println(msg);
    System.exit(1);
  }

  @Override
  public Object getLVal() {
    return tokenInfo;
  }

  public boolean isZzAtBOL() {
    return zzAtBOL;
  };

  private compiler.holder.TokenInfo tokenInfo;



  /**
   * Creates a new scanner
   *
   * @param   in  the java.io.Reader to read input from.
   */
  CompilerFlex(java.io.Reader in) {
    this.zzReader = in;
  }


  /** 
   * Unpacks the compressed character translation table.
   *
   * @param packed   the packed character translation table
   * @return         the unpacked character translation table
   */
  private static char [] zzUnpackCMap(String packed) {
    char [] map = new char[0x110000];
    int i = 0;  /* index in packed string  */
    int j = 0;  /* index in unpacked array */
    while (i < 148) {
      int  count = packed.charAt(i++);
      char value = packed.charAt(i++);
      do map[j++] = value; while (--count > 0);
    }
    return map;
  }


  /**
   * Refills the input buffer.
   *
   * @return      <code>false</code>, iff there was new input.
   * 
   * @exception   java.io.IOException  if any I/O-Error occurs
   */
  private boolean zzRefill() throws java.io.IOException {

    /* first: make room (if you can) */
    if (zzStartRead > 0) {
      zzEndRead += zzFinalHighSurrogate;
      zzFinalHighSurrogate = 0;
      System.arraycopy(zzBuffer, zzStartRead,
                       zzBuffer, 0,
                       zzEndRead-zzStartRead);

      /* translate stored positions */
      zzEndRead-= zzStartRead;
      zzCurrentPos-= zzStartRead;
      zzMarkedPos-= zzStartRead;
      zzStartRead = 0;
    }

    /* is the buffer big enough? */
    if (zzCurrentPos >= zzBuffer.length - zzFinalHighSurrogate) {
      /* if not: blow it up */
      char newBuffer[] = new char[zzBuffer.length*2];
      System.arraycopy(zzBuffer, 0, newBuffer, 0, zzBuffer.length);
      zzBuffer = newBuffer;
      zzEndRead += zzFinalHighSurrogate;
      zzFinalHighSurrogate = 0;
    }

    /* fill the buffer with new input */
    int requested = zzBuffer.length - zzEndRead;
    int numRead = zzReader.read(zzBuffer, zzEndRead, requested);

    /* not supposed to occur according to specification of java.io.Reader */
    if (numRead == 0) {
      throw new java.io.IOException("Reader returned 0 characters. See JFlex examples for workaround.");
    }
    if (numRead > 0) {
      zzEndRead += numRead;
      /* If numRead == requested, we might have requested to few chars to
         encode a full Unicode character. We assume that a Reader would
         otherwise never return half characters. */
      if (numRead == requested) {
        if (Character.isHighSurrogate(zzBuffer[zzEndRead - 1])) {
          --zzEndRead;
          zzFinalHighSurrogate = 1;
        }
      }
      /* potentially more input available */
      return false;
    }

    /* numRead < 0 ==> end of stream */
    return true;
  }

    
  /**
   * Closes the input stream.
   */
  public final void yyclose() throws java.io.IOException {
    zzAtEOF = true;            /* indicate end of file */
    zzEndRead = zzStartRead;  /* invalidate buffer    */

    if (zzReader != null)
      zzReader.close();
  }


  /**
   * Resets the scanner to read from a new input stream.
   * Does not close the old reader.
   *
   * All internal variables are reset, the old input stream 
   * <b>cannot</b> be reused (internal buffer is discarded and lost).
   * Lexical state is set to <tt>ZZ_INITIAL</tt>.
   *
   * Internal scan buffer is resized down to its initial length, if it has grown.
   *
   * @param reader   the new input stream 
   */
  public final void yyreset(java.io.Reader reader) {
    zzReader = reader;
    zzAtBOL  = true;
    zzAtEOF  = false;
    zzEOFDone = false;
    zzEndRead = zzStartRead = 0;
    zzCurrentPos = zzMarkedPos = 0;
    zzFinalHighSurrogate = 0;
    yyline = yychar = yycolumn = 0;
    zzLexicalState = YYINITIAL;
    if (zzBuffer.length > ZZ_BUFFERSIZE)
      zzBuffer = new char[ZZ_BUFFERSIZE];
  }


  /**
   * Returns the current lexical state.
   */
  public final int yystate() {
    return zzLexicalState;
  }


  /**
   * Enters a new lexical state
   *
   * @param newState the new lexical state
   */
  public final void yybegin(int newState) {
    zzLexicalState = newState;
  }


  /**
   * Returns the text matched by the current regular expression.
   */
  public final String yytext() {
    return new String( zzBuffer, zzStartRead, zzMarkedPos-zzStartRead );
  }


  /**
   * Returns the character at position <tt>pos</tt> from the 
   * matched text. 
   * 
   * It is equivalent to yytext().charAt(pos), but faster
   *
   * @param pos the position of the character to fetch. 
   *            A value from 0 to yylength()-1.
   *
   * @return the character at position pos
   */
  public final char yycharat(int pos) {
    return zzBuffer[zzStartRead+pos];
  }


  /**
   * Returns the length of the matched text region.
   */
  public final int yylength() {
    return zzMarkedPos-zzStartRead;
  }


  /**
   * Reports an error that occured while scanning.
   *
   * In a wellformed scanner (no or only correct usage of 
   * yypushback(int) and a match-all fallback rule) this method 
   * will only be called with things that "Can't Possibly Happen".
   * If this method is called, something is seriously wrong
   * (e.g. a JFlex bug producing a faulty scanner etc.).
   *
   * Usual syntax/scanner level error handling should be done
   * in error fallback rules.
   *
   * @param   errorCode  the code of the errormessage to display
   */
  private void zzScanError(int errorCode) {
    String message;
    try {
      message = ZZ_ERROR_MSG[errorCode];
    }
    catch (ArrayIndexOutOfBoundsException e) {
      message = ZZ_ERROR_MSG[ZZ_UNKNOWN_ERROR];
    }

    throw new Error(message);
  } 


  /**
   * Pushes the specified amount of characters back into the input stream.
   *
   * They will be read again by then next call of the scanning method
   *
   * @param number  the number of characters to be read again.
   *                This number must not be greater than yylength()!
   */
  public void yypushback(int number)  {
    if ( number > yylength() )
      zzScanError(ZZ_PUSHBACK_2BIG);

    zzMarkedPos -= number;
  }


  /**
   * Resumes scanning until the next regular expression is matched,
   * the end of input is encountered or an I/O-Error occurs.
   *
   * @return      the next token
   * @exception   java.io.IOException  if any I/O-Error occurs
   */
  public int yylex() throws java.io.IOException {
    int zzInput;
    int zzAction;

    // cached fields:
    int zzCurrentPosL;
    int zzMarkedPosL;
    int zzEndReadL = zzEndRead;
    char [] zzBufferL = zzBuffer;
    char [] zzCMapL = ZZ_CMAP;

    int [] zzTransL = ZZ_TRANS;
    int [] zzRowMapL = ZZ_ROWMAP;
    int [] zzAttrL = ZZ_ATTRIBUTE;

    while (true) {
      zzMarkedPosL = zzMarkedPos;

      boolean zzR = false;
      int zzCh;
      int zzCharCount;
      for (zzCurrentPosL = zzStartRead  ;
           zzCurrentPosL < zzMarkedPosL ;
           zzCurrentPosL += zzCharCount ) {
        zzCh = Character.codePointAt(zzBufferL, zzCurrentPosL, zzMarkedPosL);
        zzCharCount = Character.charCount(zzCh);
        switch (zzCh) {
        case '\u000B':  // fall through
        case '\u000C':  // fall through
        case '\u0085':  // fall through
        case '\u2028':  // fall through
        case '\u2029':
          yyline++;
          yycolumn = 0;
          zzR = false;
          break;
        case '\r':
          yyline++;
          yycolumn = 0;
          zzR = true;
          break;
        case '\n':
          if (zzR)
            zzR = false;
          else {
            yyline++;
            yycolumn = 0;
          }
          break;
        default:
          zzR = false;
          yycolumn += zzCharCount;
        }
      }

      if (zzR) {
        // peek one character ahead if it is \n (if we have counted one line too much)
        boolean zzPeek;
        if (zzMarkedPosL < zzEndReadL)
          zzPeek = zzBufferL[zzMarkedPosL] == '\n';
        else if (zzAtEOF)
          zzPeek = false;
        else {
          boolean eof = zzRefill();
          zzEndReadL = zzEndRead;
          zzMarkedPosL = zzMarkedPos;
          zzBufferL = zzBuffer;
          if (eof) 
            zzPeek = false;
          else 
            zzPeek = zzBufferL[zzMarkedPosL] == '\n';
        }
        if (zzPeek) yyline--;
      }
      if (zzMarkedPosL > zzStartRead) {
        switch (zzBufferL[zzMarkedPosL-1]) {
        case '\n':
        case '\u000B':  // fall through
        case '\u000C':  // fall through
        case '\u0085':  // fall through
        case '\u2028':  // fall through
        case '\u2029':  // fall through
          zzAtBOL = true;
          break;
        case '\r': 
          if (zzMarkedPosL < zzEndReadL)
            zzAtBOL = zzBufferL[zzMarkedPosL] != '\n';
          else if (zzAtEOF)
            zzAtBOL = false;
          else {
            boolean eof = zzRefill();
            zzMarkedPosL = zzMarkedPos;
            zzEndReadL = zzEndRead;
            zzBufferL = zzBuffer;
            if (eof) 
              zzAtBOL = false;
            else 
              zzAtBOL = zzBufferL[zzMarkedPosL] != '\n';
          }
          break;
        default:
          zzAtBOL = false;
        }
      }
      zzAction = -1;

      zzCurrentPosL = zzCurrentPos = zzStartRead = zzMarkedPosL;
  
      if (zzAtBOL)
        zzState = ZZ_LEXSTATE[zzLexicalState+1];
      else
        zzState = ZZ_LEXSTATE[zzLexicalState];

      // set up zzAction for empty match case:
      int zzAttributes = zzAttrL[zzState];
      if ( (zzAttributes & 1) == 1 ) {
        zzAction = zzState;
      }


      zzForAction: {
        while (true) {
    
          if (zzCurrentPosL < zzEndReadL) {
            zzInput = Character.codePointAt(zzBufferL, zzCurrentPosL, zzEndReadL);
            zzCurrentPosL += Character.charCount(zzInput);
          }
          else if (zzAtEOF) {
            zzInput = YYEOF;
            break zzForAction;
          }
          else {
            // store back cached positions
            zzCurrentPos  = zzCurrentPosL;
            zzMarkedPos   = zzMarkedPosL;
            boolean eof = zzRefill();
            // get translated positions and possibly new buffer
            zzCurrentPosL  = zzCurrentPos;
            zzMarkedPosL   = zzMarkedPos;
            zzBufferL      = zzBuffer;
            zzEndReadL     = zzEndRead;
            if (eof) {
              zzInput = YYEOF;
              break zzForAction;
            }
            else {
              zzInput = Character.codePointAt(zzBufferL, zzCurrentPosL, zzEndReadL);
              zzCurrentPosL += Character.charCount(zzInput);
            }
          }
          int zzNext = zzTransL[ zzRowMapL[zzState] + zzCMapL[zzInput] ];
          if (zzNext == -1) break zzForAction;
          zzState = zzNext;

          zzAttributes = zzAttrL[zzState];
          if ( (zzAttributes & 1) == 1 ) {
            zzAction = zzState;
            zzMarkedPosL = zzCurrentPosL;
            if ( (zzAttributes & 8) == 8 ) break zzForAction;
          }

        }
      }

      // store back cached position
      zzMarkedPos = zzMarkedPosL;

      if (zzInput == YYEOF && zzStartRead == zzCurrentPos) {
        zzAtEOF = true;
        return YYEOF;
      }
      else {
        switch (zzAction < 0 ? zzAction : ZZ_ACTION[zzAction]) {
          case 1: 
            { tokenInfo = new TokenInfo<>(yytext(),yyline+1);
                return ERROR;
            } 
            // fall through
          case 41: break;
          case 2: 
            { /* Do nothing */
            } 
            // fall through
          case 42: break;
          case 3: 
            { tokenInfo = new TokenInfo<>(yytext(),yyline+1, PIDENTIFIER);
                return PIDENTIFIER;
            } 
            // fall through
          case 43: break;
          case 4: 
            { if (tokenInfo.getTokenId() == PIDENTIFIER) yyerror("Error in line "+(yyline+1)+": unrecognized inscription "+yytext());
                 tokenInfo = new TokenInfo<>(Integer.parseInt(yytext()),yyline+1);
                 return NUM;
            } 
            // fall through
          case 44: break;
          case 5: 
            { System.out.print(yytext());
            } 
            // fall through
          case 45: break;
          case 6: 
            { tokenInfo = new TokenInfo<>(yytext(),yyline+1);
                return COMMA;
            } 
            // fall through
          case 46: break;
          case 7: 
            { tokenInfo = new TokenInfo<>(yytext(),yyline+1);
                return L_BRACKET;
            } 
            // fall through
          case 47: break;
          case 8: 
            { tokenInfo = new TokenInfo<>(yytext(),yyline+1);
                return R_BRACKET;
            } 
            // fall through
          case 48: break;
          case 9: 
            { tokenInfo = new TokenInfo<>(yytext(),yyline+1);
                return SEMICOLON;
            } 
            // fall through
          case 49: break;
          case 10: 
            { tokenInfo = new TokenInfo<>(yytext(),yyline+1);
                return COLON;
            } 
            // fall through
          case 50: break;
          case 11: 
            { tokenInfo = new TokenInfo<>(yytext(),yyline+1);
                return DO;
            } 
            // fall through
          case 51: break;
          case 12: 
            { tokenInfo = new TokenInfo<>(yytext(),yyline+1);
                return EQ;
            } 
            // fall through
          case 52: break;
          case 13: 
            { tokenInfo = new TokenInfo<>(yytext(),yyline+1);
                return LE;
            } 
            // fall through
          case 53: break;
          case 14: 
            { tokenInfo = new TokenInfo<>(yytext(),yyline+1);
                return GE;
            } 
            // fall through
          case 54: break;
          case 15: 
            { tokenInfo = new TokenInfo<>(yytext(),yyline+1);
                return IF;
            } 
            // fall through
          case 55: break;
          case 16: 
            { tokenInfo = new TokenInfo<>(yytext(),yyline+1);
                return TO;
            } 
            // fall through
          case 56: break;
          case 17: 
            { tokenInfo = new TokenInfo<>(yytext(),yyline+1);
                return DIV;
            } 
            // fall through
          case 57: break;
          case 18: 
            { tokenInfo = new TokenInfo<>(yytext(),yyline+1);
                return END;
            } 
            // fall through
          case 58: break;
          case 19: 
            { tokenInfo = new TokenInfo<>(yytext(),yyline+1);
                return LEQ;
            } 
            // fall through
          case 59: break;
          case 20: 
            { tokenInfo = new TokenInfo<>(yytext(),yyline+1);
                return GEQ;
            } 
            // fall through
          case 60: break;
          case 21: 
            { tokenInfo = new TokenInfo<>(yytext(),yyline+1);
                return NEQ;
            } 
            // fall through
          case 61: break;
          case 22: 
            { tokenInfo = new TokenInfo<>(yytext(),yyline+1);
                return FOR;
            } 
            // fall through
          case 62: break;
          case 23: 
            { tokenInfo = new TokenInfo<>(yytext(),yyline+1);
                return MOD;
            } 
            // fall through
          case 63: break;
          case 24: 
            { tokenInfo = new TokenInfo<>(yytext(),yyline+1);
                return ELSE;
            } 
            // fall through
          case 64: break;
          case 25: 
            { tokenInfo = new TokenInfo<>(yytext(),yyline+1);
                return READ;
            } 
            // fall through
          case 65: break;
          case 26: 
            { tokenInfo = new TokenInfo<>(yytext(),yyline+1);
                return FROM;
            } 
            // fall through
          case 66: break;
          case 27: 
            { tokenInfo = new TokenInfo<>(yytext(),yyline+1);
                return THEN;
            } 
            // fall through
          case 67: break;
          case 28: 
            { tokenInfo = new TokenInfo<>(yytext(),yyline+1);
                return PLUS;
            } 
            // fall through
          case 68: break;
          case 29: 
            { tokenInfo = new TokenInfo<>(yytext(),yyline+1);
                return ENDDO;
            } 
            // fall through
          case 69: break;
          case 30: 
            { tokenInfo = new TokenInfo<>(yytext(),yyline+1);
                return ENDIF;
            } 
            // fall through
          case 70: break;
          case 31: 
            { tokenInfo = new TokenInfo<>(yytext(),yyline+1);
                return BEGIN;
            } 
            // fall through
          case 71: break;
          case 32: 
            { tokenInfo = new TokenInfo<>(yytext(),yyline+1);
                return TIMES;
            } 
            // fall through
          case 72: break;
          case 33: 
            { tokenInfo = new TokenInfo<>(yytext(),yyline+1);
                return WRITE;
            } 
            // fall through
          case 73: break;
          case 34: 
            { tokenInfo = new TokenInfo<>(yytext(),yyline+1);
                return WHILE;
            } 
            // fall through
          case 74: break;
          case 35: 
            { tokenInfo = new TokenInfo<>(yytext(),yyline+1);
                return MINUS;
            } 
            // fall through
          case 75: break;
          case 36: 
            { tokenInfo = new TokenInfo<>(yytext(),yyline+1);
                return DOWNTO;
            } 
            // fall through
          case 76: break;
          case 37: 
            { tokenInfo = new TokenInfo<>(yytext(),yyline+1);
                return ENDFOR;
            } 
            // fall through
          case 77: break;
          case 38: 
            { tokenInfo = new TokenInfo<>(yytext(),yyline+1);
                return ASSIGN;
            } 
            // fall through
          case 78: break;
          case 39: 
            { tokenInfo = new TokenInfo<>(yytext(),yyline+1);
                return DECLARE;
            } 
            // fall through
          case 79: break;
          case 40: 
            { tokenInfo = new TokenInfo<>(yytext(),yyline+1);
                return ENDWHILE;
            } 
            // fall through
          case 80: break;
          default:
            zzScanError(ZZ_NO_MATCH);
        }
      }
    }
  }

  /**
   * Runs the scanner on input files.
   *
   * This is a standalone scanner, it will print any unmatched
   * text to System.out unchanged.
   *
   * @param argv   the command line, contains the filenames to run
   *               the scanner on.
   */
  public static void main(String argv[]) {
    if (argv.length == 0) {
      System.out.println("Usage : java CompilerFlex [ --encoding <name> ] <inputfile(s)>");
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
        CompilerFlex scanner = null;
        try {
          java.io.FileInputStream stream = new java.io.FileInputStream(argv[i]);
          java.io.Reader reader = new java.io.InputStreamReader(stream, encodingName);
          scanner = new CompilerFlex(reader);
          while ( !scanner.zzAtEOF ) scanner.yylex();
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
