package eecs.util;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.Enumeration;

public class InterfaceCheck {
  // Check to see if any network interfaces are available, active and not local.
  public boolean isAnyExternalAvailable() {
    Enumeration e;
    Enumeration addr;
    NetworkInterface ni;
    try {
      for (e = NetworkInterface.getNetworkInterfaces(); e.hasMoreElements();) {
        // If we reach this spot, then at least one
        // network interface exists
        ni = (NetworkInterface) e.nextElement();
        for (addr = ni.getInetAddresses(); addr.hasMoreElements();) {
          // If we reach this spot, then at least
          // one InetAddress is associated with this
          // network interface. Check to see if it
          // is the local address.
          InetAddress i = (InetAddress) addr.nextElement();
          // If the address isn't the local loopback
          // then we have at least one valid
          // possibility for network connectivity,
          // so return true
          if (!i.isLoopbackAddress()) {
            return true;
          }
        }
      }
      // Found no network interfaces that were active
      // that were not the local loopback device.
      return false;
    }
    catch (SocketException se) {
      System.err.println(se);
      return false;
    }
  }

  // Check to see if there is a local network interface available.
  public boolean isLocalAvailable() {
    Enumeration e;
    Enumeration addr;
    NetworkInterface ni;
    try {
      for (e = NetworkInterface.getNetworkInterfaces(); e.hasMoreElements();) {
        // If we reach this spot, then at least one
        // network interface exists
        ni = (NetworkInterface) e.nextElement();
        for (addr = ni.getInetAddresses(); addr.hasMoreElements();) {
          // If we reach this spot, then at least
          // one InetAddress is associated with this
          // network interface. Check to see if it
          // is the local address.
          InetAddress i = (InetAddress) addr.nextElement();
          // For this method we want the local
          // loopback address.
          if (i.isLoopbackAddress()) {
            return true;
          }
        }
      }
      // Found no local network interfaces, or none
      // that were active.
      return false;
    }
    catch (SocketException se) {
      System.err.println(se);
      return false;
    }
  }

  // If a String is available (such as might come in via an HttpRequest parameter), convert it to a URL before testing.
  public boolean isURLAvailable(String address) {
    try {
      URL u = new URL(address);
      return isURLAvailable(u);
    }
    catch (MalformedURLException m) {
      System.err.println(m);
      return false;
    }
  }

  // Given a URL, get the host portion. Then see if the InetAddress associated with that host is the same as the InetAddress associated with
  // the localhost. If they are the same, then the address is local, so make sure the local interface is available. Otherwise the address is
  // external, so make sure external interfaces are available. An UnknownHostException will be thrown when interfaces are down, so take that
  // as meaning false.
  public boolean isURLAvailable(URL url) {
    String host = url.getHost();
    boolean found = false;
    try {
      InetAddress i = InetAddress.getByName(host);
      if (i.equals(InetAddress.getLocalHost())) {
        return isLocalAvailable();
      }
      if (isAnyExternalAvailable()) {
        try {
          HttpURLConnection uc = (HttpURLConnection) url.openConnection();
          // Default is to request the entire page, so set it to just retrieve the head
          uc.setRequestMethod("HEAD");
          uc.setUseCaches(false);
          System.setProperty("sun.net.client.defaultConnectTimeout", "300");
          uc.connect();
          int code = uc.getResponseCode();
          switch (code) {
            case HttpURLConnection.HTTP_OK:
            case HttpURLConnection.HTTP_ACCEPTED:
            case HttpURLConnection.HTTP_CREATED:
            case HttpURLConnection.HTTP_NO_CONTENT:
              found = true;
              break;
            default:
              break;
          }
        }
        catch (IOException ioe) {
          // Actually catches MalformedURLException too
          // Default is not found, so do nothing in here
        }
      }
    }
    catch (UnknownHostException ue) {
      // Default is not found, which is what this means, so do nothing
    }
    return found;
  }
}