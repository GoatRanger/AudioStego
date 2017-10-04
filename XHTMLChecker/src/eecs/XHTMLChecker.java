/*
 * Created on Sep 23, 2003
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package eecs;

import eecs.utility.BrowserControl;
import eecs.utility.CSSChecker;
import eecs.utility.HTMLChecker;
import eecs.utility.Unzip;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import java.nio.channels.FileChannel;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;


/**
 * DOCUMENT ME!
 *
 * @author DK8685 To change the template for this generated type comment go to
 *         Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and
 *         Comments
 */
public class XHTMLChecker {
  //public static String STYLESHEET_LINK = "<link rel=\"stylesheet\" type=\"text/css\" href=\"http://www-internal.eecs.usma.edu/courses/it105/resources/xhtmlChecker/default.css\">";

  /** DOCUMENT ME! */
  public static String STYLESHEET_LINK = "";

  /**
   * Creates a new XHTMLChecker_new object.
   */
  public XHTMLChecker() {
    JFileChooser jfc = new JFileChooser();
    jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
    jfc.setDialogTitle("Select Root HTML directory");
    jfc.setApproveButtonText("Select");
    jfc.showOpenDialog(null);

    if (jfc.getSelectedFile() != null) {
      String extractBase = jfc.getSelectedFile().getAbsolutePath();
      File extractDir = new File(extractBase + "\\extracted\\");
      System.out.println("Base: " + extractBase);
      System.out.println("Directory: " + extractDir.getAbsolutePath());
      extractDir.mkdir();

      File baseDir = jfc.getSelectedFile();
      File[] dirlist = baseDir.listFiles();
      JOptionPane.showMessageDialog(null, "Press OK to start unzipping files.");
      traverseAll(extractDir, dirlist);
      JOptionPane.showMessageDialog(null,
        "All files unzipped to " + extractBase
        + "\\extracted\\. Press OK to begin checking for errors.");

      checkAll(extractDir);

      int viewIt = JOptionPane.showConfirmDialog(null,
          "Checking complete.  Extracted files and results are in a new subdirectory <em>extracted</em>.\nWould you like to view the results now?",
          "Completed", JOptionPane.YES_NO_OPTION);

      if (viewIt == JOptionPane.YES_OPTION) {
        String url = extractDir.getAbsolutePath() + "\\results.htm";
        BrowserControl.displayURL(url);
      }
    }
  }

  /**
   * DOCUMENT ME!
   *
   * @param args DOCUMENT ME!
   */
  public static void main(String[] args) {
    new XHTMLChecker();
    System.exit(0);
  }

  /**
   * DOCUMENT ME!
   *
   * @param contents DOCUMENT ME!
   * @param tag DOCUMENT ME!
   *
   * @return DOCUMENT ME!
   */
  private int getBinaryTagCount(StringBuffer contents, String tag) {
    int count = 0;
    Pattern pattern = Pattern.compile("<" + tag + ".*?>");
    Matcher matcher = pattern.matcher(contents);

    while (matcher.find()) {
      count++;
    }

    pattern = Pattern.compile("</" + tag + ".*?>");
    matcher = pattern.matcher(contents);

    // Count both open and close to avoid problem with detecting nested tag pairs
    int endcount = 0;

    while (matcher.find()) {
      endcount++;
    }

    if (count != endcount) {
      count = (count + endcount) - Math.max(count, endcount);
      System.err.println("Binary Tag count not equal, new count: " + count);
    }

    return count;
  }

  /**
   * DOCUMENT ME!
   *
   * @param results DOCUMENT ME!
   *
   * @return DOCUMENT ME!
   */
  private StringBuffer getErrorSummary(StringBuffer results) {
    Pattern xhtmlError = Pattern.compile("XHTML-(Warning|Error)");
    Pattern linkError = Pattern.compile("Link Error");

    StringBuffer result = new StringBuffer();
    Matcher match = xhtmlError.matcher(results.toString());
    boolean errors = false;
    result.append("\n<div class=\"errorlist\"><h3>Errors</h3>");

    if (match.find()) {
      errors = true;
      result.append("<p> - Document has XHTML errors.</p>");
    }

    Matcher linkMatch = linkError.matcher(results.toString());

    if (linkMatch.find()) {
      errors = true;
      result.append("<p> - Document has errors in links/hrefs.</p>");
    }

    if (!errors) {
      result.append("<p>No XHTML or Link(href) errors found.</p>");
    }

    return result;
  }

