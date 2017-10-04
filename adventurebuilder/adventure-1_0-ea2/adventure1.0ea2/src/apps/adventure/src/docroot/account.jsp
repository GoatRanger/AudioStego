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
 $Id: account.jsp,v 1.3 2002/11/06 02:12:02 inder Exp $ --%>
<%@ taglib prefix="c" uri="/WEB-INF/c-rt.tld" %>

<font size="+3">Account</font>
<hr>
<br/>
<table>
 <tr>
  <td>
   <font size="+2">Name:</font>
  </td>
  <td>
   <c:out value="${customerBean.givenName}"/>
   <c:out value="${customerBean.familyName}"/>
  </td>
 </tr>
 <tr>
 <tr><td>&nbsp;</td></tr>
  <td>
  </td>
  <td>
   <c:out value="${customerBean.streetName1}"/>
  </td>
 </tr>
 <tr>
  <td><font size="+2">Address:</font>
  </td>
  <td>
   <c:out value="${customerBean.streetName2}"/>
  </td>
 </tr>
 <tr>
  <td>
  </td>
  <td>
   <c:out value="${customerBean.city}"/>,
   <c:out value="${customerBean.state}"/> &nbsp;
   <c:out value="${customerBean.zipCode}"/>
  </td>
 </tr>
 <tr>
  <td>
  </td>
  <td>
   <c:out value="${customerBean.country}"/>
  </td>
 </tr>
</table>


