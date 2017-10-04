/*
 * Created on May 14, 2004
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package eecs.editor;

import java.util.MissingResourceException;
import java.util.ResourceBundle;


/**
 * @author dk8685
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public class Messages {
  private static final String BUNDLE_NAME = "eecs.editor.editor";//$NON-NLS-1$
  private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle
      .getBundle(BUNDLE_NAME);

  /**
   * Hide the default constructor.
   */
  private Messages() {
  }

  /**
   * @param key
   * @return
   */
  public static String getString(String key) {
    try {
      return RESOURCE_BUNDLE.getString(key);
    } catch (MissingResourceException e) {
      return '!' + key + '!';
    }
  }
}