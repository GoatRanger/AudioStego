/*
 * PropsTokenMarker.java - Java props/DOS INI token marker Copyright (C) 1998, 1999 Slava Pestov You may use and modify this package for any
 * purpose. Redistribution is permitted, in both source and binary form, provided that this notice remains intact in all source
 * distributions of this package.
 */
package eecs.editor.jedit;

import javax.swing.text.Segment;

/**
 * Java properties/DOS INI token marker.
 * 
 * @author Slava Pestov
 * @version $Id: CSSTokenMarker.java,v 1.1 2005/01/07 16:17:16 dj2087 Exp $
 */
public class CSSTokenMarker extends TokenMarker {
  public static final byte VALUE = Token.INTERNAL_FIRST;

  public byte markTokensImpl(byte token, Segment line, int lineIndex) {
    char[] array = line.array;
    int offset = line.offset;
    int lastOffset = offset;
    int length = line.count + offset;
    loop: for (int i = offset; i < length; i++) {
      int i1 = (i + 1);
      switch (token) {
        case Token.NULL:
          switch (array[i]) {
            case '{':
              addToken(i - lastOffset, Token.KEYWORD1);
              token = Token.NULL;
              lastOffset = i;
              break;
            case ':':
              addToken(i - lastOffset, Token.KEYWORD2);
              token = Token.NULL;
              lastOffset = i;
              break;
            case ';':
              addToken(i1 - lastOffset, Token.LITERAL1);
              token = Token.NULL;
              lastOffset = i1;
              break;
            case '/':
              if (length - i > 1) {
                switch (array[i1]) {
                  case '*':
                    addToken(i - lastOffset, token);
                    lastOffset = i;
                    token = Token.COMMENT1;
                    break;
                }
              }
              break;
          }
          break;
        case Token.COMMENT1:
          if (array[i] == '*' && length - i > 1) {
            if (array[i1] == '/') {
              i++;
              addToken((i + 1) - lastOffset, token);
              token = Token.NULL;
              lastOffset = i + 1;
            }
          }
          break;
        case Token.KEYWORD1:
          if (array[i] == '}') {
            addToken(i1 - lastOffset, token);
            token = Token.NULL;
            lastOffset = i1;
          }
          break;
        case VALUE:
          break;
        default:
          throw new InternalError("Invalid state: " + token);
      }
    }
    switch (token) {
      case Token.LITERAL1:
      case Token.LITERAL2:
        addToken(length - lastOffset, Token.INVALID);
        token = Token.NULL;
        break;
      case Token.KEYWORD2:
        addToken(length - lastOffset, token);
        token = Token.NULL;
      default:
        addToken(length - lastOffset, token);
        break;
    }
    return token;
  }
}