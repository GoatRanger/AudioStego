package eecs.editor.gauntlet;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import com.sun.tools.javac.Main;
import eecs.editor.environment.Environment;
import eecs.util.JavaWebStartManager;

public class CheckerFile {
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
      reader.setMessages(new ArrayList());
      // Redirects standard error, so must run it first
      reader.start();
      // Wait to ensure the reader starts before continuing
      while (!reader.isRunning()) {
        try {
          Thread.sleep(50);
        }
        catch (InterruptedException ie) {
          //
        }
      }
      String fileName = file.getAbsolutePath();
      String[] args;
      String newJavaExtDir = "";
      args = new String[] { "-extdirs", newJavaExtDir, "-classpath",
          Environment.getJagoPath() + ";" + Environment.getWrapperPath() + ";" + Environment.getEclipseLibDirectory() + ";" +
          Environment.getInstructorPath() + ";" + Environment.getMediaPath(), fileName };
      
System.out.println("Run javac:jws="+(JavaWebStartManager.hasBeenStarted()?"true":"false"));    
System.out.println("  javac -extdirs=\"" + newJavaExtDir + "\" -classpath=\"" +
    Environment.getJagoPath() + ";" + Environment.getWrapperPath() + ";" + Environment.getEclipseLibDirectory() +
    ";" + Environment.getInstructorPath() +
    ";" + Environment.getMediaPath()+ "\" " + fileName);
      
      int compileReturnCode = Main.compile(args, new PrintWriter(new PipedOutputStream(pis), true));
      // Return code will be 1 if errors, 2 if can't run, 0 if ok
      if (compileReturnCode != 0) {
        while (reader.isRunning()) {
          try {
            Thread.sleep(100);
          }
          catch (InterruptedException e) {
            //
          }
        }
      }
      else {
        reader.end();
      }
      try {
        d.close();
      }
      catch (IOException e) {
        //
      }
      System.setErr(origErr);
      compilerMessages = reader.getMessages();
    }
    final String[] copy = new String[compilerMessages.length];
    System.arraycopy(compilerMessages, 0, copy, 0, compilerMessages.length);
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
      System.setProperty("java.class.path", ".;" + workingDir + ";" + classPath);
    }
  }

  public String[] getLines() throws IOException {
    if (lines == null) {
      lines = Util.removeComments(file);
    }
    final String[] copy = new String[lines.length];
    System.arraycopy(lines, 0, copy, 0, lines.length);
    return copy;
  }

  public String getName() {
    return name;
  }

  private class ReaderThread extends Thread {
    List messageList;
    private BufferedReader reader;
    private boolean isRunning = false;

    public ReaderThread(BufferedReader reader) {
      super("Stream_reader");
      this.reader = reader;
    }

    public final void setMessages(final List messages) {
      messageList = new ArrayList(messages);
    }

    public final String[] getMessages() {
      if (messageList != null) {
        return (String[]) messageList.toArray(new String[messageList.size()]);
      }
      return null;
    }

    public final boolean isRunning() {
      return isRunning;
    }

    public final void end() {
      //
    }

    public void run() {
      String line;
      isRunning = true;
      // Assumes that the stream will have SOMETHING on it, so wait until it does
      try {
        while (!reader.ready()) {
          try {
            Thread.sleep(80);
          }
          catch (InterruptedException e) {
            //
          }
        }
        // Now we know standard err is receiving input, start reading
        while (reader.ready() && ((line = reader.readLine()) != null)) {
          messageList.add(line);
        }
      }
      catch (IOException e) {
        messageList.add("System error.  Couldn't compile your program; try again.");
      }
      isRunning = false;
    }
  }
}