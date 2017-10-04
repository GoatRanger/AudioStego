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
 $Id: top_menubar.jsp,v 1.4 2003/03/07 23:27:58 gmurray Exp $ --%>

<%@ taglib prefix="c" uri="/WEB-INF/c-rt.tld" %>

<table border="0" bgcolor="#7786A4" width="100%" height="25" border="2" cellpadding="0" cellspacing="0" bordercolor="#6699FF">

 <tr>
  <td align="center"  valign="middle">
 <c:choose>
  <c:when test="${cart.packageId == null}">
   <image src="images/arrow.gif">
   <font  size="+2" color="white">Select Adventure Package</font>
   <image src="images/green-arrow.gif">
   <font  color="white">Set Package Options</font>
   <image src="images/green-arrow.gif">
   <font  color="white">Select Transportation</font>
   <image src="images/green-arrow.gif">
   <font  color="white">Checkout</font>
  </c:when>
  <c:when test="${cart.adventureDays <= 0}">
   <image src="images/arrow.gif">
   <font  color="white">Select Adventure Package</font>
   <image src="images/green-arrow.gif">
   <font   size="+2"  color="white">Set Package Options</font>
   <image src="images/green-arrow.gif">
   <font  color="white">Select Transportation</font>
   <image src="images/green-arrow.gif">
   <font  color="white">Checkout</font>
  </c:when>
  <c:when test="${cart.origin == null}">
   <image src="images/arrow.gif">
   <font  color="white">Select Adventure Package</font>
   <image src="images/green-arrow.gif">
   <font   color="white">Set Package Options</font>
   <image src="images/green-arrow.gif">
   <font  size="+2"  color="white">Select Transportation</font>
   <image src="images/green-arrow.gif">
   <font  color="white">Checkout</font>
  </c:when>
  <c:otherwise>
    <a href="cart.do">
   <font  color="white"> Cart</font></a>
   &nbsp; &nbsp; &nbsp;
   <a href="enter_order_info.screen">
    <font  size="+2" color="white">Checkout</font>
   </a>
  </c:otherwise>
 </c:choose>
 </td>
 </tr>
</table>