package eecs.editor.problems;

import eecs.editor.environment.Environment;
import eecs.util.ProcessWrapper;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

// A Problem that includes a problem description and code template. May also include test inputs and outputs, if the problem can be
// automatically tested. Problems are described in an XML document, see the Examples directory for an example.
public class Problem {
  ProcessWrapper process = null;
  private String description;
  private String externalLink;
  String tempResult = null;
  StringBuffer result = null;
  private String template;
  String title;
  private String type;
  Vector testCaseDescription;
  Vector testCaseOutputType;
  Vector testInputs;
  Vector testOutputs;
  private boolean testable = false;
  int testsComplete = 0;
  private int testsToRun = 0;

  public Problem() {
    //
  }

  public String getDescription() {
    return description;
  }

  public String getExternalLink() {
    return externalLink;
  }

  public String getLanguage() {
    return type;
  }

  public String getTemplate() {
    return template;
  }

  // If the problem has test cases associated with it, this should be <code>true</code>. It is set as an attribute of the problem xml
  // file, so if the attribute is set incorrectly, this will also return <code>false</code>.
  public boolean isTestable() {
    return testable;
  }

  public String getTitle() {
    return title;
  }

  // Reads a problem (exercise) from the specified file. If the document isn't well-formed, this method will throw a NullPointerException.
  public boolean readProblem(File file) {
    boolean success = false;
    File f = file; // The file to parse. Assume this is initialized elsewhere
    // Create a factory object for creating DOM parsers
    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    DocumentBuilder parser = null;
    Document document = null;
    try {
      // Now use the factory to create a DOM parser (a.k.a. a DocumentBuilder)
      parser = factory.newDocumentBuilder();
      // Parse the file and build a Document tree to represent its content
      document = parser.parse(f);
      type = getProblemLanguage(document);
      // Get each of the singletons by name only
      // If the document isn't well formed (missing one of these), this will
      // throw a NullPointerException, which should be handled by the caller.
      title = getDocumentItem(document, "title");
      getDescription(document);
      template = getDocumentItem(document, "template");
      getTestCases(document);
      success = true;
    }
    catch (SAXException se) {
      f.delete();
      String userID = System.getProperty("user.name", "x00000");
      if (!(userID.toLowerCase().startsWith("x"))) {
        JOptionPane.showMessageDialog(null, "Error in problem set \"" + f.getName() + "\".  Message:\n" + se.getMessage());
      }
    }
    catch (ParserConfigurationException pce) {
      String userID = System.getProperty("user.name", "x00000");
      if (!(userID.toLowerCase().startsWith("x"))) {
        JOptionPane.showMessageDialog(null, "Error in problem set \"" + f.getName() + "\".  Message:\n" + pce.getMessage());
      }
    }
    catch (IOException e) {
      String userID = System.getProperty("user.name", "x00000");
      if (!(userID.toLowerCase().startsWith("x"))) {
        JOptionPane.showMessageDialog(null, "Error in problem set \"" + f.getName() + "\".  Message:\n" + e.getMessage());
      }
    }
    return success; // default is false (so not set in exception handlers)
  }

