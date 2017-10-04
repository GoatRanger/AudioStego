/*
 * Created on Jan 30, 2004
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package eecs.editor.language;

/**
 * @author DK8685
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public class LanguageKitFactory {
	
	private LanguageKitFactory(){
		
	}
	
	/** Returns a LanguageKit for the specified language.
	 * If no LanguageKit exists for the specified language,
	 * returns <code>null</code>.
	 * 
	 * @param type  The language type string
	 * 
	 * @return a <code>LanguageKit</code> for the language
	 */
	public static LanguageKit getLanguageKit(String type) {
		LanguageKit kit = new DefaultLanguageKit();
		if (type.equals(LanguageKit.CSS)) {
			kit = new CSSKit();
		} else if (type.equals(LanguageKit.HTML)) {
			kit = new HTMLKit();
		} else if (type.equals(LanguageKit.JAVA)) {
			kit = new JavaKit();
		} else if (type.equals(LanguageKit.TEXT)){
			kit = new DefaultLanguageKit();
		} else if (type.equals(LanguageKit.HUDSON)) {
			kit = new DefaultLanguageKit();
		}
		return kit;
	}
}
