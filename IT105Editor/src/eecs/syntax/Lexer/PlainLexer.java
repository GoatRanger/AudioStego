/* Copyright (C) 2003  USMA
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * any later version.

 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.

 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 */

/* The following code was generated by JFlex 1.3.5 on 11/14/02 11:44 AM */
/* PlainLexer.java is a generated file.  You probably want to
 * edit PlainLexer.lex to make changes.  Use JFlex to generate it.
 * To generate PlainLexer.java
 * Install <a href="http://jflex.de/">JFlex</a> v1.3.2 or later.
 * Once JFlex is in your classpath run<br>
 * <code>java JFlex.Main PlainLexer.lex</code><br>
 * You will then have a file called PlainLexer.java
 */
/*
 * This file is part of a <a href="http://ostermiller.org/syntax/">syntax
 * highlighting</a> package.
 * Copyright (C) 2002 Stephen Ostermiller
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
 * * * This class is based on the class originally written by Steven Ostermiller,
 * {@link http://ostermiller.org/syntax/}.
 * Modified by Karl A. Gossett, 11 April 2003
 */
package eecs.syntax.Lexer;

import java.io.*;


/**
 * PlainLexer is a plain text lexer.  Created with JFlex.  An example of how it is used:
 *  <CODE>
 *  <PRE>
 *  PlainLexer shredder = new PlainLexer(System.in);
 *  PlainToken t;
 *  while ((t = shredder.getNextToken()) != null){
 *      System.out.println(t);
 *  }
 *  </PRE>
 *  </CODE>
 * @see PlainToken
 */
/**
 * This class is a scanner generated by
 * <a href="http://www.jflex.de/">JFlex</a> 1.3.5
 * on 11/14/02 11:44 AM from the specification file
 * <tt>file:/home/steveo/personal/projects/java/com/Ostermiller/Syntax/Lexer/PlainLexer.lex</tt>
 */
public class PlainLexer implements Lexer {
  /** This character denotes the end of file */
  final public static int YYEOF = -1;

  /** initial size of the lookahead buffer */
  final private static int YY_BUFFERSIZE = 16384;

  /** lexical states */
  final public static int YYINITIAL = 0;

  /**
 * Translates characters to character classes
 */
  final private static char[] yycmap = {
    0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
    0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0
  };

  /**
 * Translates a state to a row index in the transition table
 */
  final private static int[] yy_rowMap = { 0, 2, 2 };

  /**
 * The packed transition table of the DFA (part 0)
 */
  final private static String yy_packed0 = "\1\2\1\3\2\0";

  /**
 * The transition table of the DFA
 */
  final private static int[] yytrans = yy_unpack();

  /* error codes */
  final private static int YY_UNKNOWN_ERROR = 0;
  final private static int YY_ILLEGAL_STATE = 1;
  final private static int YY_NO_MATCH = 2;
  final private static int YY_PUSHBACK_2BIG = 3;

  /* error messages for the codes above */
  final private static String[] YY_ERROR_MSG = {
    "Unkown internal scanner error", "Internal error: unknown state",
    "Error: could not match input", "Error: pushback value was too large"
  };

  /**
 * YY_ATTRIBUTE[aState] contains the attributes of state <code>aState</code>
 */
  private final static byte[] YY_ATTRIBUTE = { 0, 9, 9 };

  /** the input device */
  private java.io.Reader yy_reader;

  /** the current state of the DFA */
  private int yy_state;

  /** the current lexical state */
  private int yy_lexical_state = YYINITIAL;

  /** this buffer contains the current text to be matched and is
    the source of the yytext() string */
  private char[] yy_buffer = new char[YY_BUFFERSIZE];

  /** the textposition at the last accepting state */
  private int yy_markedPos;

  /** the textposition at the last state to be included in yytext */
  private int yy_pushbackPos;

  /** the current text position in the buffer */
  private int yy_currentPos;

  /** startRead marks the beginning of the yytext() string in the buffer */
  private int yy_startRead;

  /** endRead marks the last character in the buffer, that has been read
    from input */
  private int yy_endRead;

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
 * yy_atBOL == true <=> the scanner is currently at the beginning of a line
 */
  private boolean yy_atBOL = true;

  /** yy_atEOF == true <=> the scanner is at the EOF */
  private boolean yy_atEOF;

  /* user code: */
  private int lastToken;
  private int nextState = YYINITIAL;

  /**
 * Creates a new scanner
 * There is also a java.io.InputStream version of this constructor.
 *
 * @param   in  the java.io.Reader to read input from.
 */
  public PlainLexer(java.io.Reader in) {
    this.yy_reader = in;
  }

  /**
 * Creates a new scanner.
 * There is also java.io.Reader version of this constructor.
 *
 * @param   in  the java.io.Inputstream to read input from.
 */
  public PlainLexer(java.io.InputStream in) {
    this(new java.io.InputStreamReader(in));
  }

