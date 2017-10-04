package eecs.editor.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import org.xml.sax.InputSource;

public class HTMLChecker {
  private String nicCheckURL = "http://www.w3.org";
  StringBuffer buffer;
  boolean isValidHTML = true;
  private double percentComplete = 0;

  public HTMLChecker() {
    //
  }

  public boolean getValidHTML() {
    return isValidHTML;
  }

  public int getPercentComplete() {
    return (int) percentComplete;
  }

  // Validate and parse the specified file (if the DOCTYPE isn't specified, document cannot be validated). Returns a list of errors.
  public String check(final File file) {
    percentComplete = 5;
    isValidHTML = true;
    buffer = new StringBuffer();
    buffer.append("<html><head><title>XHTML Error Check</title><body>");
    buffer.append("<h2>--- Checking for Errors (Local DTD) ---</h2>");
    javax.xml.parsers.DocumentBuilderFactory factory = javax.xml.parsers.DocumentBuilderFactory.newInstance();
    javax.xml.parsers.DocumentBuilder parser = null;
    try {
      factory.setIgnoringComments(false);
      factory.setValidating(true);
      File dtd = new File(System.getProperty("java.ext.dirs") + "/xhtml1-strict.dtd");
      if (!dtd.exists()) {
        buffer.append("<p>Local DTD not found trying network.</p>");
        // Now use the factory to create a DOM parser
        // (a.k.a. a DocumentBuilder)
        eecs.util.InterfaceCheck nic = new eecs.util.InterfaceCheck();
        // Check for external and url, just in case wireless reports available when not logged in
        if (!(nic.isAnyExternalAvailable() && nic.isURLAvailable(nicCheckURL))) {
          factory.setValidating(false);
          buffer
              .append("<h2><font color='red'>Couldn't validate document; DTD is not stored locally and there is no network connection.</font></h2>");
        }
      }
      percentComplete = 10;
      parser = factory.newDocumentBuilder();
      parser.setErrorHandler(new HTMLErrorHandler());
      if (dtd.exists()) {
        BufferedReader br = new BufferedReader(new FileReader(file));
        StringBuffer sb = new StringBuffer("");
        String line = br.readLine();
        while (line != null) {
          sb.append(line + "\r\n");
          line = br.readLine();
        }
        String page = changeDTDToLocalCopy(sb.toString());
        parser.parse(new InputSource(new StringReader(page)));
      }
      else {
        parser.parse(file);
      }
      percentComplete = 15;
      // Parse the file and build a Document tree to represent its content
    }
    catch (org.xml.sax.SAXParseException spe) {
      // Do nothing--caught in the HTMLErrorHandler
    }
    catch (org.xml.sax.SAXException se) {
      // Do nothing--caught in the HTMLErrorHandler
    }
    catch (javax.xml.parsers.ParserConfigurationException pce) {
      // Do nothing--caught in the HTMLErrorHandler
    }
    catch (java.io.IOException e) {
      // Do nothing--caught in the HTMLErrorHandler
    }
    catch (Exception e) {
      JOptionPane.showMessageDialog(null, "An Error has occurred." + e.getMessage());
      isValidHTML = false;
      // Do nothing--caught in the HTMLErrorHandler
    }
    if (isValidHTML) {
      buffer.append("<em>No structural errors found.</em><br>");
    }
    // buffer is modified in the errorHandler (HTMLErrorHandler)
    checkCommonErrors(file);
    buffer.append("</body></html>");
    percentComplete = 100;
    return buffer.toString();
  }

  private String changeDTDToLocalCopy(String text) {
    if (text == null) {
      return null;
    }
    String remoteDTDString = "PUBLIC \\x22-//W3C//DTD XHTML 1.0 Strict//EN\\x22\\s*"
        + "\\x22http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd";
    String extLibDirectory = System.getProperty("java.ext.dirs");
    extLibDirectory = extLibDirectory.replaceAll("\\x5C", "\\\\\\\\");
    String localDTDString = "SYSTEM \"" + "file:///" + extLibDirectory + "\\\\xhtml1-strict.dtd";
    String result = text.replaceAll(remoteDTDString, localDTDString);
    return result;
  }

  private boolean isValidMailTo(String url) {
    boolean result = true;
    // First check for basic xxx@xxx
    Pattern pattern = Pattern.compile("^(.+)@(.+)$");
    Matcher matcher = pattern.matcher(url);
    if (matcher.find()) {
      String[] parts = url.split("@");
      if (parts.length != 2) {
        result = false;
      }
      else {
        // The name must not contain any of the special chars
        String specialChars = "[\\s\\(\\)><@,;:\\[\\]]+";
        Pattern namePat = Pattern.compile(specialChars);
        Matcher nameMatch = namePat.matcher(parts[0]);
        if (nameMatch.find()) {
          result = false;
        }
        else {
          // User Name and generic format passed, check address
          String ip = "^\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}$";
          String addr = "^[a-zA-Z][a-zA-Z_0-9-]*\\.([a-zA-Z]{2,3})$";
          Pattern domain = Pattern.compile(ip + "|" + addr);
          Matcher domMatch = domain.matcher(parts[1]);
          result = domMatch.find();
        }
      }
    }
    else {
      result = false;
    }
    return result;
  }

