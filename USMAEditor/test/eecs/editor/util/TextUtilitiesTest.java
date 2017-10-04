/*
 * Created on Jun 2, 2004
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package eecs.editor.util;

import javax.swing.text.Segment;

import jedit.JavaTokenMarker;
import jedit.Token;
import jedit.TokenMarker;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Tests for the TextUtilities class.
 * @author Karl A. Gossett
 * 
 */
public class TextUtilitiesTest extends TestCase {

	public static void main(String[] args) {
		junit.textui.TestRunner.run(TextUtilitiesTest.suite());
	}

	/** Verifies that the XHTML formatter for literals formats correctly. */
	public final void testGetSyntaxLineLiteral() {
		TokenMarker tm = new JavaTokenMarker();
		tm.insertLines(0, 1);
		String orig = "\"quoted\"";
		String correct = "<span class=\"codeliteral\">&quot;quoted&quot;</span>\n";
		Segment line = new Segment(orig.toCharArray(), 0, orig.length());
		Token tokens = tm.markTokens(line, 0);
		String result = TextUtilities.getSyntaxLine(line, tokens);
		assertEquals("single literal", correct, result);

		tm = new JavaTokenMarker();
		tm.insertLines(0, 1);
		orig = "\"quote1\" word \"quote2\"";
		correct = "<span class=\"codeliteral\">&quot;quote1&quot;</span> word <span class=\"codeliteral\">&quot;quote2&quot;</span>\n";
		line = new Segment(orig.toCharArray(), 0, orig.length());
		tokens = tm.markTokens(line, 0);
		result = TextUtilities.getSyntaxLine(line, tokens);
		assertEquals("block comment", correct, result);
	}

	/** Verifies that the XHTML formatter for comments formats correctly. */
	public final void testGetSyntaxLineComment() {
		TokenMarker tm = new JavaTokenMarker();
		tm.insertLines(0, 1);
		String orig = "// comment";
		String correct = "<span class=\"codecomment\">// comment</span>\n";
		Segment line = new Segment(orig.toCharArray(), 0, orig.length());
		Token tokens = tm.markTokens(line, 0);
		String result = TextUtilities.getSyntaxLine(line, tokens);
		assertEquals("single line comment", correct, result);

		tm = new JavaTokenMarker();
		tm.insertLines(0, 1);
		orig = "/* start block comment";
		correct = "<span class=\"codecomment\">/* start block comment</span>\n";
		line = new Segment(orig.toCharArray(), 0, orig.length());
		tokens = tm.markTokens(line, 0);
		result = TextUtilities.getSyntaxLine(line, tokens);
		assertEquals("block comment", correct, result);

		tm = new JavaTokenMarker();
		tm.insertLines(0, 1);
		orig = "/* block with public keyword";
		correct = "<span class=\"codecomment\">/* block with public keyword</span>\n";
		line = new Segment(orig.toCharArray(), 0, orig.length());
		tokens = tm.markTokens(line, 0);
		result = TextUtilities.getSyntaxLine(line, tokens);
		assertEquals("comment with embedded keyword", correct, result);
	}

	/**
	 * Verifies that the XHTML formatter formats correctly for multiple classes
	 * on a line.
	 */
	public final void testGetSyntaxLineMultiple() {
		TokenMarker tm = new JavaTokenMarker();
		tm.insertLines(0, 1);
		String orig = "\"quoted\" with keyword public";
		String correct = "<span class=\"codeliteral\">&quot;quoted&quot;</span> with keyword <span class=\"codekeyword\">public</span>\n";
		Segment line = new Segment(orig.toCharArray(), 0, orig.length());
		Token tokens = tm.markTokens(line, 0);
		String result = TextUtilities.getSyntaxLine(line, tokens);
		assertEquals("literal and keyword", correct, result);

		tm = new JavaTokenMarker();
		tm.insertLines(0, 1);
		orig = "public keyword // eol comment";
		correct = "<span class=\"codekeyword\">public</span> keyword <span class=\"codecomment\">// eol comment</span>\n";
		line = new Segment(orig.toCharArray(), 0, orig.length());
		tokens = tm.markTokens(line, 0);
		result = TextUtilities.getSyntaxLine(line, tokens);
		assertEquals("keyword and eol comment", correct, result);
	}

	/** Verifies that the XHTML formatter for keywords formats correctly. */
	public final void testGetSyntaxLineKeywords() {
		String orig = "public words";
		String correct = "<span class=\"codekeyword\">public</span> words\n";
		Segment line = new Segment(orig.toCharArray(), 0, orig.length());
		TokenMarker tm = new JavaTokenMarker();
		tm.insertLines(0, 1);
		Token tokens = tm.markTokens(line, 0);
		String result = TextUtilities.getSyntaxLine(line, tokens);
		assertEquals("Keyword spanning", correct, result);

		tm = new JavaTokenMarker();
		tm.insertLines(0, 1);
		orig = "public words public";
		correct = "<span class=\"codekeyword\">public</span> words <span class=\"codekeyword\">public</span>\n";
		line = new Segment(orig.toCharArray(), 0, orig.length());
		tokens = tm.markTokens(line, 0);
		result = TextUtilities.getSyntaxLine(line, tokens);
		assertEquals("multiple keyword spanning", correct, result);
	}

	/** Creates a <code>Test</code> suite of tests. */
	public static Test suite() {
		TestSuite suite = new TestSuite(TextUtilitiesTest.class);

		return suite;
	}

}