  /**
   * DOCUMENT ME!
   *
   * @param file DOCUMENT ME!
   *
   * @return DOCUMENT ME!
   */
  private StringBuffer getFileContents(File file) {
    BufferedReader reader = null;
    StringBuffer buffer = new StringBuffer();

    try {
      reader = new BufferedReader(new FileReader(file));
    } catch (FileNotFoundException e) {
      buffer.append("Couldn't open file to check for common mistakes.\r\n");

      return buffer;
    } catch (NullPointerException e) {
      buffer.append(
        "Filesystem error, please save your work and restart the editor.\r\n");

      return buffer;
    }

    String line = "";
    StringBuffer fileBuffer = new StringBuffer();

    try {
      while ((line = reader.readLine()) != null) {
        fileBuffer.append(line + "\r\n");
      }
    } catch (IOException e) {
      buffer.append("Errors while reading your file.\r\n");
    } finally {
      try {
        reader.close();
      } catch (IOException e) {
        reader = null;
      }
    }

    return fileBuffer;
  }

  /**
   * DOCUMENT ME!
   *
   * @param contents DOCUMENT ME!
   * @param pat DOCUMENT ME!
   *
   * @return DOCUMENT ME!
   */
  private int getPatternCount(StringBuffer contents, String pat) {
    int count = 0;

    Pattern pattern = Pattern.compile(pat, Pattern.DOTALL);
    Matcher matcher = pattern.matcher(contents);

    while (matcher.find()) {
      count++;
      System.err.println("Pattern: " + matcher.group());
    }

    return count;
  }

  /**
   * DOCUMENT ME!
   *
   * @param contents DOCUMENT ME!
   *
   * @return DOCUMENT ME!
   */
  private int getStyleSheetCount(StringBuffer contents) {
    int count = 0;
    Pattern pattern = Pattern.compile("<link.*=\\\".*css\\\".*/>");
    Matcher matcher = pattern.matcher(contents);

    while (matcher.find()) {
      count++;
      System.err.println("Stylesheet link: " + matcher.group());
    }

    return count;
  }

  /**
   * DOCUMENT ME!
   *
   * @param contents DOCUMENT ME!
   * @param tag DOCUMENT ME!
   *
   * @return DOCUMENT ME!
   */
  private int getUnaryTagCount(StringBuffer contents, String tag) {
    int count = 0;
    Pattern pattern = Pattern.compile("<" + tag + ".*[^<].*/>");
    Matcher matcher = pattern.matcher(contents);

    while (matcher.find()) {
      count++;
      System.err.println("UnaryTag: " + matcher.group());
    }

    return count;
  }