  private void checkCommonErrors(final java.io.File file) {
    BufferedReader reader = null;
    try {
      reader = new BufferedReader(new FileReader(file));
    }
    catch (FileNotFoundException e) {
      buffer.append("<h1>Couldn't open file to check for common mistakes</h1>");
      return;
    }
    catch (NullPointerException e) {
      buffer.append("<h1>Filesystem error, please save your work and restart the editor.</h1></body></html>");
      return;
    }
    String line = "";
    StringBuffer fileBuffer = new StringBuffer();
    try {
      while ((line = reader.readLine()) != null) {
        fileBuffer.append(line + "<br>");
      }
    }
    catch (IOException e) {
      buffer.append("<h1>Errors while reading your file.</h1>");
    }
    finally {
      try {
        reader.close();
      }
      catch (IOException e) {
        reader = null;
      }
    }
    Pattern pattern = Pattern.compile("href\\s*=\\s*\\x22\\s*((\\\\)|([a-zA-Z]:))");
    Matcher matcher = pattern.matcher(fileBuffer.toString());
    int counter = 0;
    while (matcher.find()) {
      counter++;
    }
    percentComplete = 18;
    if (counter > 0) {
      buffer.append("<h3>---- Link Reference Warning ----</h3>");
      buffer.append("<em>I found " + counter + " instance");
      if (counter > 1) {
        buffer.append("s");
      }
      buffer.append(" where you used either an absolute file path (like \"c:\\...\")");
      buffer.append(" or an absolute network reference (like \"\\\\usmasvdzgemini\\2008\\...\")");
      buffer.append(" These reference may work on your machine now, but as soon");
      buffer.append(" as you turn in your work or move it, they'll be broken.");
      buffer.append(" You should ALWAYS use relative references!</em><br>");
      buffer.append("<strong>----- End Warning ----</strong><br>");
    }
    boolean badLink = false;
    pattern = Pattern.compile("(href\\s*=\\s*\\x22\\s*)|(src\\s*=\\s*\\x22\\s*)");
    matcher = pattern.matcher(fileBuffer.toString());
    int linkNo = 1;
    int checksLeft = pattern.split(fileBuffer.toString()).length;
    double change = (checksLeft > (95 - percentComplete)) ? 1 : (95 - percentComplete) / checksLeft;
    while (matcher.find()) {
      percentComplete += change;
      // Subtract 1 from the matcher.end because the ^http check adds 1
      String url = fileBuffer.substring(matcher.end(), fileBuffer.indexOf("\"", matcher.end()));
      // HTTP urls can be handled with the java HttpURLConnection, all others
      // require a different mechanism
      // Ignores internal links (for now)
      if (url.indexOf("#") > 0) {
        url = url.substring(0, url.indexOf("#"));
      }
      // Because of the above line, the url will be empty if just an internal link
      // so the startsWith is currently impossible.
      if (url.startsWith("#") || (url.length() == 0)) {
        if (url.startsWith("#")) {
          Pattern anchor = Pattern.compile("id=\"" + url.substring(1) + "\"");
          Matcher match = anchor.matcher(fileBuffer);
          if (!match.find()) {
            badLink = true;
            buffer.append("<em><font color=\"#A0522D\">Warning " + (linkNo++) + "</font>: I looked everywhere, "
                + "but I can't find a tag with an attribute \"id=\"" + url.substring(1) + "\"</em><br>");
          }
        }
        // eventually handle anchors in other documents
      }
      else
        if (url.toLowerCase().startsWith("mailto:")) {
          if (!isValidMailTo(url.substring(7))) {
            badLink = true;
            buffer.append("<em><font color=\"#A0522D\">Warning " + (linkNo++) + "</font>: I don't think the person specified in \"" + url
                + "\" exists, you might want to check that address (it isn't valid).</em><br>");
          }
        }
        else
          if (url.startsWith("http")) { // Here's the HTTP URL handling
            java.net.URL fileURL = null;
            try {
              fileURL = new java.net.URL(url);
              // I am not checking links outside of usma.edu
              
              if (!((fileURL.getHost()).toLowerCase().endsWith("usma.edu"))){
              	break;
              }
              HttpURLConnection uc = (HttpURLConnection) fileURL.openConnection();
              // Default is to request the entire page, so set it to just retrieve the head
              uc.setRequestMethod("HEAD");
              uc.connect();
              int code = uc.getResponseCode();
              switch (code) {
                case HttpURLConnection.HTTP_OK:
                case HttpURLConnection.HTTP_ACCEPTED:
                case HttpURLConnection.HTTP_CREATED:
                case HttpURLConnection.HTTP_NO_CONTENT:
                  break;
                case HttpURLConnection.HTTP_FORBIDDEN:
                  // Couldn't just retrieve header, try full GET
                  HttpURLConnection getUC = (HttpURLConnection) fileURL.openConnection();
                  getUC.setRequestMethod("GET");
                  getUC.connect();
                  int secondCode = getUC.getResponseCode();
                  if (secondCode == HttpURLConnection.HTTP_FORBIDDEN) {
                    badLink = true;
                    buffer.append("<em><font color=\"#A0522D\">Link Access Error (HTTP 403)</font>: " + " URL " + url
                        + ". The site does not allow access (HTTP403 => Forbidden).</em><br>");
                  }
                  break;
                default:
                  badLink = true;
                  buffer.append("<em><font color=\"#A0522D\">Warning " + (linkNo++) + ": Broken Link</font>(HTTP " + code + "): URL " + url
                      + "</em><br>");
              }
            }
            catch (java.net.MalformedURLException me) {
              badLink = true;
              buffer.append("<em><font color=\"#A0522D\">Bad URL Warning</font>: " + url + "</em>");
            }
            catch (IOException e) {
              badLink = true;
              buffer.append("<em><font color=\"#A0522D\">Warning " + (linkNo++) + ": Broken Link</font>: URL " + url + "</em><br>");
            }
          }
          else {
            String newURL = url;
            if (!url.startsWith("file")) {
              String userDir = "";
              userDir = file.getPath();
              Pattern absolute = Pattern.compile("^(\\\\)|^([a-zA-Z]:)");
              Matcher m = absolute.matcher(url);
              if (m.find()) {
                if (newURL.startsWith("\\")) {
                  newURL = "file:\\\\" + url;
                }
                else {
                  newURL = "file:///" + url;
                }
              }
              else {
                newURL = "file:\\\\\\" + userDir.substring(0, userDir.lastIndexOf('\\')) + "\\" + url;
              }
            }
            try {
              URL u = new URL(newURL);
              u.getContent();
            }
            catch (MalformedURLException e) {
              // If the file isn't found, gives an exception
              badLink = true;
              buffer.append("<em><font color=\"#A0522D\">Warning " + (linkNo++) + "</font>: File \"" + url
                  + "\" not found. Where'd you put it?</em><br>");
            }
            catch (IOException ioe) {
              badLink = true;
              buffer.append("<em><font color=\"#A0522D\">Warning " + (linkNo++) + "</font>: File \"" + url
                  + "\" not found. Where'd you put it?</em><br>");
            }
          }
    }
    if (!badLink) {
      buffer.append("<em>Links ok.</em>");
    }
  }

