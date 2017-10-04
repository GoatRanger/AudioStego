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
 * Created on Aug 27, 2003
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package eecs.editor.language;

import java.io.File;


/**
 * DOCUMENT ME!
 *
 * @author DK8685 To change the template for this generated type comment go to
 *         Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and
 *         Comments
 */
class CSSKit extends DefaultLanguageKit {
  /** DOCUMENT ME! */
  StringBuffer cssTemplate = new StringBuffer("/* Style Sheet */\n\n");

  /** DOCUMENT ME! */
  private String compilerMessages = null;
  private int compilerPercentComplete = 0;

  /**
   * Creates a new CSSKit object.
   */
  CSSKit() {
    super();
    setTemplate(cssTemplate);
    language = LanguageKit.CSS;
    description = "Cascading style sheet";
    fileSuffix = ".css";
    compileResult = LanguageKit.COMPILE_INCOMPLETE;
  }

  /* (non-Javadoc)
   * @see eecs.editor.language.DefaultLanguageKit#getCompileResult()
   */
  public int getCompileResult() {
    return compileResult;
  }

  /**
   * Returns the last result, or null if never compiled.
   *
   * @see eecs.editor.language.DefaultLanguageKit#getCompilerMessages()
   */
  public String getCompilerMessages() {
    return compilerMessages;
  }

  /**
   * Returns the percentage of error checking that is complete.  Since the
   * current checker does not report partial status, always returns 100.
   *
   * @see eecs.editor.language.DefaultLanguageKit#getCompilerPercentComplete()
   */
  public int getCompilerPercentComplete() {
    return compilerPercentComplete;
  }

  /* Checks the CSS file for errors.
   * @see eecs.editor.language.DefaultLanguageKit#compile(java.io.File)
   */
  public void compile(File file) {
  	compilerPercentComplete = 0;
    compileResult = LanguageKit.COMPILE_INCOMPLETE;

    eecs.editor.util.CSSChecker checker = new eecs.editor.util.CSSChecker();
    compilerMessages = checker.check(file);
    compileResult = (checker.getValid()) ? LanguageKit.COMPILE_SUCCESS
                                         : LanguageKit.COMPILE_FAILURE;
    compilerPercentComplete = 100;
  }

  /**
   * DOCUMENT ME!
   *
   * @return DOCUMENT ME!
   */
  public boolean hasCompiler() {
    return true;
  }
}
