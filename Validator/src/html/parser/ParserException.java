/*
 * ParserException
 *
 * $Id: ParserException.java,v 1.2 2002/04/08 21:22:41 plehegar Exp $
 *
 * @author Vincent Mallet (vmallet@sophia.inria.fr)
 *
 */


package html.parser;

/**
 * Signals than an error (probably fatal) occured in the parser.
 * @author Vincent Mallet  (Vincent.Mallet@sophia.inria.fr)
 * @version $Revision: 1.2 $
 */
public class ParserException extends Exception {
  
  /**
   * Create a new ParserException
   */
  public ParserException() {
    super();
  }
  
  /**
   * Create a new ParserException
   * @param message the error message
   */
  public ParserException(String message) {
    super(message);
  }
}
