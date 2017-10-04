package eecs.editor;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;

public class CollectErrors {
  static private AbstractMap map = new HashMap();
  static private String[] errorsAndCounts = null;

  static public void sendErrorsToServlet() {
    transferMapToArray();
    String url = "http://usmasvddgosling:8080/errorCollector/collectServlet";
    HttpClient client = new HttpClient();
    HttpMethod method = new GetMethod(url);
    StringBuffer sb = new StringBuffer("");
    for (int i = 0; i < errorsAndCounts.length; ++i) {
      sb.append(errorsAndCounts[i]);
    }
    NameValuePair pair = new NameValuePair("info", sb.toString());
    method.setQueryString(new NameValuePair[] { pair });
    try {
      client.executeMethod(method);
      method.releaseConnection();
    }
    catch (HttpException e) {
      e.printStackTrace();
    }
    catch (UnknownHostException e) {
      e.printStackTrace();
    }
    catch (IOException e) {
      e.printStackTrace();
    }
  }

  static private void transferMapToArray() {
    Set keyValuePairs = map.entrySet();
    errorsAndCounts = new String[keyValuePairs.size()];
    int index = 0;
    Iterator it = keyValuePairs.iterator();
    while (it.hasNext()) {
      Map.Entry me = (Map.Entry) it.next();
      errorsAndCounts[index++] = me.getKey() + "~" + me.getValue() + "^";
    }
  }

  static public void addErrorsToTotal(String[] errors) {
    for (int i = 0; i < errors.length; ++i) {
      String error = errors[i];
      try {
        if (error.indexOf(":") != -1) {
          String message = error.substring(error.indexOf(":") + 1, error.length());
          if (message != null && message.length() > 0) {
            if (!Character.isDigit(message.charAt(0))) {
              message = message.substring(message.indexOf(":") + 1, message.length());
            }
            message = message.substring(message.indexOf(":") + 2, message.length());
            if (map.containsKey(message)) {
              Integer count = (Integer) map.get(message);
              count = new Integer(count.intValue() + 1);
              map.remove(message);
              map.put(message, count);
            }
            else {
              map.put(message, new Integer(1));
            }
          }
        }
      }
      catch (RuntimeException e) {
        System.out.println("RuntimeException thrown in eecs.editor.CollectErrors.addErrorsToTotal()");
      }
    }
  }
}