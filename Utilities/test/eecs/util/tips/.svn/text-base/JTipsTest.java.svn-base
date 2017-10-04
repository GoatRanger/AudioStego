/*
 * Created on Mar 31, 2004
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package eecs.util.tips;

import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * @author DK8685
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public class JTipsTest extends TestCase {
  public static void main(String[] args) {
    junit.textui.TestRunner.run(JTipsTest.suite());
  }

  /*
   * Class to test for void JTips(String, String). Ensures that the JTips
   * is created with the appropriate backing store (Registry, in Windows).
   */
  public void testJTipsStringString() {
    JTips tips = new JTips("test.tips", null);
    // Preferences should be stored in the eecs.util.tips package
    Preferences prefs = Preferences.userRoot().node(this.getClass().getPackage().toString().substring(8).replaceAll("\\.","/"));
    assertNotNull(prefs);
    assertTrue(prefs.getInt("tips.index",-1) >= 0);
    
    tips = new JTips("test.tips","eecs/test");
    prefs = Preferences.userRoot().node("eecs/test");
    assertNotNull(prefs);
    assertTrue(prefs.getInt("tips.index",-1) >= 0);
    try {
      prefs.removeNode();
      prefs.flush();
    } catch (BackingStoreException be) {}
    
  }
  
  public static Test suite() {
    TestSuite suite = new TestSuite(JTipsTest.class);
    
    return suite;
}
}
