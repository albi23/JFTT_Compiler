package compiler;

import compiler.holder.TokenInfo;
import java.math.BigInteger;

%%
%implements CompilerBison.Lexer
%class CompilerFlex
%public
%standalone
%line
%column
/*%debug*/
%{
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

  private int setLastTokenAndReturn(int id){
    lastTokenId = id;
    return id;
  }

  private int lastTokenId = -1;
  private compiler.holder.TokenInfo tokenInfo;

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
                 if (lastTokenId == PIDENTIFIER) yyerror("Error in line "+(yyline+1)+": unrecognized inscription "+yytext());
                 tokenInfo = new TokenInfo<>(new BigInteger(yytext()),yyline+1, lastTokenId);
                 return setLastTokenAndReturn(NUM);
                }
"DECLARE"     {
                tokenInfo = new TokenInfo<>(yytext(),yyline+1);
                return setLastTokenAndReturn(DECLARE);}
{pidentifier} {
                tokenInfo = new TokenInfo<>(yytext(),yyline+1, lastTokenId);
                return setLastTokenAndReturn(PIDENTIFIER); }
"BEGIN"       {
                tokenInfo = new TokenInfo<>(yytext(),yyline+1);
                return setLastTokenAndReturn(BEGIN); }
"END"         {
                tokenInfo = new TokenInfo<>(yytext(),yyline+1);
                return setLastTokenAndReturn(END); }
"ASSIGN"      {
                tokenInfo = new TokenInfo<>(yytext(),yyline+1);
                return setLastTokenAndReturn(ASSIGN); }
"IF"          {
                tokenInfo = new TokenInfo<>(yytext(),yyline+1);
                return setLastTokenAndReturn(IF); }
"THEN"        {
                tokenInfo = new TokenInfo<>(yytext(),yyline+1);
                return setLastTokenAndReturn(THEN); }
"ELSE"        {
                tokenInfo = new TokenInfo<>(yytext(),yyline+1);
                return setLastTokenAndReturn(ELSE); }
"ENDIF"       {
                tokenInfo = new TokenInfo<>(yytext(),yyline+1);
                return setLastTokenAndReturn(ENDIF); }
"WHILE"       {
                tokenInfo = new TokenInfo<>(yytext(),yyline+1);
                return setLastTokenAndReturn(WHILE); }
"DO"          {
                tokenInfo = new TokenInfo<>(yytext(),yyline+1);
                return setLastTokenAndReturn(DO); }
"ENDWHILE"    {
                tokenInfo = new TokenInfo<>(yytext(),yyline+1);
                return setLastTokenAndReturn(ENDWHILE); }
"ENDDO"       {
                tokenInfo = new TokenInfo<>(yytext(),yyline+1);
                return setLastTokenAndReturn(ENDDO); }
"FOR"         {
                tokenInfo = new TokenInfo<>(yytext(),yyline+1);
                return setLastTokenAndReturn(FOR); }
"FROM"        {
                tokenInfo = new TokenInfo<>(yytext(),yyline+1);
                return setLastTokenAndReturn(FROM); }
"TO"          {
                tokenInfo = new TokenInfo<>(yytext(),yyline+1);
                return setLastTokenAndReturn(TO); }
"ENDFOR"      {
                tokenInfo = new TokenInfo<>(yytext(),yyline+1);
                return setLastTokenAndReturn(ENDFOR); }
"DOWNTO"      {
                tokenInfo = new TokenInfo<>(yytext(),yyline+1);
                return setLastTokenAndReturn(DOWNTO); }
"READ"        {
                tokenInfo = new TokenInfo<>(yytext(),yyline+1);
                return setLastTokenAndReturn(READ); }
"WRITE"       {
                tokenInfo = new TokenInfo<>(yytext(),yyline+1);
                return setLastTokenAndReturn(WRITE); }
"PLUS"        {
                tokenInfo = new TokenInfo<>(yytext(),yyline+1);
                return setLastTokenAndReturn(PLUS); }
"MINUS"       {
                tokenInfo = new TokenInfo<>(yytext(),yyline+1);
                return setLastTokenAndReturn(MINUS); }
"TIMES"       {
                tokenInfo = new TokenInfo<>(yytext(),yyline+1);
                return setLastTokenAndReturn(TIMES); }
"DIV"         {
                tokenInfo = new TokenInfo<>(yytext(),yyline+1);
                return setLastTokenAndReturn(DIV); }
"MOD"         {
                tokenInfo = new TokenInfo<>(yytext(),yyline+1);
                return setLastTokenAndReturn(MOD); }
"EQ"          {
                tokenInfo = new TokenInfo<>(yytext(),yyline+1);
                return setLastTokenAndReturn(EQ); }
"NEQ"         {
                tokenInfo = new TokenInfo<>(yytext(),yyline+1);
                return setLastTokenAndReturn(NEQ); }
"LE"          {
                tokenInfo = new TokenInfo<>(yytext(),yyline+1);
                return setLastTokenAndReturn(LE); }
"GE"          {
                tokenInfo = new TokenInfo<>(yytext(),yyline+1);
                return setLastTokenAndReturn(GE); }
"LEQ"         {
                tokenInfo = new TokenInfo<>(yytext(),yyline+1);
                return setLastTokenAndReturn(LEQ); }
"GEQ"         {
                tokenInfo = new TokenInfo<>(yytext(),yyline+1);
                return setLastTokenAndReturn(GEQ); }
,             {
                tokenInfo = new TokenInfo<>(yytext(),yyline+1);
                return setLastTokenAndReturn(COMMA);}
\(            {
                tokenInfo = new TokenInfo<>(yytext(),yyline+1);
                return setLastTokenAndReturn(L_BRACKET); }
\)            {
                tokenInfo = new TokenInfo<>(yytext(),yyline+1);
                return setLastTokenAndReturn(R_BRACKET); }
;             {
                tokenInfo = new TokenInfo<>(yytext(),yyline+1);
                return setLastTokenAndReturn(SEMICOLON);  }
:             {
                tokenInfo = new TokenInfo<>(yytext(),yyline+1);
                return setLastTokenAndReturn(COLON);  }
.             {
                tokenInfo = new TokenInfo<>(yytext(),yyline+1);
                return setLastTokenAndReturn(ERROR); }

