/*
 * HTMLChecker.java
 *
 * Created on May 30, 2003, 3:24 PM
 */
package eecs.utility;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * The Checker class for checking HTML documents.
 *
 * @author Karl A. Gossett
 */
public class HTMLChecker {
    /** DOCUMENT ME! */
    private StringBuffer buffer;

    private String nicCheckURL = "http://www.w3.org";
    /**
     * Creates a new instance of HTMLChecker
     */
    public HTMLChecker() {
    }

    /**
     * Validate and parse the specified file (if the DOCTYPE isn't specified,
     * document cannot be validated).  Returns a list of errors.
     *
     * @param file The file to be checked.
     *
     * @return The list of errors, as a <CODE>String</CODE>.
     */
    public String check(final java.io.File file) {
        buffer = new StringBuffer();
        buffer.append("<h3>Errors</h3>");


        javax.xml.parsers.DocumentBuilderFactory factory = javax.xml.parsers.DocumentBuilderFactory
            .newInstance();

        //        SAXParserFactory factory = SAXParserFactory.newInstance();
        //        SAXParser parser = null;
        javax.xml.parsers.DocumentBuilder parser = null;
        org.w3c.dom.Document document = null;

        try {
            // Now use the factory to create a DOM parser 
            // (a.k.a. a DocumentBuilder)
            // TODO: If a network isn't available, use a local doctype when possible
            
            eecs.utility.InterfaceCheck nic = new eecs.utility.InterfaceCheck();
            // Check for external and url, just in case wireless reports available when not logged in
            if (nic.isAnyExternalAvailable() && nic.isURLAvailable(nicCheckURL)) {
				factory.setValidating(true);
            } else {
            	factory.setValidating(false);
            	buffer.append("<h4 class=\"warning\">Couldn't validate document; network is not available.</h4>");
             }

            //            parser = factory.newSAXParser();
            parser = factory.newDocumentBuilder();
            parser.setErrorHandler(new HTMLErrorHandler());

            //            parser.parse(file, new HTMLErrorHandler());
            buffer.append("<h4>XHTML Error Checks</h4>");
            document = parser.parse(file);

            // Parse the file and build a Document tree to represent its content
        } catch (org.xml.sax.SAXParseException spe) {
            // Do nothing--caught in the HTMLErrorHandler
        } catch (org.xml.sax.SAXException se) {
            // Do nothing--caught in the HTMLErrorHandler
        } catch (javax.xml.parsers.ParserConfigurationException pce) {
            // Do nothing--caught in the HTMLErrorHandler
        } catch (java.io.IOException e) {
            // Do nothing--caught in the HTMLErrorHandler
        } catch (Exception e) {
            // Do nothing--caught in the HTMLErrorHandler
        }
		 // buffer is modified in the errorHandler (HTMLErrorHandler)
		checkCommonErrors(file);
        return buffer.toString();
    }

    /**
     * DOCUMENT ME!
     *
     * @param url DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    private boolean isValidMailTo(String url) {
        boolean result = true;

        // First check for basic xxx@xxx
        Pattern pattern = Pattern.compile("^(.+)@(.+)$");
        Matcher matcher = pattern.matcher(url);

        if (matcher.find()) {
            String[] parts = url.split("@");

            if (parts.length != 2) {
                result = false;
            } else {
                // The name must not contain any of the special chars
                String specialChars = "[\\s\\(\\)><@,;:\\[\\]]+";
                Pattern namePat = Pattern.compile(specialChars);
                Matcher nameMatch = namePat.matcher(parts[0]);

                if (nameMatch.find()) {
                    result = false;
                } else {
                    // User Name and generic format passed, check address
                    String ip = "^\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}$";
                    String addr = "^[a-zA-Z][a-zA-Z_0-9-]*\\.([a-zA-Z]{2,3})$";
                    Pattern domain = Pattern.compile(ip + "|" + addr);
                    Matcher domMatch = domain.matcher(parts[1]);
                    result = domMatch.find();
                }
            }
        } else {
            result = false;
        }

        return result;
    }

    /**
     * DOCUMENT ME!
     *
     * @param file DOCUMENT ME!
     */
    private void checkCommonErrors(final java.io.File file) {
        BufferedReader reader = null;

        try {
            reader = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException e) {
            buffer.append("<h4 class=\"warning\">Couldn't open file to check for common mistakes.</h4>");

            return;
        } catch (NullPointerException e) {
        	buffer.append("<h4 class=\"warning\">Filesystem error, please save your work and restart the editor.</h4>");
        	return;
        }

        String line = "";
        StringBuffer fileBuffer = new StringBuffer();

        try {
            while ((line = reader.readLine()) != null) {
                fileBuffer.append(line + "\r\n");
            }
        } catch (IOException e) {
            buffer.append("<h4 class=\"warning\">Errors while reading your file.</h4>");
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                reader = null;
            }
        }

        Pattern pattern = Pattern.compile(
                "href\\s*=\\s*\\x22\\s*((\\\\)|([a-zA-Z]:))");
        Matcher matcher = pattern.matcher(fileBuffer.toString());
        int counter = 0;

        while (matcher.find()) {
            counter++;
        }

        if (counter > 0) {
            buffer.append("<h4>Link Reference Warning</h4>");
            buffer.append("<p>Found " + counter + " instance");

            if (counter > 1) {
                buffer.append("s");
            }

            buffer.append(" of an absolute file reference.</p>");
        }

