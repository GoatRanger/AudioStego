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
 $Id: cart.jsp,v 1.3 2003/03/10 20:55:40 gmurray Exp $ --%>
<%@ taglib prefix="c" uri="/WEB-INF/c-rt.tld" %>
<%@ taglib prefix="fmt" uri="/WEB-INF/fmt-rt.tld" %>

<font size="+3" color="black">Adventure Package</font>
<hr/>
<br/>

<%--
 Initialize page objects for page
--%>
<fmt:setLocale value="en_US" />

<%-- Create the tab view --%>

<table border="0"  cellpadding="0" cellspacing="0" bordercolor="#66ff66">

 <c:choose>
  <c:when test="${param.tab == 'lodging'}">
<%-- Lodging SELECTED --%>
 <tr>
  <td><image src="images/left_unselected.gif"/></td>
  <td bgcolor="#7786a4">
    <a href="cart.do?tab=summary">
    <font size="+1" color="white">Summary</font>
    </a>
  </td>
  <td><image src="images/right_unselected.gif"/></td>
  <td><image src="images/left_selected.gif"/></td>
  <td bgcolor="#6699FF">
   <font size="+1" color="white">Lodging</font></td>
  <td><image src="images/right_selected.gif"/></td>
  <td><image src="images/left_unselected.gif"/></td>
  <td bgcolor="#7786a4">
  <c:choose>
   <c:when test="${cart.activities != null}">
     <a href="cart.do?tab=activities">
      <font size="+1" color="white">Activities</font>
     </a>
    </c:when>
    <c:otherwise>
     <font size="+1" color="white">Activities</font>
    </c:otherwise>
  </c:choose>
  </td>
  <td><image src="images/right_unselected.gif"/></td>
  <td><image src="images/left_unselected.gif"/></td>
  <td bgcolor="#7786a4">
     <a href="cart.do?tab=transportation">
      <font size="+1" color="white">Transportation</font>
     </a>
  </td>
  <td><image src="images/right_unselected.gif"/></td>
 </tr>
</table>
<table border="1" width="100%" height="300" cellpadding="0" cellspacing="0" bordercolor="black">
 <tr>
   <td>
    <jsp:include page="cart_lodging_tab.jsp"/>
   </td>
  </tr>
 </table>
</c:when>
<%-- Lodging SELECTED DONE --%>

<%-- ACTIVITIES SELECTED --%>
<c:when test="${param.tab == 'activities'}">
 <tr>
  <td><image src="images/left_unselected.gif"/></td>
  <td bgcolor="#7786a4">
    <a href="cart.do?tab=summray">
    <font size="+1" color="white">Summary</font>
    </a>
  </td>
  <td><image src="images/right_unselected.gif"/></td>
  <td><image src="images/left_unselected.gif"/></td>
  <td bgcolor="#7786a4">
  <c:choose>
   <c:when test="${cart.lodgingId != null}">
     <a href="cart.do?tab=lodging">
      <font size="+1" color="white">Lodging</font>
     </a>
    </c:when>
    <c:otherwise>
     <font size="+1" color="white">Lodging</font>
    </c:otherwise>
  </c:choose>
  </td>
  <td><image src="images/right_unselected.gif"/></td>
  <td><image src="images/left_selected.gif"/></td>
  <td bgcolor="#6699FF">
  <c:choose>
   <c:when test="${cart.activities != null}">
     <a href="cart.do?tab=activities">
      <font size="+1" color="white">Activities</font>
     </a>
    </c:when>
    <c:otherwise>
     <font size="+1" color="white">Activities</font>
    </c:otherwise>
  </c:choose>
  </td>
  <td><image src="images/right_selected.gif"/></td>
  <td><image src="images/left_unselected.gif"/></td>
  <td bgcolor="#7786a4">
     <a href="cart.do?tab=transportation">
      <font size="+1" color="white">Transportation</font>
     </a>
  </td>
  <td><image src="images/right_unselected.gif"/></td>
 </tr>
</table>
<table border="1" width="100%" height="300" cellpadding="0" cellspacing="0" bordercolor="black">
 <tr>
   <td>
    <jsp:include page="cart_activities_tab.jsp"/>
   </td>
  </tr>
 </table>
