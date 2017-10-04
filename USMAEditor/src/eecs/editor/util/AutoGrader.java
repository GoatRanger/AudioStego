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
 * Created on Nov 10, 2003
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package eecs.editor.util;

import eecs.problem.Problem;

import java.awt.Toolkit;
import eecs.util.EMail;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import java.text.DateFormat;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;
import javax.swing.Timer;


/**
 * @author Karl A. Gossett
 *
 * Allows instructors to generate results files for programs that adhere to a
 * Problem Set format.
 * <br />
 * Requirements for use
 */
public class AutoGrader {
  final public static String STYLESHEET_LINK = "<link rel=\"stylesheet\" type=\"text/css\" href=\"http://www-internal.eecs.usma.edu/courses/it105/resources/xhtmlChecker/default.css\">";
  Problem problem;
  File directory;
  GauntletCompileTask compileTask;
  File javaFile;
  StringBuffer directorySummary;
  Timer timer;
  boolean finished;
  String errorText = "You submitted a homework with no .java file.  " +
    "You may have submitted the .class file instead (it has the same name), " +
    "or you might not have submitted anything.  " +
    "Please go to EECSWeb and submit your .java file, or contact me if you have problems.\r\n\r\n" +
    "This is an automatically generated message.";

  public void setProblem(Problem problem) {
    this.problem = problem;
  }

  public void setBaseDirectory(File directory) {
    this.directory = directory;
  }

