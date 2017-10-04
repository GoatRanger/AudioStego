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
 $Id: available_transport.jsp,v 1.3 2003/03/12 22:44:30 gmurray Exp $ --%>

<%@ taglib prefix="fmt" uri="/WEB-INF/fmt-rt.tld" %>
<%@ taglib prefix="c" uri="/WEB-INF/c-rt.tld" %>

<fmt:setLocale value="en_US" />

<font size="+2"> Departure - Available Flights</font>
<br/><br/>

<table border="0">
 <form method="post" action="cart.do">
  <input type="hidden" name="target_action" value="purchase_transportation" />
 <c:forEach var="item" begin="0" items="${departure_result}">
  <tr>
    <td><input type="checkbox" name="departure_flight" value="${item.id}"/>&nbsp;</td> 
   <td>
             <b>${item.carrier}:</b> ${item.name}<br/>
             <b>Departure Time :</b> ${item.departureTime}<br/>
             <b>Arrival Time :</b> ${item.arrivalTime}<br/>
             <b>Travel Class :</b> ${item.travelClass}<br/>
             <b>Price : </b><fmt:formatNumber value="${item.price}" type="currency" /><br/>
    </td>
    <td>
     <c:url var="imageURL" value="images/${item.imageURI}"/>
     <image width="100" height="100" src="${imageURL}"/>
    </td>
  </tr>
</c:forEach>
</table>
<hr noshade="true"><br/>
<font size="+2"> Return - Available Flights</font>
<br/><br/>
<table border="0">
 <c:forEach var="item" begin="0" items="${return_result}">
  <tr>
  <td><input type="checkbox" name="return_flight" value="${item.id}"/>&nbsp;</td>
  <td>
             <b>${item.carrier}</b>${item.name}<br/>
             <b>Departure Time :</b> ${item.departureTime}<br/>
             <b>Arrival Time :</b> ${item.arrivalTime}<br/>
             <b>Travel Class :</b> ${item.travelClass}<br/>
             <b>Price : </b><fmt:formatNumber value="${item.price}" type="currency" /><br/>
   </td>
    <td>
     <c:url var="imageURL" value="images/${item.imageURI}"/>
     <image width="100" height="100" src="${imageURL}"/>
    </td>
  </tr>
</c:forEach>
</table>
<br/><br/>
<input type="submit" value="Purchase Selected Flights">
</form>

