package compiler;

%%
%implements CompilerBison.Lexer
%class CompilerFlex
%standalone
%line
%column
/*%debug*/
%{
  @Override
  public void yyerror(String msg) {
    System.err.println(msg);
  }

  @Override
  public Object getLVal() {
    return tokenInfo;
  }

  public boolean isZzAtBOL() {
    return zzAtBOL;
  };

  public int getYyline() {
    return yyline;
  }

  private TokenInfo tokenInfo;

    public class TokenInfo<V> {

      private V semanticValue;
      private int linePos;

      public TokenInfo(V semanticValue, int linePos) {
        this.semanticValue = semanticValue;
        this.linePos = linePos;
      }

      public V getSemanticValue() {
        return semanticValue;
      }
      public int getLinePos() {
        return linePos;
      }

      @Override
      public String toString() {
        return "TokenInfo{" +
                "semanticValue='" + semanticValue + '\'' +
                ", linePos=" + linePos +
                '}';
      }
    }
%}
escaped_newline = \\\n
pidentifier = [_a-z]+
comment = \[[^\[\]]*\]
numbers = 0|[1-9][0-9]*|-[1-9][0-9]*
%%

^#(.|{escaped_newline})*\n  {/* Do nothing */}
{escaped_newline}           {/* Do nothing */}
[ \t]+ 	                    {/* Do nothing */}
{comment}     	            {/* Do nothing */}
\n            { /* Do nothing */ }
{numbers}       {
                 tokenInfo = new TokenInfo<>(Integer.parseInt(yytext()),yyline+1);
                 return NUM;
                }
"DECLARE"     {
                tokenInfo = new TokenInfo<>(yytext(),yyline+1);
                return DECLARE;}
{pidentifier} {
                tokenInfo = new TokenInfo<>(yytext(),yyline+1);
                return PIDENTIFIER; }
"BEGIN"       {
                tokenInfo = new TokenInfo<>(yytext(),yyline+1);
                return BEGIN; }
"END"         {
                tokenInfo = new TokenInfo<>(yytext(),yyline+1);
                return END; }
"ASSIGN"      {
                tokenInfo = new TokenInfo<>(yytext(),yyline+1);
                return ASSIGN; }
"IF"          {
                tokenInfo = new TokenInfo<>(yytext(),yyline+1);
                return IF; }
"THEN"        {
                tokenInfo = new TokenInfo<>(yytext(),yyline+1);
                return THEN; }
"ELSE"        {
                tokenInfo = new TokenInfo<>(yytext(),yyline+1);
                return ELSE; }
"ENDIF"       {
                tokenInfo = new TokenInfo<>(yytext(),yyline+1);
                return ENDIF; }
"WHILE"       {
                tokenInfo = new TokenInfo<>(yytext(),yyline+1);
                return WHILE; }
"DO"          {
                tokenInfo = new TokenInfo<>(yytext(),yyline+1);
                return DO; }
"ENDWHILE"    {
                tokenInfo = new TokenInfo<>(yytext(),yyline+1);
                return ENDWHILE; }
"ENDDO"       {
                tokenInfo = new TokenInfo<>(yytext(),yyline+1);
                return ENDDO; }
"FOR"         {
                tokenInfo = new TokenInfo<>(yytext(),yyline+1);
                return FOR; }
"FROM"        {
                tokenInfo = new TokenInfo<>(yytext(),yyline+1);
                return FROM; }
"TO"          {
                tokenInfo = new TokenInfo<>(yytext(),yyline+1);
                return TO; }
"ENDFOR"      {
                tokenInfo = new TokenInfo<>(yytext(),yyline+1);
                return ENDFOR; }
"DOWNTO"      {
                tokenInfo = new TokenInfo<>(yytext(),yyline+1);
                return DOWNTO; }
"READ"        {
                tokenInfo = new TokenInfo<>(yytext(),yyline+1);
                return READ; }
"WRITE"       {
                tokenInfo = new TokenInfo<>(yytext(),yyline+1);
                return WRITE; }
"PLUS"        {
                tokenInfo = new TokenInfo<>(yytext(),yyline+1);
                return PLUS; }
"MINUS"       {
                tokenInfo = new TokenInfo<>(yytext(),yyline+1);
                return MINUS; }
"TIMES"       {
                tokenInfo = new TokenInfo<>(yytext(),yyline+1);
                return TIMES; }
"DIV"         {
                tokenInfo = new TokenInfo<>(yytext(),yyline+1);
                return DIV; }
"MOD"         {
                tokenInfo = new TokenInfo<>(yytext(),yyline+1);
                return MOD; }
"EQ"          {
                tokenInfo = new TokenInfo<>(yytext(),yyline+1);
                return EQ; }
"NEQ"         {
                tokenInfo = new TokenInfo<>(yytext(),yyline+1);
                return NEQ; }
"LE"          {
                tokenInfo = new TokenInfo<>(yytext(),yyline+1);
                return LE; }
"GE"          {
                tokenInfo = new TokenInfo<>(yytext(),yyline+1);
                return GE; }
"LEQ"         {
                tokenInfo = new TokenInfo<>(yytext(),yyline+1);
                return LEQ; }
"GEQ"         {
                tokenInfo = new TokenInfo<>(yytext(),yyline+1);
                return GEQ; }
,             {
                tokenInfo = new TokenInfo<>(yytext(),yyline+1);
                return COMMA;}
\(            {
                tokenInfo = new TokenInfo<>(yytext(),yyline+1);
                return L_BRACKET; }
\)            {
                tokenInfo = new TokenInfo<>(yytext(),yyline+1);
                return R_BRACKET; }
;             {
                tokenInfo = new TokenInfo<>(yytext(),yyline+1);
                return SEMICOLON;  }
:             {
                tokenInfo = new TokenInfo<>(yytext(),yyline+1);
                return COLON;  }
.             {
                tokenInfo = new TokenInfo<>(yytext(),yyline+1);
                return ERROR; }
