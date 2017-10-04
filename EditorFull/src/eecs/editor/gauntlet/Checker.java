/*
 * Copyright (C) 2003 USMA This program is free software; you can redistribute it and/or modify it under the terms of the GNU General Public
 * License as published by the Free Software Foundation; either version 2 of the License, or any later version. This program is distributed
 * in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A
 * PARTICULAR PURPOSE. See the GNU General Public License for more details. You should have received a copy of the GNU General Public
 * License along with this program; if not, write to the Free Software Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA 02111-1307
 * USA
 */
package eecs.editor.gauntlet;

/**
 * Provides the logic for checking the code for an error. Ideally, each type of error would have its own concrete Checker class definition.
 * The actual checking of the code would be provided in the execute method.
 */
public abstract class Checker {
  private String description = "Checker";
  private String errorMessage = "Error";

  /**
   * Returns an error if the text contains an error; returns null otherwise.
   * 
   * @param file
   *          the java file to be inspected.
   * @return an Error instance if the text contains an error; null otherwise
   * @throws CheckerException
   */
  public abstract CheckerError execute(CheckerFile file) throws CheckerException;

  /**
   * Returns the description of this Checker, which explains what error it checks for.
   * 
   * @return description of the error to check for
   */
  public String getDescription() {
    return description;
  }

  /**
   * Provides this checker with a description of what error it checks for. The description may be used to explain to the user what the
   * checker checks for.
   * 
   * @param desc
   *          Describes what this checker does
   */
  public void setDescription(String desc) {
    this.description = desc;
  }

  /**
   * Gets the error message that is associated with this checker.
   * 
   * @return a message that will eecs.editor.help the student debug their code.
   */
  public String getErrorMessage() {
    return errorMessage;
  }

  /**
   * Each checker has an associated error message, which will be used to eecs.editor.help the student debug their code.
   * 
   * @param errorMessage
   *          the error message
   */
  public void setErrorMessage(String errorMessage) {
    this.errorMessage = errorMessage;
  }
}