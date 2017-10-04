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
 $Id: activitybar.jsp,v 1.10 2002/11/22 18:50:56 gmurray Exp $ --%>
<%@ taglib prefix="sql" uri="/WEB-INF/sql-rt.tld" %>
<%@ taglib prefix="c" uri="/WEB-INF/c-rt.tld" %>
<%@page contentType="text/html"%>

<sql:setDataSource dataSource="jdbc/CatalogDB"/>

<sql:query var="items">
  select itemdetails.itemid,itemdetails.name,itemdetails.descn from item, itemdetails,product where product.productid = item.productid and item.itemid = itemdetails.itemid and product.catid = ? and product.productid like '%ACTIVITY%' order by itemdetails.name
  <sql:param><c:out value="${selectedCategory}"/></sql:param>
</sql:query>

<table border="2" bordercolordark="#7786A4"   cellpadding="0" cellspacing="0" width="280" height="400">
 <tr><td>
 <table border="0"cellpadding="0" cellspacing="0" width="100%" height="100%">
 <tr valign="top">
  <td align="center">
    <font size="+2" color="black">Activities</font>
    <hr noshade="true"/>
  </td>
 </tr>
 <c:forEach var="item" begin="0" items="${items.rows}">
  <tr valign="top">
   <td>
      <c:url value="/activity_item.screen" var="viewItemURL">
       <c:param name="ITEM_ID" value="${item.itemId}"/>
      </c:url>
      <br/>
      <a href='<c:out value="${viewItemURL}"/>'>
       <font size="+2" color="black"><c:out value="${item.name}"/></font>
      </a>
   </td>
  </tr>
 </c:forEach>
 </table>
 </td></tr>
</table>