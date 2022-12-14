//
// $Id: FakeFile.java,v 1.2 2002/04/08 21:19:15 plehegar Exp $
// From Philippe Le Hegaret (Philippe.Le_Hegaret@sophia.inria.fr)
//
// (c) COPYRIGHT MIT and INRIA, 1997.
// Please first read the full copyright statement in file COPYRIGHT.html
/*
 * $Log: FakeFile.java,v $
 * Revision 1.2  2002/04/08 21:19:15  plehegar
 * New
 *
 */
package org.w3c.css.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.ByteArrayInputStream;

/**
 * @version $Revision: 1.2 $
 */
public class FakeFile {
  
  protected String fileName;

  /**
   * The array buffer into which the components of this object are 
   * stored. The capacity of this object is the length of this array buffer.
   */
  protected byte[] data;

  /**
   * The number of valid components in the vector. 
   */
  protected int    count;
  
  /**
   * Create a new FakeFile.
   *
   * @param   name   the file name.
   */
  public FakeFile(String fileName) {
    this.fileName = fileName;
    data = new byte[255];
  }
  
  /**
   * Returns the name of the file represented by this object.
   *
   * @return  the name of the file represented by this <code>File</code> 
   *          object.
   */
  public String getName() {
    return fileName;
  }
  
  /**
   * Gets an InputStream for this fake file.
   *
   * @return  the input stream of this fake file or <code>null</code> if no
   *          data are available.
   */
  public synchronized InputStream getInputStream() throws IOException {
    if (count > 0) {
      trimToSize();
      return new ByteArrayInputStream(data);
    } else {
      return null;
    }
  }

  /**
   * Get the data size of this fake file
   */
  public synchronized int getSize() {
    return count;
  }

  /**
   * Trims the capacity of this object to be this object's current 
   * size. An application can use this operation to minimize the 
   * storage of a fake file. 
   */
  public final synchronized void trimToSize() {
    int oldCapacity = data.length;
    if (count < oldCapacity) {
      byte oldData[] = data;
      data = new byte[count];
      System.arraycopy(oldData, 0, data, 0, count);
    }
  }
  
  /**
   * Increases the capacity of this object, if necessary, to ensure 
   * that it can hold at least the number of components specified by 
   * the minimum capacity argument.
   *
   * @param   minCapacity   the desired minimum capacity.
   */
  private final synchronized void ensureCapacity(int minCapacity) {
    int oldCapacity = data.length;
    if (minCapacity > oldCapacity) {
      byte oldData[] = data;
      data = new byte[minCapacity];
      System.arraycopy(oldData, 0, data, 0, count);
    }
  }

  /**
   * Writes <code>len</code> bytes from the specified byte array 
   * starting at offset <code>off</code> to this fake file. 
   *
   * @param      b     the data.
   * @param      off   the start offset in the data.
   * @param      len   the number of bytes to write.
   *
   * @exception  IOException  if an I/O error occurs.
   */
  public void write(byte[] data, int start, int len) {
    if (len <= 0)
      return;
    ensureCapacity(count + len);
    for (int i = 0; i < len; i++) {
      this.data[count+i] = data[i+start];
    }
    count += len;
  }

}
