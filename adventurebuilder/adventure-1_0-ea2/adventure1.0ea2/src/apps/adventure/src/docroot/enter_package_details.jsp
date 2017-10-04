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
 $Id: enter_package_details.jsp,v 1.2 2003/03/01 00:20:54 gmurray Exp $ --%>
<%@ taglib prefix="sql" uri="/WEB-INF/sql-rt.tld" %>
<%@ taglib prefix="c" uri="/WEB-INF/c-rt.tld" %>
<%@page contentType="text/html"%>



<form method="post" action="cart.do" >
<input type="hidden" name="target_action" value="set_package_details" />
<table>
  <tr><td> Destination : </td>
   <td> 
       ${cart.destination} 
  </td>
  </tr> 
  <tr>
   <td>
    Number of People:
   </td>
   <td>
     <select name="head_count">
     <c:forEach varStatus="status" begin="1" end="9">
       <option>${status.index}</option>
     </c:forEach>
     </select>
    </td>
  </tr>
  <tr>
   <td>
    Start Date:
   </td>
   <td>
     <select>
     <c:forEach varStatus="status" begin="1" end="12">
       <option>${status.index}</option>
     </c:forEach>
     </select>
     <select>
     <c:forEach varStatus="status" begin="1" end="31">
       <option>${status.index}</option>
     </c:forEach>
     </select>
     <select>
     <c:forEach varStatus="status" begin="2003" end="2004">
       <option>${status.index}</option>
     </c:forEach>
     </select>
    </td>
  </tr>
  <tr>
    <td>
       Number of Days:
    </td>
    <td>
     <select name="adventure_days">
     <c:forEach varStatus="status" begin="3" end="31">
       <option>${status.index}</option>
     </c:forEach>
     </select>
    </td>
  </tr>
  <tr>
    <td>
       Number of Lodging Rooms:
    </td>
    <td>
     <select name="lodging_room_count">
     <c:forEach varStatus="status" begin="1" end="5">
       <option>${status.index}</option>
     </c:forEach>
     </select>
    </td>
  </tr>
  <tr>
  <td> <td/>
    <input type="submit" value="Continue"/>
  </td>
  </tr>
</table>


