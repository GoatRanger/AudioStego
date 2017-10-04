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
 $Id: adventurebar.jsp,v 1.3 2003/03/12 22:44:30 gmurray Exp $ --%>
<%@ taglib prefix="sql" uri="/WEB-INF/sql-rt.tld" %>
<%@ taglib prefix="c" uri="/WEB-INF/c-rt.tld" %>
<%@page contentType="text/html"%>
<%@ taglib uri="/WEB-INF/waftags.tld" prefix="waf" %>

<%-- Need to set the category id  --%>
 <c:choose>
  <c:when test="${cart.packageId  == null}">
   <c:set var="thePackageId" value="${param.PACKAGE_ID}" scope="request"/>
  </c:when>
  <c:otherwise>
   <c:set var="thePackageId" value="${cart.packageId}" scope="request"/>
  </c:otherwise>
 </c:choose>

<sql:setDataSource dataSource="jdbc/CatalogDB"/>

<sql:query var="packages"> 
 select p.packageid,p.name, p.location, p.imageuri, c.name as catname from package p, category c where  p.packageid = ? and c.catid = p.catid and p.locale = ?
 <sql:param>${thePackageId}</sql:param>
 <sql:param>en_US</sql:param>
</sql:query>

<table border="2" bordercolordark="#7786A4"   cellpadding="0" cellspacing="0" width="280" height="400">
 <tr><td>
 <table border="0"cellpadding="0" cellspacing="0" width="100%" height="100%">
  <tr valign="top">
   <td align="center">
    <font size="+2" color="black">
     ${packages.rows[0].catname} 
    </font>
    <hr noshade="true"/><br/><br/>
    <c:url var="imageURL" value="images/${packages.rows[0].imageuri}"/>
     <image src="${imageURL}" width="50" height="50"/>
     <font size="+1" color="black">
       <c:url var="packageURL" value="adventure.screen">
        <c:param name="PACKAGE_ID" value="${packages.rows[0].packageid}"/>
       </c:url>
       &nbsp;&nbsp;<a href="${packageURL}">${packages.rows[0].name}</a>
     </font>
   </td>
  </tr>
  <tr align="bottom">
    <td align="center">
     <br/><br/>
     <hr noshade="true"/>
   </td>
  </tr>
   <c:choose>
   <c:when test="${cart.packageId != null }">
    <tr>
     <td align="center">
     <a href="enter_package_details.screen">Enter Package Details</a>
    </td>
  </tr>
  </c:when>
 </c:choose>
</table>
</td>
</tr>
  <tr align="bottom">
    <td align="center">
    <form method="POST" action="cart.do"> 
     <input type="HIDDEN" name="target_action" value="cancel"/>
     <input type="SUBMIT" value="Start Over"/>
    </form>
   </td>
  </tr>
</table>

