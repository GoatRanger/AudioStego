package eecs.editor.problems;

import java.io.File;
import java.io.FilenameFilter;

public class JavaProblemFilenameFilter implements FilenameFilter {
  public boolean accept(File file, String filename) {
    if (file == null || filename == null) {
      return false;
    }
    return filename.endsWith("ice") || file.isDirectory();
  }
}