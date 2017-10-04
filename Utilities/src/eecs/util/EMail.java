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
 * Created on Oct 24, 2003
 *
 */
package eecs.util;

import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;


/**
 * @author DK8685
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public class EMail {
	
	/** Hides the constructor so EMail cannot be instantiated */
	private EMail() {
		
	}
	
  public static void sendMsg(String _to, String _from, String _subject,
      String _bodyText) {
    boolean debug = false;
    Properties propsOutgoing = new Properties();
    propsOutgoing.put("mail.smtp.host", "mail.acsalaska.net");
	
    Session session = Session.getDefaultInstance(propsOutgoing, null);
    session.setDebug(debug);

    try {
      // create a message
      Message msg = new MimeMessage(session);

      // set the from
      msg.setFrom(new InternetAddress(_from));
      //msg.setFrom(new InternetAddress("anon_user@usma.edu"));
      InternetAddress[] address = { new InternetAddress(_to) };
      msg.setRecipients(Message.RecipientType.TO, address);
      msg.setSubject(_subject);

      MimeBodyPart textPart = new MimeBodyPart();
      textPart.setContent(_bodyText, "text/plain");

      Multipart mp = new MimeMultipart();
      mp.addBodyPart(textPart);
      msg.setContent(mp);
      Transport.send(msg);
    } catch (MessagingException me) {
      System.err.println(
        "Trying to send error report, but email is not available.");
      me.printStackTrace();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  public static void sendMsg(String _to, String _subject,
    String _bodyText) {
    String from = System.getProperty("user.name", "anon") +
      "@usma.edu";
    sendMsg(_to,from,_subject,_bodyText);
  }
  public static void sendMsgAttachFile(String _to, String _subject,
    String _bodyText, String[] _fileNames) {
    boolean debug = false;
    Properties propsOutgoing = new Properties();
    propsOutgoing.put("mail.smtp.host", "mail.acsalaska.net");
  
    Session session = Session.getDefaultInstance(propsOutgoing, null);
    session.setDebug(debug);
  
    try {
      // create a message
      Message msg = new MimeMessage(session);
  
      // set the from
      msg.setFrom(new InternetAddress(System.getProperty("user.name", "anon") +
          "@usma.edu"));
      //msg.setFrom(new InternetAddress("anon_user@usma.edu"));
      InternetAddress[] address = { new InternetAddress(_to) };
      msg.setRecipients(Message.RecipientType.TO, address);
      msg.setSubject(_subject);
  
      MimeBodyPart textPart = new MimeBodyPart();
      textPart.setContent(_bodyText, "text/plain");
  
      Multipart mp = new MimeMultipart();
      mp.addBodyPart(textPart);
  
      for (int i = 0; i < _fileNames.length; i++) {
        MimeBodyPart attachFilePart = new MimeBodyPart();
        FileDataSource fds = new FileDataSource(_fileNames[i]);
        attachFilePart.setDataHandler(new DataHandler(fds));
        attachFilePart.setFileName(fds.getName());
        mp.addBodyPart(attachFilePart);
      }
  
      msg.setContent(mp);
      Transport.send(msg);
    } catch (MessagingException me) {
      System.err.println(
        "Trying to send error report, but email is not available.");
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static void sendMsgAttachFile(String _to, String _subject,
    String _bodyText, String _fileName) {
    String[] files = { _fileName };
    sendMsgAttachFile(_to, _subject, _bodyText, files);
  }
  
  public static void main(String[] args) {
    EMail.sendMsg("kegossett@acsalaska.net","Subject","Test email system");
  }
}
