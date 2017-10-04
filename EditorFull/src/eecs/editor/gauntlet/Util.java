package eecs.editor.gauntlet;

import java.io.*;
import java.util.*;

class Util {
  private Util() {
    // Prevent instantiation
  }

  /**
   * Returns an array of strings where each element of the array is a line from the file and comments have been stripped from the text. The
   * order and number of lines are preserved. A line that is totally commented out will be a blank line. This is done to preserve the line
   * count.
   * 
   * @param file
   *          The java file to process
   * @returns Lines from the file with comments removed. Does not return null.
   * @throws IOException
   *           If an error occurs when processing the file.
   */
  public static String[] removeComments(File file) throws IOException {
    List lines = new ArrayList();
    FileReader fileReader = new FileReader(file);
    BufferedReader reader = new BufferedReader(fileReader);
    String line = "";
    StringBuffer buffer;
    char currentChar;
    boolean ignore = false;
    try {
      while ((line = reader.readLine()) != null) {
        buffer = new StringBuffer();
        for (int i = 0; i < line.length(); i++) {
          currentChar = line.charAt(i);
          if (currentChar == '\"') {
            buffer.append(currentChar);
            int endQuote = i + 1;
            boolean found = false;
            while (!found && endQuote < line.length()) {
              if (line.charAt(endQuote) == '\"' && line.charAt(endQuote - 1) != '\\') {
                buffer.append(line.charAt(endQuote));
                found = true;
              }
              else {
                buffer.append(line.charAt(endQuote));
                endQuote++;
              }
            }
            i = endQuote;
            continue;
          }
          if (!ignore) {
            if (currentChar == '/') {
              if (i < (line.length() - 1)) {
                char nextChar = line.charAt(i + 1);
                // EOL comment?
                if (nextChar == '/') {
                  //ignore the rest of the line
                  i = line.length();
                }
                // multiline comment?
                if (nextChar == '*') {
                  // forward to end of multiline comment
                  ignore = true;
                }
              }
            }
            else {
              buffer.append(currentChar);
            }
          }
          else {
            // while ignoring comments, look for */ and stop ignoring
            if (currentChar == '*') {
              if (i < (line.length() - 1)) {
                char nextChar = line.charAt(i + 1);
                if (nextChar == '/') {
                  ignore = false;
                }
              }
            }
          }
        }
        lines.add(buffer.toString());
      }
    }
    finally {
      reader.close();
      reader = null;
      fileReader.close();
      fileReader = null;
    }
    return (String[]) lines.toArray(new String[lines.size()]);
  }
}