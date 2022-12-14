//
// $Id: Date.java,v 1.2 2002/04/08 21:19:15 plehegar Exp $
// From Philippe Le Hegaret (Philippe.Le_Hegaret@sophia.inria.fr)
//
// (c) COPYRIGHT MIT and INRIA, 1997.
// Please first read the full copyright statement in file COPYRIGHT.html
// DateThread.java
// $Id: Date.java,v 1.2 2002/04/08 21:19:15 plehegar Exp $
// From Yves Lafon (ylafon@w3.org)
//
// (c) COPYRIGHT MIT and INRIA, 1997.
// Please first read the full copyright statement in file COPYRIGHT.html

package org.w3c.css.util;

import java.util.GregorianCalendar;
import java.util.TimeZone;
import java.util.Calendar;
import java.util.Locale;

public class Date {
  protected static String days[] = { "Sun", "Mon", "Tue", "Wed",
				     "Thu" , "Fri", "Sat" };
  protected static String months[] = { "Jan", "Feb", "Mar", "Apr",
				       "May", "Jun", "Jul", "Aug",
				       "Sep", "Oct", "Nov", "Dec" };
  String date = "";
  String logDate = "";
  private TimeZone tz;
  
  /**
   * Create a new Date
   */
  public Date() {
    tz = TimeZone.getTimeZone("GMT"); // should be UTC
  }
  
  /**
   * Give the Date in HTTP/1.1 preferred format
   *
   * @return A String giving the date in the right format
   */
  public synchronized String getDate() {
    computeDate();
    return date;
  }
  
  public synchronized String getLogDate() {
    computeDate();
    return logDate;
  }

  /**
   * Returns a string representation of the object.
   */
  public String toString() {  
    computeDate();
    return date;
  }
  
  /**
   * The main loop which calculate the date every second
   */
  
  private void computeDate () { 
    int day;
    int hr,mn,sec;
    StringBuffer sbdate, ldate;
    Calendar cal;
    
    sbdate = new StringBuffer(30);
    ldate = new StringBuffer(30);
    
    cal = new GregorianCalendar(tz, Locale.getDefault());
    day = cal.get(Calendar.DAY_OF_MONTH);
    hr = cal.get(Calendar.HOUR_OF_DAY);
    mn = cal.get(Calendar.MINUTE);
    sec = cal.get(Calendar.SECOND);
    
    // construct date according to the http/1.1 format
    sbdate.append(days[cal.get(Calendar.DAY_OF_WEEK)-1]);
    sbdate.append(", ");
    if (day < 10)
      sbdate.append('0');
    sbdate.append(day);
    sbdate.append(' ');
    sbdate.append(months[cal.get(Calendar.MONTH)]);
    sbdate.append(' ');
    sbdate.append(cal.get(Calendar.YEAR));
    sbdate.append(' ');
    if (hr < 10) 
      sbdate.append('0');
    sbdate.append(hr);
    sbdate.append(':');
    if (mn < 10) 
      sbdate.append('0');
    sbdate.append(mn);
    sbdate.append(':');
    if (sec < 10) 
      sbdate.append('0');
    sbdate.append(sec);
    sbdate.append(" GMT");
    
    // construct date according to the log date format
    if (day < 10)
      ldate.append('0');
    ldate.append(day);
    ldate.append('/');
    ldate.append(months[cal.get(Calendar.MONTH)]);
    ldate.append('/');
    ldate.append(cal.get(Calendar.YEAR));
    ldate.append(':');
    if (hr < 10)
      ldate.append('0');
    ldate.append(hr);
    ldate.append(':');
    if (mn < 10)
      ldate.append('0');
    ldate.append(mn);
    ldate.append(':');
    if (sec < 10) 
      ldate.append('0');
    ldate.append(sec);
    ldate.append(" +0");
    
    
    synchronized(this) {
      date = sbdate.toString();
      logDate = ldate.toString();
    }
  }
  
}
