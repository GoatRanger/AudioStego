/*
 * OutputApplet.java
 *
 * Created on December 16, 2002, 9:55 PM
 */
package eecs.applet;

import javax.swing.text.StyleConstants;


/**
 * DOCUMENT ME!
 *
 * @author Karl Gossett
 */
public class OutputApplet extends javax.swing.JApplet
    implements Runnable
{
    /** The output window document object */
    protected javax.swing.text.Document outputDocument = null;
    private javax.swing.JScrollPane scrollPane;
    private javax.swing.JTextPane outputArea;

    /**
     * Creates a new instance of OutputApplet
     */
    public OutputApplet()
    {
    }

    /**
     * Clears all text from the output window.
     */
    public void clearOutput()
    {
        try
        {
            outputDocument.remove(0, outputDocument.getLength());
        }
        catch (javax.swing.text.BadLocationException e)
        {
            System.err.println("Could not clear the output area.");
        }
    }

    public void init()
    {
        initComponents();

        outputDocument = outputArea.getDocument();
        initTextStyles();
        getContentPane().add(scrollPane);
    }

    /**
     * Prints a message in the text window using the normal text
     * style.
     *
     * @param msg The text message to display.
     */
    public void print(String msg)
    {
        print(msg, "normal");
    }

    /**
     * Prints a message using a "code" style of text (smaller,
     * sansserif). The message is terminated with a newline marker.
     *
     * @param code The line of code or text message to display.
     */
    public void printCode(String code)
    {
        print(code + "\n", "system");
    }

    /**
     * Prints an error message in the text window using the error
     * style. The message will be prefixed with a newline and
     * "ERROR:", and terminated with a newline.
     *
     * @param error The contents of the error message.
     */
    public void printError(String error)
    {
        print("\nERROR: " + error + "\n", "error");
    }

    /**
     * Prints a message with a terminal newline marker in the text
     * window using the normal text style.
     *
     * @param msg The text message to display.
     */
    public void printLine(String msg)
    {
        print(msg + "\n");
    }

    public void run()
    {
    }

    protected void initComponents()
    {
        outputArea = new javax.swing.JTextPane();
        scrollPane = new javax.swing.JScrollPane();
        scrollPane.setVerticalScrollBarPolicy(javax.swing.JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setPreferredSize(new java.awt.Dimension(300, 300));
        scrollPane.setMinimumSize(new java.awt.Dimension(250, 300));
        scrollPane.setDoubleBuffered(true);
        scrollPane.setAutoscrolls(true);
        scrollPane.setViewportView(outputArea);
    }

    /**
     * Creates the <CODE>Style</CODE>s used for the different display
     * fonts in the output window.
     */
    protected void initTextStyles()
    {
        //Initialize some styles.
        javax.swing.text.Style def =
            javax.swing.text.StyleContext.getDefaultStyleContext()
                                         .getStyle(javax.swing.text.StyleContext.DEFAULT_STYLE);
        javax.swing.text.Style regular =
            outputArea.addStyle("normal", def);
        StyleConstants.setFontFamily(def, "SansSerif");

        javax.swing.text.Style s =
            outputArea.addStyle("italic", regular);
        StyleConstants.setItalic(s, true);
        s = outputArea.addStyle("bold", regular);
        StyleConstants.setBold(s, true);
        s = outputArea.addStyle("system", def);
        StyleConstants.setFontSize(s, 12);
        StyleConstants.setItalic(s, false);
        StyleConstants.setFontFamily(s, "Serif");
        s = outputArea.addStyle("error", regular);
        StyleConstants.setFontSize(s, 12);
        StyleConstants.setForeground(s, java.awt.Color.red);
    }

    /**
     * Prints a message in the text window. Uses the specified style.
     *
     * @param msg The <CODE>String</CODE> to display.
     * @param style The Style of the message.
     */
    private void print(String msg, String style)
    {
        try
        {
            outputDocument.insertString(outputDocument.getLength(),
                msg, outputArea.getStyle(style));
            outputArea.setCaretPosition(outputDocument.getLength() -
                1);
        }
        catch (javax.swing.text.BadLocationException e)
        {
            System.err.println(
                "Unable to insert text into output area");
        }
    }
}
