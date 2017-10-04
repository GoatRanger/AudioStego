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
 $Id: categorybar.jsp,v 1.11 2003/03/10 20:55:41 gmurray Exp $ --%>
<%@ taglib prefix="sql" uri="/WEB-INF/sql-rt.tld" %>
<%@ taglib prefix="c" uri="/WEB-INF/c-rt.tld" %>
<%@page contentType="text/html"%>
<%@ taglib uri="/WEB-INF/waftags.tld" prefix="waf" %>

<waf:cache name="categorybar_en_US" scope="context" duration="300000">

<sql:setDataSource dataSource="jdbc/CatalogDB"/>

<sql:query var="categories"> 
 select catid,name,description from category where locale = ? order by name
 <sql:param>en_US</sql:param>
</sql:query>


<table border="2" bordercolordark="#7786A4"   cellpadding="0" cellspacing="0" width="280" height="400">
 <tr><td>
 <table border="0"cellpadding="0" cellspacing="0" width="100%" height="100%">
  <tr valign="top">
   <td align="center">
    <font size="+3" color="black">
     Adventure Categories
    </font>
    <hr noshade="true"/>
   </td>
  </tr>
 <c:forEach var="category" begin="0" items="${categories.rows}">
 <tr>
   <td>
      <c:url value="/adventures.screen" var="viewCategoryURL">
       <c:param name="CATEGORY_ID" value="${category.catid}"/>
      </c:url>
      <br/>
      <a href='<c:out value="${viewCategoryURL}"/>'>
       <font size="+2" color="black"><c:out value="${category.name}"/></font>
      </a>
   </td>
  </tr>
 </c:forEach>

</table>
</td></tr>
</table>
</waf:cache>
