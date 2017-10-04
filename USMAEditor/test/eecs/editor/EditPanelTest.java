/*
 * Created on Jun 2, 2004
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package eecs.editor;

import eecs.editor.language.LanguageKit;
import eecs.editor.language.LanguageKitFactory;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * @author DK8685
 * 
 * To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Generation - Code and Comments
 */
public class EditPanelTest extends TestCase {
	EditPanel panel;
	public static void main(String[] args) {
		junit.textui.TestRunner.run(EditPanelTest.suite());
	}
	public final void setUp() {
		panel = new EditPanel(LanguageKitFactory
				.getLanguageKit(LanguageKit.JAVA));
	}
    
	public final void testGetAsXHTML() {
		String text = "//comment\r\nclass Program\npublic \"string\"";
		panel.setText(text);
		StringBuffer result = panel.getAsXHTML();
		String correct = "<pre><span class=\"codecomment\">//comment</span>\n<span class=\"codekeyword\">class</span> Program\n<span class=\"codekeyword\">public</span> <span class=\"codeliteral\">&quot;string&quot;</span></pre>\n";
		assertEquals("Simple file", correct, result.toString());
	}
    
    public final void testGetAsXHTMLTable() {
        String text = "//comment\nclass Program\npublic \"string\"";
        panel.setText(text);
        StringBuffer result = panel.getAsXHTMLTable();
        String correct = "<table border=\"0\">\n  <tr>\n   <td style=\"vertical-align: top;\"><pre>1\n2\n3\n</pre>\n   </td>\n   <td style=\"vertical-align: top;\">\n<pre><span class=\"codecomment\">//comment</span>\n<span class=\"codekeyword\">class</span> Program\n<span class=\"codekeyword\">public</span> <span class=\"codeliteral\">&quot;string&quot;</span></pre>\n   </td>\n  </tr>\n </table>\n";
        assertEquals("Simple file", correct, result.toString());
    }

	/** Creates a <code>Test</code> suite of tests. */
	public static Test suite() {
		TestSuite suite = new TestSuite(EditPanelTest.class);

		return suite;
	}
}