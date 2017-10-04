/*
 * Copyright (C) 2003 USMA This program is free software; you can redistribute it and/or modify it under the terms of the GNU General Public
 * License as published by the Free Software Foundation; either version 2 of the License, or any later version. This program is distributed
 * in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A
 * PARTICULAR PURPOSE. See the GNU General Public License for more details. You should have received a copy of the GNU General Public
 * License along with this program; if not, write to the Free Software Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA 02111-1307
 * USA
 */
package eecs.editor;

import java.io.File;

/**
 * This class provides a generic filter for managing files in a JFileChooser.
 */
public class GenericFileFilter extends javax.swing.filechooser.FileFilter {
  private static final boolean ONE = true;
  private String fileExt;
  private String[] fileExts;
  private boolean type = false;
  private String description;
  private int length;
  private String extension;

  /**
   * This is the constructor - it takes in the following:- <br />
   * extsIn - this is the array of file extensions that you wish to create a file filter for. <br />
   * description - the description displayed in the file chooser.
   */
  public GenericFileFilter(String[] extsIn, String description) {
    if (extsIn.length == 1) { //we only have one file
      type = ONE;
      fileExt = extsIn[0];
    }
    else {
      fileExts = extsIn;
      length = fileExts.length;
    }
    this.description = description;
  }

  /**
   * Accepts or rejects a file, according to the specification. This method is called by the model that handles the FileChooser dialog.
   */
  public boolean accept(File f) {
    if (f.isDirectory()) {
      return true;
    }
    extension = getExtension(f);
    if (extension != null) {
      if (type) {
        return check(fileExt);
      }
      for (int i = 0; i < length; i++) {
        if (check(fileExts[i])) {
          return true;
        }
      }
    }
    return false;
  }

  /**
   * This checks to see if the extension (stored) is the same as the file type stored at construction time. The "in" being the value passed
   * over.
   */
  private boolean check(String in) {
    return extension.equalsIgnoreCase(in);
  }

  /**
   * Gets the description of the filter.
   */
  public String getDescription() {
    return description;
  }

  /**
   * Returns the extension of a file (everything after the last '.').
   */
  private String getExtension(File file) {
    String filename = file.getName();
    int fileLength = filename.length();
    int i = filename.lastIndexOf('.');
    if ((i > 0) && (i < (fileLength - 1))) {
      return filename.substring(i + 1).toLowerCase();
    }
    return null;
  }
}