</c:when>
<%-- ACTIVITIES SELECTED DONE--%>

<%-- TRANSPORTATION SELECTED --%>
<c:when test="${param.tab == 'transportation'}">
 <tr>
  <td><image src="images/left_unselected.gif"/></td>
  <td bgcolor="#7786a4">
    <a href="cart.do?tab=summray">
    <font size="+1" color="white">Summary</font>
    </a>
  </td>
  <td><image src="images/right_unselected.gif"/></td>
  <td><image src="images/left_unselected.gif"/></td>
  <td bgcolor="#7786a4">
  <c:choose>
   <c:when test="${cart.lodgingId != null}">
     <a href="cart.do?tab=lodging">
      <font size="+1" color="white">Lodging</font>
     </a>
    </c:when>
    <c:otherwise>
     <font size="+1" color="white">Lodging</font>
    </c:otherwise>
  </c:choose>
  </td>
  <td><image src="images/right_unselected.gif"/></td>
  <td><image src="images/left_unselected.gif"/></td>
  <td bgcolor="#7786a4">
  <c:choose>
   <c:when test="${cart.activities != null}">
     <a href="cart.do?tab=activities">
      <font size="+1" color="white">Activities</font>
     </a>
    </c:when>
    <c:otherwise>
     <font size="+1" color="white">Activities</font>
    </c:otherwise>
  </c:choose>
  </td>
  <td><image src="images/right_unselected.gif"/></td>
  <td><image src="images/left_selected.gif"/></td>
  <td bgcolor="#6699FF">
     <font size="+1" color="white">Transportation</font>
  </td>
  <td><image src="images/right_selected.gif"/></td>
   </tr>
 </table>

<table border="1" width="100%" height="300" cellpadding="0" cellspacing="0" bordercolor="black">
 <tr>
   <td>
    <c:choose>
     <c:when test="${cart.origin != 'None'}">
        <jsp:include page="cart_transportation_tab.jsp"/>
     </c:when>
    <c:otherwise>
      You have not selected an origin for your trip. Please select
      an origin. 
      <br/><br/>
      <jsp:include page="select_transport.jsp"/>
    </c:otherwise>
    </c:choose>
   </td>
  </tr>
 </table>
</c:when>
<%-- TRANSPORTATION SELECTED DONE--%>

<%-- SUMMARY SELECTED --%>
<c:otherwise>
 <tr>
  <td><image src="images/left_selected.gif"/></td>
  <td bgcolor="#6699FF">
    <font size="+2" color="white">Summary</font>
  </td>
  <td><image src="images/right_selected.gif"/></td>
  <td><image src="images/left_unselected.gif"/></td>
  <td bgcolor="#7786a4">
  <c:choose>
   <c:when test="${cart.lodgingId != null}">
     <a href="cart.do?tab=lodging">
      <font size="+1" color="white">Lodging</font>
     </a>
    </c:when>
    <c:otherwise>
     <font size="+1" color="white">Lodging</font>
    </c:otherwise>
  </c:choose>
  </td>
  <td><image src="images/right_unselected.gif"/></td>
  <td><image src="images/left_unselected.gif"/></td>
  <td bgcolor="#7786a4">
  <c:choose>
   <c:when test="${cart.activities != null}">
     <a href="cart.do?tab=activities">
      <font size="+1" color="white">Activities</font>
     </a>
    </c:when>
    <c:otherwise>
     <font size="+1" color="white">Activities</font>
    </c:otherwise>
  </c:choose>
  </td>
  <td><image src="images/right_unselected.gif"/></td>
  <td><image src="images/left_unselected.gif"/></td>
  <td bgcolor="#7786a4">
     <a href="cart.do?tab=transportation">
      <font size="+1" color="white">Transportation</font>
     </a>
  </td>
  <td><image src="images/right_unselected.gif"/></td>
 </tr>
</table>
<table border="1" width="100%" height="300" cellpadding="0" cellspacing="0" bordercolor="black">
 <tr>
   <td>
    <jsp:include page="cart_summary_tab.jsp"/>
   </td>
  </tr>
 </table>
 </c:otherwise>
<%-- SUMMARY SELECTED DONE--%>
</c:choose>


