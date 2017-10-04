package eecs.editor.environment;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.Map.Entry;
import eecs.editor.environment.Messages;
import eecs.util.JavaWebStartManager;

public class Environment {
  static protected HashMap userPrefs = new HashMap();
  static protected String userPrefsPath = "";
  static protected String eclipseLibDirectory = Messages.getString("eclipse.workspace.lib.dir");
  static protected String jagoPath = getEclipseBinDirectory();
  static protected String instructorPath = getEclipseBinDirectory();
  static protected String wrapperPath = getEclipseBinDirectory();
  static protected String mediaPath = getEclipseBinDirectory();
  
  static {
    userPrefsPath = getUsersConfigPath() + getEditorPrefsFilename();
    if(!doesUserPrefsFileExist()) {
      generateInitialUserPrefsFile();
    }
    readUserPrefs();
    
    String webstartCache = (String) userPrefs.get("java.webstart.cache.path");
    if (webstartCache != null && (webstartCache.indexOf("http/") == -1) && JavaWebStartManager.hasBeenStarted()) {
      JavaWebStartManager.determineCachePath();
      userPrefs.put("java.webstart.cache.path", JavaWebStartManager.getCachePath());
      writeUserPrefs();
    }
   
    webstartCache = (String) userPrefs.get("java.webstart.cache.path");
   
    JavaWebStartManager.setCachePath(webstartCache);
   
    String editorURL = (String) userPrefs.get("java.webstart.editor.url");
    if (editorURL != null) {
    	if (JavaWebStartManager.hasBeenStarted()) {
    		if (!editorURL.equals(JavaWebStartManager.getURL()) || (editorURL.equals(""))) {
				userPrefs.put("java.webstart.editor.url", JavaWebStartManager.getURL());
				writeUserPrefs();
    	    }
        }
    }
    displayUserPrefs();
    
    if (JavaWebStartManager.hasBeenStarted()) {
      eclipseLibDirectory = "";
      String javaWebStartCachePath = JavaWebStartManager.getCachePath();
      jagoPath = javaWebStartCachePath + "RM" + Messages.getString("editor.jago.archive.name");
      instructorPath = javaWebStartCachePath + "RM" + Messages.getString("editor.instructor.archive.name");
      wrapperPath = javaWebStartCachePath + "RM" + Messages.getString("editor.wrapper.archive.name");
      mediaPath = javaWebStartCachePath + "RM" + Messages.getString("editor.media.archive.name");
    }
  }
  
  private Environment() {
    // prevent instantiation
  }

  static protected boolean doesUserPrefsFileExist() {
    File file = new File(userPrefsPath);
    return file.exists();
  }
  
  static protected void generateInitialUserPrefsFile() {
    try {
      File file = new File(getUsersConfigPath());
      file.mkdirs();
      PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(userPrefsPath)));
      out.write("# IT105 User Preferences.\r\n");
      out.write("# use slashes (i.e., '/') and not backslashes (i.e., '\\') in paths.\r\n\r\n");
      JavaWebStartManager.determineCachePath();
      out.write("java.webstart.editor.url="+JavaWebStartManager.getURL()+"\r\n\r\n");
      out.write("java.webstart.cache.path="+JavaWebStartManager.getCachePath()+"\r\n\r\n");
      out.write("editor.format.before.run=true\r\n");
      out.close();
    }
    catch (IOException e) {
      e.printStackTrace();
    }
  }
  
  static public String getUserPref(String key) {
    return (String) userPrefs.get(key);
  }
  
  static public void setUserPref(String key, String value) {
    userPrefs.put(key, value);
    writeUserPrefs();
  }

  static public void displayUserPrefs() {
    System.out.println("User preferences found in file: " + userPrefsPath);
    Set userPrefsSet = userPrefs.entrySet();
    for (Iterator i=userPrefsSet.iterator();i.hasNext();) {
      Entry entry = (Entry) i.next();
      System.out.println("   " + entry.getKey() + "=" + entry.getValue());
    }
  }
  
  static protected void readUserPrefs() {
    File file = new File(userPrefsPath);
    if (!file.exists()) {
      return;
    }
    try {
      BufferedReader in = new BufferedReader(new FileReader(userPrefsPath));
      String line = in.readLine();
      while (line != null) {
        parsePrefs(line);
        line = in.readLine();
      }
      in.close();
    }
    catch (IOException e) {
      e.printStackTrace();
    }
  }
  
  static protected void parsePrefs(String line) {
    int equalsIndex = line.indexOf("=");
    if (equalsIndex > 0) {
      String key = line.substring(0,equalsIndex);
      String value = line.substring(equalsIndex+1);
      userPrefs.put(key, value);
    }
  }
  
  static protected void writeUserPrefs() {
    try {
      PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(userPrefsPath)));
      out.write("# IT105 User Preferences.\r\n");
      out.write("# use slashes (i.e., '/') and not backslashes (i.e., '\\') in paths.\r\n");
      Set userPrefsSet = userPrefs.entrySet();
      for (Iterator i=userPrefsSet.iterator();i.hasNext();) {
        Entry entry = (Entry) i.next();
        out.write(entry.getKey()+"="+entry.getValue()+"\r\n");
      }
      out.close();
    }
    catch (IOException e) {
      e.printStackTrace();
    }
  }
  
  static protected String getEclipseBinDirectory() {
    //return Messages.getString("eclipse.workspace.bin.dir");
    return "c:/projects/EditorFull/bin";
  }

  static public String getUsersConfigPath() {
    return getUsersHomeDirectory() + "/" + getUsersConfigDir();
  }

  static public String getUsersConfigDir() {
    return Messages.getString("editor.user.dir");
  }

  static public String getUsersHomeDirectory() {
    return System.getProperty("user.home").replace('\\', '/');
  }

  static public String getEditorPrefsFilename() {
    return Messages.getString("editor.prefs.name");
  }
  
  static public String getEclipseLibDirectory() {
    return eclipseLibDirectory;
  }

  static public String getJagoPath() {
    return jagoPath;
  }

  static public String getInstructorPath() {
    return instructorPath;
  }

  static public String getWrapperPath() {
    return wrapperPath;
  }

  static public String getMediaPath() {
    return mediaPath;
  }
}