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

/*
 * Created on Aug 25, 2003
 */
package eecs.editor.language;

import eecs.util.ProcessWrapper;

import java.io.File;

import jedit.JEditTextArea;


/**
 * An interface to manage how languages are handled in the editor.  By implementing this
 * interface, languages can be registered in the editor and get support from 
 * compiling, formatting, and running/viewing.
 *
 * @author Karl A. Gossett
 */
public interface LanguageKit {
  /** DOCUMENT ME! */
  public static final int NOT_SUPPORTED = -1;

  /** DOCUMENT ME! */
  public static final int REFORMAT_SUCCESS = 0;

  /** DOCUMENT ME! */
  public static final int REFORMAT_WARNINGS = 1;

  /** DOCUMENT ME! */
  public static final int REFORMAT_ERRORS = 2;

  /** DOCUMENT ME! */
  public static final int COMPILE_SUCCESS = 0;

  /** DOCUMENT ME! */
  public static final int COMPILE_FAILURE = 1;
  public static final int COMPILE_INCOMPLETE = Integer.MAX_VALUE;

  /** DOCUMENT ME! */
  public static final String JAVA = "text/java";

  /** DOCUMENT ME! */
  public static final String HTML = "text/html";

  /** DOCUMENT ME! */
  public static final String CSS = "text/css";

  /** DOCUMENT ME! */
  public static final String TEXT = "text/plain";
  
  public static final String HUDSON = "text/hudson";

  /**
   * DOCUMENT ME!
   *
   * @return DOCUMENT ME!
   */
  public abstract int getCompileResult();

  /**
   * DOCUMENT ME!
   *
   * @return DOCUMENT ME!
   */
  public abstract StringBuffer getFormatMessages();

  /**
   * DOCUMENT ME!
   *
   * @return DOCUMENT ME!
   */
  public abstract String getLanguage();

  /**
   * DOCUMENT ME!
   *
   * @return DOCUMENT ME!
   */
  public String getDefaultSuffix();

  /**
   * DOCUMENT ME!
   *
   * @return DOCUMENT ME!
   */
  public String getDescription();

  /**
   * DOCUMENT ME!
   *
   * @return DOCUMENT ME!
   */
  public abstract boolean isRunnable();

  /**
   * DOCUMENT ME!
   *
   * @return DOCUMENT ME!
   */
  public abstract String getTemplate();

  /**
   * DOCUMENT ME!
   *
   * @param file DOCUMENT ME!
   *
   * @return DOCUMENT ME!
   */
  public abstract void compile(File file);

  /**
   * DOCUMENT ME!
   *
   * @return DOCUMENT ME!
   */
  public abstract boolean hasCompiler();

  /**
   * DOCUMENT ME!
   *
   * @param textpane DOCUMENT ME!
   */
  public abstract void reformat(JEditTextArea textpane);
  
  /**
   * DOCUMENT ME!
   *
   * @param window DOCUMENT ME!
   */
  public void setFormatPrefs(java.awt.Window window);

  /**
   * DOCUMENT ME!
   *
   * @return DOCUMENT ME!
   */
  public abstract int getFormatResult();

  /**
   * DOCUMENT ME!
   *
   * @return DOCUMENT ME!
   */
  public boolean hasFormatPreferences();

  /**
   * DOCUMENT ME!
   *
   * @return DOCUMENT ME!
   */
  public abstract boolean hasFormatter();

  /**
   * DOCUMENT ME!
   *
   * @param file DOCUMENT ME!
   *
   * @return DOCUMENT ME!
   */
  public abstract ProcessWrapper run(File file);

  public abstract void stopRun();
  /**
   * DOCUMENT ME!
   *
   * @param file DOCUMENT ME!
   */
  public abstract void submitAction(File file);

  /**
   * DOCUMENT ME!
   *
   * @return DOCUMENT ME!
   */
  public String getCompilerMessages();

  /* TODO: Should probably convert the whole compilation/error checking process, and have
   * a listener interface to notify of status/messages, etc.
   */

  /** Gets the status of the compiler as a percentage complete (0-100, integers).
   *  If there is nothing to compile, or it is finished,
   *  this will return 100 (representing 100% complete).
   *
   *  @return The percent of checking complete, as an int 0-100.
   */
  public int getCompilerPercentComplete();

  /**
   * Returns any output or messages produced when the program is run. Should
   * return a <code>null</code> if the language does not support execution,
   * or the program has not been executed. Expected behavior is that if a
   * program was executed with the {@link #run} command then this method will
   * not return until results are available.
   *
   * @return a String containing the results, or <code>null</code> if  this
   *         language doesn't support execution. Should return an empty
   *         String if there is no output (as opposed to <code>null</code>).
   */
  public String getRunResults();
}
