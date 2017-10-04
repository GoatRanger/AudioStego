/*
 * Created on May 14, 2004 for USMA EECS Editor; imported for this project 21 Jun 08
 */
package edu.chem.util;

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