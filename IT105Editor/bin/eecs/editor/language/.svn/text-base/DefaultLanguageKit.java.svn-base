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
 * Created on Aug 21, 2003
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package eecs.editor.language;

import eecs.util.*;

import java.io.File;

import jedit.JEditTextArea;


/**
 * DOCUMENT ME!
 *
 * @author DK8685 To change the template for this generated type comment go to
 *         Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and
 *         Comments
 */
class DefaultLanguageKit implements LanguageKit {
  private StringBuffer template = new StringBuffer();
  protected StringBuffer formatMessages = new StringBuffer();
  protected int formatResult = NOT_SUPPORTED;
  protected int compileResult = NOT_SUPPORTED;
  protected String language = LanguageKit.TEXT;
  protected String description = "unknown type";
  protected String fileSuffix = ".txt";

  /** Reports if this language can be executed.  The default
   * language is not runnable; other languages must override
   * this method if they can be executed.
   * @see eecs.editor.language.LanguageKit#isRunnable()
   */
  public boolean isRunnable() {
    return false;
  }

  /**
   * DOCUMENT ME!
   *
   * @return DOCUMENT ME!
   */
  public final String getTemplate() {
    return template.toString();
  }

  public final String getDescription() {
    return description;
  }

  public final String getDefaultSuffix() {
    return fileSuffix;
  }

  public final String getLanguage() {
    return language;
  }

  protected final void setTemplate(StringBuffer t) {
    this.template = t;
  }

  /**
   * DOCUMENT ME!
   *
   * @param file DOCUMENT ME!
   */
  public void reformat(JEditTextArea textpane) {
  }

  public boolean hasFormatter() {
    return false;
  }

  public boolean hasFormatPreferences() {
    return false;
  }

  public void setFormatPrefs(java.awt.Window window) {
  }

  public final int getFormatResult() {
    return formatResult;
  }

  public final StringBuffer getFormatMessages() {
    return formatMessages;
  }

  /**
   * DOCUMENT ME!
   *
   * @param file DOCUMENT ME!
   */
  public void runAction(File file) {
  }

  /**
   * DOCUMENT ME!
   *
   * @param file DOCUMENT ME!
   */
  public void submitAction(File file) {
  }

  public boolean hasCompiler() {
    return false;
  }

  public int getCompileResult() {
    return compileResult;
  }

  public String getCompilerMessages() {
    return "<html><head></head><body><h3>This type of file can't be compiled/checked for errors.</h3>" +
    		"<p>If you expected to be able to check for errors, check your file type/extension, and" +
    		"make sure it is correct.</p></body></html>";
  }

  public void compile(File file) {
  }

  /** Returns the percent of error checking completed.  Default is to always reply with 100.
   * @see eecs.editor.language.LanguageKit#getCompilerPercentComplete()
   */
  public int getCompilerPercentComplete() {
    return 100;
  }

  public ProcessWrapper run(File file) {
    return null;
  }

  public String getRunResults() {
    return null;
  }
}
