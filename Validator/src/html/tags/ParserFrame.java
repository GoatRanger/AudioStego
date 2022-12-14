/**
 * ParserFrame
 * @author Vincent Mallet  (vmallet@sophia.inria.fr)
 * 
 * $Id: ParserFrame.java,v 1.2 2002/04/08 21:22:41 plehegar Exp $
 *
 * @version $Revision: 1.2 $
 */


package html.tags;

import java.net.*;

// import Validator.*;
import org.w3c.css.css.StyleSheetParser;
import org.w3c.css.util.ApplContext;



/**
 * The class <code>ParserFrame</code> is used to carry some informations
 * through the Html tree being built by the Html Parser
 * @@ [FIXME]
 *
 * @author Vincent Mallet
 */

public class ParserFrame {

  /**
   * The StyleSheet generator: it is used to parse all CSS informations, 
   * and then produce the specific stylesheets (CssStyles) for given 
   * contexts.
   */

  //  StyleSheet styleSheet = new StyleSheet(); 
  StyleSheetParser styleSheetParser = new StyleSheetParser(); 

  // needed by the CSS parser
  ApplContext ac;

  /**
   * The URL of the HTML page currently read/parsed.
   */
  URL url;

  /**
   * The base URL of the HTML page to resolve all relative links.
   */
  URL baseURL;

  /**
   * The current line number in the source file
   */
  int line;

  /**
   * Create a new ParserFrame
   */

  public ParserFrame() { 
  }

}