  class HTMLErrorHandler implements org.xml.sax.ErrorHandler {
    //    class HTMLErrorHandler extends DefaultHandler {
    public void error(org.xml.sax.SAXParseException sAXParseException) {
      int line = sAXParseException.getLineNumber();
      String error = sAXParseException.getMessage();
      if (error.indexOf("marquee") != -1) {
        // There are two errors generated by the marquee, only worry about 1 of them
        if (error.indexOf("does not allow") == -1) {
          buffer
              .append("<font color=\"#A0522D\">Warning: You are using the 'marquee' tag, "
                  + "which is technically not valid in XHTML.<br>"
                  + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;It can be easily misused and very distracting, so make sure it is really necessary in your web page."
                  + "</font><br><br>");
        }
      }
      else {
        // if a PCDATA style error, clean it up a bit
        if (error.indexOf("(#PCDATA") >= 0) {
          String endStuff = error.substring(error.indexOf("(#PCDATA") + 8);
          error = error.substring(0, error.indexOf("(#PCDATA") - 1);
          error += endStuff;
        }
        buffer.append("<b><font color=\"red\">Error: Line: " + line + ": " + error + "</font></b><br>");
        isValidHTML = false;
      }
    }

    public void fatalError(org.xml.sax.SAXParseException sAXParseException) {
      int line = sAXParseException.getLineNumber();
      buffer.append("<b><font color=\"red\">Fatal Error in line " + line + ": " + sAXParseException.getMessage() + "</font></b><br>");
      isValidHTML = false;
    }

    public void warning(org.xml.sax.SAXParseException sAXParseException) {
      int line = sAXParseException.getLineNumber();
      String warning = sAXParseException.getMessage();
      if (warning.indexOf("Using original") < 0) {
        buffer.append("<font color=\"#A0522D\"Warning: Line: " + line + ": " + warning + "</font><br>");
      }
    }
  }
}