
%%
%implements Compiler.Lexer
%class Flex
%public
%standalone
%line
%column

%{
  @Override
  public void yyerror(String msg) {
    System.err.println(msg);
    System.exit(1);
  }

  @Override
  public Object getLVal() {
    return tokenHolder;
  }

  public int getYyline(){
  return yyline++;
  }

  private TokenHolder tokenHolder = setToken();

  private TokenHolder setToken(){
  TokenHolder tokenHolder = new TokenHolder();
  JumpHolder jump = new JumpHolder();
  tokenHolder.jumpHolder = jump;
  return tokenHolder;
  }
%}
num = [-]?[0-9]+
id = [_a-z]+
comment = \[[^\[\]]*\]

%%
{comment}           {/* do nothink */}
{id} 				{
                        tokenHolder = setToken();
                        tokenHolder.setSemanticValue(yytext());
                        return PIDENTIFIER	;			}
{num} 				{
                        tokenHolder = setToken();
                        tokenHolder.setNumericValue(Long.parseLong(yytext()));
						return NUM;					    }
"BEGIN"				{ 	return BEGIN;		 		    }
"DECLARE"		    {	return DECLARE;					}
"WRITE" 		    {	return WRITE;					}
"ASSIGN" 		    {	return ASSIGN;	 				}
"IF" 			    {	return IF;						}
"THEN" 				{	return THEN;					}
"ELSE" 				{	return ELSE;					}
"ENDIF" 		    {	return ENDIF;					}
"FOR" 				{	return FOR;						}
"TO" 			    {	return TO;						}
"DOWNTO" 		    {	return DOWNTO;					}
"WHILE" 		    {	return WHILE;					}
"ENDWHILE" 			{	return ENDWHILE;				}
"ENDDO" 			{	return ENDDO;					}
"ENDFOR" 			{	return ENDFOR;					}
"FROM" 				{	return FROM;					}
"READ" 				{	return READ;					}
"DO" 				{	return DO;						}
"PLUS" 				{	return PLUS;					}
"END"				{	return END;						}
"MINUS" 			{	return MINUS;					}
"TIMES" 			{	return TIMES;					}
"DIV" 				{	return DIV;						}
"MOD" 				{	return MOD;						}
"EQ" 				{	return EQ;						}
"NEQ" 				{	return NEQ;						}
"LE" 				{	return LE;						}
"GE" 				{	return GE;						}
"LEQ" 				{	return LEQ;						}
"GEQ" 				{	return GEQ;						}
,                   {     return (COMMA);               }
\(                  {   return (L_BRACKET);             }
\)                  {   return (R_BRACKET);             }
;                   {   return (SEMICOLON);             }
:                   {   return (COLON);                 }

[ \t\n]+			{		/* do nothink */			}
.  				{ }