  /**
 * next Token method that allows you to control if whitespace and comments are
 * returned as tokens.
 */
  public Token getNextToken(boolean returnComments, boolean returnWhiteSpace)
    throws IOException {
    Token t = getNextToken();

    while ((t != null) &&
          ((!returnWhiteSpace && t.isWhiteSpace()) ||
          (!returnComments && t.isComment()))) {
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
      if (args.length > 0) {
        File f = new File(args[0]);

        if (f.exists()) {
          if (f.canRead()) {
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

      PlainLexer shredder = new PlainLexer(in);
      Token t;

      while ((t = shredder.getNextToken()) != null) {
        if (t.getID() != PlainToken.WHITE_SPACE) {
          System.out.println(t);
        }
      }
    } catch (IOException e) {
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
  public void reset(java.io.Reader reader, int yyline, int yychar, int yycolumn)
    throws IOException {
    yyreset(reader);
    this.yyline = yyline;
    this.yychar = yychar;
    this.yycolumn = yycolumn;
  }

  /**
 * Unpacks the split, compressed DFA transition table.
 *
 * @return the unpacked transition table
 */
  private static int[] yy_unpack() {
    int[] trans = new int[4];
    int offset = 0;
    offset = yy_unpack(yy_packed0, offset, trans);

    return trans;
  }

  /**
 * Unpacks the compressed DFA transition table.
 *
 * @param packed   the packed transition table
 * @return         the index of the last entry
 */
  private static int yy_unpack(String packed, int offset, int[] trans) {
    int i = 0; /* index in packed string  */
    int j = offset; /* index in unpacked array */
    int l = packed.length();

    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      value--;

      do
        trans[j++] = value;
      while (--count > 0);
    }

    return j;
  }

  /**
 * Refills the input buffer.
 *
 * @return      <code>false</code>, iff there was new input.
 *
 * @exception   IOException  if any I/O-Error occurs
 */
  private boolean yy_refill() throws java.io.IOException {
    /* first: make room (if you can) */
    if (yy_startRead > 0) {
      System.arraycopy(yy_buffer, yy_startRead, yy_buffer, 0,
        yy_endRead - yy_startRead);

      /* translate stored positions */
      yy_endRead -= yy_startRead;
      yy_currentPos -= yy_startRead;
      yy_markedPos -= yy_startRead;
      yy_pushbackPos -= yy_startRead;
      yy_startRead = 0;
    }

    /* is the buffer big enough? */
    if (yy_currentPos >= yy_buffer.length) {
      /* if not: blow it up */
      char[] newBuffer = new char[yy_currentPos * 2];
      System.arraycopy(yy_buffer, 0, newBuffer, 0, yy_buffer.length);
      yy_buffer = newBuffer;
    }

    /* finally: fill the buffer with new input */
    int numRead = yy_reader.read(yy_buffer, yy_endRead,
        yy_buffer.length - yy_endRead);

    if (numRead < 0) {
      return true;
    } else {
      yy_endRead += numRead;

      return false;
    }
  }

  /**
 * Closes the input stream.
 */
  final public void yyclose() throws java.io.IOException {
    yy_atEOF = true; /* indicate end of file */
    yy_endRead = yy_startRead; /* invalidate buffer    */

    if (yy_reader != null) {
      yy_reader.close();
    }
  }

  /**
 * Closes the current stream, and resets the
 * scanner to read from a new input stream.
 *
 * All internal variables are reset, the old input stream
 * <b>cannot</b> be reused (internal buffer is discarded and lost).
 * Lexical state is set to <tt>YY_INITIAL</tt>.
 *
 * @param reader   the new input stream
 */
  final public void yyreset(java.io.Reader reader) throws java.io.IOException {
    yyclose();
    yy_reader = reader;
    yy_atBOL = true;
    yy_atEOF = false;
    yy_endRead = yy_startRead = 0;
    yy_currentPos = yy_markedPos = yy_pushbackPos = 0;
    yyline = yychar = yycolumn = 0;
    yy_lexical_state = YYINITIAL;
  }

  /**
 * Returns the current lexical state.
 */
  final public int yystate() {
    return yy_lexical_state;
  }

  /**
 * Enters a new lexical state
 *
 * @param newState the new lexical state
 */
  final public void yybegin(int newState) {
    yy_lexical_state = newState;
  }

  /**
 * Returns the text matched by the current regular expression.
 */
  final public String yytext() {
    return new String(yy_buffer, yy_startRead, yy_markedPos - yy_startRead);
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
  final public char yycharat(int pos) {
    return yy_buffer[yy_startRead + pos];
  }

  /**
 * Returns the length of the matched text region.
 */
  final public int yylength() {
    return yy_markedPos - yy_startRead;
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
  private void yy_ScanError(int errorCode) {
    String message;

    try {
      message = YY_ERROR_MSG[errorCode];
    } catch (ArrayIndexOutOfBoundsException e) {
      message = YY_ERROR_MSG[YY_UNKNOWN_ERROR];
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
  private void yypushback(int number) {
    if (number > yylength()) {
      yy_ScanError(YY_PUSHBACK_2BIG);
    }

    yy_markedPos -= number;
  }

  /**
 * Resumes scanning until the next regular expression is matched,
 * the end of input is encountered or an I/O-Error occurs.
 *
 * @return      the next token
 * @exception   IOException  if any I/O-Error occurs
 */
  public Token getNextToken() throws java.io.IOException {
    int yy_input;
    int yy_action;

    // cached fields:
    int yy_currentPos_l;
    int yy_startRead_l;
    int yy_markedPos_l;
    int yy_endRead_l = yy_endRead;
    char[] yy_buffer_l = yy_buffer;
    char[] yycmap_l = yycmap;

    int[] yytrans_l = yytrans;
    int[] yy_rowMap_l = yy_rowMap;
    byte[] yy_attr_l = YY_ATTRIBUTE;

    while (true) {
      yy_markedPos_l = yy_markedPos;

      yychar += (yy_markedPos_l - yy_startRead);

      boolean yy_r = false;

      for (yy_currentPos_l = yy_startRead; yy_currentPos_l < yy_markedPos_l;
            yy_currentPos_l++) {
        switch (yy_buffer_l[yy_currentPos_l]) {
        case '\u000B':
        case '\u000C':
        case '\u0085':
        case '\u2028':
        case '\u2029':
          yyline++;
          yycolumn = 0;
          yy_r = false;

          break;

        case '\r':
          yyline++;
          yycolumn = 0;
          yy_r = true;

          break;

        case '\n':

          if (yy_r) {
            yy_r = false;
          } else {
            yyline++;
            yycolumn = 0;
          }

          break;

        default:
          yy_r = false;
          yycolumn++;
        }
      }

      if (yy_r) {
        // peek one character ahead if it is \n (if we have counted one line too much)
        boolean yy_peek;

        if (yy_markedPos_l < yy_endRead_l) {
          yy_peek = yy_buffer_l[yy_markedPos_l] == '\n';
        } else if (yy_atEOF) {
          yy_peek = false;
        } else {
          boolean eof = yy_refill();
          yy_markedPos_l = yy_markedPos;
          yy_buffer_l = yy_buffer;

          if (eof) {
            yy_peek = false;
          } else {
            yy_peek = yy_buffer_l[yy_markedPos_l] == '\n';
          }
        }

        if (yy_peek) {
          yyline--;
        }
      }

      yy_action = -1;

      yy_startRead_l = yy_currentPos_l = yy_currentPos = yy_startRead = yy_markedPos_l;

      yy_state = yy_lexical_state;

yy_forAction:  {
        while (true) {
          if (yy_currentPos_l < yy_endRead_l) {
            yy_input = yy_buffer_l[yy_currentPos_l++];
          } else if (yy_atEOF) {
            yy_input = YYEOF;

            break yy_forAction;
          } else {
            // store back cached positions
            yy_currentPos = yy_currentPos_l;
            yy_markedPos = yy_markedPos_l;

            boolean eof = yy_refill();

            // get translated positions and possibly new buffer
            yy_currentPos_l = yy_currentPos;
            yy_markedPos_l = yy_markedPos;
            yy_buffer_l = yy_buffer;
            yy_endRead_l = yy_endRead;

            if (eof) {
              yy_input = YYEOF;

              break yy_forAction;
            } else {
              yy_input = yy_buffer_l[yy_currentPos_l++];
            }
          }

          int yy_next = yytrans_l[yy_rowMap_l[yy_state] + yycmap_l[yy_input]];

          if (yy_next == -1) {
            break yy_forAction;
          }

          yy_state = yy_next;

          int yy_attributes = yy_attr_l[yy_state];

          if ((yy_attributes & 1) == 1) {
            yy_action = yy_state;
            yy_markedPos_l = yy_currentPos_l;

            if ((yy_attributes & 8) == 8) {
              break yy_forAction;
            }
          }
        }
      }

      // store back cached position
      yy_markedPos = yy_markedPos_l;

      switch (yy_action) {
      case 2: {
        lastToken = PlainToken.WHITE_SPACE;

        String text = yytext();
        PlainToken t = (new PlainToken(lastToken, text, yyline, yychar,
            yychar + text.length(), nextState));

        return (t);
      }

      case 4:
        break;

      case 1: {
        lastToken = PlainToken.TEXT;

        String text = yytext();
        PlainToken t = (new PlainToken(lastToken, text, yyline, yychar,
            yychar + text.length(), nextState));

        return (t);
      }

      case 5:
        break;

      default:

        if ((yy_input == YYEOF) && (yy_startRead == yy_currentPos)) {
          yy_atEOF = true;

          return null;
        } else {
          yy_ScanError(YY_NO_MATCH);
        }
      }
    }
  }
}
