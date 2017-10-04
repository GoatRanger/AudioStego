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

package eecs.editor.util;

import java.io.*;

import java.util.logging.*;


/**
 * DOCUMENT ME!
 *
 * @author $author$
 * @version $Revision: 1.1 $
 */
public class ProcessWrapper {
  /** DOCUMENT ME! */
  private Logger logger = Logger.getLogger("eecs.editor");

  /** DOCUMENT ME! */
  private PrintWriter in;

  /** DOCUMENT ME! */
  private Process process;

  /** DOCUMENT ME! */
  private ReaderThread errThread;

  /** DOCUMENT ME! */
  private ReaderThread outThread;

  /** DOCUMENT ME! */
  private boolean stopRequested = false;

  /** DOCUMENT ME! */
  private int streamCount = 2;

  // hide default constructor
  public ProcessWrapper(String command, File workingDir) {
    //    FileHandler logHandler = null;
    String homePath = System.getProperty("user.home", "") + "/.it105editor/";

    //    FileManager fm = new FileManager();
    // This log used to be called process_log.txt
    //    fm.deleteFilesStartingWith(homePath, "process_log.txt");
    //    fm.deleteFilesStartingWith(homePath, "ProcessWrapper.log");
    try {
      String workDir = System.getProperty("user.dir");

      if (workDir.indexOf(workingDir.getName()) < 0) {
        System.setProperty("user.dir", workingDir + ";" + workDir);
      }

      String classPath = System.getProperty("java.class.path");

      if ((classPath.indexOf(workingDir.getName()) < 0) ||
            (classPath.indexOf(".;") < 0)) {
        System.setProperty("java.class.path",
          ".;" + workingDir.getName() + ";" + classPath);
      }

      if (logger.isLoggable(Level.CONFIG)) {
        logger.info("Running--Classpath: " + classPath);
        logger.info("Running--Command: " + command);
        logger.info("Running--WorkingDir: " + workingDir);
      }

      process = Runtime.getRuntime().exec(command, null, workingDir);

      //System.err.println("Executed " + command);
    } catch (Exception e) {
      if (logger.isLoggable(Level.WARNING)) {
        logger.log(Level.WARNING,
          "Command \"" + command + "\" failed to execute.", e);
      }

      process = null;
    }

    if (process != null) {
      streamCount = 2;
      in = new PrintWriter(process.getOutputStream());
      new ReaderThread(process.getInputStream()).start();

      new ReaderThread(process.getErrorStream()).start();
    } else {
      logger.log(Level.WARNING,
        "Trying to listen to process, but it is already null!");
      onStop();
    }
  }

  /**
   * Creates a new ProcessWrapper object.
   */
  private ProcessWrapper() {
  }

  /**
   * DOCUMENT ME!
   *
   * @return DOCUMENT ME!
   */
  public boolean isRunning() {
    return process != null;
  }

  /**
   * DOCUMENT ME!
   */
  public void closeInput() {
    if (in != null) {
      in.flush();
      in.close();
      in = null;
    }
  }

  /**
   * DOCUMENT ME!
   *
   * @param f DOCUMENT ME!
   *
   * @return DOCUMENT ME!
   */
  public static String fileToPackageName(File f) {
    String cpath = null;

    try {
      // Get the file separator for the OS
      String sep = System.getProperty("file.separator");

      // (as a char)
      char sepChar = sep.charAt(0);

      // Get full path
      cpath = f.getCanonicalPath();

      /**
       * Strip off drive details. This is the crucial place to adjust the
       * package name in relation to the class path.
       */
      cpath = cpath.substring(cpath.indexOf(sep) + 1);

      // Turn it into package notation
      cpath = cpath.replace(sepChar, '.');
    } catch (IOException e) {
      e.printStackTrace();
    }

    return cpath;
  }

  /**
   * DOCUMENT ME!
   *
   * @param args DOCUMENT ME!
   */
  public static void main(String[] args) {
    new ProcessWrapper("java Factorial", new File(".")) {
        public void onOutput(char c) {
          System.out.print(c);
        }
      };
  }

  /**
   * DOCUMENT ME!
   *
   * @param c DOCUMENT ME!
   */
  public void onOutput(char c) {
  }

  /**
   * DOCUMENT ME!
   */
  public void onOutputClose() {
  }

  /**
   * DOCUMENT ME!
   */
  public void onStop() {
  }

  /**
   * DOCUMENT ME!
   *
   * @param s DOCUMENT ME!
   */
  public void sendInput(String s) {
    if (in != null) {
      in.println(s);
      in.flush();
    }
  }

  /**
   * DOCUMENT ME!
   */
  public void stop() {
    if (stopRequested) {
      return;
    }

    // Since we're trying to stop, sleep briefly to allow the readers 
    // to finish (don't know if this is necessary, but having some thread
    // timing/timing out issues, so perhaps this will resolve it).
//    try {
//      Thread.sleep(400);
//    } catch (InterruptedException ie) {
//      // don't worry about it
//    }

    stopRequested = true;

    if (process != null) {
      process.destroy();
      process = null;
    }

    closeInput();
    onStop();
  }

  /**
   * DOCUMENT ME!
   */
  private void waitUntilDone() {
    int exitValue = -1;

    for (;;) {
      try {
        if (process != null) {
          exitValue = process.exitValue();
        }

        break;
      } catch (Exception exn) {
        try {
          Thread.sleep(100);
        } catch (InterruptedException exn2) {
          System.err.println("Error in waitUntilDone");
        }
      }
    }

    if (logger.isLoggable(Level.INFO)) {
      logger.info("Exit value of process is " + exitValue);
    }

    process = null;
    stop();
  }

  private class ReaderThread extends Thread {
    private Reader reader;

    public ReaderThread(InputStream inStream) {
      super("Stream_reader");
      reader = new BufferedReader(new InputStreamReader(inStream));
    }

    public void run() {
      int c;

      try {
        for (;;) {
          if (stopRequested) {
            break;
          }

          c = reader.read();

          if (c == -1) {
            onOutputClose();

            if (--streamCount == 0) {
              waitUntilDone(); // ProcessWrapper.this.stop();
            }

            break;
          }

          onOutput((char) c);
        }
      } catch (Exception exn) {
        //this = null;
      }
    }
  }
}
