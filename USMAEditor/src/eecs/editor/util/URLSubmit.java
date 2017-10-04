/* Copyright (C) 2003  USMA
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * any later version.

 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.

 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 */

/*
 * URLSubmit.java
 *
 * Created on May 27, 2003, 12:33 PM
 */
package eecs.editor.util;

import java.io.File;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.util.Date;

import javax.swing.JOptionPane;

import eecs.util.*;


/**
 *
 * @author  dk8685
 */
public class URLSubmit {
	
  /** Creates a new instance of URLSubmit.
   * This is all utility methods, so no public constructor. */
  private URLSubmit() {
  }

  public static boolean submit(File saveFile) {
    boolean result = false;
    String userID = System.getProperty("user.name", "unknown");
    InterfaceCheck nic = new InterfaceCheck();

    if (nic.isAnyExternalAvailable()) {
      // Now let's submit it!
      String[] types = {
        "Web Portal", "Selection Homework", "Iteration Homework",
        "Final Project"
      };
      String[] typeNames = {
        "web-portal", "selection", "iteration", "final-project"
      };
      int hwNumber = JOptionPane.showOptionDialog(null, "Select a requirement",
          "Submit A Homework", 0, 0, null, types, types[0]);

      Connection connection = null;

      try {
        // Load mySQL driver
        //String driverName = "org.gjt.mm.mysql.Driver"; // MySQL MM JDBC driver
        //Class.forName(driverName);
        Class.forName("com.mysql.jdbc.Driver").newInstance();
        // connect
        String serverName = "remedios";
        String mydatabase = "eecs_web";
        String url = "jdbc:mysql://" + serverName + "/" + mydatabase; // a JDBC url
        String username = "dt4347";
        String password = "nfpwy5GG";
        connection = DriverManager.getConnection(url, username, password);
      } catch (IllegalAccessException iae) {
        
      } catch (InstantiationException ie) {
      } catch (ClassNotFoundException e) {
        //logger.log(Level.WARNING, "Couldn't load database driver.");
      } catch (SQLException e) {
        //logger.log(Level.WARNING,
        //	"Couldn't make a connection to the database.");
      }

      String username = "";
      String section = "";
      String sectionID = "";
      String hour = "";

      try {
        username = selectQuery(userID, connection, "persons", "personID",
            "lastName");
        sectionID = selectQuery(userID, connection, "persons_sections",
            "personID", "sectionID");

        if (!sectionID.equals("")) {
          section = selectQuery(sectionID, connection, "sections", "sectionID",
              "number");
          hour = selectQuery(sectionID, connection, "sections", "sectionID",
              "hour");

          if (!section.equals("")) {
            String path = "\\\\remedios\\IT105-TURN-IN\\AY042\\" + hour +
              section + "\\" + typeNames[hwNumber] + "\\" + username + "_" +
              userID + "\\";
System.err.println("Copying file to " + path);
            if (FileManager.copyNIO(saveFile.getAbsolutePath(),
                    path + saveFile.getName())) {
              DateFormat df = DateFormat.getInstance();
              String mesg = "The file " + saveFile.getName() + " was submitted for the " +
              		typeNames[hwNumber] + " requirement at " + df.format(new Date(System.currentTimeMillis())) + " for " + username + " in " + hour + " Hour.\r\n";
              mesg += mesg.hashCode();
              System.err.println(mesg);
              EMail.sendMsgAttachFile(userID+"@usma.edu","Confirmation: " + typeNames[hwNumber] + " homework submitted", mesg, new String[] {} );
              JOptionPane.showMessageDialog(null,
                "The file \"" + saveFile.getName() + "\"" +
                " has been submitted.");
              result = true;
            } else {
              JOptionPane.showMessageDialog(null,
                "The file \"" + saveFile.getName() + "\"" +
                " could not be submitted. Please try again later, or contact your instructor.",
                "ERROR", JOptionPane.ERROR_MESSAGE);

              //							logger.log(Level.WARNING, "Couldn't Copy File");
            }
          }
        }
      } catch (SQLException sqe) {
        //				logger.log(Level.WARNING,
        //					"Error reading user information from EECSWeb database.", sqe);
      }
    } else {
      JOptionPane.showMessageDialog(null, "No Network Connection Found",
        "Cannot Submit", JOptionPane.WARNING_MESSAGE);
    }

    return result;
  }

  /**
 * DOCUMENT ME!
 *
 * @param value DOCUMENT ME!
 * @param connection DOCUMENT ME!
 * @param table DOCUMENT ME!
 * @param field DOCUMENT ME!
 * @param returnField DOCUMENT ME!
 *
 * @return DOCUMENT ME!
 *
 * @throws SQLException DOCUMENT ME!
 */
  private static String selectQuery(String value, Connection connection,
    String table, String field, String returnField) throws SQLException {
    String result = "";

    // Get a resultset
    Statement stmt = connection.createStatement();
    ResultSet rs = stmt.executeQuery("SELECT * FROM " + table + " WHERE " +
        field + "=\"" + value + "\"");
    boolean found = false;

    while (!found && rs.next()) {
      found = true;
      result = rs.getString(returnField);
    }

    return result;
  }
}