  /**
   * DOCUMENT ME!
   *
   * @param baseDirectory DOCUMENT ME!
   */
  private void checkAll(File baseDirectory) {
    File[] studentDirs = baseDirectory.listFiles();
    System.err.println("-------------Checking " + baseDirectory.getName());

    StringBuffer dirBuffer = new StringBuffer(
        "<html><head><title>XHTML Results</title>\r\n  " + STYLESHEET_LINK
        + "\r\n</head><body>");
    dirBuffer.append("<h1>Results</h1>\n<ol>");

    for (int i = 0; i < studentDirs.length; i++) {
      FileResults fileResults = new FileResults();

      // Shouldn't be any files in the root (but our results.htm might be if rerun of tester)!
      if (studentDirs[i].isFile()) {
        System.err.println("Skipping " + studentDirs[i].toString());

        continue;
      }

      // Make a new directory for results
      File resultsDir = new File(studentDirs[i].getAbsoluteFile()
          + "\\results\\");
      resultsDir.mkdir();

      // Create a link in the main HTML reference that links to the results in the new directory
      dirBuffer.append("<li><a href=\"" + studentDirs[i].getName()
        + "/results/check-results.htm\">" + studentDirs[i].getName()
        + "</a></li>");

      System.err.println("Process " + studentDirs[i].getName() + " Of "
        + studentDirs.length + " students");

      StringBuffer directorySummary = new StringBuffer(
          "<html><head><title>Error Summary for " + studentDirs[i].getName()
          + "</title>\r\n  " + STYLESHEET_LINK + "\r\n</head><body>\r\n");
      fileResults = doDirectory("", fileResults, studentDirs[i]);

      // This return link has ../../ to back out of both the results and the cadet directory
      directorySummary.append(
        "\r\n<a class=\"returnlink\" href=\"../../results.htm\">Return to Student List</a>\r\n");
      directorySummary.append("<h1>Results for Directory: "
        + studentDirs[i].getName());
      directorySummary.append(summaryTable(fileResults));
      directorySummary.append(
        "<h2 class=\"main\">Summary Results by File</h2>\r\n");
      directorySummary.append(fileResults.getMessages());
      directorySummary.append(
        "\r\n<a class=\"returnlink\" href=\"../../results.htm\">Return to Student List</a>\r\n");
      directorySummary.append("</body></html>");

      String fileName = studentDirs[i].getAbsolutePath()
        + "\\results\\check-results.htm";
      writeResultsFile(fileName, directorySummary);
    }

    dirBuffer.append("</ol></body></html>");

    String fileName = baseDirectory.getAbsolutePath() + "\\results.htm";
    writeResultsFile(fileName, dirBuffer);
  }

  /**
   * DOCUMENT ME!
   *
   * @param file DOCUMENT ME!
   *
   * @return DOCUMENT ME!
   */
  private String cssSummary(File file) {
    StringBuffer buffer = new StringBuffer(
        "<table><thead><tr><td>Tag Type</td><td class=\"centered\">Usage Count</td></thead><tbody>\n");
    StringBuffer contents = getFileContents(file);

    // Check for hr modifications...ensure it has at least one attribute/value pair
    int hrCount = getPatternCount(contents, "hr[^\\}]*?\\{.*?\\:.*?\\}");
    int colorCount = getPatternCount(contents, "color:");
    int backgndCount = getPatternCount(contents, "background");

    buffer.append(
      "<tr><td>Modified Horizontal Rule</td><td class=\"centered\">" + hrCount
      + "</td></tr>");
    buffer.append("<tr><td>Modified Color</td><td class=\"centered\">"
      + colorCount + "</td></tr>");
    buffer.append("<tr><td>Modified Background</td><td class=\"centered\">"
      + backgndCount + "</td></tr>");
    buffer.append("\r\n</tbody></table>");

    return buffer.toString();
  }

  /**
   * DOCUMENT ME!
   *
   * @param currentPath DOCUMENT ME!
   * @param directoryResults DOCUMENT ME!
   * @param current DOCUMENT ME!
   *
   * @return DOCUMENT ME!
   */
  private FileResults doDirectory(String currentPath,
    FileResults directoryResults, File current) {
    // Create a directory for results
    File resultsDir = new File(current.getAbsoluteFile() + "\\results\\");
    resultsDir.mkdir();

    File[] dirList = current.listFiles();

    // First process all files in the current directory
    for (int num = 0; num < dirList.length; num++) {
      if (dirList[num].isFile()) {
        System.err.println("Looking at " + num + " of " + dirList.length
          + " in " + current.getName());

        FileResults fileResults = handleFile(currentPath,
            current.getAbsolutePath(), dirList[num]);

        // Since we're currently only processing html/css files, only draw
        // a dividing hr when the file is one of those two types
        if (dirList[num].getName().matches(".*\\.[s]?htm[l]?|.*\\.css")) {
          fileResults.appendMessage("<hr />\r\n");
        }

        directoryResults.addResults(fileResults);
      }
    }

    // Once all files in the current directory are processed, do directories
    for (int num = 0; num < dirList.length; num++) {
      if (dirList[num].isDirectory()) {
        //Skip results directories--assumes it's the directory we created
        if (dirList[num].getName().equalsIgnoreCase("results")) {
          continue;
        }

        doDirectory(currentPath + dirList[num].getName() + "/",
          directoryResults, dirList[num]);
      }
    }

    return directoryResults;
  }

