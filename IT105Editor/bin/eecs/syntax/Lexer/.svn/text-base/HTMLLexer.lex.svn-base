/* HTMLLexer.java is a generated file.  You probably want to
 * edit HTMLLexer.lex to make changes.  Use JFlex to generate it.
 * To generate HTMLLexer.java
 * Install <a href="http://jflex.de/">JFlex</a> v1.3.2 or later.
 * Once JFlex is in your classpath run<br>
 * <code>java JFlex.Main HTMLLexer.lex</code><br>
 * You will then have a file called HTMLLexer.java
 */

/*
 * This file is part of a <a href="http://ostermiller.org/syntax/">syntax
 * highlighting</a> package.
 * Copyright (C) 1999-2002 Stephen Ostermiller
 * http://ostermiller.org/contact.pl?regarding=Syntax+Highlighting
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * See COPYING.TXT for details.
 */
/* This is an extended version of the original HTMLLexer1, to support XHTML syntax
 * Created by D/EECS, USMA 2003
 */
package eecs.syntax.Lexer;

import java.io.*;

/** 
 * HTMLLexer is an XHTML lexer.  Created with JFlex.  An example of how it is used:
 *  <CODE>
 *  <PRE>
 *  HTMLLexer shredder = new HTMLLexer(System.in);
 *  HTMLToken t;
 *  while ((t = shredder.getNextToken()) != null){
 *      System.out.println(t);
 *  }
 *  </PRE>
 *  </CODE>
 *
 * <P>
 * Changed From: TagUnmatched=([^a-zA-Z0-9\ \r\n\-\.\/\>])
 * 
 * @see HTMLToken
 */ 

%%

%public
%class HTMLLexer
%implements Lexer
%function getNextToken
%type Token 

%{

	private int lastToken;

    private int nextState=YYINITIAL;
	
    /** 
     * next Token method that allows you to control if whitespace and comments are
     * returned as tokens.
     */
    public Token getNextToken(boolean returnComments, boolean returnWhiteSpace)throws IOException{
        Token t = getNextToken();
        while (t != null && ((!returnWhiteSpace && t.isWhiteSpace()) || (!returnComments && t.isComment()))){
            t = getNextToken();
        }
        return (t); 
    }

    /**
     * Prints out tokens from a file or System.in.
     * If no arguments are given, System.in will be used for input.
     * If more arguments are given, the first argument will be used as
     * the name of the file to use as input
     *
     * @param args program arguments, of which the first is a filename
     */
    public static void main(String[] args) {
        InputStream in;
        try {
            if (args.length > 0){
                File f = new File(args[0]);
                if (f.exists()){
                    if (f.canRead()){
                        in = new FileInputStream(f);
                    } else {
                        throw new IOException("Could not open " + args[0]);
                    }
                } else {
                    throw new IOException("Could not find " + args[0]);
                }                   
            } else {
                in = System.in;
            }       
            HTMLLexer shredder = new HTMLLexer(in);
            Token t;
            while ((t = shredder.getNextToken()) != null) {
                if (t.getID() != CToken.WHITE_SPACE){
                    System.out.println(t);
                }
            }
        } catch (IOException e){
            System.out.println(e.getMessage());
        }
    }

    /**
     * Closes the current input stream, and resets the scanner to read from a new input stream.
	 * All internal variables are reset, the old input stream  cannot be reused
	 * (content of the internal buffer is discarded and lost).
	 * The lexical state is set to the initial state.
     * Subsequent tokens read from the lexer will start with the line, char, and column
     * values given here.
     *
     * @param reader The new input.
     * @param yyline The line number of the first token.
     * @param yychar The position (relative to the start of the stream) of the first token.
     * @param yycolumn The position (relative to the line) of the first token.
     * @throws IOException if an IOExecption occurs while switching readers.
     */
    public void reset(java.io.Reader reader, int yyline, int yychar, int yycolumn) throws IOException{
        yyreset(reader);
        this.yyline = yyline;
		this.yychar = yychar;
		this.yycolumn = yycolumn;
	}
%}

%line
%char
%column
%full

%state START_TAG
%state START_END_TAG
%state START_DOC_TAG
%state TAG
%state START_EQUAL
%state START_VALUE
%state SCRIPT_TAG
%state START_SCRIPT_EQUAL
%state START_SCRIPT_VALUE
%state SCRIPT
%state PRE_TAG
%state START_PRE_EQUAL
%state START_PRE_VALUE
%state PRE
%state TEXTAREA_TAG
%state START_TEXTAREA_EQUAL
%state START_TEXTAREA_VALUE
%state TEXTAREA
%state TAG_END
%state DOCTYPE
%state COMMENT_DEF
%state FINISH_END_TAG

Digit=([0-9])
Letter=([a-zA-Z])
HexDigit=({Digit}|[a-fA-F])
BLANK=([ ])
TAB=([\t])
FF=([\f])
CR=([\r])
LF=([\n])
EOL=({CR}|{LF}|{CR}{LF})
WhiteSpace=({BLANK}|{TAB}|{FF}|{EOL})
AnyChar=([^])

