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
 $Id: adventures.jsp,v 1.3 2003/03/07 23:27:58 gmurray Exp $ --%>
<%@ taglib prefix="sql" uri="/WEB-INF/sql-rt.tld" %>
<%@ taglib prefix="c" uri="/WEB-INF/c-rt.tld" %>
<%@page contentType="text/html"%>

<sql:setDataSource dataSource="jdbc/CatalogDB"/>

<sql:query var="packages"> 
 select distinct p.packageid, p.name,c.name  as catname from  package p, category c  where p.catid=?  and c.catid = ? and p.locale = ?
 <sql:param>${param.CATEGORY_ID}</sql:param>
 <sql:param>${param.CATEGORY_ID}</sql:param>
 <sql:param>en_US</sql:param>
</sql:query>

 <table border="0"cellpadding="0" cellspacing="0" width="100%" height="100%">  <tr valign="top">
   <td align="center">
    <font size="+3" color="black">
      ${packages.rows[0].catname}
    </font>
    <hr noshade="true"/>
   </td>
  </tr>
 <c:forEach var="package" begin="0" items="${packages.rows}">
 <tr>
   <td>
      <c:url value="/adventure.screen" var="viewPackageURL">
       <c:param name="PACKAGE_ID" value="${package.packageid}"/>
      </c:url>
      <br/> &nbsp;&nbsp;&nbsp;
      <a href="${viewPackageURL}">
       <font size="+2" color="black">${package.name}</font>
      </a>
   </td>
  </tr>
 </c:forEach>

</table>

