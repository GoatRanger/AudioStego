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
 $Id: cart_lodging_tab.jsp,v 1.4 2003/03/11 23:26:53 gmurray Exp $ --%>
<%@ taglib prefix="sql" uri="/WEB-INF/sql-rt.tld" %>
<%@ taglib prefix="c" uri="/WEB-INF/c-rt.tld" %>
<%@ taglib prefix="fmt" uri="/WEB-INF/fmt-rt.tld" %>
<%@ taglib uri="/WEB-INF/waftags.tld" prefix="waf" %>
<%@page contentType="text/html"%>

<fmt:setLocale value="en_US" />

<sql:setDataSource dataSource="jdbc/CatalogDB"/>

<sql:query var="lodgingDetails"> 
 select name, description, price,imageuri from lodging where lodgingid =  ? and locale = ?
 <sql:param>${cart.lodgingId}</sql:param>
 <sql:param>en_US</sql:param>
</sql:query>

<form method="post" action="cart.do" >
<input type="hidden" name="target_action" value="update_lodging_room_count" />
<table>
  <tr>
   <td>
     <c:url var="imageURL" value="images/${lodgingDetails.rows[0].imageuri}"/>
     <image src="${imageURL}"/>
   </td>
    <td >
    <font size="+2">${lodgingDetails.rows[0].name}</font>
      <br/>${lodgingDetails.rows[0].description}
      
      <br/><b>Number of Nights:</b> ${cart.lodgingDays}
      <br/><b>Number of Rooms:</b>
      <waf:select size="1"
                           name="lodging_room_count">
        <waf:selected>${cart.lodgingRoomCount}</waf:selected>
          <c:forEach varStatus="status" begin="1" end="5">
           <waf:option value="${status.index}">${status.index}</waf:option>
          </c:forEach>
       </waf:select> <input type="submit" value="Update"/>
       <br/><br/>
       <b>Price Per Room Per Night:</b><fmt:formatNumber value="${lodgingDetails.rows[0].price}" type="currency" />
   </td>
   </tr>
  <tr>
    <td colspan="3" align="right">
        <br/>
       <a href="lodgings.screen">Change Lodging</a>
  </tr>
  <tr>
   <td colspan="3">
     <hr noshade="true"/>
   </td>
   </tr>
  <tr>
   <td>
    <font size="+1" color="black">Lodging Total:</font>
    </td>
   <td/>
   <td>
     <b><fmt:formatNumber value="${cartBean.lodgingTotal}" type="currency" /></b>
    </td>
  </tr>
 </table>
</form>