  /**
   * DOCUMENT ME!
   *
   * @param parentToTopDir DOCUMENT ME!
   * @param directory DOCUMENT ME!
   * @param file DOCUMENT ME!
   *
   * @return DOCUMENT ME!
   */
  private FileResults handleFile(String parentToTopDir, String directory,
    File file) {
    FileResults results = new FileResults();

    Pattern cssError = Pattern.compile("CSS\\ (Warning|Error):");

    int lastSlash = directory.lastIndexOf('\\');
    String dirName = directory;

    if (lastSlash >= 0) {
      dirName = dirName.substring(lastSlash, dirName.length());
    }

    String name = file.getName();

    StringBuffer htmlFile = new StringBuffer(
        "<html><head><title>Error Details for " + name + "</title>\r\n  "
        + STYLESHEET_LINK + "\r\n</head><body>\r\n");
    String saveFileName = name + "-results.htm";

    if (name.endsWith(".htm") || name.endsWith(".html")) {
      if (!name.endsWith("results.htm")) {
        htmlFile.append("<h1>Details for " + parentToTopDir + name + "</h1>");

        String namePart = name.substring(0, name.lastIndexOf("."));

        String fullPath = parentToTopDir + "/" + dirName + "/";
        results.appendMessage("<h2>HTML File: <a href=\"../" + parentToTopDir
          + "results/" + namePart + "-results.htm\">" + parentToTopDir + name
          + "</a></h2>");

        HTMLChecker check = new HTMLChecker();
        htmlFile.append(check.check(file));

        results.appendMessage(getErrorSummary(htmlFile).toString());
        results.appendMessage("\r\n</div>\r\n");
        results.appendMessage("\n<h3>XHTML Structures Summary</h3>\n");
        results = tagSummary(results, getFileContents(file));
        results.appendMessage("\r\n<p><a href=\"../" + parentToTopDir + name
          + "\" target=\"file\">View File (New Window)</a></p>");
        saveFileName = directory + "\\results\\" + namePart + "-results.htm";
      }
    } else if (name.endsWith(".css")) {
      htmlFile.append("<h1>Details for " + parentToTopDir + name + "</h1>");

      String namePart = name.substring(0, name.lastIndexOf("."));
      results.appendMessage("<h2>Stylesheet: <a href=\"../" + parentToTopDir
        + "results/" + namePart + "-css-results.htm\">" + parentToTopDir
        + name + "</a></h2>");

      CSSChecker checker = new CSSChecker();
      htmlFile.append(checker.check(file));

      Matcher match = cssError.matcher(htmlFile.toString());
      boolean errors = false;

      if (match.find()) {
        errors = true;
        results.appendMessage("<p> - Stylesheet has errors.</p>");
      }

      if (!errors) {
        results.appendMessage("<p>No CSS errors found.</p>");
      }

      results.appendMessage(cssSummary(file));
      results.appendMessage("\r\n<p><a href=\"../" + parentToTopDir + name
        + "\" target=\"file\">View File (New Window)</a></p>");
      saveFileName = directory + "\\results\\" + namePart + "-css-results.htm";

      StringBuffer contents = getFileContents(file);
      results.addStyleHrCount(getPatternCount(contents,
          "hr[^\\}]*?\\{.*?\\:.*?\\}"));
      results.addStyleColorCount(getPatternCount(contents, "color:"));
      results.addStyleBackgroundCount(getPatternCount(contents, "background"));
    } else {
      // Isn't html or css...should we do something else at this point?
    }

    int slashes = 0;
    int pos = 0;

    while (pos >= 0) {
      pos = parentToTopDir.indexOf("/", pos + 1);
      slashes++;
    }

    String backDirs = "";

    for (; slashes > 0; slashes--) {
      backDirs += "../";
    }

    htmlFile.append("\r\n<div class=\"linklist\"><p>Links:</p>\r\n");
    htmlFile.append("\r\n<ul><li><a href=\"../" + name
      + "\" target=\"file\">View File (New Window)</a></li>");

    // The following javascript version doesn't work on Firebird..but it does on IE, so use it
    // (Use the commented link version for browsers that don't work correctly with history.go())
    htmlFile.append(
      "<li><a href=\"javascript:history.go(-1)\">Back to main results for this cadet</a></li>");

    //    htmlFile.append("<li><a href=\"" + backDirs
    //      + "results/check-results.htm\">Return to Main Results for this Cadet"
    //      + "</a></li></ul></div></body></html>");
    writeResultsFile(saveFileName, htmlFile);

    return results;
  }

