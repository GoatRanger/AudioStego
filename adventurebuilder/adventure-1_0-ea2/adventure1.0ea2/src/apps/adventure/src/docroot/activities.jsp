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
 $Id: activities.jsp,v 1.2 2003/03/10 20:55:40 gmurray Exp $ --%>
<%@ taglib prefix="sql" uri="/WEB-INF/sql-rt.tld" %>
<%@ taglib prefix="c" uri="/WEB-INF/c-rt.tld" %>
<%@ taglib prefix="fmt" uri="/WEB-INF/fmt-rt.tld" %>
<%@page contentType="text/html"%>

<fmt:setLocale value="en_US" />

<sql:setDataSource dataSource="jdbc/CatalogDB"/>
<sql:query var="activities"> 
select activityid,name, description, price, imageuri from activity where location = ? and locale = ?
 <sql:param>${cart.destination}</sql:param>
 <sql:param>en_US</sql:param>
</sql:query>
<table>
<c:forEach var="activity"  items="${activities.rows}">
  <tr>
  <form action="cart.do">
   <td>
     <c:url var="imageURL" value="images/${activity.imageuri}"/>
     <image src="${imageURL}"/>
   </td>
   <td>
    <font size="+2">${activity.name}</font><br/><br/>
     ${activity.description}
    </td>
   <td><b>Price</b> <fmt:formatNumber value="${activity.price}" type="currency" /> per person</td>
    <td>
      <label>Number of People:</label>
     <select name="head_count">
     <c:forEach varStatus="status" begin="1" end="${cart.headCount}">
       <option>${status.index}</option>
     </c:forEach>
     </select>
    </td>
    <td>
      <input type="hidden" name ="target_action" value="purchase_activity"/>
      <input type="hidden" name ="activity_id" value="${activity.activityid}"/>
      <input type="submit" name="activity_id" value="Purchase"/>
    </td>
   </form>
  </tr>
 </c:forEach>
</table>