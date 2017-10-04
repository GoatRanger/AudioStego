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
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package eecs.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;

import java.net.MalformedURLException;
import java.net.URL;

import java.nio.channels.FileChannel;


/**
 * DOCUMENT ME!
 *
 * @author Karl A. Gossett
 */
public class FileManager {
  
  public static boolean copy(String source, String dest) {
    boolean success = false;
    FileWriter newFile = null;
    InputStream fileIn = null;
    BufferedInputStream fileBufIn = null;
	  try {
		  URL fileUrl = new URL(source);
	      newFile = new FileWriter(dest);
		  fileIn = fileUrl.openStream();
	      fileBufIn = new BufferedInputStream(fileIn);
	      // Repeat until end of file
	      for (; ; ) {
	        int data = fileBufIn.read();
	        // Check for EOF
	        if (data == -1) {
	          break;
	        } else {
	          newFile.write(data);
	        }
	      }
	      success = true;
	  } catch (MalformedURLException mue) {
	    mue.printStackTrace();
	  } catch (IOException ioe) {
	    ioe.printStackTrace();
	  } finally {
	    if (fileIn != null) {
	      try {
	        fileIn.close();
	        fileBufIn.close();
	      } catch (IOException in) {
	        in.printStackTrace();
	      }
	    }
	    if (newFile!=null) {
	      try {
	        newFile.flush();
	        newFile.close();
		  } catch (IOException ie) {
		    ie.printStackTrace();
		  }
	    }
	  }
	  return success;
  }
  
  /**
   * DOCUMENT ME!
   *
   * @param source DOCUMENT ME!
   * @param dest DOCUMENT ME!
   *
   * @return DOCUMENT ME!
   */
  public static boolean copyNIO(String source, String dest) {
    // get channels
    try {
      File src = new File(source);

      if (!src.exists()) {
        System.err.println("File " + source + " couldn't be loaded!");

        return false;
      }
      File destFile = new File(dest);
      
      FileInputStream fis = new FileInputStream(src);
      FileOutputStream fos = new FileOutputStream(dest);
      FileChannel fcin = fis.getChannel();
      FileChannel fcout = fos.getChannel();

      // do the file copy
      fcin.transferTo(0, fcin.size(), fcout);

      // finish up
      fcin.close();
      fcout.close();
      fis.close();
      fos.close();

      return true;
    } catch (FileNotFoundException fe) {
      fe.printStackTrace();

      return false;
    } catch (IOException ioe) {
      ioe.printStackTrace();

      return false;
    }
  }

  /**
   * Copies a file from the the network to the local machine classpath.
   * Currently hardcoded to copy the file from
   * <code>http://www-internal.eecs.usma.edu/courses/it105/Resources/IT105%20Editor/</code>
   *
   * @param remotePath DOCUMENT ME!
   * @param fileName
   * @param overwrite DOCUMENT ME!
   *
   * @return <code>true</code> if the file was successfully copied.
   */
  public static boolean copyFileToJavaExt(String remotePath, String fileName,
    boolean overwrite) {
    String path = System.getProperty("java.ext.dirs") + "\\";

    try {
      URL url = new URL(remotePath + fileName);

      InputStream is = url.openStream();
      BufferedInputStream bin = new BufferedInputStream(is);
      File f = new File(path + fileName);

      if ((f.exists() && !overwrite)) {
        return false;
      }

      BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(
            f));
      int b = -1;

      while ((b = bin.read()) != -1) {
        bos.write(b);
      }

      bin.close();
      is.close();
      bos.flush();
      bos.close();

      return true;
    } catch (Exception e) {
      return false;
    }
  }

  public static boolean copyFileToSubmit(String remotePath, String localName) {
    try {
      File inFile = new File(localName);
      URL url = inFile.toURL();

      //URL url = new URL(localName);
      InputStream is = url.openStream();
      BufferedInputStream bin = new BufferedInputStream(is);
      File f = new File(remotePath);

      BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(
            f));
      int b = -1;

      while ((b = bin.read()) != -1) {
        bos.write(b);
      }

      bin.close();
      is.close();
      bos.flush();
      bos.close();

      return true;
    } catch (Exception e) {
      e.printStackTrace();

      return false;
    }
  }
  
  public static boolean copyFile(String source, String dest) {
    File in = new File(source);
    File out = new File(dest);
    try {
		  FileInputStream fis  = new FileInputStream(in);
		  FileOutputStream fos = new FileOutputStream(out);
		  byte[] buf = new byte[1024];
		  int i = 0;
		  while((i=fis.read(buf))!=-1) {
		    fos.write(buf, 0, i);
		    }
		  fis.close();
		  fos.close();
		  return true;
    } catch (FileNotFoundException fnfe) {
      fnfe.printStackTrace();
      return false;
    } catch (IOException ioe) {
      ioe.printStackTrace();
      return false;
    }
  }


  /**
   * Deletes files with a specified extension in a directory.
   *
   * @param directory The directory where the files are located.
   * @param extension The extension of the files to delete.
   */
  public boolean deleteFiles(String directory, String extension) {
    ExtensionFilter filter = new ExtensionFilter(extension);
    File dir = new File(directory);

    String[] list = dir.list(filter);
    File file;

    if (list.length == 0) {
      return true;
    }

    boolean deletedAll = true;

    for (int i = 0; i < list.length; i++) {
      file = new File(directory + list[i]);

      if (!file.delete()) {
        deletedAll = false;
      }
    }

    return deletedAll;
  }

  public static boolean deleteAll(String directory) {
    boolean deleted = true;
    File dir = new File(directory);
    File[] list = dir.listFiles();

    for (int i = 0; i < list.length; i++) {
      if (list[i].isDirectory()) {
        deleteAll(list[i].getAbsolutePath());
      }

      if (!list[i].delete()) {
        deleted = false;
      }
    }

    return deleted;
  }

  /**
   * DOCUMENT ME!
   *
   * @param d DOCUMENT ME!
   * @param prefix DOCUMENT ME!
   */
  public boolean deleteFilesStartingWith(String d, String prefix) {
    StartsWithFilter filter = new StartsWithFilter(prefix);
    File dir = new File(d);

    String[] list = dir.list(filter);
    File file;

    if (list.length == 0) {
      return true;
    }

    boolean deletedAll = true;

    for (int i = 0; i < list.length; i++) {
      file = new File(d + list[i]);

      if (!file.delete()) {
        deletedAll = false;
      }
    }

    return deletedAll;
  }

  class ExtensionFilter implements FilenameFilter {
    private String extension;

    public ExtensionFilter(String extension) {
      this.extension = extension;
    }

    public boolean accept(File dir, String name) {
      return (name.endsWith(extension));
    }
  }

  class StartsWithFilter implements FilenameFilter {
    private String extension;

    public StartsWithFilter(String extension) {
      this.extension = extension;
    }

    public boolean accept(File dir, String name) {
      return (name.startsWith(extension));
    }
  }
}