DecCharRef=("#"{Digit}+)
HexCharRef=("#"[Xx]{HexDigit}+)
ECR1=("nbsp"|"iexcl"|"cent"|"pound"|"curren"|"yen"|"brvbar"|"sect"|"uml"|"copy"|"ordf"|"laquo"|"not"|"shy"|"reg"|"macr"|"deg")
ECR2=("plusmn"|"sup2"|"sup3"|"acute"|"micro"|"para"|"middot"|"cedil"|"sup1"|"ordm"|"raquo"|"frac14"|"frac12"|"frac34"|"iquest")
ECR3=("Agrave"|"Aacute"|"Acirc"|"Atilde"|"Auml"|"Aring"|"AElig"|"Ccedil"|"Egrave"|"Eacute"|"Ecirc"|"Euml"|"Igrave"|"Iacute")
ECR4=("Icirc"|"Iuml"|"ETH"|"Ntilde"|"Ograve"|"Oacute"|"Ocirc"|"Otilde"|"Ouml"|"times"|"Oslash"|"Ugrave"|"Uacute"|"Ucirc")
ECR5=("Uuml"|"Yacute"|"THORN"|"szlig"|"agrave"|"aacute"|"acirc"|"atilde"|"auml"|"aring"|"aelig"|"ccedil"|"egrave"|"eacute")
ECR6=("ecirc"|"euml"|"igrave"|"iacute"|"icirc"|"iuml"|"eth"|"ntilde"|"ograve"|"oacute"|"ocirc"|"otilde"|"ouml"|"divide")
ECR7=("oslash"|"ugrave"|"uacute"|"ucirc"|"uuml"|"yacute"|"thorn"|"yuml"|"fnof"|"Alpha"|"Beta"|"Gamma"|"Delta"|"Epsilon")
ECR8=("Zeta"|"Eta"|"Theta"|"Iota"|"Kappa"|"Lambda"|"Mu"|"Nu"|"Xi"|"Omicron"|"Pi"|"Rho"|"Sigma"|"Tau"|"Upsilon"|"Phi"|"Chi")
ECR9=("Psi"|"Omega"|"alpha"|"beta"|"gamma"|"delta"|"epsilon"|"zeta"|"eta"|"theta"|"iota"|"kappa"|"lambda"|"mu"|"nu"|"xi")
ECR10=("omicron"|"pi"|"rho"|"sigmaf"|"sigma"|"tau"|"upsilon"|"phi"|"chi"|"psi"|"omega"|"thetasym"|"upsih"|"piv"|"bull")
ECR11=("hellip"|"prime"|"Prime"|"oline"|"frasl"|"weierp"|"image"|"real"|"trade"|"larr"|"uarr"|"rarr"|"darr"|"harr"|"crarr")
ECR12=("lArr"|"uArr"|"rArr"|"dArr"|"hArr"|"forall"|"part"|"exist"|"empty"|"nabla"|"isin"|"notin"|"ni"|"prod"|"sum"|"minus")
ECR13=("lowast"|"radic"|"prop"|"infin"|"ang"|"and"|"or"|"cap"|"cup"|"int"|"there4"|"sim"|"cong"|"asymp"|"ne"|"equiv"|"le")
ECR14=("ge"|"sub"|"sup"|"nsub"|"sube"|"supe"|"oplus"|"otimes"|"perp"|"sdot"|"lceil"|"rceil"|"lfloor"|"rfloor"|"lang"|"rang")
ECR15=("loz"|"spades"|"clubs"|"hearts"|"diams"|"quot"|"amp"|"lt"|"gt"|"OElig"|"oelig"|"Scaron"|"scaron"|"Yuml"|"circ"|"tilde")
ECR16=("ensp"|"emsp"|"thinsp"|"zwnj"|"zwj"|"lrm"|"rlm"|"ndash"|"mdash"|"lsquo"|"rsquo"|"sbquo"|"ldquo"|"rdquo"|"bdquo"|"dagger")
ECR17=("Dagger"|"permil"|"lsaquo"|"rsaquo"|"euro")
UNARY=("link"|"meta"|"hr"|"br"|"img"|"area"|"base"|"input")
EntityCharRef=({ECR1}|{ECR2}|{ECR3}|{ECR4}|{ECR5}|{ECR6}|{ECR7}|{ECR8}|{ECR9}|{ECR10}|{ECR11}|{ECR12}|{ECR13}|{ECR14}|{ECR15}|{ECR16}|{ECR17})
CharacterReference=("&"({EntityCharRef}|{DecCharRef}|{HexCharRef})";"?)
FalseCharRef=("&"[^\ \r\n\<\;\&]*)

