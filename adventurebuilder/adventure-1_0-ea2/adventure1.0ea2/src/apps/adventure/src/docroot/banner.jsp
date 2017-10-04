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
 $Id: banner.jsp,v 1.9 2003/03/04 23:09:26 gmurray Exp $ --%>

<%@ taglib prefix="c" uri="/WEB-INF/c-rt.tld" %>

<table border="0" bgcolor="white" width="100%" height="100" cellpadding="0" cellspacing="0" bordercolor="#66ff66">
 <tr>
  <td>
    <table border="0">
     <tr>
      <td><a href="main.screen"><font size="+1" color="black">Home</font></a></td>
     </tr>
     <tr>
      <td><a href="customer.do"><font size="+1" color="black">Account</font></a></td>
     </tr>
     <tr>
      <td><a href="track_order.screen"><font size="+1" color="black">Track Order</font></a></td>
     </tr>
     <tr>
    <c:choose>
     <c:when test="${j_signon == true}">
      <td><a href="signoff.do"><font size="+1" color="black">Sign Off</font></a></td>
      </c:when>
      <c:otherwise>
      <td><a href="signon_welcome.screen"><font size="+1" color="black">Sign On</font></a></td>
      </c:otherwise>
    </c:choose>
     </tr>
    </table>
  </td>
  <td colspan="3" align="center"><img src="images/adventure-logo.gif" alt="Adventure Builder"></font></td>
 </tr>
</table>