  /**
   * DOCUMENT ME!
   *
   * @param directoryResults DOCUMENT ME!
   *
   * @return DOCUMENT ME!
   */
  private StringBuffer summaryTable(FileResults directoryResults) {
    StringBuffer summary = new StringBuffer();
    summary.append("</h1>\r\n<h2>Structure Summary</h2>\r\n");
    summary.append(
      "<table><thead><tr><td>Tag Type</td><td class=\"centered\">Usage Count</td></thead><tbody>\n");
    summary.append("<tr><td>Stylesheet Link</td><td class=\"centered\">"
      + directoryResults.getStylesheets() + "</td></tr>");
    summary.append("<tr><td>Anchor Tags</td><td class=\"centered\">"
      + directoryResults.getLinkCount() + "</td></tr>");
    summary.append("<tr><td>Ordered Lists</td><td class=\"centered\">"
      + directoryResults.getOlCount() + "</td></tr>");
    summary.append("<tr><td>Unordered Lists</td><td class=\"centered\">"
      + directoryResults.getUlCount() + "</td></tr>");
    summary.append("<tr><td>Tables</td><td class=\"centered\">"
      + directoryResults.getTableCount() + "</td></tr>");
    summary.append("<tr><td>Headings (h1-h6)</td><td class=\"centered\">"
      + directoryResults.getHeadingCount() + "</td></tr>");
    summary.append("<tr><td>Images</td><td class=\"centered\">"
      + directoryResults.getImgCount() + "</td></tr>");
    summary.append("<tr><td>Horizontal Rules</td><td class=\"centered\">"
      + directoryResults.getHrCount() + "</td></tr>");
    summary.append(
      "<tr><td>Stylesheet Background Attributes</td><td class=\"centered\">"
      + directoryResults.getStyleBackgroundCount() + "</td></tr>");
    summary.append(
      "<tr><td>Stylesheet Color Attribute</td><td class=\"centered\">"
      + directoryResults.getStyleColorCount() + "</td></tr>");
    summary.append(
      "<tr><td>Stylesheet Horizontal Rules</td><td class=\"centered\">"
      + directoryResults.getStyleHrCount() + "</td></tr>");
    summary.append("</tbody></table>\n<hr />\n");

    return summary;
  }