StringLiteral=(([\"][^\"]*[\"])|([\'][^\']*[\']))
NameToken=(("xml:")?({Letter}|{Digit}|[\.\-])+)
Value=({StringLiteral})

TagStart=("<")
EndTagStart=("</")
DocTagStart=("<!")
TagEnd=(">")
SelfClosingTagEnd=([\ ]*"/>")
Doctype=([Dd][Oo][Cc][Tt][Yy][Pp][Ee])
DoctypeText=(([^\>\"\']|{StringLiteral})*)

NameScript=([Ss][Cc][Rr][Ii][Pp][Tt])
FalseEndScript=([\<]|[\<][\/]{WhiteSpace}*|[\<][\/]{WhiteSpace}*[Ss]|[\<][\/]{WhiteSpace}*[Ss][Cc]|[\<][\/]{WhiteSpace}*[Ss][Cc][Rr]|[\<][\/]{WhiteSpace}*[Ss][Cc][Rr][Ii]|[\<][\/]{WhiteSpace}*[Ss][Cc][Rr][Ii][Pp]|[\<][\/]{WhiteSpace}*[Ss][Cc][Rr][Ii][Pp][Tt]{WhiteSpace}*)
ScriptText=({CommentDeclaration}|([^\<]|{FalseEndScript}*[\<][^\/\<]|{FalseEndScript}*[\<][\/]{WhiteSpace}*[^Ss\<]|{FalseEndScript}*[\<][\/]{WhiteSpace}*[Ss][^Cc\<]|{FalseEndScript}*[\<][\/]{WhiteSpace}*[Ss][Cc][^Rr\<]|{FalseEndScript}*[\<][\/]{WhiteSpace}*[Ss][Cc][Rr][^Ii\<]|{FalseEndScript}*[\<][\/]{WhiteSpace}*[Ss][Cc][Rr][Ii][^Pp\<]|{FalseEndScript}*[\<][\/]{WhiteSpace}*[Ss][Cc][Rr][Ii][Pp][^Tt\<]|{FalseEndScript}*[\<][\/]{WhiteSpace}*[Ss][Cc][Rr][Ii][Pp][Tt]{WhiteSpace}*[^\<\>])*)

NamePre=(pre)
FalseEndPre=([\<]|[\<][\/]{WhiteSpace}*|[\<][\/]{WhiteSpace}*[Pp]|[\<][\/]{WhiteSpace}*[Pp][Rr]|[\<][\/]{WhiteSpace}*[Pp][Rr][Ee]{WhiteSpace}*)
PreText=(([^\<]|{FalseEndPre}*[\<][^\/\<]|{FalseEndPre}*[\<][\/]{WhiteSpace}*[^Pp\<]|{FalseEndPre}*[\<][\/]{WhiteSpace}*[Pp][^Rr\<]|{FalseEndPre}*[\<][\/]{WhiteSpace}*[Pp][Rr][^Ee\<]|{FalseEndPre}*[\<][\/]{WhiteSpace}*[Pp][Rr][Ee]{WhiteSpace}*[^\<\>])*)

NameTextArea=("textarea")
FalseEndTextArea=([\<]|[\<][\/]{WhiteSpace}*|[\<][\/]{WhiteSpace}*[t]|[\<][\/]{WhiteSpace}*[t][e]|[\<][\/]{WhiteSpace}*[t][e][x]|[\<][\/]{WhiteSpace}*[t][e][x][t]|[\<][\/]{WhiteSpace}*[t][e][x][t][a]|[\<][\/]{WhiteSpace}*[t][e][x][t][a][r]|[\<][\/]{WhiteSpace}*[t][e][x][t][a][r][e]|[\<][\/]{WhiteSpace}*[t][e][x][t][a][r][e][a]{WhiteSpace}*)
TextAreaText=(([^\<]|{FalseEndTextArea}*[\<][^\/\<]|{FalseEndTextArea}*[\<][\/]{WhiteSpace}*[^Tt\<]|{FalseEndTextArea}*[\<][\/]{WhiteSpace}*[Tt][^Ee\<]|{FalseEndTextArea}*[\<][\/]{WhiteSpace}*[Tt][Ee][^Xx\<]|{FalseEndTextArea}*[\<][\/]{WhiteSpace}*[Tt][Ee][Xx][^Tt\<]|{FalseEndTextArea}*[\<][\/]{WhiteSpace}*[Tt][Ee][Xx][Tt][^Aa\<]|{FalseEndTextArea}*[\<][\/]{WhiteSpace}*[Tt][Ee][Xx][Tt][Aa][^Rr\<]|{FalseEndTextArea}*[\<][\/]{WhiteSpace}*[Tt][Ee][Xx][Tt][Aa][Rr][^Ee\<]|{FalseEndTextArea}*[\<][\/]{WhiteSpace}*[Tt][Ee][Xx][Tt][Aa][Rr][Ee][^Aa\<]|{FalseEndTextArea}*[\<][\/]{WhiteSpace}*[Tt][Ee][Xx][Tt][Aa][Rr][Ee][Aa]{WhiteSpace}*[^\<\>])*)

SGMLProcessingStart=("<?")
SGMLProcessingEnd=([\?]+">")
ASPProcessingStart=("<%")
ASPProcessingEnd=([\%]+">")
SGMLProcessingText=(([^\?]|[\?]+[^\>\?])*)
SGMLProcessing=({SGMLProcessingStart}{SGMLProcessingText}{SGMLProcessingEnd})
ASPProcessingText=(([^\%]|[\%]+[^\>\%])*)
ASPProcessing=({ASPProcessingStart}{ASPProcessingText}{ASPProcessingEnd})

Name=({Letter}({Letter}|{Digit}|[\.\-])*)
EndTag=({EndTagStart}{WhiteSpace}*{Name}{WhiteSpace}*{TagEnd})
Comment=("--"([^\-]|([\-][^\-]))*"--")
FalseComment=([^\>])
CommentDeclaration=({DocTagStart}((({WhiteSpace}*){Comment}({WhiteSpace}*))*){TagEnd})

Word=(([^\ \r\n\f\t\<\&])*)

StartTagUnmatched=([^a-zA-Z\>]|[^a-zA-Z\ /\>])
StartEndTagUnmatched=([^a-zA-Z\>\ \r\n])
FinishEndTagUnmatched=([^\>\ \r\n])
TagUnmatched=([^a-zA-Z0-9\ \r\n\-\.\>])
StartEqualUnmatched=([^a-zA-Z0-9\ \r\n\-\.\>\=])
StartValueUnmatched=([^\ \"\'])
UnclosedStringLiteral=(([\"][^\"]*)|([\'][^\']*))
DocTagUnmatched=({WhiteSpace}*(([Dd][^Oo\>])|([d][Oo][^Cc\>])|([Dd][Oo][Cc][^Tt\>])|([Dd][Oo][Cc][Tt][^Yy\>])|([Dd][Oo][Cc][Tt][Yy][^Pp\>])|([Dd][Oo][Cc][Tt][Yy][Pp][^Ee\>])|([^Dd\-\ \r\n\>])|([\-][^\-\>])))
EndTagError=([^\>]*({TagEnd}|{SelfClosingTagEnd})?)
%%

<YYINITIAL> {SGMLProcessing}|{ASPProcessing} {
    nextState = YYINITIAL;
	lastToken = HTMLToken.SCRIPT;
    String text = yytext();
	HTMLToken t = (new HTMLToken(lastToken,text,yyline,yychar,yychar+text.length(),nextState));
	return(t);
}
<YYINITIAL> {TagStart} {
    nextState = START_TAG;
	lastToken = HTMLToken.TAG_START;	
    String text = yytext();
	HTMLToken t = (new HTMLToken(lastToken,text,yyline,yychar,yychar+text.length(),nextState));
	yybegin(nextState);
	return(t);
}
<START_TAG> {NameScript} {
    nextState = SCRIPT_TAG;
	lastToken = HTMLToken.TAG_NAME;	
    String text = yytext();
	HTMLToken t = (new HTMLToken(lastToken,text,yyline,yychar,yychar+text.length(),nextState));
	yybegin(nextState);
	return(t);
}
<START_TAG> {NamePre} {
    nextState = PRE_TAG;
	lastToken = HTMLToken.TAG_NAME;
    String text = yytext();
	HTMLToken t = (new HTMLToken(lastToken,text,yyline,yychar,yychar+text.length(),nextState));
	yybegin(nextState);
	return(t);
}
<START_TAG> {NameTextArea} {
    nextState = TEXTAREA_TAG;
	lastToken = HTMLToken.TAG_NAME;
    String text = yytext();
	HTMLToken t = (new HTMLToken(lastToken,text,yyline,yychar,yychar+text.length(),nextState));
	yybegin(nextState);
	return(t);
}
<START_TAG> {Name} {
    nextState = TAG;
	lastToken = HTMLToken.TAG_NAME;
    String text = yytext();
	HTMLToken t = (new HTMLToken(lastToken,text,yyline,yychar,yychar+text.length(),nextState));
	yybegin(nextState);
	return(t);
}
<TAG, START_EQUAL> {NameToken} {
    nextState = START_EQUAL;
	lastToken = HTMLToken.NAME;
	String text = yytext();
	HTMLToken t = (new HTMLToken(lastToken,text,yyline,yychar,yychar+text.length(),nextState));
	yybegin(nextState);
	return(t);
}
<SCRIPT_TAG, START_SCRIPT_EQUAL> {NameToken} {
    nextState = START_SCRIPT_EQUAL;
	lastToken = HTMLToken.NAME;
	String text = yytext();
	HTMLToken t = (new HTMLToken(lastToken,text,yyline,yychar,yychar+text.length(),nextState));
	yybegin(nextState);
	return(t);
}
<PRE_TAG, START_PRE_EQUAL> {NameToken} {
    nextState = START_PRE_EQUAL;
	lastToken = HTMLToken.NAME;
	String text = yytext();
	HTMLToken t = (new HTMLToken(lastToken,text,yyline,yychar,yychar+text.length(),nextState));
	yybegin(nextState);
	return(t);
}
<TEXTAREA_TAG, START_TEXTAREA_EQUAL> {NameToken} {
    nextState = START_TEXTAREA_EQUAL;
	lastToken = HTMLToken.NAME;
	String text = yytext();
	HTMLToken t = (new HTMLToken(lastToken,text,yyline,yychar,yychar+text.length(),nextState));
	yybegin(nextState);
	return(t);
}
<START_SCRIPT_EQUAL> "=" {
    nextState = START_SCRIPT_VALUE;
	lastToken = HTMLToken.EQUAL;
	String text = yytext();
	HTMLToken t = (new HTMLToken(lastToken,text,yyline,yychar,yychar+text.length(),nextState));
	yybegin(nextState);
	return(t);
}
<START_PRE_EQUAL> "=" {
    nextState = START_PRE_VALUE;
	lastToken = HTMLToken.EQUAL;
	String text = yytext();
	HTMLToken t = (new HTMLToken(lastToken,text,yyline,yychar,yychar+text.length(),nextState));
	yybegin(nextState);
	return(t);
}
<START_TEXTAREA_EQUAL> "=" {
    nextState = START_TEXTAREA_VALUE;
	lastToken = HTMLToken.EQUAL;
	String text = yytext();
	HTMLToken t = (new HTMLToken(lastToken,text,yyline,yychar,yychar+text.length(),nextState));
	yybegin(nextState);
	return(t);
}
<START_EQUAL> "=" {
    nextState = START_VALUE;
	lastToken = HTMLToken.EQUAL;
	String text = yytext();
	HTMLToken t = (new HTMLToken(lastToken,text,yyline,yychar,yychar+text.length(),nextState));
	yybegin(nextState);
	return(t);
}
<START_SCRIPT_VALUE> {Value} {
    nextState = SCRIPT_TAG;
	lastToken = HTMLToken.VALUE;
	String text = yytext();
	HTMLToken t = (new HTMLToken(lastToken,text,yyline,yychar,yychar+text.length(),nextState));
	yybegin(nextState);
	return(t);
} 
<START_SCRIPT_VALUE> {TagEnd} {
    nextState = SCRIPT;
	lastToken = HTMLToken.ERROR_MALFORMED_TAG;
	String text = yytext();
	HTMLToken t = (new HTMLToken(lastToken,text,yyline,yychar,yychar+text.length(),nextState));
	yybegin(nextState);
	return(t);
}
<START_PRE_VALUE> {Value} {
    nextState = PRE_TAG;
	lastToken = HTMLToken.VALUE;
	String text = yytext();
	HTMLToken t = (new HTMLToken(lastToken,text,yyline,yychar,yychar+text.length(),nextState));
	yybegin(nextState);
	return(t);
}
<START_TEXTAREA_VALUE> {Value} {
    nextState = TEXTAREA_TAG;
	lastToken = HTMLToken.VALUE;
	String text = yytext();
	HTMLToken t = (new HTMLToken(lastToken,text,yyline,yychar,yychar+text.length(),nextState));
	yybegin(nextState);
	return(t);
}
<START_VALUE> {Value} {
    nextState = TAG;
	lastToken = HTMLToken.VALUE;
	String text = yytext();
	HTMLToken t = (new HTMLToken(lastToken,text,yyline,yychar,yychar+text.length(),nextState));
	yybegin(nextState);
	return(t);
}

<YYINITIAL> {EndTagStart} {
    nextState = START_END_TAG;
	lastToken = HTMLToken.TAG_START;
	String text = yytext();
	HTMLToken t = (new HTMLToken(lastToken,text,yyline,yychar,yychar+text.length(),nextState));
	yybegin(nextState);
	return(t);
}
<YYINITIAL> {DocTagStart} {
    nextState = START_DOC_TAG;
	lastToken = HTMLToken.TAG_START;
	String text = yytext();
	HTMLToken t = (new HTMLToken(lastToken,text,yyline,yychar,yychar+text.length(),nextState));
	yybegin(nextState);
	return(t);
}
<START_DOC_TAG> {Doctype} {
    nextState = DOCTYPE;
	lastToken = HTMLToken.TAG_NAME;
	String text = yytext();
	HTMLToken t = (new HTMLToken(lastToken,text,yyline,yychar,yychar+text.length(),nextState));
	yybegin(nextState);
	return(t);
}
<DOCTYPE> {DoctypeText} {
	lastToken = HTMLToken.NAME;
	String text = yytext();
	HTMLToken t = (new HTMLToken(lastToken,text,yyline,yychar,yychar+text.length(),nextState));
	return(t);
}
<START_DOC_TAG, COMMENT_DEF> {Comment} {
    nextState = COMMENT_DEF;
	lastToken = HTMLToken.COMMENT;
	String text = yytext();
	HTMLToken t = (new HTMLToken(lastToken,text,yyline,yychar,yychar+text.length(),nextState));
	yybegin(nextState);
	return(t);
}

<START_DOC_TAG, COMMENT_DEF> {FalseComment} {
    nextState = COMMENT_DEF;
	lastToken = HTMLToken.ERROR_MALFORMED_TAG;
	String text = yytext();
	HTMLToken t = (new HTMLToken(lastToken,text,yyline,yychar,yychar+text.length(),nextState));
	yybegin(nextState);
	return(t);
}

<START_TAG, START_END_TAG, START_VALUE, START_SCRIPT_VALUE, START_PRE_VALUE, START_TEXTAREA_VALUE, START_EQUAL, START_SCRIPT_EQUAL, START_PRE_EQUAL, START_TEXTAREA_EQUAL> {TagEnd} {
    nextState = YYINITIAL;
	lastToken = HTMLToken.ERROR_MALFORMED_TAG;
	String text = yytext();
	HTMLToken t = (new HTMLToken(lastToken,text,yyline,yychar,yychar+text.length(),nextState));
	yybegin(nextState);
	return(t);
}
<START_TAG> {StartTagUnmatched}{EndTagError} {
    nextState = YYINITIAL;
	lastToken = HTMLToken.ERROR_MALFORMED_TAG;
	String text = yytext();
	HTMLToken t = (new HTMLToken(lastToken,text,yyline,yychar,yychar+text.length(),nextState));
	yybegin(nextState);
	return(t);
}
<START_DOC_TAG> {DocTagUnmatched}{EndTagError} {
    nextState = YYINITIAL;
	lastToken = HTMLToken.ERROR_MALFORMED_TAG;
	String text = yytext();
	HTMLToken t = (new HTMLToken(lastToken,text,yyline,yychar,yychar+text.length(),nextState));
	yybegin(nextState);
	return(t);
}
<TAG> {TagUnmatched}{EndTagError} {
    nextState = YYINITIAL;
	lastToken = HTMLToken.ERROR_MALFORMED_TAG;
	String text = yytext();
	HTMLToken t = (new HTMLToken(lastToken,text,yyline,yychar,yychar+text.length(),nextState));
	yybegin(nextState);
	return(t);
}
<SCRIPT_TAG> {TagUnmatched}{EndTagError} {
    nextState = SCRIPT;
	lastToken = HTMLToken.ERROR_MALFORMED_TAG;
	
    String text = yytext();
	HTMLToken t = (new HTMLToken(lastToken,text,yyline,yychar,yychar+text.length(),nextState));
	yybegin(nextState);
	return(t);
}
<PRE_TAG> {TagUnmatched}{EndTagError} {
    nextState = PRE;
	lastToken = HTMLToken.ERROR_MALFORMED_TAG;
	
    String text = yytext();
	HTMLToken t = (new HTMLToken(lastToken,text,yyline,yychar,yychar+text.length(),nextState));
	yybegin(nextState);
	return(t);
}
<TEXTAREA_TAG> {TagUnmatched}{EndTagError} {
    nextState = TEXTAREA;
	lastToken = HTMLToken.ERROR_MALFORMED_TAG;
	
    String text = yytext();
	HTMLToken t = (new HTMLToken(lastToken,text,yyline,yychar,yychar+text.length(),nextState));
	yybegin(nextState);
	return(t);
}

<START_EQUAL> {StartEqualUnmatched}{EndTagError} {
    nextState = YYINITIAL;
	lastToken = HTMLToken.ERROR_MALFORMED_TAG;
	String text = yytext();
	HTMLToken t = (new HTMLToken(lastToken,text,yyline,yychar,yychar+text.length(),nextState));
	yybegin(nextState);
	return(t);
}
<START_SCRIPT_EQUAL> {StartEqualUnmatched}{EndTagError} {
    nextState = SCRIPT;
	lastToken = HTMLToken.ERROR_MALFORMED_TAG;
	String text = yytext();
	HTMLToken t = (new HTMLToken(lastToken,text,yyline,yychar,yychar+text.length(),nextState));
	yybegin(nextState);
	return(t);
}
<START_PRE_EQUAL> {StartEqualUnmatched}{EndTagError} {
    nextState = PRE;
	lastToken = HTMLToken.ERROR_MALFORMED_TAG;
	
    String text = yytext();
	HTMLToken t = (new HTMLToken(lastToken,text,yyline,yychar,yychar+text.length(),nextState));
	yybegin(nextState);
	return(t);
}
<START_TEXTAREA_EQUAL> {StartEqualUnmatched}{EndTagError} {
    nextState = TEXTAREA;
	lastToken = HTMLToken.ERROR_MALFORMED_TAG;
	
    String text = yytext();
	HTMLToken t = (new HTMLToken(lastToken,text,yyline,yychar,yychar+text.length(),nextState));
	yybegin(nextState);
	return(t);
}
<START_VALUE> {StartValueUnmatched}{EndTagError} {
    nextState = YYINITIAL;
	lastToken = HTMLToken.ERROR_MALFORMED_TAG;
	String text = yytext();
	HTMLToken t = (new HTMLToken(lastToken,text,yyline,yychar,yychar+text.length(),nextState));
	yybegin(nextState);
	return(t);
}

<START_VALUE> {UnclosedStringLiteral} {
    nextState = YYINITIAL;
	lastToken = HTMLToken.ERROR_MALFORMED_TAG;
	String text = yytext();
	HTMLToken t = (new HTMLToken(lastToken,text,yyline,yychar,yychar+text.length(),nextState));
	yybegin(nextState);
	return(t);
}
<START_SCRIPT_VALUE> {StartValueUnmatched}{EndTagError}|{UnclosedStringLiteral} {
    nextState = SCRIPT;
	lastToken = HTMLToken.ERROR_MALFORMED_TAG;
	
    String text = yytext();
	HTMLToken t = (new HTMLToken(lastToken,text,yyline,yychar,yychar+text.length(),nextState));
	yybegin(nextState);
	return(t);
}
<START_PRE_VALUE> {StartValueUnmatched}{EndTagError}|{UnclosedStringLiteral} {
    nextState = PRE;
	lastToken = HTMLToken.ERROR_MALFORMED_TAG;
	
    String text = yytext();
	HTMLToken t = (new HTMLToken(lastToken,text,yyline,yychar,yychar+text.length(),nextState));
	yybegin(nextState);
	return(t);
}
<START_TEXTAREA_VALUE> {StartValueUnmatched}{EndTagError}|{UnclosedStringLiteral} {
    nextState = TEXTAREA;
	lastToken = HTMLToken.ERROR_MALFORMED_TAG;
	String text = yytext();
	HTMLToken t = (new HTMLToken(lastToken,text,yyline,yychar,yychar+text.length(),nextState));
	yybegin(nextState);
	return(t);
}
<TAG, COMMENT_DEF, DOCTYPE, FINISH_END_TAG> {TagEnd}|{SelfClosingTagEnd} {
    nextState = YYINITIAL;
	lastToken = HTMLToken.TAG_END;
	String text = yytext();
	HTMLToken t = (new HTMLToken(lastToken,text,yyline,yychar,yychar+text.length(),nextState));
	yybegin(nextState);
	return(t);
}
<SCRIPT_TAG> {TagEnd} {
    nextState = SCRIPT;
	lastToken = HTMLToken.TAG_END;
	String text = yytext();
	HTMLToken t = (new HTMLToken(lastToken,text,yyline,yychar,yychar+text.length(),nextState));
	yybegin(nextState);
	return(t);
}
<PRE_TAG> {TagEnd} {
    nextState = PRE;
	lastToken = HTMLToken.TAG_END;
	String text = yytext();
	HTMLToken t = (new HTMLToken(lastToken,text,yyline,yychar,yychar+text.length(),nextState));
	yybegin(nextState);
	return(t);
}
<TEXTAREA_TAG> {TagEnd} {
    nextState = TEXTAREA;
	lastToken = HTMLToken.TAG_END;
	String text = yytext();
	HTMLToken t = (new HTMLToken(lastToken,text,yyline,yychar,yychar+text.length(),nextState));
	yybegin(nextState);
	return(t);
}
<START_END_TAG> {StartEndTagUnmatched}{EndTagError} {
    nextState = YYINITIAL;
	lastToken = HTMLToken.ERROR_MALFORMED_TAG;
	String text = yytext();
	HTMLToken t = (new HTMLToken(lastToken,text,yyline,yychar,yychar+text.length(),nextState));
	yybegin(nextState);
	return(t);
}
<START_END_TAG> {Name} {
    nextState = FINISH_END_TAG;
	lastToken = HTMLToken.END_TAG_NAME;
	String text = yytext();
	HTMLToken t = (new HTMLToken(lastToken,text,yyline,yychar,yychar+text.length(),nextState));
	yybegin(nextState);
	return(t);
}
<FINISH_END_TAG> {FinishEndTagUnmatched}{EndTagError} {
    nextState = YYINITIAL;
	lastToken = HTMLToken.ERROR_MALFORMED_TAG;
	String text = yytext();
	HTMLToken t = (new HTMLToken(lastToken,text,yyline,yychar,yychar+text.length(),nextState));
	yybegin(nextState);
	return(t);
}
<YYINITIAL> {Word} {
    nextState = YYINITIAL;
	lastToken = HTMLToken.WORD;
	String text = yytext();
	HTMLToken t = (new HTMLToken(lastToken,text,yyline,yychar,yychar+text.length(),nextState));
	return(t);
}
<YYINITIAL> {CharacterReference} {
    nextState = YYINITIAL;
	lastToken = HTMLToken.CHAR_REF;
	String text = yytext();
	HTMLToken t = (new HTMLToken(lastToken,text,yyline,yychar,yychar+text.length(),nextState));
	return(t);
}
<YYINITIAL> {FalseCharRef} {
    nextState = YYINITIAL;
	lastToken = HTMLToken.ERROR_MALFORMED_TAG;
	String text = yytext();
	HTMLToken t = (new HTMLToken(lastToken,text,yyline,yychar,yychar+text.length(),nextState));
	return(t);
}
<YYINITIAL, START_END_TAG, SCRIPT_TAG, PRE_TAG, TEXTAREA_TAG, TAG, START_EQUAL, START_SCRIPT_EQUAL, START_PRE_EQUAL, START_TEXTAREA_EQUAL, START_VALUE, START_SCRIPT_VALUE, START_PRE_VALUE, START_TEXTAREA_VALUE, START_DOC_TAG, COMMENT_DEF, START_END_TAG, FINISH_END_TAG> ({WhiteSpace}+) {
	lastToken = HTMLToken.WHITE_SPACE;
	String text = yytext();
	HTMLToken t = (new HTMLToken(lastToken,text,yyline,yychar,yychar+text.length(),nextState));
	return(t);
}
<SCRIPT> {ScriptText} {
	lastToken = HTMLToken.SCRIPT;
	String text = yytext();
	HTMLToken t = (new HTMLToken(lastToken,text,yyline,yychar,yychar+text.length(),nextState));
	return(t);
}
<SCRIPT> {FalseEndScript} {
	lastToken = HTMLToken.SCRIPT;
	String text = yytext();
	HTMLToken t = (new HTMLToken(lastToken,text,yyline,yychar,yychar+text.length(),nextState));
	return(t);
}

<SCRIPT> {EndTag} {
    nextState = YYINITIAL;
	lastToken = HTMLToken.END_TAG_NAME;
	String text = yytext();
	HTMLToken t = (new HTMLToken(lastToken,text,yyline,yychar,yychar+text.length(),nextState));
	yybegin(nextState);
	return(t);
}
<PRE> {PreText} {
	lastToken = HTMLToken.WORD;
	String text = yytext();
	HTMLToken t = (new HTMLToken(lastToken,text,yyline,yychar,yychar+text.length(),nextState));
	return(t);
}
<PRE> {FalseEndPre} {
	lastToken = HTMLToken.WORD;
	String text = yytext();
	HTMLToken t = (new HTMLToken(lastToken,text,yyline,yychar,yychar+text.length(),nextState));
	return(t);
}
<PRE> {EndTag} {
    nextState = YYINITIAL;
	lastToken = HTMLToken.END_TAG_NAME;
	String text = yytext();
	HTMLToken t = (new HTMLToken(lastToken,text,yyline,yychar,yychar+text.length(),nextState));
	yybegin(nextState);
	return(t);
}
<TEXTAREA> {TextAreaText} {
	lastToken = HTMLToken.WORD;
	String text = yytext();
	HTMLToken t = (new HTMLToken(lastToken,text,yyline,yychar,yychar+text.length(),nextState));
	return(t);
}
<TEXTAREA> {FalseEndTextArea} {
	lastToken = HTMLToken.WORD;
	String text = yytext();
	HTMLToken t = (new HTMLToken(lastToken,text,yyline,yychar,yychar+text.length(),nextState));
	return(t);
}
<TEXTAREA> {EndTag} {
    nextState = YYINITIAL;
	lastToken = HTMLToken.END_TAG_NAME;
	String text = yytext();
	HTMLToken t = (new HTMLToken(lastToken,text,yyline,yychar,yychar+text.length(),nextState));
	yybegin(nextState);
	return(t);
}

<YYINITIAL, START_TAG,START_END_TAG,START_DOC_TAG,TAG,START_EQUAL,START_VALUE,SCRIPT_TAG,START_SCRIPT_EQUAL,START_SCRIPT_VALUE,SCRIPT,PRE_TAG,START_PRE_EQUAL,START_PRE_VALUE,PRE,TEXTAREA_TAG,START_TEXTAREA_EQUAL,START_TEXTAREA_VALUE,TEXTAREA,TAG_END,DOCTYPE,COMMENT_DEF,FINISH_END_TAG> {AnyChar} {
    System.err.println("Unmatched input.");
    System.err.println("Last token: " + lastToken + ", text: " + yytext());
    String state = "";    
	String text = yytext();
    switch (nextState){
        case YYINITIAL: state = "YYINITIAL"; break;
        case START_TAG: state = "START_TAG"; break;
        case START_END_TAG: state = "START_END_TAG"; break;
        case START_DOC_TAG: state = "START_DOC_TAG"; break;
        case TAG: state = "TAG"; break;
        case START_EQUAL: state = "START_EQUAL"; break;
        case START_VALUE: state = "START_VALUE"; break;
        case SCRIPT_TAG: state = "SCRIPT_TAG"; break;
        case START_SCRIPT_EQUAL: state = "START_SCRIPT_EQUAL"; break;
        case START_SCRIPT_VALUE: state = "START_SCRIPT_VALUE"; break;
        case SCRIPT: state = "SCRIPT"; break;
        case PRE_TAG: state = "PRE_TAG"; break;
        case START_PRE_EQUAL: state = "START_PRE_EQUAL"; break;
        case START_PRE_VALUE: state = "START_PRE_VALUE"; break;
        case PRE: state = "PRE"; break;
        case TEXTAREA_TAG: state = "TEXTAREA_TAG"; break;
        case START_TEXTAREA_EQUAL: state = "START_TEXTAREA_EQUAL"; break;
        case START_TEXTAREA_VALUE: state = "START_TEXTAREA_VALUE"; break;
        case TEXTAREA: state = "TEXTAREA"; break;
        case TAG_END: state = "TAG_END"; break;
        case DOCTYPE: state = "DOCTYPE"; break;
        case COMMENT_DEF: state = "COMMENT_DEF"; break;
        case FINISH_END_TAG: state = "FINISH_END_TAG"; break;
    }
    System.err.println("State: " + state);
    System.err.println("Text: " + text);
    System.err.println("Line: " + (yyline+1));
    System.err.println("Column: " + (yycolumn+1));
	yy_ScanError(YY_NO_MATCH);
}