        buffer.append("<h4>Links</h4><ul>");

        boolean badLink = false;
        pattern = Pattern.compile(
                "(href\\s*=\\s*\\x22\\s*)|(src\\s*=\\s*\\x22\\s*)");
        matcher = pattern.matcher(fileBuffer.toString());

        int linkNo = 1;
        boolean firstMessage = true;

        while (matcher.find()) {
            // Subtract 1 from the matcher.end because the ^http check adds 1 
            String url = fileBuffer.substring(matcher.end(),
                    fileBuffer.indexOf("\"", matcher.end()));

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
                    Pattern anchor = Pattern.compile("id=\""
                            + url.substring(1) + "\"");
                    Matcher match = anchor.matcher(fileBuffer);

                    if (!match.find()) {
                        badLink = true;
                        linkNo++;
                        buffer.append("<li>Link Error: " +
                           ": Internal href invalid for id=\""
                            + url.substring(1) + "\"</li>");
                    }
                }

                // eventually handle anchors in other documents
            } else if (url.toLowerCase().startsWith("mailto:")) {
                if (!isValidMailTo(url.substring(7))) {
                    badLink = true;
                    buffer.append("<li>Link Error: " + 
                        ": Invalid mailto: \"" + url
                        + "\" exists.</li>");
                }
            } else if (url.startsWith("http")) { // Here's the HTTP URL handling

                java.net.URL fileURL = null;

                try {
                    fileURL = new java.net.URL(url);

                    HttpURLConnection uc = (HttpURLConnection) fileURL
                        .openConnection();
                    int code = uc.getResponseCode();

                    switch (code) {
                        case HttpURLConnection.HTTP_OK:
                        case HttpURLConnection.HTTP_ACCEPTED:
                        case HttpURLConnection.HTTP_CREATED:
                        case HttpURLConnection.HTTP_NO_CONTENT:
                            break;

                        default:
                            badLink = true;
                            buffer.append("<li>Link Error: " +
                              " Broken Link, URL " + url + "</li>");
                    }
                } catch (java.net.MalformedURLException me) {
                    buffer.append("<li>Link Error: Bad URL Warning: " + url + "</li>");
                } catch (IOException e) {
                    badLink = true;
                    buffer.append("<li>Link Error: Warning " +
                        ": Broken Link, URL " + url + "</li>");
                }
            } else {
                String newURL = url;

                if (!url.startsWith("file")) {
                    String userDir = "";
                    userDir = file.getPath();

                    Pattern absolute = Pattern.compile("^(\\\\)|^([a-zA-z]:)");
                    Matcher m = absolute.matcher(url);

                    if (m.find()) {
                        if (newURL.startsWith("\\")) {
                            newURL = "file:\\\\" + url;
                        } else {
                            newURL = "file:///" + url;
                        }
                    } else {
                        newURL = "file:\\\\\\"
                            + userDir.substring(0, userDir.lastIndexOf('\\'))
                            + "\\" + url;
                    }
                }

                try {
                    URL u = new URL(newURL);
                    u.getContent();
                } catch (MalformedURLException e) {
                    // If the file isn't found, gives an exception
                    badLink = true;
                    linkNo++;
                    buffer.append("<li>Link Error : File \"" + url
                        + "\" not found.</li>");
                } catch (IOException ioe) {
                    badLink = true;
                    linkNo++;
                    buffer.append("<li>Link Error: File \"" + url
                        + "\" not found.</li>");
                }
            }

        }
        buffer.append("\n</ul>\n");
        if (!badLink) {
            buffer.append("<p>Links ok.</p>");
        }        
    }

    class HTMLErrorHandler implements org.xml.sax.ErrorHandler {
        //    class HTMLErrorHandler extends DefaultHandler {    
        public void error(org.xml.sax.SAXParseException sAXParseException)
            throws org.xml.sax.SAXException {
            int line = sAXParseException.getLineNumber();
            String error = sAXParseException.getMessage();

            // if a PCDATA style error, clean it up a bit
            if (error.indexOf("(#PCDATA") >= 0) {
            	error = "Invalid nesting of tags: " + error.substring(0, error.indexOf("(#PCDATA") - 1);
//                String endStuff = error.substring(error.indexOf("(#PCDATA")
//                        + 8);
//                error = error.substring(0, error.indexOf("(#PCDATA") - 1);
//                error += (" must be one of " + endStuff);
            }

            buffer.append("<li>XHTML-Error: Line: " + line + ": " + error + "</li>");
        }

        public void fatalError(org.xml.sax.SAXParseException sAXParseException)
            throws org.xml.sax.SAXException {
            int line = sAXParseException.getLineNumber();
            buffer.append("<li>XHTML-Error: Fatal Error in line " + line + ": "
                + sAXParseException.getMessage() + "</li>");
        }

        public void warning(org.xml.sax.SAXParseException sAXParseException)
            throws org.xml.sax.SAXException {
            int line = sAXParseException.getLineNumber();
            String warning = sAXParseException.getMessage();

            if (warning.indexOf("Using original") < 0) {
                buffer.append("<li>XHTML-Warning: Line: " + line + ": " + warning + "</li>");
            }
        }
    }
}
