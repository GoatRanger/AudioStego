<%-- Copyright 2003 Sun Microsystems, Inc. All rights reserved.
 
 Redistribution and use in source and binary forms, with or without
 modification, are permitted provided that the following conditions
 are met:
 
 - Redistributions of source code must retain the above copyright
   notice, this list of conditions and the following disclaimer.
 
 - Redistribution in binary form must reproduce the above copyright
   notice, this list of conditions and the following disclaimer in
   the documentation and/or other materials provided with the
   distribution.
 
 Neither the name of Sun Microsystems, Inc. or the names of
 contributors may be used to endorse or promote products derived
 from this software without specific prior written permission.
 
 This software is provided "AS IS," without a warranty of any
 kind. ALL EXPRESS OR IMPLIED CONDITIONS, REPRESENTATIONS AND
 WARRANTIES, INCLUDING ANY IMPLIED WARRANTY OF MERCHANTABILITY,
 FITNESS FOR A PARTICULAR PURPOSE OR NON-INFRINGEMENT, ARE HEREBY
 EXCLUDED. SUN AND ITS LICENSORS SHALL NOT BE LIABLE FOR ANY DAMAGES
 SUFFERED BY LICENSEE AS A RESULT OF USING, MODIFYING OR
 DISTRIBUTING THE SOFTWARE OR ITS DERIVATIVES. IN NO EVENT WILL SUN
 OR ITS LICENSORS BE LIABLE FOR ANY LOST REVENUE, PROFIT OR DATA, OR
 FOR DIRECT, INDIRECT, SPECIAL, CONSEQUENTIAL, INCIDENTAL OR
 PUNITIVE DAMAGES, HOWEVER CAUSED AND REGARDLESS OF THE THEORY OF
 LIABILITY, ARISING OUT OF THE USE OF OR INABILITY TO USE SOFTWARE,
 EVEN IF SUN HAS BEEN ADVISED OF THE POSSIBILITY OF SUCH DAMAGES.
 
 You acknowledge that Software is not designed, licensed or intended
 for use in the design, construction, operation or maintenance of
 any nuclear facility.
 $Id: cartbar.jsp,v 1.3 2003/03/12 22:44:30 gmurray Exp $ --%>
<%@ taglib prefix="sql" uri="/WEB-INF/sql-rt.tld" %>
<%@ taglib prefix="c" uri="/WEB-INF/c-rt.tld" %>
<%@page contentType="text/html"%>
<%@ taglib uri="/WEB-INF/waftags.tld" prefix="waf" %>

<sql:setDataSource dataSource="jdbc/CatalogDB"/>

<sql:query var="package"> 
 select name, location, imageuri from package where  packageid = ? and locale = ?
 <sql:param>${cart.packageId}</sql:param>
 <sql:param>en_US</sql:param>
</sql:query>

<table border="2" bordercolordark="#7786A4"   cellpadding="0" cellspacing="0" width="280" height="400">
 <tr>
  <td>
  <table border="0"cellpadding="0" cellspacing="0" width="100%" height="100%">
    <tr valign="top">
     <td align="center">
      <c:url var="imageURL" value="images/${package.rows[0].imageuri}"/>
      <image src="${imageURL}" width="50" height="50"/>
      <font size="+2" color="black">
       ${package.rows[0].name}
      </font>
     <hr noshade="true"/>
    </td>
   </tr>
  <tr>
   <td>
     <a href="cart.do?tab=transportation"><font size="+1" color="black">Transportation Details</font></a>
   </td>
  </tr>
  <tr>
   <td>
     <a href="cart.do?tab=lodging"><font size="+1" color="black">Lodging Details</font></a>
   </td>
  </tr>
  <tr>
   <td>
     <a href="cart.do?tab=activities"><font size="+1" color="black">Activity Details</font></a>
   </td>
  </tr>
</table>
  </td>
  </tr>
  <tr align="bottom">
   <td align="center">
   <form method="POST" action="cart.do"> 
    <input type="HIDDEN" name="target_action" value="cancel"/>
    <input type="SUBMIT" value="Cancel this Adventure"/>
   </form>
   </td>
  </tr>
</table>