  // Runs a test on the input file (via the command), and reports results through the TestCallback. Reports an empty String ("")
  // if the test was successful, and an error message for every test it failed.
  public void verify(final String command, final File dir, final TestCallBack callback) {
    testsComplete = 0;

        result = new StringBuffer("<h3>Test Results for exercise \"");
        result.append(title);
        result.append("\", run by ");
        result.append(System.getProperty("user.name", "unknown"));
        result.append("</h3>");
        Properties p = new Properties();
        String home = Environment.getUsersConfigPath();
        // if the properties were never created, make sure the directory exists
        File f = new File(home);
        f.mkdirs();
        int correct = 0;
        for (int i = 0; i < testInputs.size(); i++) {
          // Testing needs to be turned on for every run, because it is shut off in the Jago package as soon
          // as the property is read, to ensure that testing isn't accidentally left "on" in the event that
          // this process crashes without disabling the flag.
          p.setProperty("eecs.testing", "true");
          try {
            FileOutputStream out = new FileOutputStream(home + "/testing.cfg");
            p.store(out, "testing configuration");
            out.flush();
            out.close();
          }
          catch (FileNotFoundException fnfe) {
            //
          }
          catch (IOException e) {
            result.append("<h2><font color=\"red\">There is a problem with the testing system.</font></h2>"
                + "\r\n<h3>Please contact your instructor for assistance.</h3>");
          }
          try {
            Thread.sleep(200);
          }
          catch (InterruptedException ie) {
            System.err.println("Interrupted!");
            ie.printStackTrace();
          }
          String testResult = runTest(command, dir, (String) testInputs.elementAt(i), (String) testOutputs.elementAt(i));
          if (testResult == null) {
            testResult = "";
          }
          
          testResult = testResult.trim();
          boolean useExact = true;
          String expectedResult = ((String) testOutputs.elementAt(i)).trim();
          boolean exactResult = useExact && testResult.equals(expectedResult);
          boolean regexResult = false;
          if (((String) testCaseOutputType.elementAt(i)).equalsIgnoreCase("regex")) {
            useExact = false;
            Pattern pattern = Pattern.compile(expectedResult, Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
            Matcher matcher = pattern.matcher(testResult);
            regexResult = !useExact && matcher.find();
          }
          if (exactResult || regexResult) {
            result.append("<b><font color=\"green\">Passed case #");
            result.append(i + 1);
            if (testCaseDescription.elementAt(i) != "Undescribed") {
              result.append(" (");
              result.append(testCaseDescription.elementAt(i));
              result.append(")");
            }
            result.append(".</font></b><br>\r\n");
            correct++;
          }
          else {
            result.append("<b><font color=\"red\">Failed Case #");
            result.append(i + 1);
            if (!testCaseDescription.elementAt(i).equals("Undescribed")) {
              result.append(" (");
              result.append(testCaseDescription.elementAt(i));
              result.append(")");
            }
            result.append(" (spaces are shown as a double-underline for clarity)");
            result.append(": </font></b><br>\r\n");
            result.append("&nbsp;&nbsp;Your Output: ");
            // If timed out response, don't modify it.
            testResult = toHTML(testResult);
            if (!testResult.startsWith("Timed out on input") && !testResult.startsWith("(NO OUTPUT) An error occurred with input:")) {
              testResult = spacesToUnderlines(testResult);
            }
            if (testResult.equals("")) {
              result.append("-- no output produced --");
            }
            else {
              result.append("<br><hr width=100px>" + testResult + "<hr width=300px>");
            }
            result.append("<br>\r\n");
            result.append("&nbsp;&nbsp;Expected: ");
            if (useExact) {
              result.append("<br><hr width=100px>" + spacesToUnderlines(toHTML(expectedResult)) + "<hr width=300px>");
            }
            else {
              result.append(translateRegex(expectedResult));
            }
            result.append("<br>\r\n");
          }
          testsComplete++;
        }
        // Make sure testing is shut off
        p.setProperty("eecs.testing", "false");
        try {
          FileOutputStream out = new FileOutputStream(home + "\\testing.cfg");
          p.store(out, "testing configuration");
          out.flush();
          out.close();
        }
        catch (IOException e) {
          e.printStackTrace();
        }
        result.append("\n<hr><font size=\"+1\"><b>Completed " + correct + " of " + testInputs.size() + " tests correctly.</b></font>\r\n");
        // Save the results of the completed test
        //Preferences prefs = Preferences.userNodeForPackage(Editor.class);
        if (correct == testInputs.size()) {
          // If it exists, it shouldn't have a time of 0.
          if (Constants.PREFERENCES.getLong("ice" + title, 0) == 0) {
            Constants.PREFERENCES.putLong("ice" + title, System.currentTimeMillis());
          }
        }
        callback.done(result.toString());
  }

  public String getResults() {
    return result.toString();
  }

  String spacesToUnderlines(String input) {
    String result = input.replaceAll(" ", "&#8215;");
    return result;
  }

  String toHTML(String input) {
    String result = input;
    result = result.replaceAll("&", "&amp;");
    result = result.replaceAll("<", "&lt;");
    result = result.replaceAll(">", "&gt;");
    result = result.replaceAll("\n", "<br>");
    result = result.replaceAll("\"", "&quot;");
    return result;
  }

  // Gets the text of a names element from the document. This method will throw a NullPointerException if the tag is not
  // found
  private String getDocumentItem(final Document document, String item) {
    return document.getElementsByTagName(item).item(0).getFirstChild().getNodeValue();
  }

  private void getDescription(final Document document) {
    description = getDocumentItem(document, "description");
    // See if it has a link to an external file
    Element descriptionElem = (Element) document.getElementsByTagName("description").item(0);
    if (descriptionElem != null) {
      externalLink = descriptionElem.getAttribute("link");
    }
    if (externalLink == "") {
      externalLink = null;
    }
    if ((description != null) && description.trim().startsWith("external:")) {
      int eol = description.indexOf("\n");
      int endURL = ((eol > 0) ? eol : description.length());
      externalLink = description.substring(9, endURL);
      if (eol > 0) {
        int startText = description.indexOf("text:");
        if (startText > 0) {
          description = "(For more details, go to " + description.substring(9, endURL) + ")\r\n\r\n" + description.substring(startText + 6);
        }
        else {
          description = "See Browser Window for Full Description.";
        }
      }
      else {
        description = "See Browser Window for Full Description.";
      }
    }
  }

  private StringBuffer getEntries(final NodeList inputElems) {
    StringBuffer entryList = new StringBuffer();
    Node entryHead;
    for (int j = 0; j < inputElems.getLength(); j++) {
      entryHead = ((Element) inputElems.item(j)).getFirstChild();
      while (entryHead != null) {
        entryHead = entryHead.getNextSibling();
        // The node types will either be ELEMENT_NODE or TEXT_NODE,
        // the ELEMENT_NODEs are the children <entry> elements
        if ((entryHead != null) && (entryHead.getNodeType() == Node.ELEMENT_NODE)) {
          // Now just get the value of the element
          //        (assumes <entry> is a leaf)
          // Must catch exception to allow for empty entries (in output)
          String entry = null;
          try {
            entry = entryHead.getFirstChild().getNodeValue();
          }
          catch (NullPointerException e) {
            entry = "";
          }
          entryList.append(entry);
          entryList.append("\r\n");
        }
      }
    }
    return entryList;
  }

  private String getProblemLanguage(final Document document) {
    String type = "unknown";
    if (document.getDocumentElement().hasAttributes()) {
      NamedNodeMap attrs = document.getDocumentElement().getAttributes();
      Node attribute = attrs.getNamedItem("testable");
      if ((attribute != null) && attribute.getNodeValue().equalsIgnoreCase("true")) {
        testable = true;
      }
      attribute = attrs.getNamedItem("language");
      if (attribute != null) {
        type = attribute.getNodeValue();
      }
    }
    if (!type.startsWith("text/")) {
      type = "text/" + type;
    }
    return type;
  }

  private void getTestCases(final Document document) {
    // Ask the document for a list of all <test-case> tags it contains
    NodeList sections = document.getElementsByTagName("test-case");
    int numSections = sections.getLength();
    testsToRun = numSections;
    testInputs = new Vector(numSections);
    testCaseDescription = new Vector(numSections);
    testCaseOutputType = new Vector(numSections);
    testOutputs = new Vector(numSections);
    // Loop through those <test-case> elements one at a time
    for (int i = 0; i < numSections; i++) {
      Element section = (Element) sections.item(i); // A <test-case>
      String desc = section.getAttribute("description");
      if (desc == "") {
        desc = "Undescribed";
      }
      testCaseDescription.add(desc);
      String type = section.getAttribute("outputtype");
      if (type == "") {
        type = "exact";
      }
      testCaseOutputType.add(type);
      // Get a list of all <input> elements from the current <test-case>
      NodeList inputElems = section.getElementsByTagName("input");
      StringBuffer inputList = getEntries(inputElems);
      testInputs.addElement(inputList.toString());
      // Repeat the same process for the <output> elements
      NodeList outputElems = section.getElementsByTagName("output");
      StringBuffer outputList = getEntries(outputElems);
      testOutputs.addElement(outputList.toString());
    }
  }

  String runTest(final String command, final File dir, final String input, final String output) {
    tempResult = null;
    Thread testThread = new Thread("run_tests") {
      public void run() {
        process = new ProcessWrapper(command, dir) {
          int i = 0;
          boolean pendingNewlineInDesiredOutput = false;
          boolean pendingNewlineInActualOutput = false;

          public void onOutput(char c) {
            if (tempResult == null) {
              tempResult = "";
            }
            tempResult += c;
          }

          public void onClose() {
            if (i < output.length()) {
              if (tempResult == null)
                tempResult = "";
              tempResult += "\nNot enough output for input:\n" + input + "\n";
              stop();
            }
          }

          public void onStop() {
            if (tempResult == null) {
              tempResult = "-#NONE#-";
            }
          }
        };
        process.sendInput(input);
        int count = 0;
        while ((process != null && process.isRunning()) && count++ < 30) {
          try {
            Thread.sleep(100);
          }
          catch (InterruptedException ie) {
            //
          }
        }
        if (process.exitValue() == -1) {
          tempResult = "(NO OUTPUT) An error occurred with input:\n" + input + "\nYou should try these values manually or ask your "
              + "instructor for assistance.\n";
        }
      }
    };
    testThread.start();
    // Wait up to 12 seconds for completion (in 100ms increments)
    long startTime = System.currentTimeMillis();
    try {
      testThread.join(5000);
    }
    catch (InterruptedException ie) {
      tempResult += "\r\nTest Interrupted.\r\n";
      ie.printStackTrace();
    }
    long finishTime = System.currentTimeMillis();
    if (tempResult == null) {
      tempResult = "Timed out on input:\n" + input + ". Waited " + ((int) (finishTime - startTime) / 1000) + "s before giving up.\n"
          + " This is often caused by getting the wrong type as input (i.e. input type is real numbers, but you used getInt).\n";
    }
    else
      if (tempResult.equals("-#NONE#-")) {
        tempResult = null;
      }
    if (process != null)
      process.stop();
    return tempResult;
  }

  String translateRegex(String regex) {
    String translation = "";
    translation = "Various results accepted, but must have a specified format. Contact your instructor for more information.";
    return translation;
  }

  public static interface TestCallBack {
    public void done(String result);
  }

  public int getTestsComplete() {
    return testsComplete;
  }

  public int getTestsToRun() {
    return testsToRun;
  }
}