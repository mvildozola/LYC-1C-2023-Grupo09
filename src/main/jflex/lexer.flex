package lyc.compiler;

import java_cup.runtime.Symbol;
import lyc.compiler.ParserSym;
import lyc.compiler.model.*;
import static lyc.compiler.constants.Constants.*;

%%

%public
%class Lexer
%unicode
%cup
%line
%column
%throws CompilerException
%eofval{
  return symbol(ParserSym.EOF);
%eofval}


%{
  private Symbol symbol(int type) {
    return new Symbol(type, yyline, yycolumn);
  }
  private Symbol symbol(int type, Object value) {
    return new Symbol(type, yyline, yycolumn, value);
  }
%}


LineTerminator = \r|\n|\r\n
InputCharacter = [^\r\n]
Identation =  [ \t\f]

While = "ciclo"
If="if"
Else= "else"
Read= "read"
Write="write"
Not="not"
Init="init"
Float="Float"
String="String"
Int="Int"
ElemntInTheMiddle="ElementInTheMiddle"

OpenBracket = "("
CloseBracket = ")"
OpenCurlyBracket = "{"
CloseCurlyBracket = "}"
Letter = [a-zA-Z]
Digit = [0-9]
Comments =  "*-"~"-*"
LessThan = "<"
LessEqualThan= "<="
GreaterThan=">"
GreaterEqualThan=">="
And="&"
Or="||"

Plus = "+"
Mult = "*"
Sub = "-"
Div = "/"
Assig = "="
DoubleQuote="\""
Dot="."
DoublePoints=":"
Comma= ","

WhiteSpace = {LineTerminator} | {Identation}
Identifier = {Letter} ({Letter}|{Digit})*
IntegerConstant = {Digit}+
StringConstant= \"([^\"\\\\]|\\\\.)*\"
FloatConstant = {Digit}*{Dot}{Digit}*


%%


/* keywords */

<YYINITIAL> {
  /* Comments */
  {Comments}                                { /* ignore */ }
  /* identifiers */
  {While}                                  { return symbol(ParserSym.WHILE); }  
  {If}                                     { return symbol(ParserSym.IF); } 
  {Else}                                   { return symbol(ParserSym.ELSE); }
  {Read}                                   { return symbol(ParserSym.READ); }
  {Write}                                  { return symbol(ParserSym.WRITE); }
  {Not}                                    { return symbol(ParserSym.NOT); }
  {DoublePoints}                           { return symbol(ParserSym.DOUBLE_POINTS); }
  {Float}                                  { return symbol(ParserSym.FLOAT); }
  {String}                                 { return symbol(ParserSym.STRING); }
  {Int}                                    { return symbol(ParserSym.INT); }
  {Init}                                   { return symbol(ParserSym.INIT); }
  {ElemntInTheMiddle}                      { return symbol(ParserSym.ELEMENT_IN_THE_MIDDLE); }
  {Identifier}                             { return symbol(ParserSym.IDENTIFIER, yytext()); }
  /* Constants */
  {IntegerConstant}                        { return symbol(ParserSym.INTEGER_CONSTANT, yytext()); }
  {StringConstant}                         { return symbol(ParserSym.STRING_CONSTANT, yytext()); }
  {FloatConstant}                          { return symbol(ParserSym.FLOAT_CONSTANT, yytext()); }

  /* operators */
  {Plus}                                    { return symbol(ParserSym.PLUS); }
  {Sub}                                     { return symbol(ParserSym.SUB); }
  {Mult}                                    { return symbol(ParserSym.MULT); }
  {Div}                                     { return symbol(ParserSym.DIV); }
  {Assig}                                   { return symbol(ParserSym.ASSIG); }
  {OpenBracket}                             { return symbol(ParserSym.OPEN_BRACKET); }
  {CloseBracket}                            { return symbol(ParserSym.CLOSE_BRACKET); }
  {OpenCurlyBracket}                        { return symbol(ParserSym.OPEN_CURLY_BRACKET); }
  {CloseCurlyBracket}                       { return symbol(ParserSym.CLOSE_CURLY_BRACKET); }
  {LessThan}                                { return symbol(ParserSym.LESS_THAN); }
  {LessEqualThan}                           { return symbol(ParserSym.LESS_EQUAL_THAN); }
  {GreaterThan}                             { return symbol(ParserSym.GREATER_THAN); }
  {GreaterEqualThan}                        { return symbol(ParserSym.GREATER_EQUAL_THAN); }
  {And}                                     { return symbol(ParserSym.AND); }
  {Or}                                      { return symbol(ParserSym.OR); }
  {Comma}                                   { return symbol(ParserSym.COMMA); }

  /* whitespace */
  {WhiteSpace}                              { /* ignore */ }
}

/* error fallback */
[^]                                         { throw new UnknownCharacterException(yytext()); }