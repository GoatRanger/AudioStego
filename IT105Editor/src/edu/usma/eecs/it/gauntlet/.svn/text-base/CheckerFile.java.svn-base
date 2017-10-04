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

package edu.usma.eecs.it.gauntlet;

import java.io.*;

import java.util.*;
import java.util.logging.Logger;


/**
 * Represents the student's file.
 */
public class CheckerFile {
  private Logger logger = Logger.getLogger("eecs.editor");
  private File file;
  private String name;
  private String[] compilerMessages;
  private String[] lines;

  public CheckerFile(File file) {
    this.file = file;
    name = file.getName();
  }

  public String[] getCompilerMessages() throws IOException {
    if (compilerMessages == null) {
      updateClassPath();

      PrintStream origErr = System.err;
      PipedInputStream pis = new PipedInputStream();
      BufferedReader d = new BufferedReader(new InputStreamReader(pis), 32768);
      ReaderThread reader = new ReaderThread(d);

//      try {
//        System.setErr(new PrintStream(new PipedOutputStream(pis)));
//      } catch (Exception e) {
//        // Sigh.  Couldn't open the file.
//        logger.log(Level.WARNING, "Redirect:  Unable to open error file!", e);
//
//        return null;
//      }
      reader.setMessages(new ArrayList());

      // Redirects standard error, so must run it first
      reader.start();

      // Wait to ensure the reader starts before continuing
      while (!reader.isRunning()) {
        try {
          Thread.sleep(50);
        } catch (InterruptedException ie) {
        }
      }

      String fileName = file.getAbsolutePath();
      int compileReturnCode = com.sun.tools.javac.Main.compile(new String[] {
            fileName
          }, new PrintWriter(new PipedOutputStream(pis),true));

      //reader.start();
      // Return code will be 1 if errors, 2 if can't run, 0 if ok
      if (compileReturnCode != 0) {
//      	ArrayList msg = new ArrayList();
//      	String line;
//      	while (d.ready() && (line = d.readLine()) != null) {
//      		msg.add(line);
//      	}
//      	compilerMessages = (String[])msg.toArray(new String[msg.size()]);
      	//reader.start();
        while (reader.isRunning()) {
          try {
            Thread.sleep(100);
          } catch (InterruptedException e) {
          }
        }
      }
      else {
      	reader.end();
      }

      try {
        d.close();
      } catch (IOException e) {
      }

      System.setErr(origErr);
      compilerMessages = reader.getMessages();
    }
    final String[] copy = new String[compilerMessages.length];
    System.arraycopy(compilerMessages,0,copy,0,compilerMessages.length);
    return copy;
  }

  /** Ensures the current work directory is on the classpath. */
  private void updateClassPath() {
    String workDir = java.lang.System.getProperty("user.dir");
    int lastSlash = file.getAbsolutePath().lastIndexOf("\\");
    String workingDir = ".";

    if (lastSlash >= 0) {
      workingDir = file.getAbsolutePath().substring(0, lastSlash);
    }

    if (workDir.indexOf(workingDir) < 0) {
      System.setProperty("user.dir", workingDir + ";" + workDir);
    }

    String classPath = System.getProperty("java.class.path");

    if ((classPath.indexOf(workingDir) < 0) || (classPath.indexOf(".;") < 0)) {
      System.setProperty("java.class.path",
        ".;" + workingDir + ";" + classPath);
    }
  }

  public String[] getLines() throws IOException {
    if (lines == null) {
      lines = Util.removeComments(file);
    }
    final String[] copy = new String[lines.length];
    System.arraycopy(lines,0,copy,0,lines.length);
    return copy;
  }

  public String getName() {
    return name;
  }

  private class ReaderThread extends Thread {
    List messageList;
    private BufferedReader reader;
    private boolean isRunning = false;
    private boolean shouldRun = false;

    public ReaderThread(BufferedReader reader) {
      super("Stream_reader");
      this.reader = reader;

      //            lines = new ArrayList();
    }

    public final void setMessages(final List messages) {
      messageList = new ArrayList(messages);
    }

    public final String[] getMessages() {
      if (messageList != null) {
        return (String[]) messageList.toArray(new String[messageList.size()]);
      } else {
        return null;
      }
    }

    public final boolean isRunning() {
      return isRunning;
    }
    
    public final void end() {
    	shouldRun = false;
    }
    
//	public void run() {
//	  String line;
//	  isRunning = true;
//
//	  // Assumes that the stream will have SOMETHING on it, so wait
//	  // until it does
//	  
//	  try {
//       System.out.println("Ready to read");
//		// Now we know standard err is receiving input, start reading
//		while ((line = reader.readLine()) != null) {
//			System.out.println(line);
//		  	lines.add(line);
//		}
//		System.out.println("Done Reading");
//	  } catch (IOException e) {
//		//		logger.log(Level.WARNING,"Problem waiting for System.err.",e);
//		lines.add("System error.  Couldn't compile your program; try again.");
//	  }
//
//	  isRunning = false;
//	}
    public void run() {
      String line;
      isRunning = true;

      // Assumes that the stream will have SOMETHING on it, so wait
      // until it does
      try {
        while (!reader.ready()) {
          try {
            Thread.sleep(80);
          } catch (InterruptedException e) {
          }
        }

        // Now we know standard err is receiving input, start reading
        while (reader.ready() && ((line = reader.readLine()) != null)) {
          messageList.add(line);
        }
      } catch (IOException e) {
        //		logger.log(Level.WARNING,"Problem waiting for System.err.",e);
        messageList.add("System error.  Couldn't compile your program; try again.");
      }

      isRunning = false;
    }
  }
}
