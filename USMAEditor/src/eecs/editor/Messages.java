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
 * A manager to access the properties resource bundle.
 * 
 * @author Karl A. Gossett
 */
public class Messages {
  private static final String BUNDLE_NAME = "eecs.editor.editor";//$NON-NLS-1$
  private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle
      .getBundle(BUNDLE_NAME);

  /**
   * 
   */
  private Messages() {
    // TODO Auto-generated constructor stub
  }

  /**
   * Gets a String from the resource bundle.
   * @param key  The key to look up.
   * @return The contents of the key.
   */
  public static String getString(String key) {
    try {
      return RESOURCE_BUNDLE.getString(key);
    } catch (MissingResourceException e) {
      return '!' + key + '!';
    }
  }
}