  /**
   * DOCUMENT ME!
   *
   * @param directoryResults DOCUMENT ME!
   * @param contents DOCUMENT ME!
   *
   * @return DOCUMENT ME!
   */
  private FileResults tagSummary(FileResults directoryResults,
    StringBuffer contents) {
    FileResults result = new FileResults(directoryResults);
    int fstylesheets = getStyleSheetCount(contents);
    int flinkCount = getBinaryTagCount(contents, "a");
    int fheadCount = getBinaryTagCount(contents, "head");
    int fhtmlCount = getBinaryTagCount(contents, "html");
    int folCount = getBinaryTagCount(contents, "ol");
    int fulCount = getBinaryTagCount(contents, "ul");
    int ftableCount = getBinaryTagCount(contents, "table");
    int fheadingCount = getBinaryTagCount(contents, "h1")
      + getBinaryTagCount(contents, "h2") + getBinaryTagCount(contents, "h3")
      + getBinaryTagCount(contents, "h4") + getBinaryTagCount(contents, "h5")
      + getBinaryTagCount(contents, "h6");
    int fimgCount = getUnaryTagCount(contents, "img");
    int fhrCount = getUnaryTagCount(contents, "hr");
    result.appendMessage(
      "<table><thead><tr><td>Tag Type</td><td class=\"centered\">Usage Count</td></thead><tbody>\n");
    result.appendMessage("<tr><td>Stylesheet Link</td><td class=\"centered\">"
      + fstylesheets + "</td></tr>");
    result.appendMessage("<tr><td>Anchor Tags</td><td class=\"centered\">"
      + flinkCount + "</td></tr>");
    result.appendMessage("<tr><td>Ordered Lists</td><td class=\"centered\">"
      + folCount + "</td></tr>");
    result.appendMessage("<tr><td>Unordered Lists</td><td class=\"centered\">"
      + fulCount + "</td></tr>");
    result.appendMessage("<tr><td>Tables</td><td class=\"centered\">"
      + ftableCount + "</td></tr>");
    result.appendMessage(
      "<tr><td>Headings (h1-h6)</td><td class=\"centered\">" + fheadingCount
      + "</td></tr>");
    result.appendMessage("<tr><td>Images</td><td class=\"centered\">"
      + fimgCount + "</td></tr>");
    result.appendMessage(
      "<tr><td>Horizontal Rules</td><td class=\"centered\">" + fhrCount
      + "</td></tr>");
    result.appendMessage("</tbody></table>\n");
    result.addStyleCount(fstylesheets);
    result.addOlCount(folCount);
    result.addUlCount(fulCount);
    result.addTableCount(ftableCount);
    result.addHeadingCount(fheadingCount);
    result.addLinkCount(flinkCount);
    result.addHrCount(fhrCount);
    result.addImgCount(fimgCount);

    return result;
  }

  /**
   * DOCUMENT ME!
   *
   * @param extractTo DOCUMENT ME!
   * @param dirlist DOCUMENT ME!
   */
  private void traverseAll(File extractTo, File[] dirlist) {
    File extractDir = extractTo;

    for (int i = 0; i < dirlist.length; i++) {
      if (dirlist[i].isDirectory()) {
        File next = new File(extractDir,dirlist[i].getName());
        traverseAll(/*extractDir*/next, dirlist[i].listFiles());
      } else {
        if (dirlist[i].getName().endsWith(".zip")) {
          String parent = dirlist[i].getParent();
          System.out.println("Parent:" + parent);

          String ext = dirlist[i].getParent().substring(dirlist[i].getParent()
                                                                  .lastIndexOf("\\"));
          System.out.println("Root for file: " + ext);

          String endExtract = extractDir.getAbsolutePath() + ext;
          File nextDir = new File(endExtract);
          nextDir.mkdir();
          Unzip.zipExtract(nextDir.getAbsolutePath() + "\\",
            dirlist[i].getAbsolutePath());
        } else {
          // Just try to copy the file to the new directory
          try {
            String ext = dirlist[i].getParent().substring(dirlist[i].getParent()
                                                                    .lastIndexOf("\\"));
            System.out.println("Root for new file: " + ext + " from "
              + dirlist[i].getParent());

            String endExtract = extractDir.getAbsolutePath() + ext;
            File nextDir = new File(endExtract);
            nextDir.mkdir();

            FileChannel sourceChannel = new FileInputStream(dirlist[i]
                .getAbsolutePath()).getChannel();
            System.out.println("Copying " + nextDir.getAbsolutePath() + "\\"
              + dirlist[i].getName());

            FileChannel destinationChannel = new FileOutputStream(nextDir
                .getAbsolutePath() + "\\" + dirlist[i].getName()).getChannel();

            // Copy source file to destination file
            destinationChannel.transferFrom(sourceChannel, 0,
              sourceChannel.size());
            sourceChannel.close();
            destinationChannel.close();
          } catch (IOException e) { // handle any IOException
            System.err.println("Couldn't copy " + dirlist[i].getAbsolutePath());
          }
        }
      }
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
