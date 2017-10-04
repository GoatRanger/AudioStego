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
 $Id: transportation.jsp,v 1.4 2003/03/10 20:55:41 gmurray Exp $ --%>
<%@ taglib prefix="sql" uri="/WEB-INF/sql-rt.tld" %>
<%@ taglib prefix="c" uri="/WEB-INF/c-rt.tld" %>
<%@page contentType="text/html"%>

<sql:setDataSource dataSource="jdbc/CatalogDB"/>
<sql:query var="flightOrigins"> 
  select distinct origin from transportation where locale = 'en_US'  and destination = ?
 <sql:param>${cart.destination}</sql:param>
</sql:query>


<form method="post" name="searchtransportation" action="searchtransport.do" >
<input type="hidden" name="target_action" value="select_transport" />
<table>
  <tr> <td> Origin : </td>
   <td> 
     <select name="origin">
     <c:forEach var="flightOrigin" begin="0" items="${flightOrigins.rows}">
       <option>${flightOrigin.origin}</option>
     </c:forEach> 
     </select>
  </td>
  </tr>
  <tr><td> Destination : </td>
   <td> 
       ${cart.destination} 
  </td>
  </tr> 
  <tr>
  <td> <td/>
    <input type="submit" value="Show Available Flights"/>
     </form>
     <br/><br/>
     <form method="post" action="cart.do" >
      <input type="hidden" name="target_action" value="no_transportation" />
      <input type="submit"  value="I Will Provide My Own Transportation"/>
     </form>
  </td>
  </tr>
</table>


