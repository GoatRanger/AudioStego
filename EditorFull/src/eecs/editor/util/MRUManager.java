/*
 * Copyright (C) 2003 USMA This program is free software; you can redistribute it and/or modify it under the terms of the GNU General Public
 * License as published by the Free Software Foundation; either version 2 of the License, or any later version. This program is distributed
 * in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A
 * PARTICULAR PURPOSE. See the GNU General Public License for more details. You should have received a copy of the GNU General Public
 * License along with this program; if not, write to the Free Software Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA 02111-1307
 * USA
 */
/*
 * Created on Nov 7, 2003 To change the template for this generated file go to Window - Preferences - Java - Code Generation - Code and
 * Comments
 */
package eecs.editor.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author DK8685 To change the template for this generated type comment go to Window - Preferences - Java - Code Generation - Code and
 *         Comments
 */
public class MRUManager {
  List files;
  int capacity;

  /** Creates a new MRUManager that can hold 5 files. */
  public MRUManager() {
    this(5);
  }

  public MRUManager(int size) {
    files = new ArrayList(size);
    capacity = size;
  }

  /**
   * Adds a new file to the MRUManager. If the addition would exceed the capacity of the list, the last item is lost.
   * 
   * @param filename
   *          The full path to the file to add
   */
  public void add(String filename) {
    if (files.contains(filename)) {
      files.remove(filename);
    }
    if (files.size() >= capacity) {
      files.remove(capacity - 1);
    }
    files.add(0, filename);
  }

  public String[] getFiles() {
    String[] list = new String[files.size()];
    Iterator iter = files.iterator();
    int idx = 0;
    while (iter.hasNext()) {
      list[idx++] = (String) iter.next();
    }
    return list;
  }

  public void clear() {
    files = new ArrayList(capacity);
  }

  public int size() {
    return files.size();
  }
}