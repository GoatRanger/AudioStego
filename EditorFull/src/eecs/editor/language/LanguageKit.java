package eecs.editor.language;

import eecs.editor.jedit.JEditTextArea;
import eecs.util.ProcessWrapper;
import java.io.File;

/**
 * An interface to manage how languages are handled in the editor. By implementing this interface, languages can be registered in the editor
 * and get support from compiling, formatting, and running/viewing.
 */
public interface LanguageKit {
  public static final int NOT_SUPPORTED = -1;
  public static final int REFORMAT_SUCCESS = 0;
  public static final int REFORMAT_WARNINGS = 1;
  public static final int REFORMAT_ERRORS = 2;
  public static final int COMPILE_SUCCESS = 0;
  public static final int COMPILE_FAILURE = 1;
  public static final int COMPILE_INCOMPLETE = Integer.MAX_VALUE;
  public static final String JAVA = "text/java";
  public static final String HTML = "text/html";
  public static final String CSS = "text/css";
  public static final String TEXT = "text/plain";
  public static final String HUDSON = "text/hudson";

  public abstract int getCompileResult();

  public abstract StringBuffer getFormatMessages();

  public abstract String getLanguage();

  public String getDefaultSuffix();

  public String getDescription();

  public abstract boolean isRunnable();

  public abstract String getTemplate();

  public abstract void compile(File file);

  public abstract boolean hasCompiler();

  public abstract void reformat(JEditTextArea textpane);

  public void setFormatPrefs(java.awt.Window window);

  public abstract int getFormatResult();

  public boolean hasFormatPreferences();

  public abstract boolean hasFormatter();

  public abstract ProcessWrapper run(File file);

  public abstract void submitAction(File file);

  public String getCompilerMessages();

  public boolean isCompilerDone();

  /**
   * Gets the status of the compiler as a percentage complete (0-100, integers). If there is nothing to compile, or it is finished, this
   * will return 100 (representing 100% complete).
   * 
   * @return The percent of checking complete, as an int 0-100.
   */
  public int getCompilerPercentComplete();

  /**
   * Returns any output or messages produced when the program is run. Should return a <code>null</code> if the language does not support
   * execution, or the program has not been executed. Expected behavior is that if a program was executed with the {@link #run}command then
   * this method will not return until results are available.
   * 
   * @return a String containing the results, or <code>null</code> if this language doesn't support execution. Should return an empty
   *         String if there is no output (as opposed to <code>null</code>).
   */
  public String getRunResults();
}