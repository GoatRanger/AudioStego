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
 $Id: cart_transportation_tab.jsp,v 1.9 2003/03/13 00:01:49 gmurray Exp $ --%>
<%@ taglib prefix="sql" uri="/WEB-INF/sql-rt.tld" %>
<%@ taglib prefix="c" uri="/WEB-INF/c-rt.tld" %>
<%@ taglib prefix="fmt" uri="/WEB-INF/fmt-rt.tld" %>
<%@page contentType="text/html"%>

<fmt:setLocale value="en_US" />

<sql:setDataSource dataSource="jdbc/CatalogDB"/>

<table border="0">

<c:choose>
  <c:when test="${cart.departureFlight != null}">
<sql:query var="departureFlight"> 
 select name, description, price,imageuri from transportation where transportationid =  ? and locale = ?
 <sql:param>${cart.departureFlight}</sql:param>
 <sql:param>en_US</sql:param>
</sql:query>
  <tr>
   <td>
     <c:url var="imageURL" value="images/${departureFlight.rows[0].imageuri}"/>
     <image width="100" height="100" src="${imageURL}"/>
   </td>
    <td >
      <font size="+2">Departing Flight</font>
      <br/>
      <b>${departureFlight.rows[0].name}</b>
      <br/>${departureFlight.rows[0].description}
      <br/><b>Price Per Person:</b><fmt:formatNumber value="${departureFlight.rows[0].price}" type="currency" />
      <br/><b>Number of People:</b> ${cart.headCount}
   </td>
   <td>
    <form action="cart.do">
     <input type="hidden" name ="target_action" value="cancel_departure_flight"/>
     <input type="submit" value="Cancel This Flight"/>
    </form>
   </td>
  </tr>
  </c:when>
 </c:choose>

<c:choose>
  <c:when test="${cart.returnFlight != null}">
<sql:query var="returnFlight"> 
 select name, description, price,imageuri from transportation where transportationid =  ? and locale = ?
 <sql:param>${cart.returnFlight}</sql:param>
 <sql:param>en_US</sql:param>
</sql:query>
  <tr>
   <td>
     <c:url var="imageURL" value="images/${returnFlight.rows[0].imageuri}"/>
     <image width="100" height="100" src="${imageURL}"/>
   </td>
    <td ><font size="+2">Return Flight</font><br/>
      <b>${returnFlight.rows[0].name}</b>
      <br/>${returnFlight.rows[0].description}
      <br/><b>Price Per Person:</b><fmt:formatNumber value="${returnFlight.rows[0].price}" type="currency" />
      <br/><b>Number of People:</b> ${cart.headCount}
   </td>
   <td>
    <form action="cart.do">
     <input type="hidden" name ="target_action" value="cancel_return_flight"/>
     <input type="submit" value="Cancel This Flight"/>
    </form>
   </td>
  </tr>
  </c:when>
 </c:choose>
 <c:choose>
  <c:when test="${cart.returnFlight != null ||  cart.departureFlight != null}">
  <tr><td colspan="2"><br/><hr noshade="true"/></td></tr>
  <tr>
   <td>
    <font size="+1" color="black">Flight Total:</font>
    </td>
   <td/>
   <td>
     <b><fmt:formatNumber value="${cartBean.transportationTotal}" type="currency" /></b>
    </td>
  </tr>
  <tr>
   <td colspan="2">
    <form method="post" action="searchtransport.do" >
     <input type="submit" value="Change Transportation"/>
    </form>
   </td>
  </tr>
  </c:when>
  <c:when test="${cart.returnFlight == null && cart.departureFlight == null}">
  <tr>
   <td colspan="2">
    You have not selected any flights. <br/><br/>
       <form method="post"  action="searchtransport.do" >
       <input type="submit" value="Search Available Transportation"/>
     </form>
    </td>
   </tr>
   </c:when>
  </c:choose>
</table>