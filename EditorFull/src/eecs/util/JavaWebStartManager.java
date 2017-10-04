package eecs.util;

import java.net.URL;
import javax.jnlp.BasicService;
import javax.jnlp.ServiceManager;
import javax.jnlp.UnavailableServiceException;
import eecs.editor.environment.Environment;

public class JavaWebStartManager {
  static protected String webAppURL = "";
  static protected String cachePath = "";
  static protected BasicService bs = null;
  
  static {
    try {
      bs = (BasicService) ServiceManager.lookup("javax.jnlp.BasicService");
      URL url = bs.getCodeBase();
      webAppURL = url.toString().substring(0, url.toString().length() - 1);
    }
    catch (UnavailableServiceException e) {
      webAppURL = "";
    }
  }

  private JavaWebStartManager() {
    // Prevent instantiation
  }
  
  static public String getURL() {
  	if (webAppURL==null){
  		webAppURL="";
  	}
    return webAppURL;
  }

  static public boolean hasBeenStarted() {
  	if (getURL()==null){
  		return false;
  	}
    return !getURL().equals("");
  }
  
  static public String getCachePath() {
    return cachePath;
  }

  static public void setCachePath(String path) {
    cachePath = path;
  }
  
  static public void determineCachePath() {
    int lengthOfHttpColonSlashSlash = 7;
    if (webAppURL.length() < lengthOfHttpColonSlashSlash + 1) {
      cachePath = Environment.getEclipseLibDirectory();
      return;
    }
    String url = webAppURL.substring(lengthOfHttpColonSlashSlash);
    int slashIndex = url.indexOf("/");
    String domain = url.substring(0, slashIndex);
    String path = url.substring(slashIndex);
    cachePath = System.getProperty("user.home").replace('\\', '/')
      + "/Application Data/Sun/Java/Deployment/javaws/cache/http/D" + domain
      + "/P80" 
      + path.replaceAll("/", "/DM") + "/";
    cachePath=cachePath.replaceAll("%20","&p20");
  }
}