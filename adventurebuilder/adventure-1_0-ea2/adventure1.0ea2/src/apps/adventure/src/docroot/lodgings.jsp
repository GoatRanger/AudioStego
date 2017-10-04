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
 $Id: lodgings.jsp,v 1.2 2003/03/10 20:55:41 gmurray Exp $ --%>
<%@ taglib prefix="sql" uri="/WEB-INF/sql-rt.tld" %>
<%@ taglib prefix="c" uri="/WEB-INF/c-rt.tld" %>
<%@ taglib prefix="fmt" uri="/WEB-INF/fmt-rt.tld" %>
<%@page contentType="text/html"%>

<fmt:setLocale value="en_US" />

<sql:setDataSource dataSource="jdbc/CatalogDB"/>

<sql:query var="lodgings"> 
select lodgingid,name, description, price, imageuri from lodging where location = ? and locale = ?
 <sql:param>${cart.destination}</sql:param>
 <sql:param>en_US</sql:param>
</sql:query>
<table>
<c:forEach var="lodging"  items="${lodgings.rows}">
<form action="cart.do">
 <input type="hidden" name ="target_action" value="purchase_lodging"/>
  <tr>
   <td>
     <c:url var="imageURL" value="images/${lodging.imageuri}"/>
     <image src="${imageURL}"/>
   </td>
   <td>
    <font size="+2">${lodging.name}</font><br/><br/>
     ${lodging.description}
    </td>
   <td><b>Price:</b> <fmt:formatNumber value="${lodging.price}" type="currency" /> per room per night</td>
    <td>
      <label>Number of Rooms:</label>
     <select name="lodging_room_count">
     <c:forEach varStatus="status" begin="1" end="5">
       <option>${status.index}</option>
     </c:forEach>
     </select>
    </td>
    <td>
     <input type="hidden" name="lodging_id" value="${lodging.lodgingid}">
     <input type="submit"  value="Purchase">
    </td>
  </tr>
  </form>
 </c:forEach>
</table>