  public void run() {
    StringBuffer missingJavaStudents = new StringBuffer(
        "Students who were notified via email that their .java was missing:\r\n");
    Pattern pattern = Pattern.compile("Completed(.*?)correctly");

    boolean emailCadets = false;
    int choice = JOptionPane.showConfirmDialog(null,
        "Would you like me to automatically email cadets who did not submit a .java file?");

    if (choice == JOptionPane.YES_OPTION) {
      emailCadets = true;
    }

    JOptionPane.showMessageDialog(null,
      "Due to poor programming, the editor will appear to lock up once you start the tests." +
      "It may beep (system ding sound) periodically as it runs tests against a cadet solution.");

    File[] studentDirs = directory.listFiles();
    System.err.println("-------------Checking " + directory.getName());

    StringBuffer dirBuffer = new StringBuffer("<html><head><title>" +
        problem.getTitle() + " Homework Results</title>\r\n  " +
        STYLESHEET_LINK + "\r\n</head><body>");
    dirBuffer.append("<h1>" + problem.getTitle() +
      " Homework Results</h1>\n<ol>");

    for (int i = 0; i < studentDirs.length; i++) {
      javaFile = null;
      directorySummary = new StringBuffer();

      // Shouldn't be any files in the root 
      if (studentDirs[i].isFile()) {
        continue;
      }

      System.err.println("Process " + studentDirs[i].getName() + " Of " +
        studentDirs.length + " students");

      File[] studentFiles = studentDirs[i].listFiles();
      javaFile = null;

      directorySummary = new StringBuffer(
          "<html><head><title>Error Summary for " + studentDirs[i].getName() +
          "</title>\r\n  " + STYLESHEET_LINK + "\r\n</head><body>\r\n");
      directorySummary.append(
        "\r\n<a class=\"returnlink\" href=\"../results.htm\">Return to Student List</a>\r\n");
      directorySummary.append("<h1>Results for: " + studentDirs[i].getName() +
        "</h1>");

      int javaCount = 0;

      for (int file = 0; file < studentFiles.length; file++) {
        if (studentFiles[file].getName().toLowerCase().endsWith(".java")) {
          javaFile = studentFiles[file];
          javaCount++;

          if (javaFile != null) {
            directorySummary.append(
              "<h2 class=\"main\">Summary Results of Tests for " +
              javaFile.getName() + "</h2>\r\n");

            System.out.println("File: " + javaFile.getAbsolutePath());

            compileTask = new eecs.editor.util.GauntletCompileTask();
            compileTask.setFile(javaFile);
            finished = false;

            boolean compiled = true;
            compileTask.go();

            while (!compileTask.done()) {
              try {
                Thread.sleep(100);
              } catch (InterruptedException e) {
              }
            }

            String[] msgs = compileTask.getCompilerMessages();

            if ((msgs != null) && (msgs.length > 0)) {
              compiled = false;
            }

            if (compiled) {
              String fileName = javaFile.getName();

              String programName = fileName.substring(0, fileName.length() - 5);
              String command = "java " + programName;
              System.out.println("Verifying against tests.");
              problem.verify(command, javaFile.getParentFile(),
                new Problem.TestCallback() {
                  public void done(final String result) {
                    directorySummary.append(result);
                    finished = true;
                  }
                }); // end verify
            } else {
              System.out.println("Compiler errors.");

              // Compiling failed, so just close out the process
              directorySummary.append(
                "<h2>Errors found, couldn't run tests</h2>");
              finished = true;
            }

            while (!finished) {
              try {
                Thread.sleep(100);
              } catch (InterruptedException e) {
              }
            }
          }
        }
      }

      if (javaCount == 0) {
        directorySummary.append("<h2>No Java Files Found</h2>");
      }

      directorySummary.append(
        "\r\n<br/><a class=\"returnlink\" href=\"../results.htm\">Return to Student List</a>\r\n");
      directorySummary.append("</body></html>");

      String fileName = studentDirs[i].getAbsolutePath() +
        "\\check-results.htm";
      writeResultsFile(fileName, directorySummary);

      // Create a link in the main HTML reference that links to the results in the new directory
      dirBuffer.append("<li><a href=\"" + studentDirs[i].getName() +
        "/check-results.htm\">" + studentDirs[i].getName());

      DateFormat df = DateFormat.getInstance();

      if (javaFile != null) {
        dirBuffer.append(" (submitted " +
          df.format(new Date(javaFile.lastModified())) + ")");
      }

      Matcher matcher = pattern.matcher(directorySummary.toString());

      while (matcher.find()) {
        dirBuffer.append(" - " + matcher.group());
        System.out.println(matcher.group());
      }

      if (!matcher.find()) {
        if (directorySummary.toString().indexOf("Errors found, couldn't run tests") > 0) {
          dirBuffer.append(" - Compiler Errors");
        } else {
          dirBuffer.append(" - no Java file");
          System.out.println("No Java");

          if (emailCadets) {
            int underline = studentDirs[i].getName().indexOf("_");

            if (underline > 0) {
              String userID = studentDirs[i].getName().substring(underline + 1);
              missingJavaStudents.append("\r\n" + userID);

              EMail.sendMsgAttachFile(userID + "@usma.edu",
                "Missing Selection Java File", errorText, new String[] {  });
            }
          }
        }
      }

      dirBuffer.append("</a></li>");
    }

    dirBuffer.append("</ol></body></html>");

    if (emailCadets) {
      EMail.sendMsgAttachFile(System.getProperty("user.name", "unknown") +
        "@usma.edu", "Students With Missing Java File\r\n",
        missingJavaStudents.toString() +
        "\r\n\r\nMessage they received:\r\n\r\n" + errorText, new String[] {  });
    }

    String fileName = directory.getAbsolutePath() + "\\results.htm";
    writeResultsFile(fileName, dirBuffer);
    Toolkit.getDefaultToolkit().beep();
    Toolkit.getDefaultToolkit().beep();
    Toolkit.getDefaultToolkit().beep();
    Toolkit.getDefaultToolkit().beep();
    Toolkit.getDefaultToolkit().beep();
    JOptionPane.showMessageDialog(null, "Finished Evaluations!");

    int open = JOptionPane.showConfirmDialog(null,
        "Would you like to view the web page now?");

    if (open == JOptionPane.YES_OPTION) {
      eecs.util.html.BrowserControl.displayURL("file:///" +
        directory.getPath() + "/results.htm");
    }
  }

  /**
   * DOCUMENT ME!
   *
   * @param name DOCUMENT ME!
   * @param results DOCUMENT ME!
   */
  private void writeResultsFile(String name, StringBuffer results) {
    File rFile = new File(name);

    try {
      FileWriter fw = new FileWriter(rFile);
      fw.write(results.toString());
      fw.flush();
      fw.close();
    } catch (IOException e) {
      System.err.println("Error writing results for " + name);
    }
  }
}
